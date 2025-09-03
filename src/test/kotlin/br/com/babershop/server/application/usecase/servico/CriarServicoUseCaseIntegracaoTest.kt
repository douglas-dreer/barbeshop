package br.com.babershop.server.application.usecase.servico

import br.com.babershop.server.domain.exception.CampoObrigatorioException
import br.com.babershop.server.domain.exception.ServicoJaCadastradoException
import br.com.babershop.server.domain.model.Servico
import br.com.babershop.server.domain.port.input.servico.CriarServicoUseCase
import br.com.babershop.server.infrastructure.persistence.mapper.toEntity
import br.com.babershop.server.infrastructure.persistence.repository.SpringDataServicoRepository
import br.com.babershop.server.util.ServicoFactory
import jakarta.transaction.Transactional
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertNotNull

@SpringBootTest(
   properties = ["spring.profiles.active=test"],
)
@Transactional
class CriarServicoUseCaseIntegracaoTest() : ServicoFactory() {

    @Autowired
    private lateinit var useCase: CriarServicoUseCase

    @Autowired
    private lateinit var repository: SpringDataServicoRepository

    @Test
    fun `deve criar um novo servico`() {
        val dados = criarServico(id = 0)
        val servicoSalvo = useCase.executar(dados)

        assertNotNull(servicoSalvo)
        assert(servicoSalvo.id > 0)
    }

    @Test
    fun `must return CampoObrigatorioException when servico name is null`() {
        val dados = criarServico(nome = "")

        assertThrows<CampoObrigatorioException> { useCase.executar(dados) }
    }

    @Test
    fun `must return ServicoJaCadastradoException when servico already exists`() {
       garanteQueORegistroExista()

        val servico = criarServico(id = 0, "Corte de barba")

        assertThrows<ServicoJaCadastradoException> { useCase.executar(servico) }
    }

    /**
     * Garante que o serviço o qual estamos trabalhando exista no banco de dados
     */
    private fun garanteQueORegistroExista(
       servico: Servico = criarServico(id = 0, nome = "Corte de barba")
    ) {
        repository.saveAndFlush(servico.toEntity())
    }
}
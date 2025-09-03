package br.com.babershop.server.domain.service

import br.com.babershop.server.domain.exception.CampoObrigatorioException
import br.com.babershop.server.domain.exception.ServicoJaCadastradoException
import br.com.babershop.server.domain.port.output.ServicoRepositoryPort
import br.com.babershop.server.domain.service.servico.ServicoValidator
import br.com.babershop.server.util.ServicoFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class ServicoValidatorTest: ServicoFactory() {
    @MockK
    lateinit var servicoRepositoryPort: ServicoRepositoryPort

    @InjectMockKs
    private lateinit var servicoValidator: ServicoValidator

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }


    @Test
    fun `deve validar se o servico existe`() {
        val servico = criarServico()
        every { servicoRepositoryPort.existePorNome(any()) }.returns(false)

        servicoValidator.validarAntesDeInserir(servico)

        verify(exactly = 1) { servicoRepositoryPort.existePorNome(any()) }
    }

    @Test
    fun `must return ServicoJaCadastradoException when servico already exists`() {
        val servico = criarServico()
        every { servicoRepositoryPort.existePorNome(any()) }.returns(true)

        assertThrows<ServicoJaCadastradoException> { servicoValidator.validarAntesDeInserir(servico) }

        verify(exactly = 1) { servicoRepositoryPort.existePorNome(any()) }
    }

    @Test
    fun `must return CampoObrigatorioException when servico name is null`() {
        val servico = criarServico(nome = "")

        assertThrows<CampoObrigatorioException> { servicoValidator.validarAntesDeInserir(servico) }

        verify(exactly = 0) { servicoRepositoryPort.existePorNome(any()) }
    }
}
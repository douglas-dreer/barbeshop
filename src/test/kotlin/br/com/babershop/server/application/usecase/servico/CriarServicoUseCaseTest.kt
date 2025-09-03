package br.com.babershop.server.application.usecase.servico

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
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class CriarServicoUseCaseTest : ServicoFactory() {

    @MockK
    private lateinit var servicoRepository: ServicoRepositoryPort

    @MockK
    private lateinit var validador: ServicoValidator

    @InjectMockKs
    private lateinit var useCase: CriarServicoUseCaseImpl

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `deve criar um novo servico com sucesso`() {
        val servicoParaCriar = criarServico()
        val servicoCriado = criarServico().copy(id = 1)


        every { validador.validarAntesDeInserir(any()) } returns Unit
        every { servicoRepository.salvar(any()) } returns servicoCriado

        val resultado = useCase.executar(servicoParaCriar)

        assertEquals(servicoCriado, resultado)

        verify(exactly = 1) { validador.validarAntesDeInserir(any()) }
        verify(exactly = 1) { servicoRepository.salvar(servicoParaCriar) }
    }

    @Test
    fun `deve lancar excecao ao criar servico com nome invalido`() {
        val servicoInvalido = criarServico().copy(nome = "")
        every {
            validador.validarAntesDeInserir(any())
        } throws IllegalArgumentException("Nome do serviço não pode ser vazio")

        assertThrows<IllegalArgumentException> { useCase.executar(servicoInvalido) }
        verify(exactly = 1) { validador.validarAntesDeInserir(any()) }
        verify(exactly = 0) { servicoRepository.salvar(any()) }
    }

    @Test
    fun `deve lancar excecao ao criar servico com nome ja cadastrado`() {
        val servicoInvalido = criarServico()

        every {
            validador.validarAntesDeInserir(any())
        } throws ServicoJaCadastradoException("Serviço nome já está cadastrado")

        assertThrows<ServicoJaCadastradoException> { useCase.executar(servicoInvalido) }

        verify(exactly = 1) { validador.validarAntesDeInserir(any()) }
        verify(exactly = 0) { servicoRepository.salvar(any()) }
    }
}
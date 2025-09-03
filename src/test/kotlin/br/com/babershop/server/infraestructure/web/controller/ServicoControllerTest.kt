package br.com.babershop.server.infraestructure.web.controller

import br.com.babershop.server.domain.port.input.servico.*
import br.com.babershop.server.infrastructure.web.controller.ServicoController
import br.com.babershop.server.util.ServicoFactory
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Test

@WebMvcTest(controllers = [ServicoController::class])
class ServicoControllerTest() {

    @Autowired
    lateinit var mockMvc: MockMvc
    val URL_BASE = "/api/v1/servicos"

    @MockkBean
    private lateinit var criarServicoUseCase: CriarServicoUseCase

    @MockkBean
    private lateinit var listarServicoUseCase: ListarServicoUseCase

    @MockkBean
    private lateinit var buscarPorIdServicoUseCase: BuscarPorIdServicoUseCase

    @MockkBean
    private lateinit var excluirPorIdServicoUseCase: ExcluirPorIdServicoUseCase

    @MockkBean
    private lateinit var atualizarPorIdServicoUseCase: AtualizarPorIdServicoUseCase

    private final val servicoFactory: ServicoFactory = ServicoFactory()

    @Test
    fun `must return 201 when create servico`() {
        val body = servicoFactory.criarJsonCriarServicoRequest()
        val servicoSalvo = servicoFactory.criarServico()

        every { criarServicoUseCase.executar(any()) } returns servicoSalvo

        val postMethod = post(URL_BASE)
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)

        mockMvc
            .perform(postMethod)
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isNotEmpty)
            .andExpect(jsonPath("$.nome").value(servicoSalvo.nome))
            .andReturn()

    }

}
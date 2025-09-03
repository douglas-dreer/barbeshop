package br.com.babershop.server.infrastructure.web.controller

import br.com.babershop.server.domain.port.input.servico.*
import br.com.babershop.server.infrastructure.web.mapper.toModel
import br.com.babershop.server.infrastructure.web.mapper.toResponse
import br.com.babershop.server.infrastructure.web.request.AtualizarServicoRequest
import br.com.babershop.server.infrastructure.web.request.CriarServicoRequest
import br.com.babershop.server.infrastructure.web.response.ServicoResponse
import br.com.babershop.server.infrastructure.web.response.SucessoResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/servicos")
class ServicoController(
    private val listarServicoUseCase: ListarServicoUseCase,
    private val criarServicoUseCase: CriarServicoUseCase,
    private val buscarPorIdServicoUseCase: BuscarPorIdServicoUseCase,
    private val atualizarPorIdServicoUseCase: AtualizarPorIdServicoUseCase,
    private val excluirPorIdServicoUseCase: ExcluirPorIdServicoUseCase,
) {

    @PostMapping()
    fun criarServico(@RequestBody request: CriarServicoRequest): ResponseEntity<ServicoResponse> {
        val servicoSalvo = criarServicoUseCase.executar(request.toModel())
        val location: URI = URI.create("/api/v1/servicos/${servicoSalvo.id}")
        return ResponseEntity.created(location).body(servicoSalvo.toResponse())

    }

    @GetMapping
    fun listarServico(): ResponseEntity<List<ServicoResponse>> {
        val servicoLista = listarServicoUseCase.executar()
        return ResponseEntity.ok(servicoLista.map { it.toResponse() })
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Int): ResponseEntity<ServicoResponse> {
        val servico = buscarPorIdServicoUseCase.executar(id)
        return ResponseEntity.ok(servico!!.toResponse())
    }

    @PatchMapping("/{id}")
    fun atualizarPorId(
        @PathVariable id: Int,
        @RequestBody request: AtualizarServicoRequest
    ): ResponseEntity<ServicoResponse> {
        val servico = atualizarPorIdServicoUseCase.executar(id, request.toModel())
        return ResponseEntity.ok(servico.toResponse())
    }

    @DeleteMapping("/{id}")
    fun excluirPorId(@PathVariable id: Int): ResponseEntity<SucessoResponse> {
        excluirPorIdServicoUseCase.execute(id)
        return ResponseEntity.noContent().build()
    }

}
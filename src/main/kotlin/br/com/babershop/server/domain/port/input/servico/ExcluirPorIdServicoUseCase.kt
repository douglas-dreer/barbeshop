package br.com.babershop.server.domain.port.input.servico

interface ExcluirPorIdServicoUseCase {
    fun execute(id: Int)
}
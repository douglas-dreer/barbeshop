package br.com.babershop.server.domain.port.input.servico

import br.com.babershop.server.domain.model.Servico

interface BuscarPorIdServicoUseCase {
    fun executar(id: Int): Servico?
}
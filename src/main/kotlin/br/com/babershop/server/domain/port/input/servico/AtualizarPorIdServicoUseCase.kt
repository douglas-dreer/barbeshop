package br.com.babershop.server.domain.port.input.servico

import br.com.babershop.server.domain.model.Servico

interface AtualizarPorIdServicoUseCase {
    fun executar(id: Int, servico: Servico): Servico
}
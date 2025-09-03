package br.com.babershop.server.domain.port.input.servico

import br.com.babershop.server.domain.model.Servico

interface ListarServicoUseCase {
    fun executar(): List<Servico>
}
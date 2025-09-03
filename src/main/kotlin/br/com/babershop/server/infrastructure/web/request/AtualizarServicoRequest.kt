package br.com.babershop.server.infrastructure.web.request

import br.com.babershop.server.domain.enums.StatusServico

data class AtualizarServicoRequest(
    val id: Int,
    val nome: String,
    val valor: Double,
    val descricao: String,
    val status: StatusServico = StatusServico.ATIVO,
) {
}
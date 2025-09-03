package br.com.babershop.server.infrastructure.web.request

import jakarta.validation.constraints.NotNull


data class CriarServicoRequest(
    @field:NotNull(message = "Não pode ser nulo")
    var nome: String,
    @field:NotNull(message = "Não pode ser nulo")
    var valor: Double,
    @field:NotNull(message = "Não pode ser nulo")
    var descricao: String,
)
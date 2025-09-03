package br.com.babershop.server.infrastructure.web.response

import jdk.jfr.Timespan

data class SucessoResponse(
    val mensagem: String,
    val timespan: Timespan
) {
}
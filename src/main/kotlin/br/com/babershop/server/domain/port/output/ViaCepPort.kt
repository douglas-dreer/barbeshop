package br.com.babershop.server.domain.port.output

import br.com.babershop.server.domain.model.Endereco

interface ViaCepPort {
    fun buscarPorCep(cep: String, formato: String): Endereco
}
package br.com.babershop.server.domain.port.input.cep

import br.com.babershop.server.domain.model.Endereco

interface BuscarEnderecoPorCepUseCase {
    fun execute(cep: String, formatoCep: String): Endereco
}
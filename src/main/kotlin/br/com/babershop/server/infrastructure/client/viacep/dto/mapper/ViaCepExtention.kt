package br.com.babershop.server.infrastructure.client.viacep.dto.mapper

import br.com.babershop.server.domain.model.Endereco
import br.com.babershop.server.infrastructure.client.viacep.dto.ViaCepResponse

fun ViaCepResponse.toEndereco(): Endereco {
    return Endereco(
        cep = cep ?: "",
        logradouro = logradouro ?: "",
        bairro = bairro ?: "",
        localidade = localidade ?: "",
        uf = uf ?: "",
        complemento = complemento ?: "",
        ibge = ibge ?: "",
        gia = gia ?: "",
        ddd = ddd ?: "",
        siafi = siafi ?: "",
        unidade = unidade ?: "",
        estado = estado ?: "",
        regiao = regiao ?: ""
    )
}
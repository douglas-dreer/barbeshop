package br.com.babershop.server.infrastructure.client.viacep.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ViaCepResponse(
    @JsonProperty("cep") val cep: String? = null,
    @JsonProperty("logradouro") val logradouro: String? = null,
    @JsonProperty("complemento") val complemento: String? = null,
    @JsonProperty("unidade") val unidade: String? = null,
    @JsonProperty("bairro") val bairro: String? = null,
    @JsonProperty("localidade") val localidade: String? = null,
    @JsonProperty("uf") val uf: String? = null,
    @JsonProperty("estado") val estado: String? = null,
    @JsonProperty("regiao") val regiao: String? = null,
    @JsonProperty("ibge") val ibge: String? = null,
    @JsonProperty("gia") val gia: String? = null,
    @JsonProperty("ddd") val ddd: String? = null,
    @JsonProperty("siafi") val siafi: String? = null,

    @JsonProperty("erro") val erro: Boolean? = false
)
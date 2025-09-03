package br.com.babershop.server.infrastructure.web.exception

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.Instant

/**
 * Data Transfer Object (DTO) para padronizar a representação de erros na resposta da API.
 * A anotação @JsonInclude(JsonInclude.Include.NON_NULL) garante que campos com valor nulo
 * (como `errosDeValidacao`) não sejam incluídos no JSON final, mantendo a resposta limpa.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErroDto(
    /** O momento exato em que o erro ocorreu. */
    val timestamp: Instant = Instant.now(),
    /** O código de status HTTP (ex: 400, 404, 500). */
    val status: Int,
    /** Uma breve descrição do tipo de erro (ex: "Erro de Validação", "Requisição Inválida"). */
    val erro: String,
    /** A mensagem detalhada explicando o erro. Pode ser nula. */
    val mensagem: String?,
    /** O caminho (endpoint) da requisição onde o erro ocorreu. */
    val caminho: String,
    /** Um mapa contendo erros de validação específicos por campo. Usado principalmente para erros 400. */
    val errosDeValidacao: Map<String, String>? = null
)
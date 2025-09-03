package br.com.babershop.server.domain.exception

/**
 * Exceção de negócio lançada quando um campo obrigatório não é preenchido
 * em uma operação, violando uma regra de negócio.
 */
class CampoObrigatorioException(message: String) : RuntimeException(message)
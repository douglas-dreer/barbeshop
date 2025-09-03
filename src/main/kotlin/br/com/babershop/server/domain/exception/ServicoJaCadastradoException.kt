package br.com.babershop.server.domain.exception

/**
 * Exceção de negócio lançada quando há uma tentativa de cadastrar um serviço
 * que já existe na base de dados, violando uma regra de unicidade.
 */
class ServicoJaCadastradoException(message: String) : RuntimeException(message)
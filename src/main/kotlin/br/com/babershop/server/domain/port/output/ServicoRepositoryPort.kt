package br.com.babershop.server.domain.port.output

import br.com.babershop.server.domain.model.Servico

interface ServicoRepositoryPort {
    fun salvar(servico: Servico): Servico
    fun buscarPorId(id: Int): Servico?
    fun atualizar(servico: Servico): Servico
    fun listar(): List<Servico>
    fun excluir(id: Int)
    fun existePorNome(nome: String): Boolean
}
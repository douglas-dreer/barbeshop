package br.com.babershop.server.domain.service.servico

import br.com.babershop.server.domain.exception.CampoObrigatorioException
import br.com.babershop.server.domain.exception.ServicoJaCadastradoException
import br.com.babershop.server.domain.exception.ServicoNaoEncontradoException
import br.com.babershop.server.domain.model.Servico
import br.com.babershop.server.domain.port.output.ServicoRepositoryPort
import org.springframework.stereotype.Component

@Component
class ServicoValidator(
    private val repository: ServicoRepositoryPort
) {

    private final val CAMPO_NOME = "nome";

    private final val MSG_ERRO_SERVICO_JA_CADASTRADO = "Serviço %s já está cadastrado"
    private final val MSG_ERRO_SERVICO_CAMPO_OBRIGATORIO = "O campo %s é obrigatório"
    private final val MSG_ERRO_SERVICO_NAO_ENCONTRADO = "O serviço com o id: %n não foi encontrado."
    private final val MSG_ERRO_SERVICO_CAMPO_DEVE_SER_UNICO = "O campo %s com o valor %s deve ser único"

    fun validarAntesDeInserir(servico: Servico) {
        if (servico.nome.isBlank()) throw CampoObrigatorioException(MSG_ERRO_SERVICO_CAMPO_OBRIGATORIO.format("nome"))
        if (validarSeNomeJaExiste(servico.nome)) throw ServicoJaCadastradoException(MSG_ERRO_SERVICO_JA_CADASTRADO.format(servico.nome))
    }

    fun validarAntesDeEditar(servico: Servico) {
        if (!validarSeExisteServicoPorId(servico.id)) throw ServicoNaoEncontradoException(MSG_ERRO_SERVICO_NAO_ENCONTRADO.format(servico.id))
        if (servico.nome.isBlank()) throw CampoObrigatorioException(MSG_ERRO_SERVICO_CAMPO_OBRIGATORIO.format("nome"))
        if (validarSeNomeUnico(servico.nome)) throw ServicoJaCadastradoException(MSG_ERRO_SERVICO_CAMPO_DEVE_SER_UNICO.format(CAMPO_NOME, servico.nome))
    }

    fun validarAntesDeExcluir(servicoId: Int) {
        if (!validarSeExisteServicoPorId(servicoId)) throw ServicoNaoEncontradoException(MSG_ERRO_SERVICO_NAO_ENCONTRADO.format(servicoId))
    }

    /**
     * Verifica se o nome cadastrado já existe no banco de dados
     */
    private fun validarSeNomeJaExiste(nome: String): Boolean {
        return repository.existePorNome(nome)
    }

    /**
     * Verifica se o id informado existe no banco de dados
     */
    private fun validarSeExisteServicoPorId(id: Int): Boolean {
        return repository.buscarPorId(id) != null
    }

    /**
     * Verifica se o nome do serviço é único
     */
    private fun validarSeNomeUnico(nome: String): Boolean {
        return repository.existePorNome(nome)
    }
}
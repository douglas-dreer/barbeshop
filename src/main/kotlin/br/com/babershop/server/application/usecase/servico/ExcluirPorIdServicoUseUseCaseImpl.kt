package br.com.babershop.server.application.usecase.servico

import br.com.babershop.server.domain.port.input.servico.ExcluirPorIdServicoUseCase
import br.com.babershop.server.domain.port.output.ServicoRepositoryPort
import br.com.babershop.server.domain.service.servico.ServicoValidator
import br.com.babershop.server.infrastructure.annotation.Logavel
import org.springframework.stereotype.Service

@Service
class ExcluirPorIdServicoUseUseCaseImpl(
    private val repository: ServicoRepositoryPort,
    private val validador: ServicoValidator
) : ExcluirPorIdServicoUseCase {
    @Logavel
    override fun execute(id: Int) {
        validador.validarAntesDeExcluir(id)
        return repository.excluir(id)
    }
}
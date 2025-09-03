package br.com.babershop.server.infrastructure.persistence.adapter

import br.com.babershop.server.domain.model.Servico
import br.com.babershop.server.domain.port.output.ServicoRepositoryPort
import br.com.babershop.server.infrastructure.persistence.mapper.toEntity
import br.com.babershop.server.infrastructure.persistence.mapper.toModel
import br.com.babershop.server.infrastructure.persistence.repository.SpringDataServicoRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class ServicoRepositoryAdapter(
    private val springDataRepository: SpringDataServicoRepository
) : ServicoRepositoryPort {
    @Transactional(rollbackOn = [Exception::class])
    override fun salvar(servico: Servico): Servico {
        val servicoEntity = servico.toEntity()
        return springDataRepository.save(servicoEntity).toModel()
    }

    override fun buscarPorId(id: Int): Servico? {
        return springDataRepository.findById(id).orElse(null)?.toModel()
    }

    @Transactional(rollbackOn = [Exception::class])
    override fun atualizar(servico: Servico): Servico {
        val servicoEntity = servico.toEntity()
        return springDataRepository.save(servicoEntity).toModel()
    }

    override fun listar(): List<Servico> {
        return springDataRepository.findAll().map { it.toModel() }
    }

    @Transactional(rollbackOn = [Exception::class])
    override fun excluir(id: Int) {
        springDataRepository.deleteById(id)
    }

    override fun existePorNome(nome: String): Boolean {
        return springDataRepository.existsByNome(nome);
    }

}
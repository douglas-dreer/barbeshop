package br.com.babershop.server.infrastructure.persistence.repository

import br.com.babershop.server.infrastructure.persistence.entity.ServicoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataServicoRepository : JpaRepository<ServicoEntity, Int> {
    fun existsByNome(nome: String): Boolean
}
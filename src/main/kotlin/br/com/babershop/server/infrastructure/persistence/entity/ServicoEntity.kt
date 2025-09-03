package br.com.babershop.server.infrastructure.persistence.entity

import br.com.babershop.server.domain.enums.StatusServico
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "tb_servico")
class ServicoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int,
    @Column(nullable = false, unique = true)
    val nome: String,
    @Column(nullable = false)
    val valor: Double,
    @Column(nullable = false)
    val descricao: String,

    @CreationTimestamp
    @Column(updatable = false)
    val dataCadastro: LocalDateTime,

    @UpdateTimestamp
    val dataAtualizacao: LocalDateTime,

    @Enumerated(EnumType.STRING)
    val status: StatusServico = StatusServico.ATIVO
)
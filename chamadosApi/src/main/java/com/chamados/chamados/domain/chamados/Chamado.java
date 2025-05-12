package com.chamados.chamados.domain.chamados;

import com.chamados.chamados.domain.clientes.Clientes;
import com.chamados.chamados.domain.imagens.Imagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "chamados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chamado {

    @Id
    @GeneratedValue
    private UUID id;

    private String descricao;
    private int prioridade;
    @Column(name="dataabertura")
    private Date dataAbertura;
    @Column(name = "datafinalizacao")
    private Date dataFinalizacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Clientes cliente;
}

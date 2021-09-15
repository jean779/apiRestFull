package com.example.projeto3unidade.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)

public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String prioridade;
    private Date deleted;
    @JoinColumn (name = "Nome_colaborador", nullable = false, unique = true)
    @ManyToMany(cascade = CascadeType.ALL, fetch =  FetchType.EAGER)
    private List<Colaborador> responsavel;
    @JoinColumn (name = "tipo_tarefa", nullable = false, unique = true)
    @ManyToOne(cascade = CascadeType.ALL, fetch =  FetchType.EAGER)
    private TipoDeTarefa tipodaTarefa;
}

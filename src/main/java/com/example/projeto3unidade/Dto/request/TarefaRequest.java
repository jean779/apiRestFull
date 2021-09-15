package com.example.projeto3unidade.Dto.request;

import com.example.projeto3unidade.Model.Colaborador;
import com.example.projeto3unidade.Model.Tarefa;
import com.example.projeto3unidade.Model.TipoDeTarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaRequest {

    private String titulo;
    private String descricao;
    private String prioridade;
    private List <ColaboradorRequest> responsavel;
    private TipoDeTarefaRequest tipodaTarefa;

    public Tarefa build() {

        List<Colaborador> newColab = new ArrayList<>();
        for (ColaboradorRequest colabRequest : this.responsavel)
            newColab.add(colabRequest.build());

        return new Tarefa()
                .setTitulo(this.titulo)
                .setDescricao(this.descricao)
                .setPrioridade(this.prioridade)
                .setResponsavel(newColab)
                .setTipodaTarefa(this.tipodaTarefa.build());
    }
}

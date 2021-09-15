package com.example.projeto3unidade.Dto.response;

import com.example.projeto3unidade.Controller.EndereçoController;
import com.example.projeto3unidade.Controller.TarefaController;
import com.example.projeto3unidade.Dto.request.ColaboradorRequest;
import com.example.projeto3unidade.Dto.request.TipoDeTarefaRequest;
import com.example.projeto3unidade.Model.Colaborador;
import com.example.projeto3unidade.Model.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaResponse extends RepresentationModel<TarefaResponse>{

    private String titulo;
    private String descricao;
    private String prioridade;
    private List<ColaboradorResponse> responsavel;
    private TipodeTarefaResponse tipodaTarefa;

    public TarefaResponse(Tarefa tarefa){
        List<ColaboradorResponse> newColab = new ArrayList<>();
        for (Colaborador colab : tarefa.getResponsavel())
            newColab.add(new ColaboradorResponse(colab));

        this.titulo = tarefa.getTitulo();
        this.descricao = tarefa.getDescricao();
        this.prioridade = tarefa.getPrioridade();
        this.responsavel = newColab;
        this.tipodaTarefa = new TipodeTarefaResponse(tarefa.getTipodaTarefa());

        this.add(linkTo(TarefaController.class).slash(tarefa.getId()).withSelfRel());
        this.add(linkTo(TarefaController.class).slash( tarefa.getId()).withRel("Editar a Tarefa"));
        this.add(linkTo(TarefaController.class).slash( tarefa.getId()).withRel("Excluir a Tarefa"));
        this.add(linkTo(TarefaController.class).withRel("Todos as Tarefas"));

    }
}

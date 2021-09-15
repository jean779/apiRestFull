package com.example.projeto3unidade.Dto.response;

import com.example.projeto3unidade.Controller.EndereçoController;
import com.example.projeto3unidade.Controller.TipoDeTarefaController;
import com.example.projeto3unidade.Model.TipoDeTarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipodeTarefaResponse extends RepresentationModel<TipodeTarefaResponse> {

    private  String nome;

    public TipodeTarefaResponse(TipoDeTarefa tipoTarefa){
        this.nome = tipoTarefa.getNome();

        this.add(linkTo(TipoDeTarefaController.class).slash(tipoTarefa.getId()).withSelfRel());
        this.add(linkTo(TipoDeTarefaController.class).slash( tipoTarefa.getId()).withRel("Editar o Tipo De Tarefa"));
        this.add(linkTo(TipoDeTarefaController.class).slash( tipoTarefa.getId()).withRel("Excluir o Tipo De Tarefa"));
        this.add(linkTo(TipoDeTarefaController.class).withRel("Todos os Tipos de Tarefas"));


    }
}

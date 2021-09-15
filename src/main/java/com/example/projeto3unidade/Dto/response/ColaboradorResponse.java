package com.example.projeto3unidade.Dto.response;

import com.example.projeto3unidade.Controller.ColaboradorController;
import com.example.projeto3unidade.Controller.EndereçoController;
import com.example.projeto3unidade.Model.Colaborador;
import com.example.projeto3unidade.Model.Endereço;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorResponse extends RepresentationModel<ColaboradorResponse>{

    private String nome;
    private String cargo;
    private EndereçoResponse endereço;

    public ColaboradorResponse(Colaborador colab){
        this.nome = colab.getNome();
        this.cargo = colab.getCargo();
        this.endereço = new EndereçoResponse(colab.getEndereço());

        this.add(linkTo(ColaboradorController.class).slash(colab.getId()).withSelfRel());
        this.add(linkTo(ColaboradorController.class).slash("/editar/" + colab.getId()).withRel("Editar o Colaborador"));
        this.add(linkTo(ColaboradorController.class).slash("/excluir/" + colab.getId()).withRel("Excluir o Colaborador"));
        this.add(linkTo(ColaboradorController.class).withRel("Todos os Colaboradores"));
    }
}

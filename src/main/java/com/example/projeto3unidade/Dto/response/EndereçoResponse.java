package com.example.projeto3unidade.Dto.response;

import com.example.projeto3unidade.Controller.EndereçoController;
import com.example.projeto3unidade.Model.Endereço;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndereçoResponse extends RepresentationModel<EndereçoResponse>{

    private String nEndereco;

    public EndereçoResponse(Endereço endereco){
        if(endereco != null) {
            this.nEndereco = endereco.getNEndereco();

            this.add(linkTo(EndereçoController.class).slash(endereco.getId()).withSelfRel());
            this.add(linkTo(EndereçoController.class).slash("/editar/" + endereco.getId()).withRel("Editar Endereço"));
            this.add(linkTo(EndereçoController.class).slash("/excluir/" + endereco.getId()).withRel("Excluir Endereço"));
            this.add(linkTo(EndereçoController.class).withRel("Todos os Endereços"));
        }
    }

}

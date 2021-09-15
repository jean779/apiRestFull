package com.example.projeto3unidade.Dto.request;


import com.example.projeto3unidade.Model.Colaborador;
import com.example.projeto3unidade.Model.Endereço;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorRequest {


    private String nome;
    private String cargo;
    private EndereçoRequest endereço;

    public  Colaborador build(){
       return new Colaborador()
               .setNome(this.nome)
               .setCargo(this.cargo)
               .setEndereço(this.endereço.build());

    }
}

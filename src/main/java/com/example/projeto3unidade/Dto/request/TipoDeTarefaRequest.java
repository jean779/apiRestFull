package com.example.projeto3unidade.Dto.request;

import com.example.projeto3unidade.Model.TipoDeTarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDeTarefaRequest  {

    private String nome;

    public TipoDeTarefa build(){
        return new TipoDeTarefa()
                .setNome(this.nome);

    }
}

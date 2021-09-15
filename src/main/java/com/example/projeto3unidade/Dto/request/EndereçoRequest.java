package com.example.projeto3unidade.Dto.request;

import com.example.projeto3unidade.Model.Endereço;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndereçoRequest {

    private String nEndereco;

    public Endereço build(){
        return new Endereço()
                .setNEndereco(this.nEndereco);

    }
}

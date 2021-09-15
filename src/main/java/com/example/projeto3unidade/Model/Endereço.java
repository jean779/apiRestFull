package com.example.projeto3unidade.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereço {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private Date deleted;
    @Column(name = "n_endereco")
    private String nEndereco;

}

package com.example.projeto3unidade.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDeTarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private Date deleted;
}

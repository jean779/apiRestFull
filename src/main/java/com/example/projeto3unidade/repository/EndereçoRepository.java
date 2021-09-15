package com.example.projeto3unidade.repository;

import com.example.projeto3unidade.Model.Colaborador;
import com.example.projeto3unidade.Model.Endereço;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EndereçoRepository  extends JpaRepository <Endereço, Long>{
    List<Endereço> findAllByDeletedIsNull();
    Optional<Endereço> findByDeletedIsNullAndId(Long id);
}
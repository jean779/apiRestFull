package com.example.projeto3unidade.repository;

import com.example.projeto3unidade.Model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColaboradorRepository  extends JpaRepository <Colaborador, Long>{
   List<Colaborador> findAllByDeletedIsNull();
   Optional<Colaborador> findByDeletedIsNullAndId(Long id);
}

package com.example.projeto3unidade.repository;

import com.example.projeto3unidade.Model.TipoDeTarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface TipoDeTarefaRepository  extends JpaRepository <TipoDeTarefa, Long>{
    List<TipoDeTarefa> findAllByDeletedIsNull();
    Optional<TipoDeTarefa> findByDeletedIsNullAndId(Long id);
}
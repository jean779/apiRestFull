package com.example.projeto3unidade.repository;

import com.example.projeto3unidade.Model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface TarefaRepository  extends JpaRepository <Tarefa, Long>{
    List<Tarefa> findAllByDeletedIsNull();
    Optional<Tarefa> findByDeletedIsNullAndId(Long id);
}
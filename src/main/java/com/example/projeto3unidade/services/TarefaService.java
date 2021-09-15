package com.example.projeto3unidade.services;


import com.example.projeto3unidade.Dto.response.TarefaResponse;
import com.example.projeto3unidade.Dto.response.TipodeTarefaResponse;
import com.example.projeto3unidade.Model.Tarefa;
import com.example.projeto3unidade.Model.TipoDeTarefa;
import com.example.projeto3unidade.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService{

    private TarefaRepository repository;

    @Autowired
    public void setRepository(TarefaRepository repository){
        this.repository = repository;
    }

    public Optional<Tarefa> getTarefaByID(Long id){
        return repository.findByDeletedIsNullAndId(id);
    }

    public List<TarefaResponse> getAllTarefas(){
        ArrayList<TarefaResponse> tarefaResponse = new ArrayList<>();
        for(Tarefa tarefa : repository.findAllByDeletedIsNull())
            tarefaResponse.add(new TarefaResponse(tarefa));

        return tarefaResponse;
    }

    public Tarefa isert(Tarefa tarefa){
        return repository.save(tarefa);
    }

    public Tarefa saveAndFlush(Tarefa tarefa){
        return repository.saveAndFlush(tarefa);
    }

    public Tarefa delete(Long id){
        Tarefa tarefa = repository.getById(id);
        tarefa.setDeleted(new Date());
        return repository.saveAndFlush(tarefa);
    }
}

package com.example.projeto3unidade.services;


import com.example.projeto3unidade.Dto.response.EndereçoResponse;
import com.example.projeto3unidade.Dto.response.TipodeTarefaResponse;
import com.example.projeto3unidade.Model.Endereço;
import com.example.projeto3unidade.Model.TipoDeTarefa;
import com.example.projeto3unidade.repository.TipoDeTarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TipoDeTarefaService{

    private TipoDeTarefaRepository repository;

    @Autowired
    public void setRepository(TipoDeTarefaRepository repository){
        this.repository = repository;
    }

    public Optional<TipoDeTarefa> getTipoDeTarefaByID(Long id){
        return repository.findByDeletedIsNullAndId(id);
    }

    public List<TipodeTarefaResponse> getAllTiposDeTarefas(){
        ArrayList<TipodeTarefaResponse> tipodeTarefaResponse = new ArrayList<>();
        for(TipoDeTarefa tipoDeTarefa : repository.findAllByDeletedIsNull())
            tipodeTarefaResponse.add(new TipodeTarefaResponse(tipoDeTarefa));

        return tipodeTarefaResponse;
    }

    public TipoDeTarefa isert(TipoDeTarefa tipoDeTarefa){
        return repository.save(tipoDeTarefa);
    }

    public TipoDeTarefa saveAndFlush(TipoDeTarefa tipoDeTarefa){
        return repository.saveAndFlush(tipoDeTarefa);
    }

    public TipoDeTarefa delete(Long id){
        TipoDeTarefa tipoDeTarefa = repository.getById(id);
        tipoDeTarefa.setDeleted(new Date());
        return repository.saveAndFlush(tipoDeTarefa);
    }
}

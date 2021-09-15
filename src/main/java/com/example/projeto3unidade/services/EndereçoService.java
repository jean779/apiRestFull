package com.example.projeto3unidade.services;


import com.example.projeto3unidade.Dto.response.EndereçoResponse;
import com.example.projeto3unidade.Model.Endereço;
import com.example.projeto3unidade.repository.EndereçoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EndereçoService {

    private EndereçoRepository repository;

    @Autowired
    public void setRepository(EndereçoRepository repository){
        this.repository = repository;
    }

    public Optional<Endereço> getEnderecoByID(Long id){
        return repository.findByDeletedIsNullAndId(id);
    }

    public List<EndereçoResponse> getAllEndereco(){
        ArrayList<EndereçoResponse> enderecoResponse = new ArrayList<>();
        for(Endereço endereco : repository.findAllByDeletedIsNull())
            enderecoResponse.add(new EndereçoResponse(endereco));

        return enderecoResponse;
    }

    public Endereço isert(Endereço endereco){
        return repository.save(endereco);
    }

    public Endereço saveAndFlush(Endereço endereco){
        return repository.saveAndFlush(endereco);
    }

    public Endereço delete(Long id){
        Endereço endereco = repository.getById(id);
        endereco.setDeleted(new Date());
        return repository.saveAndFlush(endereco);
    }
}

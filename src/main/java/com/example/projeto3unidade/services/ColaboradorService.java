package com.example.projeto3unidade.services;

import com.example.projeto3unidade.Dto.response.ColaboradorResponse;
import com.example.projeto3unidade.Dto.response.EndereçoResponse;
import com.example.projeto3unidade.Model.Colaborador;
import com.example.projeto3unidade.Model.Endereço;
import com.example.projeto3unidade.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    private ColaboradorRepository repository;

    @Autowired
    public void setRepository(ColaboradorRepository repository){
        this.repository = repository;
    }

    public Optional<Colaborador> getColaboradorByID(Long id){
        return repository.findByDeletedIsNullAndId(id);
    }

    public List<ColaboradorResponse> getAllColaborador(){
        ArrayList<ColaboradorResponse> colabResponse = new ArrayList<>();
        for(Colaborador colab : repository.findAllByDeletedIsNull())
            colabResponse.add(new ColaboradorResponse(colab));

        return colabResponse;
    }

    public Colaborador isert(Colaborador colab){
        return repository.save(colab);
    }

    public Colaborador saveAndFlush(Colaborador colab){
        return repository.saveAndFlush(colab);
    }

    public Colaborador delete(Long id){
        Colaborador colab = repository.getById(id);
        colab.setDeleted(new Date());
        return repository.saveAndFlush(colab);
    }
}

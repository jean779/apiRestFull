package com.example.projeto3unidade.Controller;

import com.example.projeto3unidade.Dto.response.EndereçoResponse;
import com.example.projeto3unidade.Dto.response.TipodeTarefaResponse;
import com.example.projeto3unidade.Model.Mensagem;
import com.example.projeto3unidade.Model.TipoDeTarefa;
import com.example.projeto3unidade.services.TipoDeTarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/tipodetarefa")
public class TipoDeTarefaController {

    private TipoDeTarefaService service;

    @Autowired
    public void setService(TipoDeTarefaService service){
        this.service = service;
    }

    @GetMapping
    public List<TipodeTarefaResponse> getTiposDeTarefas(){
        return service.getAllTiposDeTarefas();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity getTiposDeTarefasById(@PathVariable Long id) {
        Optional<TipoDeTarefa> tipoDeTarefa = service.getTipoDeTarefaByID(id);
        if (tipoDeTarefa.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(new TipodeTarefaResponse(tipoDeTarefa.get()));
        }
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody TipoDeTarefa tipoDeTarefa){
        service.isert(tipoDeTarefa);
        return ResponseEntity.created(URI.create("/tiposdetarefa/"+tipoDeTarefa.getId())).body(new TipodeTarefaResponse(tipoDeTarefa));
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity update(@PathVariable Long id, @RequestBody TipoDeTarefa tipoDeTarefa){

        return service.getTipoDeTarefaByID(id)
                .map( record -> {
                    if( record.getId() == tipoDeTarefa.getId()){
                        service.saveAndFlush(tipoDeTarefa);
                        return ResponseEntity.ok(new TipodeTarefaResponse(tipoDeTarefa));
                    }else{
                        return ResponseEntity.notFound().build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Long id){
        return service.getTipoDeTarefaByID(id)
                .map( record -> {
                    Mensagem m = new Mensagem();
                    m.setMensagem("The type of tasks has been deleted");
                    service.delete(record.getId());
                    return ResponseEntity.ok(linkTo(TipoDeTarefaController.class).withRel("Todos os Tipos de Tarefas" + m));
                }).orElse(ResponseEntity.notFound().build());
    }
}

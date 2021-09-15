package com.example.projeto3unidade.Controller;

import com.example.projeto3unidade.Dto.response.TarefaResponse;
import com.example.projeto3unidade.Model.Mensagem;
import com.example.projeto3unidade.Model.Tarefa;
import com.example.projeto3unidade.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private TarefaService service;

    @Autowired
    public void setService(TarefaService service){
        this.service = service;
    }

    @GetMapping
    public List<TarefaResponse> getTarefas(){ return service.getAllTarefas(); }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity getTarefasById(@PathVariable Long id) {
        Optional<Tarefa> tarefa = service.getTarefaByID(id);
        if (tarefa.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(new TarefaResponse(tarefa.get()));
        }
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody Tarefa tarefa){
        service.isert(tarefa);
        return ResponseEntity.created(URI.create("/tarefa/"+tarefa.getId())).body(new TarefaResponse(tarefa));
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Tarefa tarefa){

        return service.getTarefaByID(id)
                .map( record -> {
                    if( record.getId() == tarefa.getId()){
                        service.saveAndFlush(tarefa);
                        return ResponseEntity.ok(new TarefaResponse(tarefa));
                    }else{
                        return ResponseEntity.notFound().build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Long id){
        return service.getTarefaByID(id)
                .map( record -> {
                    Mensagem m = new Mensagem();
                    m.setMensagem("The task has been deleted");
                    service.delete(record.getId());
                    return ResponseEntity.ok(linkTo(TarefaController.class).withRel("Todos as Tarefas" + m));
                }).orElse(ResponseEntity.notFound().build());
    }
}

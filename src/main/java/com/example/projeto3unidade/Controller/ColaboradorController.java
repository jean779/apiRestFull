package com.example.projeto3unidade.Controller;

import com.example.projeto3unidade.Dto.response.ColaboradorResponse;
import com.example.projeto3unidade.Dto.response.EndereçoResponse;
import com.example.projeto3unidade.Model.Colaborador;
import com.example.projeto3unidade.Model.Mensagem;
import com.example.projeto3unidade.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {

    private ColaboradorService service;

    @Autowired
    public void setService(ColaboradorService service){
        this.service = service;
    }

    @GetMapping
    public List<ColaboradorResponse> getColaboradores(){
        return service.getAllColaborador();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity getPessoaById(@PathVariable Long id) {
        Optional<Colaborador> colab = service.getColaboradorByID(id);
        if (colab.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(new ColaboradorResponse(colab.get()));
        }
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody Colaborador colab){
        service.isert(colab);
        return ResponseEntity.created(URI.create("/colaborador/"+colab.getId())).body(new ColaboradorResponse(colab));
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Colaborador colab){

        return service.getColaboradorByID(id)
               .map( record -> {
                   if( record.getId() == colab.getId()){
                       service.saveAndFlush(colab);
                       return ResponseEntity.ok(new ColaboradorResponse(colab));
                   }else{
                       return ResponseEntity.notFound().build();
                   }
               }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Long id){
        return service.getColaboradorByID(id)
                .map( record -> {
                    Mensagem m = new Mensagem();
                    m.setMensagem("The collaborator has been deleted");
                    service.delete(record.getId());
                    return ResponseEntity.ok(linkTo(ColaboradorController.class).withRel("Todos os colaboradores" + m));
                }).orElse(ResponseEntity.notFound().build());
    }
}

package com.example.projeto3unidade.Controller;

import com.example.projeto3unidade.Dto.request.EndereçoRequest;
import com.example.projeto3unidade.Dto.response.EndereçoResponse;
import com.example.projeto3unidade.Model.Endereço;
import com.example.projeto3unidade.Model.Mensagem;
import com.example.projeto3unidade.services.EndereçoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EndereçoController {

    private EndereçoService service;

    @Autowired
    public void setService(EndereçoService service){
        this.service = service;
    }

    @GetMapping
    public List<EndereçoResponse> getEndereços(){
        return service.getAllEndereco();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity getEndereçoById(@PathVariable Long id) {
        Optional<Endereço> endereco = service.getEnderecoByID(id);
        if (endereco.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(new EndereçoResponse(endereco.get()));
        }
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody EndereçoRequest enderecoRequest){
        Endereço endereco = enderecoRequest.build();
        service.isert(endereco);
        return ResponseEntity.created(URI.create("/endereco/"+endereco.getId())).body(new EndereçoResponse(endereco));
    }

    @PutMapping(path = {"/editar/{id}"})
    public ResponseEntity update(@PathVariable Long id, @RequestBody Endereço endereco){

        return service.getEnderecoByID(id)
                .map( record -> {
                    if( record.getId() == endereco.getId()){
                        service.saveAndFlush(endereco);
                        return ResponseEntity.ok(new EndereçoResponse(endereco));
                    }else{
                        return ResponseEntity.notFound().build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/excluir/{id}"})
    public ResponseEntity delete(@PathVariable Long id){
        return service.getEnderecoByID(id)
                .map( record -> {
                    Mensagem m = new Mensagem();
                    m.setMensagem("The address has been deleted");
                    service.delete(record.getId());
                    return ResponseEntity.ok(m);
                }).orElse(ResponseEntity.notFound().build());
    }
}

package org.generation.Imigrapp.Controller;

import java.util.List;

import org.generation.Imigrapp.model.Usuario;
import org.generation.Imigrapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")

public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuario(){
    	return ResponseEntity.ok(repository.findAll()); 
    }
    
    @GetMapping("/{id_usuario}")
    public ResponseEntity<Usuario> getById(@PathVariable long id_usuario){
    	return repository.findById(id_usuario).map(resp-> ResponseEntity.ok(resp))
    			.orElse(ResponseEntity.notFound().build());
    }
	
    @GetMapping("/nome/{nomeUsuario}")
    public ResponseEntity<List<Usuario>> getByNome (@PathVariable String nomeUsuario){
    	return ResponseEntity.ok(repository.findAllByNomeUsuarioContainingIgnoreCase(nomeUsuario));
    }
    
    @PostMapping
    public ResponseEntity <Usuario> postUsuario (@RequestBody Usuario usuario){
    	return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
    }
    
    @PutMapping
    public ResponseEntity <Usuario> putUsuario (@RequestBody Usuario usuario){
    	return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
    }
    
    @DeleteMapping("/{id_usuario}")
    public void deleteUsuario (@PathVariable long id_usuario) {
    	repository.deleteById(id_usuario);
    }
}

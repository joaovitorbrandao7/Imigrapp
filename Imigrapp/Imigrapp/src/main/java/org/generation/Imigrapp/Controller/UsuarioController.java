package org.generation.Imigrapp.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.Imigrapp.model.Usuario;
import org.generation.Imigrapp.model.UsuarioDTO;
import org.generation.Imigrapp.repository.UsuarioRepository;
import org.generation.Imigrapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService servicos;
	 
	@PostMapping("/cadastro")
	public ResponseEntity<Object> cadastrarUsuario(@Valid @RequestBody Usuario novoUsuario) {
		Optional<Object> objetoCadastrado = servicos.cadastrarUsuario(novoUsuario);

		if (objetoCadastrado.isPresent()) {
			return ResponseEntity.status(201).body(objetoCadastrado.get());
		} else {
			return ResponseEntity.status(400).build();
		}

	}
	@PostMapping("/logar")
	public ResponseEntity<UsuarioDTO> autentication(@RequestBody Optional<UsuarioDTO> user){
		return servicos.Logar(user).map(resp-> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	
    @GetMapping("/todes")
    public ResponseEntity<List<Usuario>> getAllUsuario(){
    	List<Usuario> listaUsuarios = repository.findAll();
		
		if (listaUsuarios.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaUsuarios);
		}
    }
    
    
    @GetMapping("/id/{id_usuario}")
    public ResponseEntity<Usuario> getById(@PathVariable long id_usuario){
    	return repository.findById(id_usuario).map(resp-> ResponseEntity.ok(resp))
    			.orElse(ResponseEntity.notFound().build());
    }
	
    
    @GetMapping("/nome/{nomeUsuario}")
    public ResponseEntity<List<Usuario>> getByNome (@PathVariable String nomeUsuario){
    	return ResponseEntity.ok(repository.findAllByNomeUsuarioContainingIgnoreCase(nomeUsuario));
    }
    
    
    @PutMapping("/alterar")
	public ResponseEntity<Object> alterar(@Valid @RequestBody UsuarioDTO novoUsuario) {
		Optional<?> objetoAlterado = servicos.alterarUsuario(novoUsuario);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}
    
    @DeleteMapping("/deletar/{id_usuario}")
    public ResponseEntity<String> deletarPorId(@PathVariable long idUsuario) {
		Optional<Usuario> usuarioExistente = repository.findById(idUsuario);
		if (usuarioExistente.isPresent()) {
			repository.deleteById(idUsuario);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}
		
	}
}

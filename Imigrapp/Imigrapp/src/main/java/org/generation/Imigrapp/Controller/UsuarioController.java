package org.generation.Imigrapp.Controller;

import java.util.List;

import org.generation.Imigrapp.model.Usuario;
import org.generation.Imigrapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}

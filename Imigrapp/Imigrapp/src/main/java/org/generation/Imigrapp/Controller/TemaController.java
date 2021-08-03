package org.generation.Imigrapp.Controller;

import java.util.List;

import org.generation.Imigrapp.model.Tema;
import org.generation.Imigrapp.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tema")
@RestController
@CrossOrigin("*")
public class TemaController {

	
	@Autowired  // 21 a 22 - Faz acesso ao repositorio!!
	private TemaRepository repository;
	
	@GetMapping //24 a 26 - Buscando todos as informações dentro do tema!!!
	public ResponseEntity<List<Tema>> getAll ( ){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}") // 29 a 32 - Buscando informações por id
	public ResponseEntity<Tema> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse (ResponseEntity.notFound().build());
	}
	
	
	
	
	
	
}

package org.generation.Imigrapp.Controller;

import java.util.List;
import java.util.Optional;

import org.generation.Imigrapp.model.Tema;
import org.generation.Imigrapp.repository.TemaRepository;
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
	
	@GetMapping("/tema/{nomeTema}")
	public ResponseEntity<List<Tema>> getByNome(@PathVariable String nomeTema){
		return ResponseEntity.ok(repository.findAllByNomeTemaContainingIgnoreCase(nomeTema));
	}
	
	@PostMapping
	public ResponseEntity<Tema> post (@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	@PutMapping
	public ResponseEntity<Tema> put (@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
	}
	
	@DeleteMapping("/{id}")// 56 a 66 - Deletar tema retorna uma mensagem caso tenha dado certo
	public ResponseEntity<String> deletarById (@PathVariable long id) {
		Optional<Tema> UsuarioExistente = repository.findById(id);
		
		if (UsuarioExistente.isPresent()){
			repository.deleteById(id);
			return ResponseEntity.ok().body("Tema deletado com sucesso!!!");
		}
		else {
			return ResponseEntity.ok().body("Tema não encontrado!!!");
		}
		return null;
	}
}

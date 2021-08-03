package org.generation.Imigrapp.repository;

import java.util.List;

import org.generation.Imigrapp.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long>{
	
	public List<Tema> findAllByNomeTemaContainingIgnoreCase (String nomeTema);

}

package org.generation.Imigrapp.repository;

import java.util.List;

import org.generation.Imigrapp.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository <Postagem, Long> 
{
	public List<Postagem> findAllByCampoPostagemContainingIgnoreCase(String campoPostagem);
}

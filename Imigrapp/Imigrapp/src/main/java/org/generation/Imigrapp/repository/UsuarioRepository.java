package org.generation.Imigrapp.repository;

import java.util.List;

import org.generation.Imigrapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

	public List<Usuario> findAllByNomeUsuarioContainingIgnoreCase (String nomeUsuario);
}

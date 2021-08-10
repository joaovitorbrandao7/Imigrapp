package org.generation.Imigrapp.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.Imigrapp.model.Usuario;
import org.generation.Imigrapp.model.UsuarioDTO;
import org.generation.Imigrapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
		
	public Optional<Object> cadastrarUsuario(Usuario novoUsuario) {
		Optional<Usuario> usuario = repository.findByEmail(novoUsuario.getEmail());
		if (usuario.isPresent()) {
			return Optional.empty();
		} else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(senhaCriptografada);
			return Optional.ofNullable(repository.save(novoUsuario));
		}
	}
		
		public Optional<UsuarioDTO> Logar(Optional<UsuarioDTO> user){
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 

	        Optional<Usuario> usuario = repository.findByEmail(user.get().getEmail());

	        if(usuario.isPresent()) {
	        	if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
	        		String auth = user.get().getEmail()+ ":" + user.get().getSenha();
	        		byte[] encodedAuth = Base64.decodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
	        		String authHeader = "Basic " + new String (encodedAuth);
	        		
	        		user.get().setToken(authHeader);
	        		user.get().setEmail(usuario.get().getEmail());
	        		
	        		return user;

	        	}
	        }
	        return null;
	        	}
	        
	}




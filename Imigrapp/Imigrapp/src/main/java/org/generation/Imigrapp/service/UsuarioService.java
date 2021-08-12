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
		
	public Optional<?> logar (UsuarioDTO usuarioParaLogin) {
		return repository.findByEmail(usuarioParaLogin.getEmail()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(usuarioParaLogin.getSenha(), usuarioExistente.getSenha())) {
				String estruturaBasic = usuarioParaLogin.getEmail() + ":" + usuarioParaLogin.getSenha(); 
				byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII"))); 
				String autorizacaoHeader = "Basic " + new String(autorizacaoBase64);

				usuarioParaLogin.setToken(autorizacaoHeader);
				usuarioParaLogin.setId_usuario(usuarioExistente.getId_usuario());
				usuarioParaLogin.setNomeUsuario(usuarioExistente.getNomeUsuario());
				usuarioParaLogin.setSenha(usuarioExistente.getSenha());
				return Optional.ofNullable(usuarioParaLogin);
			} else {
				return Optional.empty();
			}
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

		 public Optional<?> alterarUsuario(UsuarioDTO usuarioParaAlterar) {
			return repository.findById(usuarioParaAlterar.getId_usuario()).map(usuarioExistente -> {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String senhaCriptografada = encoder.encode(usuarioParaAlterar.getSenha());
				
				usuarioExistente.setNomeUsuario(usuarioParaAlterar.getNomeUsuario());
				usuarioExistente.setSenha(senhaCriptografada);
				return Optional.ofNullable(repository.save(usuarioExistente));
			}).orElseGet(() -> {
				return Optional.empty();
			});
		}
	        
	}




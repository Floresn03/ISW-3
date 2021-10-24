package com.ab.springboot.backend.AppCursos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ab.springboot.backend.AppCursos.dao.IUsuarioDAO;
import com.ab.springboot.backend.AppCursos.model.Usuario;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private IUsuarioDAO usuarioDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		UserBuilder builder = null;
		
		if (usuario != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(usuario.getContraseña());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
			
			
		}
		else {
			throw new UsernameNotFoundException("Error al iniciar sesión, verifique su usuario y contraseña");
			
		}
		return builder.build();
	}

	
	

}

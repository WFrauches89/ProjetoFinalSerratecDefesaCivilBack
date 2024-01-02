package br.gov.api.DefesaCivil.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.gov.api.DefesaCivil.common.ConversorData;

@Entity
@Table(name = "usuario")
public class UsuarioEntity implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long id;

    @Column(nullable = false)
    private String nomeUsuario;

    @Column(nullable = false, unique = true)
    private String email;

//    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String dtCadastro;

    @Column(nullable = false)
    private ETipoPerfil perfil;
    
    public UsuarioEntity() {
    	this.dtCadastro = ConversorData.converterDateParaDataHora(new Date());
    	this.perfil = ETipoPerfil.ADMIN;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(String dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public ETipoPerfil getPerfil() {
        return perfil;
    }
    
    public void setPerfil(ETipoPerfil perfil) {
        this.perfil = perfil;
    }

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<String> perfis = new ArrayList<>();
       perfis.add(perfil.toString());
      return perfis.stream()
        .map(perfil -> new SimpleGrantedAuthority(perfil))
        .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
       return email;
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
       return true;
    }
}

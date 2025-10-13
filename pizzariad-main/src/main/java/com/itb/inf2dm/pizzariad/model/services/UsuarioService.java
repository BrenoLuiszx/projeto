package com.itb.inf2dm.pizzariad.model.services;

import com.itb.inf2dm.pizzariad.model.entity.Usuario;
import com.itb.inf2dm.pizzariad.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        usuario.setCodStatus(true);
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id: " + id));
    }

    public Usuario update(Long id, Usuario usuario) {
        Usuario usuarioExistente = findById(id);
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setCpf(usuario.getCpf());
        usuarioExistente.setTipoUsuario(usuario.getTipoUsuario());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setSenha(usuario.getSenha());
        usuarioExistente.setSexo(usuario.getSexo());
        usuarioExistente.setLogradouro(usuario.getLogradouro());
        usuarioExistente.setCep(usuario.getCep());
        usuarioExistente.setBairro(usuario.getBairro());
        usuarioExistente.setCidade(usuario.getCidade());
        usuarioExistente.setUf(usuario.getUf());
        usuarioExistente.setCodStatus(usuario.isCodStatus());
        return usuarioRepository.save(usuarioExistente);
    }

    public void delete(Long id) {
        Usuario usuarioExistente = findById(id);
        usuarioRepository.delete(usuarioExistente);
    }
}
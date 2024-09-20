package voll.med.api.domain.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registraUsuario(DadosAutenticacao dadosAutenticacao) {
        String encodedPassword = passwordEncoder.encode(dadosAutenticacao.senha());
        Usuario usuario = new Usuario();
        usuario.setLogin(dadosAutenticacao.login());
        usuario.setSenha(encodedPassword);
        usuarioRepository.save(usuario);
    }
}
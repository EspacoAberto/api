package espacoaberto.backend.controllers;

//import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.dto.Usuario.Input.InCadastro;
//import espacoaberto.backend.dto.Usuario.Input.InLogin;
//import espacoaberto.backend.dto.Usuario.Output.OutLogin;
//import espacoaberto.backend.entidades.Carteira;
import espacoaberto.backend.dto.Usuario.Input.InLogin;
import espacoaberto.backend.dto.Usuario.Output.OutLogin;
import espacoaberto.backend.entidades.Usuario;
import espacoaberto.backend.repository.UsuarioRepository;
//import espacoaberto.backend.service.ServiceBase64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    // @Autowired
    //private ServiceBase64 serviceBase64;
    @Autowired
    private UsuarioRepository usuarioRepository;
    //@Autowired
    //private CarteiraRepository carteiraRepository;

    @GetMapping()
    public ResponseEntity<List<Usuario>> consultarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> consultarUsuariosPorID(@PathVariable Integer id) {
       Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario.isEmpty()
                ? ResponseEntity.status(404).build()
                : ResponseEntity.status(200).body(usuario.get());
    }

    @PostMapping()
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        String email = usuario.getEmail();
        Optional<Usuario> opUsuario = usuarioRepository.findByEmail(email);

        if (opUsuario.isPresent()) {
            return ResponseEntity.status(409).build();
        }

        // Converter DTO para Objeto Usuario
        //Usuario usuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getCpf(), email, usuarioDTO.getSenha(), usuarioDTO.getTelefone());


        return ResponseEntity.status(201).body(this.usuarioRepository.save(usuario));
    }


    @PostMapping("login")
    public ResponseEntity<OutLogin> login(@RequestBody InLogin entrada) {
        OutLogin response;
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(
                entrada.getEmail(),
                entrada.getSenha()
        );

        if (usuario.isPresent()) {
            response = new OutLogin(
                    usuario.get().getId()
                    , usuario.get().getNome()
                    , usuario.get().getEmail()
                    , usuario.get().getCpf()
                    , usuario.get().getPremium()
                    , ""
            );


            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(404).build();
    }



    @PatchMapping("tornarPremium/{id}")
    public ResponseEntity<Usuario> tornarPremium(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (usuario.isPresent()){
                usuario.get().setPremium(true);
                usuarioRepository.save(usuario.get());
                return ResponseEntity.status(200).body(usuario.get());
            }

    return ResponseEntity.notFound().build();
    }


    @PatchMapping ("atualizar/{cpf}")
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario entrada, @PathVariable String cpf){
        Optional<Usuario> usuario  = usuarioRepository.findByCpf(cpf);



        // Ignora propriedades nulas durante a cópia dos atributos
        String[] ignoredProperties = getNullPropertyNames(entrada);
        BeanUtils.copyProperties(entrada, usuario.get(), ignoredProperties);

        if(usuario.isPresent()){
            Usuario usuarioAtualizado = usuario.get();
            return ResponseEntity.status(200).body(usuarioRepository.save(usuarioAtualizado));
        }

        return ResponseEntity.status(404).build();
    }

    // Método auxiliar para obter os nomes das propriedades nulas de um objeto
    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : src.getPropertyDescriptors()) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        return emptyNames.toArray(new String[0]);
    }

    @PatchMapping("adicionarSaldo/{id}/{saldo}")
    public ResponseEntity<Usuario> adicionarSaldo (@PathVariable Integer id, @PathVariable Double saldo) {
        if (saldo == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Usuario> usuarioOP = usuarioRepository.findById(id);

        if (usuarioOP.isPresent()) {
            Usuario usuario = usuarioOP.get();

            usuario.setSaldo(usuario.getSaldo() + saldo);

            Usuario usuarioAtualizado = usuarioRepository.save(usuario);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("removerSaldo/{id}/{saldo}")
    public ResponseEntity<Usuario> removerSaldo (@PathVariable Integer id, @PathVariable Double saldo) {
        if (saldo == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Usuario> usuarioOP = usuarioRepository.findById(id);

        if (usuarioOP.isPresent()) {
            Usuario usuario = usuarioOP.get();

            usuario.setSaldo(usuario.getSaldo() - saldo);

            Usuario usuarioAtualizado = usuarioRepository.save(usuario);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



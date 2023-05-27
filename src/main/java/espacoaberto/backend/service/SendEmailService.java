package espacoaberto.backend.service;
/*
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Slf4j
public class SendEmailService {

    private final JavaMailSender javaMailSender;

    public SendEmailService( final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviar(String email, String usuario, String codigo) {
        log.info("Enviando email simples");

        String titulo = "Autenticação de email";

        String conteudo = String.format(
                "Olá %s" +
                "\n Bem vindo(a) ao Espaço Aberto! " +
                "\n Suas credencias de acesso estão logo a baixo: " +
                "\n Email: %s\n Codigo: %s" +
                "\n clique no link abaixo parar autenticar sua conta:" +
                "\n http://localhost:9091/usuarios/autenticacao/%s/%s", usuario, email, codigo,email, codigo);

        var mensagem = new SimpleMailMessage();
        mensagem.setTo(email);

        mensagem.setSubject(titulo);
        mensagem.setText(conteudo);
        javaMailSender.send(mensagem);
        log.info("Email enviado com sucesso!");
    }

}*/

package espacoaberto.backend.service;

import lombok.extern.slf4j.Slf4j;
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

    public void enviar(String usuario, String titulo, String conteudo) {
        log.info("Enviando email simples");
        

        var mensagem = new SimpleMailMessage();
        mensagem.setTo(usuario);

        mensagem.setSubject(titulo);
        mensagem.setText(conteudo);
        javaMailSender.send(mensagem);
        log.info("Email enviado com sucesso!");
    }

}

package espacoaberto.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Foto não encontrado")
public class FotoNaoEncontradaException extends RuntimeException{
}

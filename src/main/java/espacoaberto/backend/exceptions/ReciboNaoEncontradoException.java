package espacoaberto.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Recibo não encontrado")
public class ReciboNaoEncontradoException extends RuntimeException{
}

package espacoaberto.backend.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceBase64 {


    public static String descriptografaBase64(String stringCodificada){

        if (stringCodificada != null){
            // Decodificando o CPF que vem na requisição
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(stringCodificada);
            String stringDecodificado = new String(decodedBytes);

            return stringDecodificado;
        } else{
            throw new IllegalArgumentException("String vazia!");
        }


    }
}

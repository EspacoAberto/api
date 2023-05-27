package espacoaberto.backend.service;
/*
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ServiceBase64 {
    public static void main(String[]args){
        System.out.println(descriptografaBase64("MQWEWRW"));

    }


    public static String descriptografaBase64(String stringCodificada){

        if (stringCodificada != null){
            // Decodificando o CPF que vem na requisição
            try {
                byte[] decodedBytes = java.util.Base64.getDecoder().decode(stringCodificada);
                String stringDecodificado = new String(decodedBytes);

                Integer.parseInt(stringDecodificado);
                return stringDecodificado;
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
            return null;
        } else{
            throw new IllegalArgumentException("String vazia!");
        }


    }

    public static String encriptografaBae64(Integer idDecodificado){

        if (idDecodificado != null){
            String idCodificado = Base64.getEncoder().encodeToString(idDecodificado.toString().getBytes());
            return idCodificado;
        }else{
            throw new IllegalArgumentException("Id vazio");
        }
    }
}*/

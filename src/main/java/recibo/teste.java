package recibo;

import espacoaberto.backend.entidades.Anuncio;
import lista.ListaObj;

import java.util.ArrayList;
import java.util.List;

public class teste {
    public static void main(String[] args) {
        Recibo a = new Recibo();

        List<Recibo> lista = new ArrayList();
        ListaObj<Recibo> l = new ListaObj<>(10);
        lista.add(new Recibo(1,"a","a","a",1,1,1,1.0, 777));
        lista.add(new Recibo(2,"b","b","b",2,2,2,2.0, 777));
        lista.add(new Recibo(3,"b","b","b",2,2,2,2.0, 777));

        l.adiciona(new Recibo(1,"a","a","a",1,1,1,1.0, 777));
        l.adiciona(new Recibo(2,"raffa moreira","a","a",1,1,1,1.0, 777));
       //    a.gravaReciboTxt(lista, "alunes.txt");
          // a.leReciboTxt("alunes.txt");

        // a.gravaReciboCsv(l,"recibo");
            a.leExibeReciboCsv("recibo");
    }
}

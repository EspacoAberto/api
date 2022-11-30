package recibo;

import lista.ListaObj;

import java.util.ArrayList;
import java.util.List;

public class teste {
    public static void main(String[] args) {
        ReciboTeste a = new ReciboTeste();

        List<ReciboTeste> lista = new ArrayList();
        ListaObj<ReciboTeste> l = new ListaObj<>(10);
        lista.add(new ReciboTeste(1,"a","a","a",1,1,1,1.0));
        lista.add(new ReciboTeste(2,"b","b","b",2,2,2,2.0));
        lista.add(new ReciboTeste(3,"c","b","b",3,3,3,3.0));

        l.adiciona(new ReciboTeste(1,"a","a","a",1,1,1,1.0));
//           a.gravaReciboTxt(lista, "alunes.txt");
//           a.leReciboTxt("alunes.txt");

          a.gravaReciboCsv(l,"recibo");
           a.leExibeReciboCsv("recibo");
    }
}

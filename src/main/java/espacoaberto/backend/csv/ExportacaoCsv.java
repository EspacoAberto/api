package espacoaberto.backend.csv;

import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.listaObj.ListaObj;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class ExportacaoCsv {
    public static void gravarArquivoCsvAnuncio(ListaObj<Anuncio> lista, String nomeArq){
        FileWriter arq = null; // Objeto que representa o arquivo de gravação
        Formatter saida = null; // Objeto que escreverá no arquivo
        Boolean deuRuim = false;
        nomeArq += ".csv"; // Acresenta extensão .csv ao arquivo

        // Bloco que abre o arquivo
        try {
            arq = new FileWriter(nomeArq); // Abrindo o arquivo
            saida = new Formatter(arq); // Cria o objeto saída associando ao arquivo
        } catch (IOException e){
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco que grava o arquivo
        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Anuncio a = lista.getElemento(i);
                saida.format("%d;%d;%d;%.2f;%s;%s;%d\n", a.getIdImovel(), a.getIdAnunciante(), a.getIdAnuncio(), a.getPreco(), a.getDescricao(), a.getTitulo(), a.getCurtidas());
            }
        } catch (FormatterClosedException e){
            System.out.println("Erro ao gravar arquivo");
            deuRuim = true;
        } finally{
            saida.close();
            try {
                arq.close();
            } catch (IOException e){
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }

            if(deuRuim) {
                System.exit(1);
            }

        }

    }


    public static void gravarArquivoCsvImovel(ListaObj<Imovel> lista, String nomeArq){
        FileWriter arq = null; // Objeto que representa o arquivo de gravação
        Formatter saida = null; // Objeto que escreverá no arquivo
        Boolean deuRuim = false;
        nomeArq += ".csv"; // Acresenta extensão .csv ao arquivo

        // Bloco que abre o arquivo
        try {
            arq = new FileWriter(nomeArq); // Abrindo o arquivo
            saida = new Formatter(arq); // Cria o objeto saída associando ao arquivo
        } catch (IOException e){
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco que grava o arquivo
        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Imovel Im = lista.getElemento(i);
                saida.format("%d;%s;%s;%d;%b;%d\n", Im.getId(), Im.getNome(), Im.getEndereco(), Im.getQtdQuartos(), Im.getPiscina(), Im.getAvaliacao());
            }
        } catch (FormatterClosedException e){
            System.out.println("Erro ao gravar arquivo");
            deuRuim = true;
        } finally{
            saida.close();
            try {
                arq.close();
            } catch (IOException e){
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }

            if(deuRuim) {
                System.exit(1);
            }

        }

    }
    }


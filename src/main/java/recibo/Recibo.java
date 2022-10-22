package recibo;

import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Imovel;
import lista.ListaObj;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Recibo {

    private Integer id;
    private String nome;
    private String endereco;
    private String descricao;
    private Integer qtdQuartos;
    private Integer curtidas;
    private Integer avaliacao;
    private Double preco;

    public Recibo(Integer id, String nome, String endereco, String descricao,
                    Integer qtdQuartos, Integer curtidas, Integer avaliacao, Double preco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.descricao = descricao;
        this.qtdQuartos = qtdQuartos;
        this.curtidas = curtidas;
        this.avaliacao = avaliacao;
        this.preco = preco;
    }

        public Recibo() {}

    public void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();
        }

        // try-catch para gravar e fechar o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }
    }

    public void gravaReciboTxt(List<Recibo> lista, String nomeArq) {
        int contaRegDados = 0;

        // Monta o registro de header
        String header = "00RECIBO2022";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";
        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Monta e grava os registros de corpo
        String corpo;
        for (Recibo a : lista) {
            corpo = "02";
            corpo += String.format("%05d", id);
            corpo += String.format("%-30.30s", nome);
            corpo += String.format("%-50.50s", endereco);
            corpo += String.format("%-50.50s", descricao);
            corpo += String.format("%02d", qtdQuartos);
            corpo += String.format("%04d", curtidas);
            corpo += String.format("%1d", avaliacao);
            corpo += String.format("%08.2f", preco);
            contaRegDados++;
            gravaRegistro(corpo, nomeArq);
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);
        gravaRegistro(trailer, nomeArq);
    }

    public void leReciboTxt(String nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome1, endereco1, descricao1;
        Integer id1, qtdQuartos1, curtidas1, avaliacao1;
        Double preco1;
        Integer contaRegDadoLido = 0;
        Integer qtdRegDadoGravadoTrailer;

        // Cria uma lista com os dados lidos do arquivo
        ListaObj<Recibo> listaLida = new ListaObj<>(100);

        // try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo!");
            erro.printStackTrace();
        }

        // try-catch para ler e fechar o arquivo
        try {
            registro = entrada.readLine();       // Lê o 1o registro

            while (registro != null) {
                // enquanto não chegou ao final do arquivo
                // obtém os 2 primeiros caracteres do registro
                // 01234567890
                // 00NOTA20222
                // substring - 1o argumento é o índice de onde quero obter
                // substring - 2o argumento é o índice de até onde quero + 1
                tipoRegistro = registro.substring(0,2);
                if (tipoRegistro.equals("00")) {
                    System.out.println("Registro de header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2,8));
                    System.out.println("Ano : " + registro.substring(8,12));
                    System.out.println("Data e hora de gravação: " + registro.substring(12,31));
                    System.out.println("Versão do documento: " + registro.substring(31,33));

                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("Registro de trailer");
                    qtdRegDadoGravadoTrailer = Integer.valueOf(registro.substring(2, 12));
                    if (contaRegDadoLido == qtdRegDadoGravadoTrailer) {
                        System.out.println("Quantidade de registros lidos compatível com " +
                                "quantidade de registros gravados");
                    }
                    else {
                        System.out.println("Quantidade de registros lidos incompatível com " +
                                "quantidade de registros gravados");
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    System.out.println("Registro de corpo");
                    id1 = Integer.valueOf(registro.substring(2,7));
                    nome1 = registro.substring(7, 37).trim();
                    endereco1 = registro.substring(37, 87).trim();
                    descricao1 = registro.substring(87, 137).trim();
                    qtdQuartos1 = Integer.valueOf(registro.substring(137,139));
                    curtidas1 = Integer.valueOf(registro.substring(139,143));
                    avaliacao1 = Integer.valueOf(registro.substring(143,144));
                    preco1 = Double.valueOf(registro.substring(144,152).replace(',','.'));

                    // Incrementa o contador de registros lidos
                    contaRegDadoLido++;

                    Recibo a = new Recibo(id1, nome1, endereco1, descricao1, qtdQuartos1, curtidas1, avaliacao1, preco1);

                    // No Projeto de PI
                    // repository.save(a);

                    // No nosso caso, vamos adicionar o objeto a à listaLida
                    listaLida.adiciona(a);
                }
                else {
                    System.out.println("Tipo de registro inválido!");
                }
                // Lê o próximo registro
                registro = entrada.readLine();
            }
            entrada.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }

        // Exibe o conteúdo da lista lida
        System.out.println("Conteúdo da lista lida do arquivo");
        for (int i = 0; i < listaLida.getTamanho(); i++) {
            Recibo s = listaLida.getElemento(i);
            System.out.println(s);
        }
    }

    public void gravaReciboCsv(ListaObj<Recibo> lista, String nomeArq){
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        nomeArq += ".csv";

        try{
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        }catch (IOException erro){
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try{
            for (int i = 0; i < lista.getTamanho(); i++){
                Recibo r = lista.getElemento(i);
                saida.format("%d;%s;%s;%s;%d;%d;%d;%.2f\n",
                        r.id,r.nome,r.endereco,r.descricao,r.qtdQuartos,r.curtidas,r.avaliacao,r.preco);
            }
        }catch (FormatterClosedException erro){
            System.out.println("Erro ao gravar arquivo!");
            deuRuim = true;
        }
        finally {
            saida.close();
            try{
                arq.close();
            }catch (IOException erro){
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim){
                System.exit(1);
            }
        }
    }

    public void leExibeReciboCsv(String nomeArq){
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;
        nomeArq += ".csv";

        try{
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        }catch (FileNotFoundException erro){
            System.out.println("Arquivo não encontrado");
            System.exit(1);
        }

        try {
            System.out.printf("%5S %-5s %-50s %-50s %7S, %8S, %9S, %5S",
                    "ID", "NOME", "ENDEREÇO", "DESCRIÇÃO", "QUARTOS", "CURTIDAS", "AVALIAÇÃO", "PREÇO\n");
            while (entrada.hasNext()){
                int id = entrada.nextInt();
                String nome = entrada.next();
                String endereco = entrada.next();
                String descricao = entrada.next();
                int quartos = entrada.nextInt();
                int curtidas = entrada.nextInt();
                int avaliacao = entrada.nextInt();
                Double preco = entrada.nextDouble();
                System.out.printf("%05d %-5s %-50s %-50s %02d, %08d, %1d, %08.2f\n",
                        id, nome,endereco,descricao,quartos,curtidas,avaliacao,preco);
            }
        }catch (NoSuchElementException erro){
            System.out.println("Arquivo com problemas!");
            deuRuim = true;
        }catch (IllegalStateException erro){
            System.out.println("Erro ao fechar arquivo");
            deuRuim = true;
        }finally {
            entrada.close();
            try{
                arq.close();
            }catch (IOException erro){
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }if(deuRuim){
                System.exit(1);
            }
        }
    }
    @Override
    public String toString() {
        return "Recibo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", descricao='" + descricao + '\'' +
                ", qtdQuartos=" + qtdQuartos +
                ", curtidas=" + curtidas +
                ", avaliacao=" + avaliacao +
                ", preco=" + preco +
                '}';
    }
}

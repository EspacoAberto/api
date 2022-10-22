package espacoaberto.backend.entidades;

import espacoaberto.backend.abstrato.Usuario;

import javax.persistence.*;

@Entity
public class Recibo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String endereco;
    private String descricao;
    private Integer qtdQuartos;
    private Integer curtidas;
    private Integer avaliacao;
    private Double preco;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="anuncio_id")
    private Anuncio anuncio;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getEndereco() {return endereco;}

    public void setEndereco(String endereco) {this.endereco = endereco;}

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {this.descricao = descricao;}

    public Integer getQtdQuartos() {return qtdQuartos;}

    public void setQtdQuartos(Integer qtdQuartos) {this.qtdQuartos = qtdQuartos;}

    public Integer getCurtidas() {return curtidas;}

    public void setCurtidas(Integer curtidas) {this.curtidas = curtidas;}

    public Integer getAvaliacao() {return avaliacao;}

    public void setAvaliacao(Integer avaliacao) {this.avaliacao = avaliacao;}

    public Double getPreco() {return preco;}

    public void setPreco(Double preco) {this.preco = preco;}

    public Anuncio getAnuncio() {return anuncio;}

    public void setAnuncio(Anuncio anuncio) {this.anuncio = anuncio;}

    public Cliente getCliente() {return cliente;}

    public void setCliente(Cliente cliente) {this.cliente = cliente;}
}

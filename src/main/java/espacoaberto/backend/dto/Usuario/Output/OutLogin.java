package espacoaberto.backend.dto.Usuario.Output;

public class OutLogin {
    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private Boolean isPremium;
    private String telefone;
    private String tipoUsuario;

    public OutLogin(Integer id
                   ,String nome
                   ,String email
                   ,String cpf
                   , String telefone
                   ,Boolean isPremium
                   ,String tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.isPremium = isPremium;
        this.tipoUsuario = tipoUsuario;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
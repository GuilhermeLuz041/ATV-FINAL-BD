package models;

public class Membro {
    
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String cpf;

    public Membro(String email, String endereco, int id, String nome, String telefone, String cpf) {
        this.email = email;
        this.endereco = endereco;
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
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
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

     @Override
    public String toString() {
        return "Membro{id=" + id + ", nome='" + nome + "', email='" + email + "', telefone='" + telefone + "', endereco='" + endereco + "', cpf='" + cpf + "'}";
    }
    
}

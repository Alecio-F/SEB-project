package seb.project.model;

public class Cliente {
    /* atributo de classes. A palavra-chave private indica que esses atributos 
    são privados e só podem ser acessados diretamente de dentro da própria classe, 
    garantindo o encapsulamento (controle de acesso aos dados). */
    private int id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
    private String email;
    private String telefone;
    
    //constructor usado para criar objetos das classes. Incluindo ID
    public Cliente(int id, String nome, String cnpj, String endereco, String cidade, String estado, String cep, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;
    }
    // Construtor sem o ID (quando o ID é gerado automaticamente)
    public Cliente(String nome, String cnpj, String endereco, String cidade, String estado, String cep, String email, String telefone) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;
    }
    
    /* Getters e Setters são métodos públicos que permitem acessar e modificar 
    os atributos privados da classe. Eles são importantes para garantir o 
    encapsulamento, permitindo que os dados sejam manipulados de forma controlada.*/
    // getId() retorna o valor do atributo
    public int getId() {
        return id;
    }
    //setId permiti modificar o valor do atributo
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
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
    
    // Método para exibir os dados (testes)
    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", endereco=" + endereco + 
               ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + 
               ", email=" + email + ", telefone=" + telefone + "]";
    }
}

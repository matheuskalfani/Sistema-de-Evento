package model;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String cidade;

    public Usuario(int id, String nome, String email, String cidade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCidade() { return cidade; }
}

package model;

import jakarta.persistence.*;

@Entity
@Table(name = "Empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 14, unique = true)
    private String cnpj;
    @Column(unique = true, length = 30)
    private String nome;
    @Column(length = 30)
    private String senha;

    public Empresa(){}

    public Empresa(String cnpj,String nome,String senha)
    {
        if(cnpj.length()<14)
            throw new IllegalArgumentException();
        else
            this.cnpj = cnpj;
        this.nome = nome;
        if(senha.length()<8)
            throw new IllegalArgumentException();
        else
            this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
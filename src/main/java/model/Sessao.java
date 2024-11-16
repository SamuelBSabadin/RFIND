package model;

import java.util.ArrayList;
import java.util.List;

public class Sessao //imitação da classe Empresa, porém é só para a sessão que está logada
{
    private int id;
    private String cnpj;
    private String nome;
    private String senha;
    private static List<Sessao> sessao = new ArrayList<>();

    public Sessao(int id, String cnpj, String nome, String senha){
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.senha = senha;
    }

    public Sessao(){}

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

    public static void setSessao(int id, String cnpj, String nome, String senha)
    {
        sessao.add(new Sessao(id,cnpj,nome,senha));
    }

    public static void limpaSessao(){
        for(Sessao s : sessao)
            sessao.remove(s);
    }

    public static List<Sessao> getSessao()
    {
        return sessao;
    }
}

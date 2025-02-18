package model;

import jakarta.persistence.*;

@Entity
@Table
public class Funcionario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 11, nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sobrenome;
    @Column(nullable = false)
    private String setor;
    @Column(nullable = false)
    private boolean ativado;
    @ManyToOne()//cascade = CascadeType.ALL
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    public Funcionario(){}

    public Funcionario(String cpf,String nome,String sobrenome,String setor, Empresa empresa)
    {
        if(cpf.length()<11)
            throw new IllegalArgumentException();
        else
            this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.setor = setor;
        this.empresa = empresa;
        this.ativado = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setAtivado(boolean ativado){
        this.ativado = ativado;
    }

    public boolean getAtivado(){
        return ativado;
    }
}
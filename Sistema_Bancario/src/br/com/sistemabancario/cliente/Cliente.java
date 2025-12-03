package br.com.sistemabancario.cliente;

public class Cliente {
    private String nome;
    private String cpf;

    // Construtor obrigatório
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void exibirDados() {
        System.out.println("Cliente: " + nome + " | CPF: " + cpf);
    }
}

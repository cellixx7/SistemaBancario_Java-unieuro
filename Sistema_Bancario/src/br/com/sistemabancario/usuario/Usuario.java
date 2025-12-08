package br.com.sistemabancario.usuario;

import br.com.sistemabancario.transacao.Transacao;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private String dataNascimento;
    private double saldo;

    private List<Transacao> historico = new ArrayList<>();

    public Usuario(String cpf, String nome, String email, String senha, String dataNascimento, double saldoInicial) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.saldo = saldoInicial;
    }

    /**
     * Operações financeiras
     */
    /**
     * Realiza um depósito no usuário.
     * Atualiza o saldo e adiciona uma transação de depósito ao histórico.
     * @param valor valor a ser depositado (espera-se valor > 0)
     */
    public void depositar(double valor) {
        saldo += valor;
        // registra que o destino (este usuário) recebeu o depósito
        historico.add(new Transacao("Depósito", valor, null, cpf));
    }

    /**
     * Método usado quando este usuário recebe uma transferência.
     * @param valor valor recebido
     * @param origemCpf CPF de quem enviou
     * @param origemNome Nome de quem enviou
     */
    public void receberTransferencia(double valor, String origemCpf, String origemNome) {
        saldo += valor;
        historico.add(new Transacao("Recebimento", valor, origemCpf + " - " + origemNome, cpf + " - " + nome));
    }

    /**
     * Transfere um valor deste usuário para outro usuário.
     * Verifica validade do valor e saldo suficiente antes de executar.
     * Registra a transação no histórico de ambos os usuários.
     * @param valor valor a transferir
     * @param destino usuário destino
     * @throws Exception se valor inválido ou saldo insuficiente
     */
    public void transferir(double valor, Usuario destino) throws Exception {
        if (valor <= 0) throw new Exception("Valor inválido.");
        if (saldo < valor) throw new Exception("Saldo insuficiente.");

        saldo -= valor;
        historico.add(new Transacao("Transferência", valor, cpf + " - " + nome, destino.getCpf() + " - " + destino.getNome()));
        destino.receberTransferencia(valor, cpf, nome);
    }

    // Getters
    public double getSaldo() { return saldo; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getNome() { return nome; }
    public String getDataNascimento() { return dataNascimento; }
    public List<Transacao> getHistorico() { return historico; }
    public String getSenha() { return senha; }
}

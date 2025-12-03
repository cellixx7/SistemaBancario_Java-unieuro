package br.com.sistemabancario.contas;

import br.com.sistemabancario.cliente.Cliente;
import br.com.sistemabancario.excecoes.*;


public abstract class Conta {

    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    // SOBRECARGA DE CONSTRUTOR (obrigatório)
    public Conta(int numero, Cliente cliente) {
        this.numero = numero;
        this.cliente = cliente;
    }

    public Conta(int numero, Cliente cliente, double saldoInicial) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = saldoInicial;
    }

    // Métodos gerais
    public void depositar(double valor) throws ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("Valor de depósito inválido.");
        }
        saldo += valor;
    }

    // Método abstrato (obrigatório para polimorfismo)
    public abstract void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException;

    // Sobrecarga de método (obrigatório)
    public void sacar(double valor, double taxa) throws SaldoInsuficienteException, ValorInvalidoException {
        sacar(valor + taxa);
    }

    public void exibirInformacoes() {
        System.out.println("Conta nº " + numero + " | Cliente: " + cliente.getNome() + " | Saldo: " + saldo);
    }

    public double getSaldo() {
        return saldo;
    }

    public int getNumero() {
        return numero;
    }
}

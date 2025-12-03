package br.com.sistemabancario.contas;

import br.com.sistemabancario.cliente.Cliente;
import br.com.sistemabancario.excecoes.*;

public class ContaCorrente extends Conta {

    private double taxaSaque = 2.5;

    public ContaCorrente(int numero, Cliente cliente) {
        super(numero, cliente);
    }

    public ContaCorrente(int numero, Cliente cliente, double saldoInicial) {
        super(numero, cliente, saldoInicial);
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("Valor de saque inválido.");
        }

        double total = valor + taxaSaque;

        if (total > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }

        saldo -= total;
    }
}

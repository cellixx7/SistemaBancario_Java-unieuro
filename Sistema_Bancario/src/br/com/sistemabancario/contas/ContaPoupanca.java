package br.com.sistemabancario.contas;

import br.com.sistemabancario.cliente.Cliente;
import br.com.sistemabancario.excecoes.*;

public class ContaPoupanca extends Conta {

    private double taxaRendimento = 0.02;

    public ContaPoupanca(int numero, Cliente cliente) {
        super(numero, cliente);
    }

    public ContaPoupanca(int numero, Cliente cliente, double saldoInicial) {
        super(numero, cliente, saldoInicial);
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("Valor de saque inválido.");
        }

        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }

        saldo -= valor;
    }

    public void aplicarRendimento() {
        saldo += saldo * taxaRendimento;
    }
}

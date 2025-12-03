package br.com.sistemabancario.banco;

import br.com.sistemabancario.contas.Conta;

import java.util.ArrayList;

public class Banco {

    private ArrayList<Conta> contas = new ArrayList<>();

    public void adicionarConta(Conta c) {
        contas.add(c);
    }

    public Conta buscarConta(int numero) {
        for (Conta c : contas) {
            if (c.getNumero() == numero) {
                return c;
            }
        }
        return null;
    }

    public void listarContas() {
        for (Conta c : contas) {
            c.exibirInformacoes();
        }
    }
}

package br.com.sistemabancario.ui;

import br.com.sistemabancario.banco.Banco;
import br.com.sistemabancario.cliente.Cliente;
import br.com.sistemabancario.contas.*;
import br.com.sistemabancario.excecoes.*;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {

    private Banco banco = new Banco();

    private JTextArea output;
    private JTextField campoNumero;
    private JTextField campoNome;
    private JTextField campoCpf;          // <-- ADICIONADO
    private JTextField campoSaldo;
    private JComboBox<String> tipoConta;

    private JTextField campoValor;
    private JTextField campoNumeroDestino;

    public MainUI() {
        setTitle("Sistema Bancário - Swing");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Painel superior
        JPanel painelCriacao = new JPanel(new GridLayout(7, 2, 5, 5));
        painelCriacao.setBorder(BorderFactory.createTitledBorder("Criar Conta"));

        campoNumero = new JTextField();
        campoNome = new JTextField();
        campoCpf = new JTextField();       // <-- ADICIONADO
        campoSaldo = new JTextField();

        tipoConta = new JComboBox<>(new String[]{"Conta Corrente", "Conta Poupança"});

        painelCriacao.add(new JLabel("Número da Conta:"));
        painelCriacao.add(campoNumero);

        painelCriacao.add(new JLabel("Nome do Cliente:"));
        painelCriacao.add(campoNome);

        painelCriacao.add(new JLabel("CPF:"));          // <-- ADICIONADO
        painelCriacao.add(campoCpf);                   // <-- ADICIONADO

        painelCriacao.add(new JLabel("Saldo Inicial (opcional):"));
        painelCriacao.add(campoSaldo);

        painelCriacao.add(new JLabel("Tipo:"));
        painelCriacao.add(tipoConta);

        JButton botaoCriar = new JButton("Criar Conta");
        botaoCriar.addActionListener(e -> criarConta());

        painelCriacao.add(new JLabel());
        painelCriacao.add(botaoCriar);

        add(painelCriacao, BorderLayout.NORTH);

        // Painel de operações
        JPanel painelOperacoes = new JPanel(new GridLayout(6, 2, 5, 5));
        painelOperacoes.setBorder(BorderFactory.createTitledBorder("Operações"));

        campoValor = new JTextField();
        campoNumeroDestino = new JTextField();

        JButton botaoDepositar = new JButton("Depositar");
        JButton botaoSacar = new JButton("Sacar");
        JButton botaoTransferir = new JButton("Transferir");
        JButton botaoListar = new JButton("Listar Contas");

        botaoDepositar.addActionListener(e -> depositar());
        botaoSacar.addActionListener(e -> sacar());
        botaoTransferir.addActionListener(e -> transferir());
        botaoListar.addActionListener(e -> listarContas());

        painelOperacoes.add(new JLabel("Valor:"));
        painelOperacoes.add(campoValor);

        painelOperacoes.add(new JLabel("Conta Destino (transferência):"));
        painelOperacoes.add(campoNumeroDestino);

        painelOperacoes.add(botaoDepositar);
        painelOperacoes.add(botaoSacar);

        painelOperacoes.add(botaoTransferir);
        painelOperacoes.add(botaoListar);

        add(painelOperacoes, BorderLayout.CENTER);

        // Área de saída
        output = new JTextArea();
        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(output), BorderLayout.SOUTH);

        setVisible(true);
    }

    // ====== MÉTODOS ======

    private void criarConta() {
        try {
            int numero = Integer.parseInt(campoNumero.getText());
            String nome = campoNome.getText();
            String cpf = campoCpf.getText();     // <-- ADICIONADO

            if (nome.isBlank() || cpf.isBlank()) {
                print("Nome ou CPF inválido.");
                return;
            }

            Cliente cliente = new Cliente(nome, cpf);   // <-- CORRIGIDO

            String tipo = (String) tipoConta.getSelectedItem();
            Conta conta;

            if (!campoSaldo.getText().isBlank()) {
                double saldo = Double.parseDouble(campoSaldo.getText());

                if (tipo.equals("Conta Corrente"))
                    conta = new ContaCorrente(numero, cliente, saldo);
                else
                    conta = new ContaPoupanca(numero, cliente, saldo);

            } else {
                if (tipo.equals("Conta Corrente"))
                    conta = new ContaCorrente(numero, cliente);
                else
                    conta = new ContaPoupanca(numero, cliente);
            }

            banco.adicionarConta(conta);
            print("Conta criada com sucesso!");

        } catch (Exception e) {
            print("Erro ao criar conta: " + e.getMessage());
        }
    }

    private void depositar() {
        try {
            int numero = Integer.parseInt(campoNumero.getText());
            double valor = Double.parseDouble(campoValor.getText());

            Conta conta = banco.buscarConta(numero);

            if (conta == null) {
                print("Conta não encontrada.");
                return;
            }

            conta.depositar(valor);
            print("Depósito realizado. Novo saldo: " + conta.getSaldo());

        } catch (Exception e) {
            print("Erro ao depositar: " + e.getMessage());
        }
    }

    private void sacar() {
        try {
            int numero = Integer.parseInt(campoNumero.getText());
            double valor = Double.parseDouble(campoValor.getText());

            Conta conta = banco.buscarConta(numero);

            if (conta == null) {
                print("Conta não encontrada.");
                return;
            }

            conta.sacar(valor);
            print("Saque realizado. Novo saldo: " + conta.getSaldo());

        } catch (Exception e) {
            print("Erro ao sacar: " + e.getMessage());
        }
    }

    private void transferir() {
        try {
            int origemNum = Integer.parseInt(campoNumero.getText());
            int destinoNum = Integer.parseInt(campoNumeroDestino.getText());
            double valor = Double.parseDouble(campoValor.getText());

            Conta origem = banco.buscarConta(origemNum);
            Conta destino = banco.buscarConta(destinoNum);

            if (origem == null || destino == null) {
                print("Conta(s) não encontrada(s).");
                return;
            }

            origem.sacar(valor);
            destino.depositar(valor);

            print("Transferência concluída.");

        } catch (Exception e) {
            print("Erro na transferência: " + e.getMessage());
        }
    }

    private void listarContas() {
        output.setText("");
        print("=== Lista de Contas ===");

        banco.listarContas();
    }

    private void print(String msg) {
        output.append(msg + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainUI::new);
    }
}


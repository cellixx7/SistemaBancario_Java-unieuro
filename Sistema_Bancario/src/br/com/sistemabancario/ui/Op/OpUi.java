package br.com.sistemabancario.ui.Op;

import br.com.sistemabancario.banco.Banco;
import br.com.sistemabancario.usuario.Usuario;

import javax.swing.*;
import java.awt.*;

/**
 * Janela de operações bancárias: depósito e transferência.
 * Recebe o banco, o usuário logado e um callback para atualizar o saldo
 * exibido na tela principal quando uma operação for concluída.
 */
public class OpUi extends JFrame {

    private Banco banco;
    private Usuario usuario;
    private Runnable atualizarSaldoCallback;

    private JComboBox<String> tipoOperacao;
    private JTextField campoValor;
    private JTextField campoCpfDestino;

    public OpUi(Banco banco, Usuario usuario, Runnable atualizarSaldoCallback) {
        this.banco = banco;
        this.usuario = usuario;
        this.atualizarSaldoCallback = atualizarSaldoCallback;

        setTitle("Operações Bancárias");
        setSize(420, 320);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblOperacao = new JLabel("Operação:");
        tipoOperacao = new JComboBox<>(new String[]{"Depósito", "Transferência"});
        tipoOperacao.addActionListener(e -> atualizarModo());

        JLabel lblValor = new JLabel("Valor:");
        campoValor = new JTextField();

        JLabel lblCpfDestino = new JLabel("CPF destino (somente transferência):");
        campoCpfDestino = new JTextField();

        JButton executar = new JButton("Executar");
        executar.setBackground(new Color(0, 153, 51));
        executar.setForeground(Color.WHITE);
        executar.setFocusPainted(false);
        executar.addActionListener(e -> executarOperacao());

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y++;
        painel.add(lblOperacao, gbc);
        gbc.gridy = y++;
        painel.add(tipoOperacao, gbc);
        gbc.gridy = y++;
        painel.add(lblValor, gbc);
        gbc.gridy = y++;
        painel.add(campoValor, gbc);
        gbc.gridy = y++;
        painel.add(lblCpfDestino, gbc);
        gbc.gridy = y++;
        painel.add(campoCpfDestino, gbc);
        gbc.gridy = y++;
        painel.add(executar, gbc);

        add(painel, BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setBackground(new Color(204, 0, 0));
        btnFechar.setForeground(Color.WHITE);
        btnFechar.setFocusPainted(false);
        btnFechar.addActionListener(e -> dispose());
        JPanel rodape = new JPanel();
        rodape.add(btnFechar);
        add(rodape, BorderLayout.SOUTH);

        atualizarModo();
        setVisible(true);
    }

    private void atualizarModo() {
        boolean transferencia = tipoOperacao.getSelectedItem().equals("Transferência");
        campoCpfDestino.setEnabled(transferencia);
        if (!transferencia) campoCpfDestino.setText("");
    }

    private void executarOperacao() {
        String valorTexto = campoValor.getText().trim();
        String tipo = (String) tipoOperacao.getSelectedItem();

        if (valorTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o valor.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double valor;
        try {
            valor = Double.parseDouble(valorTexto);
            if (valor <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor deve ser um número maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (tipo.equals("Depósito")) {
            try {
                usuario.depositar(valor);
                JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                campoValor.setText("");
                if (atualizarSaldoCallback != null) atualizarSaldoCallback.run();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao depositar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            String cpfDestino = campoCpfDestino.getText().trim();
            if (cpfDestino.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o CPF do destinatário.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                banco.transferir(usuario.getCpf(), cpfDestino, valor);
                JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                campoValor.setText("");
                campoCpfDestino.setText("");
                if (atualizarSaldoCallback != null) atualizarSaldoCallback.run();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro na transferência: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
    
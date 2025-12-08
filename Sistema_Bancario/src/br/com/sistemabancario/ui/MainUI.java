package br.com.sistemabancario.ui;

import br.com.sistemabancario.banco.Banco;
import br.com.sistemabancario.usuario.Usuario;
import br.com.sistemabancario.ui.Op.OpUi;
import br.com.sistemabancario.ui.historico.HistoricoUi;
import br.com.sistemabancario.ui.login.LoginUI;

import javax.swing.*;
import java.awt.*;

/**
 * Interface principal (home) exibida após login.
 * Mostra o saldo do usuário, permite abrir operações, histórico e sair.
 */
public class MainUI extends JFrame {

    private Banco banco;
    private Usuario usuario;

    private JLabel labelSaldo;

    public MainUI(Banco banco, Usuario usuario) {
        this.banco = banco;
        this.usuario = usuario;

        setTitle("Home - Sistema Bancário");
        setSize(420, 370);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Bem-vindo ao Sistema Bancário", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        labelSaldo = new JLabel("Saldo atual: R$ " + usuario.getSaldo());
        labelSaldo.setHorizontalAlignment(SwingConstants.CENTER);
        labelSaldo.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton btnOp = new JButton("Operações");
        btnOp.setBackground(new Color(0, 153, 51));
        btnOp.setForeground(Color.WHITE);
        btnOp.setFocusPainted(false);
        btnOp.addActionListener(e -> {
            try {
                new br.com.sistemabancario.ui.Op.OpUi(banco, usuario, this::atualizarSaldo);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao abrir operações: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnHist = new JButton("Histórico");
        btnHist.setBackground(new Color(0, 102, 204));
        btnHist.setForeground(Color.WHITE);
        btnHist.setFocusPainted(false);
        btnHist.addActionListener(e -> {
            try {
                new HistoricoUi(usuario);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao abrir histórico: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnLogout = new JButton("Sair");
        btnLogout.setBackground(new Color(204, 0, 0));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginUI(banco);
        });

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y++;
        painel.add(labelSaldo, gbc);
        gbc.gridy = y++;
        painel.add(btnOp, gbc);
        gbc.gridy = y++;
        painel.add(btnHist, gbc);
        gbc.gridy = y++;
        painel.add(btnLogout, gbc);

        add(painel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void atualizarSaldo() {
        labelSaldo.setText("Saldo atual: R$ " + usuario.getSaldo());
    }
}

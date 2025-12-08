package br.com.sistemabancario.ui.login;

import br.com.sistemabancario.banco.Banco;
import br.com.sistemabancario.usuario.Usuario;
import br.com.sistemabancario.ui.MainUI;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {

    private Banco banco;

    private JTextField campoEmailLogin;
    private JPasswordField campoSenhaLogin;

    private JTextField campoCpfCadastro;
    private JTextField campoNomeCadastro;
    private JTextField campoDataNascimentoCadastro;
    private JTextField campoEmailCadastro;
    private JPasswordField campoSenhaCadastro;
    private JTextField campoSaldoInicialCadastro;

    /**
     * Janela de login e cadastro de usuários.
     * Recebe uma instância de `Banco` para realizar autenticação e cadastro.
     */
    public LoginUI(Banco banco) {
        this.banco = banco;

        setTitle("Login / Cadastro");
        setSize(420, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Sistema Bancário", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JTabbedPane abas = new JTabbedPane();
        abas.add("Login", criarAbaLogin());
        abas.add("Cadastro", criarAbaCadastro());

        add(abas, BorderLayout.CENTER);
        setVisible(true);
    }
    


    private JPanel criarAbaLogin() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        campoEmailLogin = new JTextField();
        campoSenhaLogin = new JPasswordField();

        JLabel lblEmail = new JLabel("Email:");
        JLabel lblSenha = new JLabel("Senha:");

        JButton btnLogin = new JButton("Entrar");
        btnLogin.setBackground(new Color(0, 153, 51));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(e -> login());

        gbc.gridx = 0; gbc.gridy = 0;
        p.add(lblEmail, gbc);
        gbc.gridy++;
        p.add(campoEmailLogin, gbc);
        gbc.gridy++;
        p.add(lblSenha, gbc);
        gbc.gridy++;
        p.add(campoSenhaLogin, gbc);
        gbc.gridy++;
        p.add(btnLogin, gbc);

        return p;
    }

    private JPanel criarAbaCadastro() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        campoCpfCadastro = new JTextField();
        campoNomeCadastro = new JTextField();
        campoDataNascimentoCadastro = new JTextField();
        campoEmailCadastro = new JTextField();
        campoSenhaCadastro = new JPasswordField();
        campoSaldoInicialCadastro = new JTextField();

        JLabel lblCpf = new JLabel("CPF:");
        JLabel lblNome = new JLabel("Nome completo:");
        JLabel lblData = new JLabel("Data de nascimento:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblSenha = new JLabel("Senha:");
        JLabel lblSaldo = new JLabel("Depósito inicial:");

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(0, 102, 204));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.addActionListener(e -> cadastrar());

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y++;
        p.add(lblCpf, gbc);
        gbc.gridy = y++;
        p.add(campoCpfCadastro, gbc);
        gbc.gridy = y++;
        p.add(lblNome, gbc);
        gbc.gridy = y++;
        p.add(campoNomeCadastro, gbc);
        gbc.gridy = y++;
        p.add(lblData, gbc);
        gbc.gridy = y++;
        p.add(campoDataNascimentoCadastro, gbc);
        gbc.gridy = y++;
        p.add(lblEmail, gbc);
        gbc.gridy = y++;
        p.add(campoEmailCadastro, gbc);
        gbc.gridy = y++;
        p.add(lblSenha, gbc);
        gbc.gridy = y++;
        p.add(campoSenhaCadastro, gbc);
        gbc.gridy = y++;
        p.add(lblSaldo, gbc);
        gbc.gridy = y++;
        p.add(campoSaldoInicialCadastro, gbc);
        gbc.gridy = y++;
        p.add(btnCadastrar, gbc);

        return p;
    }

    private void login() {
        String email = campoEmailLogin.getText().trim();
        String senha = new String(campoSenhaLogin.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario u = null;
        try {
            u = banco.login(email, senha);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao tentar login: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (u == null) {
            JOptionPane.showMessageDialog(this, "Email ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new MainUI(banco, u);
        dispose();
    }

private void cadastrar() {
    String cpf = campoCpfCadastro.getText().trim();
    String nome = campoNomeCadastro.getText().trim();
    String dataNasc = campoDataNascimentoCadastro.getText().trim();
    String email = campoEmailCadastro.getText().trim();
    String senha = new String(campoSenhaCadastro.getPassword());
    String saldoStr = campoSaldoInicialCadastro.getText().trim();

    if (cpf.isEmpty() || nome.isEmpty() || dataNasc.isEmpty() || email.isEmpty() || senha.isEmpty() || saldoStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }

    double saldoInicial;
    try {
        saldoInicial = Double.parseDouble(saldoStr);
        if (saldoInicial < 0) throw new NumberFormatException("O saldo inicial não pode ser negativo.");
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Informe um valor válido para o saldo inicial.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        Usuario u = new Usuario(
                cpf,
                nome,
                email,
                senha,
                dataNasc,
                saldoInicial
        );

        if (!banco.cadastrarUsuario(u)) {
            JOptionPane.showMessageDialog(this, "Email ou CPF já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro no cadastro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}}



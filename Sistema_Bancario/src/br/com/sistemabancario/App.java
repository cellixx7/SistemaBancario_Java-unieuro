package br.com.sistemabancario;

import br.com.sistemabancario.banco.Banco;
import br.com.sistemabancario.ui.login.LoginUI;

public class App {
    public static void main(String[] args) {

        Banco banco = new Banco();

        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginUI(banco);
        });
    }
}

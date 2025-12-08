package br.com.sistemabancario.main;

/**
 * Ponto de entrada da aplicação. Inicializa a interface gráfica (Swing)
 * e cria uma instância do banco em memória.
 */
public class Main {
    public static void main(String[] args) {
        // Inicia a UI na thread de evento do Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            br.com.sistemabancario.banco.Banco banco = new br.com.sistemabancario.banco.Banco();
            new br.com.sistemabancario.ui.login.LoginUI(banco);
        });
    }
}


package br.com.sistemabancario.ui.historico;

import br.com.sistemabancario.usuario.Usuario;

import javax.swing.*;
import java.awt.*;

public class HistoricoUi extends JFrame {

    /**
     * Janela que exibe o histórico de transações do usuário.
     * Construtor monta a interface e preenche o conteúdo com o histórico.
     * @param usuario usuário cujo histórico será exibido
     */
    public HistoricoUi(Usuario usuario) {
        setTitle("Histórico de Transações");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topo = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Histórico de Transações", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        topo.add(titulo, BorderLayout.CENTER);
        add(topo, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));

        StringBuilder texto = new StringBuilder();
        try {
            if (usuario.getHistorico() == null || usuario.getHistorico().isEmpty()) {
                texto.append("Nenhuma transação encontrada.");
            } else {
                usuario.getHistorico().forEach(h -> {
                    // cada Transacao.toString() já está formatado
                    texto.append(h.toString()).append("\n");
                });
            }
        } catch (Exception e) {
            texto.append("Erro ao carregar histórico: ").append(e.getMessage());
        }

        area.setText(texto.toString());

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scroll, BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        JPanel rodape = new JPanel();
        rodape.add(btnFechar);
        add(rodape, BorderLayout.SOUTH);

        setVisible(true);
    }
}

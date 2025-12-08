package br.com.sistemabancario.transacao;

import java.time.LocalDateTime;

/**
 * Representa uma transação financeira realizada por ou para um usuário.
 * Contém tipo, valor, data e informações opcionais de origem/destino
 * para transferências.
 */
public class Transacao {

    /** Tipo da transação (e.g. "Depósito", "Transferência", "Recebimento") */
    private String tipo;
    /** Valor monetário da transação */
    private double valor;
    /** Data/hora da transação */
    private LocalDateTime data;
    /** Identificador (CPF - nome) da origem, quando aplicável */
    private String origem;
    /** Identificador (CPF - nome) do destino, quando aplicável */
    private String destino;

    /**
     * Construtor básico para transações sem origem/destino (ex.: depósito).
     * @param tipo tipo de transação
     * @param valor valor da transação
     */
    public Transacao(String tipo, double valor) {
        this(tipo, valor, null, null);
    }

    /**
     * Construtor completo que permite informar origem e destino.
     * Usado em transferências para registrar quem enviou e quem recebeu.
     * @param tipo tipo da transação
     * @param valor valor da transação
     * @param origem identificador da origem (ex: "cpf - nome")
     * @param destino identificador do destino (ex: "cpf - nome")
     */
    public Transacao(String tipo, double valor, String origem, String destino) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = LocalDateTime.now();
        this.origem = origem;
        this.destino = destino;
    }

    /**
     * Retorna uma representação legível da transação contendo data, tipo,
     * origem/destino (quando houver) e valor formatado.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(data.toString().replace('T', ' ')).append(" | ");
        sb.append(tipo);
        if (origem != null) sb.append(" | Origem: ").append(origem);
        if (destino != null) sb.append(" | Destino: ").append(destino);
        sb.append(" | Valor: R$ ").append(String.format("%.2f", valor));
        return sb.toString();
    }

    /**
     * Imprime a transação no console (útil para debug).
     */
    public void exibir() {
        System.out.println(this.toString());
    }
}

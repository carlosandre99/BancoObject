import java.util.Date;

/**
 * A classe Operacao representa uma operação financeira realizada em uma conta bancária.
 * Cada operação possui uma data, um valor e um tipo que descreve a natureza da operação,
 * como depósito, saque, ou transferência PIX.
 */
public class Operacao {
    private Date data;
    private double valor;
    private String tipo;

    /**
     * Construtor para a classe Operacao.
     *
     * @param data  A data em que a operação foi realizada.
     * @param valor O valor da operação.
     * @param tipo  O tipo da operação (e.g., "Depósito", "Saque", "PIX In", "PIX Out").
     */
    public Operacao(Date data, double valor, String tipo) {
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }

    /**
     * Obtém a data da operação.
     *
     * @return A data da operação.
     */
    public Date getData() {
        return data;
    }

    /**
     * Obtém o valor da operação.
     *
     * @return O valor da operação.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Obtém o tipo da operação.
     *
     * @return O tipo da operação.
     */
    public String getTipo() {
        return tipo;
    }
}
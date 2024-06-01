import java.util.ArrayList;
import java.util.Date;

/**
 * A classe abstrata Conta representa uma conta bancária genérica com operações básicas
 * como depósito e saque. Esta classe serve como base para tipos específicos de contas
 * bancárias, como ContaCorrente e ContaPoupanca.
 */
public abstract class Conta {
    protected String numero;
    protected String nomeCorrentista;
    protected String cpfCorrentista;
    protected ArrayList<Operacao> operacoes;
    protected double saldo;

    /**
     * Construtor para a classe Conta.
     *
     * @param numero           O número da conta.
     * @param nomeCorrentista  O nome do correntista.
     * @param cpfCorrentista   O CPF do correntista.
     */
    public Conta(String numero, String nomeCorrentista, String cpfCorrentista) {
        this.numero = numero;
        this.nomeCorrentista = nomeCorrentista;
        this.cpfCorrentista = cpfCorrentista;
        this.operacoes = new ArrayList<>();
        this.saldo = 0.0;
    }

    /**
     * Efetua um depósito na conta.
     *
     * @param valor O valor a ser depositado.
     */
    public void depositar(double valor) {
        this.saldo += valor;
        this.operacoes.add(new Operacao(new Date(), valor, "Depósito"));
    }

    /**
     * Efetua um saque na conta, se houver saldo suficiente.
     *
     * @param valor O valor a ser sacado.
     */
    public void sacar(double valor) {
        if (valor <= this.saldo) {
            this.saldo -= valor;
            this.operacoes.add(new Operacao(new Date(), -valor, "Saque"));
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    /**
     * Obtém o número da conta.
     *
     * @return O número da conta.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Obtém o saldo atual da conta.
     *
     * @return O saldo da conta.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Obtém a lista de operações realizadas na conta.
     *
     * @return A lista de operações.
     */
    public ArrayList<Operacao> getOperacoes() {
        return operacoes;
    }

    /**
     * Obtém o CPF do correntista.
     *
     * @return O CPF do correntista.
     */
    public String getCpfCorrentista() {
        return cpfCorrentista;
    }
}
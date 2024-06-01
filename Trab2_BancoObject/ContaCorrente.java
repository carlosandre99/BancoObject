import java.util.Date;
import java.util.HashSet;

/**
 * A classe ContaCorrente representa uma conta corrente em um banco. Esta classe
 * estende a classe abstrata {@link Conta} e implementa a interface {@link Pix},
 * permitindo operações específicas de uma conta corrente, incluindo a funcionalidade
 * de transações PIX.
 */
public class ContaCorrente extends Conta implements Pix {
    private static HashSet<String> usuariosPix = new HashSet<>();

    /**
     * Construtor para a classe ContaCorrente.
     *
     * @param numero           O número da conta.
     * @param nomeCorrentista  O nome do correntista.
     * @param cpfCorrentista   O CPF do correntista.
     */
    public ContaCorrente(String numero, String nomeCorrentista, String cpfCorrentista) {
        super(numero, nomeCorrentista, cpfCorrentista);
    }

    /**
     * Cadastra uma chave PIX para o CPF do correntista.
     *
     * @param cpf O CPF do correntista a ser cadastrado como chave PIX.
     */
    @Override
    public void cadastrarPix(String cpf) {
        usuariosPix.add(cpf);
    }

    /**
     * Efetua uma transferência PIX para um destinatário.
     *
     * @param cpfDestino O CPF do destinatário da transferência PIX.
     * @param valor      O valor a ser transferido.
     */
    @Override
    public void efetuarPix(String cpfDestino, double valor) {
        if (usuariosPix.contains(cpfDestino) && valor <= this.saldo) {
            this.saldo -= valor;
            this.operacoes.add(new Operacao(new Date(), -valor, "PIX Out"));
        } else {
            System.out.println("Falha na operação PIX.");
        }
    }

    /**
     * Recebe uma transferência PIX de um remetente.
     *
     * @param cpfOrigem O CPF do remetente da transferência PIX.
     * @param valor     O valor a ser recebido.
     */
    @Override
    public void receberPix(String cpfOrigem, double valor) {
        this.saldo += valor;
        this.operacoes.add(new Operacao(new Date(), valor, "PIX In"));
    }
}
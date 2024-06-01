import java.util.Date;

/**
 * A classe ContaPoupanca representa uma conta poupança em um banco. Esta classe
 * estende a classe abstrata {@link Conta} e implementa a interface {@link Remunerada},
 * permitindo a aplicação de correções monetárias ao saldo da conta.
 */
public class ContaPoupanca extends Conta implements Remunerada {

    /**
     * Construtor para a classe ContaPoupanca.
     *
     * @param numero           O número da conta.
     * @param nomeCorrentista  O nome do correntista.
     * @param cpfCorrentista   O CPF do correntista.
     */
    public ContaPoupanca(String numero, String nomeCorrentista, String cpfCorrentista) {
        super(numero, nomeCorrentista, cpfCorrentista);
    }

    /**
     * Aplica uma correção monetária ao saldo da conta com base em uma taxa percentual.
     *
     * @param taxa A taxa de correção a ser aplicada, expressa em porcentagem.
     */
    @Override
    public void aplicarCorrecao(double taxa) {
        double correcao = this.saldo * (taxa / 100);
        this.saldo += correcao;
        this.operacoes.add(new Operacao(new Date(), correcao, "Correção Monetária"));
    }
}
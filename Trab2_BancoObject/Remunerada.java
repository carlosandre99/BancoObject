/**
 * A interface Remunerada define um método para aplicar correções monetárias ao saldo de uma conta.
 * Implementações desta interface permitem que uma conta seja remunerada com base em uma taxa de correção.
 */
public interface Remunerada {

    /**
     * Aplica uma correção monetária ao saldo da conta com base em uma taxa percentual.
     *
     * @param taxa A taxa de correção a ser aplicada, expressa em porcentagem.
     */
    void aplicarCorrecao(double taxa);
}
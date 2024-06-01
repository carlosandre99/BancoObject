/**
 * A interface Pix define os métodos relacionados às operações do sistema de pagamento instantâneo PIX.
 * Implementações desta interface permitem cadastrar chaves PIX e realizar transferências PIX entre contas.
 */
public interface Pix {

    /**
     * Cadastra uma chave PIX associada a um CPF.
     *
     * @param cpf O CPF que será cadastrado como chave PIX.
     */
    void cadastrarPix(String cpf);

    /**
     * Efetua uma transferência PIX para um destinatário.
     *
     * @param cpfDestino O CPF do destinatário que receberá a transferência PIX.
     * @param valor      O valor a ser transferido.
     */
    void efetuarPix(String cpfDestino, double valor);

    /**
     * Recebe uma transferência PIX de um remetente.
     *
     * @param cpfOrigem O CPF do remetente que está realizando a transferência PIX.
     * @param valor     O valor a ser recebido.
     */
    void receberPix(String cpfOrigem, double valor);
}
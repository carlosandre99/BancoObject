import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * A classe Banco representa um sistema bancário simples que permite criar contas,
 * efetuar depósitos, saques, transferências via PIX, e consultar extratos.
 */
public class Banco {
    private static ArrayList<Conta> contas = new ArrayList<>();
    private static HashSet<String> usuariosPix = new HashSet<>();

    /**
     * Método principal que inicializa o sistema bancário e apresenta um menu de opções ao usuário.
     *
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1) Criar conta corrente");
            System.out.println("2) Criar conta poupança");
            System.out.println("3) Efetuar depósito");
            System.out.println("4) Efetuar saque");
            System.out.println("5) Aplicar correção");
            System.out.println("6) Cadastrar PIX");
            System.out.println("7) Efetuar PIX");
            System.out.println("8) Consultar extrato");
            System.out.println("9) Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1:
                    criarContaCorrente(scanner);
                    break;
                case 2:
                    criarContaPoupanca(scanner);
                    break;
                case 3:
                    efetuarDeposito(scanner);
                    break;
                case 4:
                    efetuarSaque(scanner);
                    break;
                case 5:
                    aplicarCorrecao(scanner);
                    break;
                case 6:
                    cadastrarPix(scanner);
                    break;
                case 7:
                    efetuarPix(scanner);
                    break;
                case 8:
                    consultarExtrato(scanner);
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 9);

        scanner.close();
    }

    /**
     * Cria uma nova conta corrente e adiciona à lista de contas.
     *
     * @param scanner Scanner para leitura de dados do usuário.
     */
    private static void criarContaCorrente(Scanner scanner) {
        System.out.print("Número da conta: ");
        String numero = scanner.nextLine();
        System.out.print("Nome do correntista: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do correntista: ");
        String cpf = scanner.nextLine();
        ContaCorrente conta = new ContaCorrente(numero, nome, cpf);
        contas.add(conta);
        System.out.println("Conta corrente criada com sucesso.");
    }

    /**
     * Cria uma nova conta poupança e adiciona à lista de contas.
     *
     * @param scanner Scanner para leitura de dados do usuário.
     */
    private static void criarContaPoupanca(Scanner scanner) {
        System.out.print("Número da conta: ");
        String numero = scanner.nextLine();
        System.out.print("Nome do correntista: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do correntista: ");
        String cpf = scanner.nextLine();
        ContaPoupanca conta = new ContaPoupanca(numero, nome, cpf);
        contas.add(conta);
        System.out.println("Conta poupança criada com sucesso.");
    }

    /**
     * Efetua um depósito em uma conta existente.
     *
     * @param scanner Scanner para leitura de dados do usuário.
     */
    private static void efetuarDeposito(Scanner scanner) {
        System.out.print("Número da conta: ");
        String numero = scanner.nextLine();
        Conta conta = buscarConta(numero);
        if (conta != null) {
            System.out.print("Valor do depósito: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();  // Consumir a nova linha
            conta.depositar(valor);
            System.out.println("Depósito efetuado com sucesso.");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    /**
     * Efetua um saque em uma conta existente.
     *
     * @param scanner Scanner para leitura de dados do usuário.
     */
    private static void efetuarSaque(Scanner scanner) {
        System.out.print("Número da conta: ");
        String numero = scanner.nextLine();
        Conta conta = buscarConta(numero);
        if (conta != null) {
            System.out.print("Valor do saque: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();  // Consumir a nova linha
            conta.sacar(valor);
            System.out.println("Saque efetuado com sucesso.");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    /**
     * Aplica uma correção nas contas poupança existentes.
     *
     * @param scanner Scanner para leitura de dados do usuário.
     */
    private static void aplicarCorrecao(Scanner scanner) {
        System.out.print("Taxa de correção (%): ");
        double taxa = scanner.nextDouble();
        scanner.nextLine();  // Consumir a nova linha
        for (Conta conta : contas) {
            if (conta instanceof ContaPoupanca) {
                ((ContaPoupanca) conta).aplicarCorrecao(taxa);
            }
        }
        System.out.println("Correção aplicada com sucesso.");
    }

    /**
     * Cadastra uma chave PIX para uma conta corrente.
     *
     * @param scanner Scanner para leitura de dados do usuário.
     */
    private static void cadastrarPix(Scanner scanner) {
        System.out.print("CPF do correntista: ");
        String cpf = scanner.nextLine();
        Conta conta = buscarContaPorCpf(cpf);
        if (conta != null && conta instanceof ContaCorrente) {
            ((ContaCorrente) conta).cadastrarPix(cpf);
            usuariosPix.add(cpf);
            System.out.println("PIX cadastrado com sucesso.");
        } else {
            System.out.println("Conta corrente não encontrada ou CPF não corresponde a uma conta corrente.");
        }
    }

    /**
     * Efetua uma transferência PIX entre contas.
     *
     * @param scanner Scanner para leitura de dados do usuário.
     */
    private static void efetuarPix(Scanner scanner) {
        System.out.print("CPF do destinatário: ");
        String cpfDestino = scanner.nextLine();
        System.out.print("Número da conta de origem: ");
        String numero = scanner.nextLine();
        Conta contaOrigem = buscarConta(numero);
        Conta contaDestino = buscarContaPorCpf(cpfDestino);
        if (contaOrigem != null && contaOrigem instanceof ContaCorrente && usuariosPix.contains(cpfDestino)) {
            System.out.print("Valor do PIX: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();  // Consumir a nova linha
            ((ContaCorrente) contaOrigem).efetuarPix(cpfDestino, valor);
            if (contaDestino != null && contaDestino instanceof ContaCorrente) {
                ((ContaCorrente) contaDestino).receberPix(contaOrigem.getCpfCorrentista(), valor);
            }
            System.out.println("PIX efetuado com sucesso.");
        } else {
            System.out.println("Conta de origem não encontrada ou CPF do destinatário não está cadastrado no PIX.");
        }
    }

    /**
     * Consulta e imprime o extrato de uma conta.
     *
     * @param scanner Scanner para leitura de dados do usuário.
     */
    private static void consultarExtrato(Scanner scanner) {
        System.out.print("Número da conta: ");
        String numero = scanner.nextLine();
        Conta conta = buscarConta(numero);
        if (conta != null) {
            System.out.println("Extrato da conta número: " + numero);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (Operacao operacao : conta.getOperacoes()) {
                LocalDate data = operacao.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                System.out.println(data.format(formatter) + " - " + operacao.getTipo() + " - R$ " + operacao.getValor());
            }
            System.out.println("Saldo atual: R$ " + conta.getSaldo());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    /**
     * Busca uma conta pelo número.
     *
     * @param numero Número da conta.
     * @return A conta correspondente ao número ou null se não encontrada.
     */
    private static Conta buscarConta(String numero) {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                return conta;
            }
        }
        return null;
    }

    /**
     * Busca uma conta pelo CPF do correntista.
     *
     * @param cpf CPF do correntista.
     * @return A conta correspondente ao CPF ou null se não encontrada.
     */
    private static Conta buscarContaPorCpf(String cpf) {
        for (Conta conta : contas) {
            if (conta.getCpfCorrentista().equals(cpf)) {return conta;
            }
        }
        return null;
    }
}
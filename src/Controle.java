import java.util.Scanner;
public class Controle {
    public int menu(){
        Scanner ler = new Scanner(System.in);

        System.out.println("Escolha uma opçao: ");
        System.out.println("(1) - Criar Conta");
        System.out.println("(2) - Exibir Extrato");
        System.out.println("(3) - Sacar");
        System.out.println("(4) - Depositar");
        System.out.println("(5) - Transferir");
        System.out.println("(6) - Listar Contas");
        System.out.println("(7) - Sair");

        return ler.nextInt();
    }

    public void criarConta(Banco banco){
        Conta novaConta;
        Scanner lerTipoConta = new Scanner(System.in);
        Scanner lerNome = new Scanner(System.in);

        System.out.println("Escolha o tipo da conta:\n(1) - Conta Corrente\n(2) - Conta Poupança");
        int tipoConta = lerTipoConta.nextInt();

        System.out.println("\nInforme o nome do titular: ");
        String nome = lerNome.nextLine();

        Cliente novoCliente = new Cliente(nome);

        if (tipoConta == 1){
            novaConta = new ContaCorrente(novoCliente);
        } else if (tipoConta == 2) {
            novaConta = new ContaPoupanca(novoCliente);
        } else {
            return;
        }

        banco.setContas(novaConta);
        System.out.println("Conta Cadastrada com sucesso!\n");
    }

    public void exibirExtrato(Banco banco){
        Scanner ler = new Scanner(System.in);

        System.out.println("Digite o numero da conta: ");
        int numeroConta = ler.nextInt();
        int contaNum = localizaConta(numeroConta, banco);

        if (contaNum != -1){
            banco.getContas().get(contaNum).imprimirExtrato();
        } else {
            System.out.println("Conta não encontrada!\n");
        }
    }

    public void sacar(Banco banco){
        Scanner lerNumConta = new Scanner(System.in);
        Scanner lerValor = new Scanner(System.in);

        System.out.println("Digite o numero da conta: ");
        int numeroConta = lerNumConta.nextInt();
        int contaNum = localizaConta(numeroConta, banco);

        if(contaNum != -1){
            System.out.println("Digite um valor: ");
            double valor = lerValor.nextDouble();
            
            if (!checarSaque(numeroConta,valor,banco)) {
                banco.getContas().get(contaNum).sacar(valor);
                System.out.println("Saque realizado com sucesso!\n");
            } else {
                System.out.println("Saldo insufiente para saque!\n");
            }
        } else {
            System.out.println("Conta não encontrada!\n");
        }
    }

    public void depositar(Banco banco){
        Scanner lerNumConta = new Scanner(System.in);
        Scanner lerValor = new Scanner(System.in);

        System.out.println("Digite o numero da conta para depositar: ");
        int numeroConta = lerNumConta.nextInt();
        int contaNum = localizaConta(numeroConta,banco);

        if(contaNum != -1){
            System.out.println("Digite um valor: ");
            double valor = lerValor.nextDouble();
            banco.getContas().get(contaNum).depositar(valor);
            System.out.println("Deposito realizado com sucesso!\n");
        } else {
            System.out.println("Conta não encotrada!\n");
        }
    }

    public void transferir(Banco banco){
        Scanner origem = new Scanner(System.in);
        Scanner destino = new Scanner(System.in);
        Scanner lerValor = new Scanner(System.in);

        System.out.println("Digite o numero da conta de origem: ");
        int contaOrigem = origem.nextInt();
        int contaNumOrigem = localizaConta(contaOrigem, banco);

        if(contaNumOrigem == -1){
            System.out.println("Conta origem não encontrada!\n");
            return;
        }

        System.out.println("Digite o numero da conta de destino: ");
        int contaDestino = destino.nextInt();
        int contaNumDestino = localizaConta(contaDestino, banco);

        if (contaNumDestino == -1){
            System.out.println("Conta destino não encontrada!\n");
            return;
        }

        System.out.println("Digite o valor de transferencia: ");
        double valor = lerValor.nextDouble();

        if(!checarSaque(contaOrigem,valor,banco)){
            banco.getContas().get(contaOrigem).transferir(valor, banco.getContas().get(contaDestino));
            System.out.println("Transferencia realizada com sucesso!\n");
        } else {
            System.out.println("Saldo insuficiente para transferir!\n");
        }
    }

    public void listarContas(Banco banco){
        if(banco.getContas().size() > 0){
            System.out.println("\t*** CONTAS ***");
            for (Conta contas: banco.getContas()) {
                contas.imprimircontas();
            }
        } else {
            System.out.println("Não existe contas cadastradas!\n");
        }
    }

    private int localizaConta(int numeroConta, Banco banco){
        int numero = -1;
        for (Conta conta: banco.getContas()) {
            if(conta.getNumero() == numeroConta){
                numero = numeroConta;
                break;
            }
        }
        return numero;
    }

    private boolean checarSaque(int numeroConta, double valor, Banco banco){
        return (banco.getContas().get(numeroConta).getSaldo() < valor);
    }
}

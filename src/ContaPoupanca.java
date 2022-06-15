public class ContaPoupanca extends Conta{

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }
    @Override
    public void imprimirExtrato() {
        System.out.println("\n*** Extrato conta poupanca ***");
        imprimirInfosComuns();
    }

    @Override
    public void imprimirContas() {
        System.out.println("*** Conta Poupança ***");
        listarContas();
    }
}

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("\n*** Extrato conta corrente ***");
        imprimirInfosComuns();
    }

    @Override
    public void imprimirContas() {
        System.out.println("*** Conta Corrente ***");
        listarContas();
    }
}

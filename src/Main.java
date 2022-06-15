import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Controle con = new Controle();

        Banco banco = new Banco("Banco do Brasil");

        int menu = 0;

        do {
            menu = con.menu();
            switch (menu){
                case 1:
                    con.criarConta(banco);
                    break;
                case 2:
                    con.exibirExtrato(banco);
                    break;
                case 3:
                    con.sacar(banco);
                    break;
                case 4:
                    con.depositar(banco);
                    break;
                case 5:
                    con.transferir(banco);
                    break;
                case 6:
                    con.listarContas(banco);
                    break;
                default:
                    System.out.println("Opção não existe!\n");
                    break;
            }
        } while(menu != 7);
    }
}


package principal;

import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		Hotel hotel = new Hotel();
		sistema();
	}
	
	public static void sistema() {
		int choice = 0;
		System.out.println("Bem vindo! Escolha uma opção para continuar:");
		while(choice != 9) {
		 System.out.println("1. Gerenciar Quartos");
         System.out.println("2. Gerenciar Reservas");
         System.out.println("3. Histórico de reservas);");
         System.out.println("9. Sair");
         choice = scan.nextInt();
         switch (choice) {
		case 1: {
			gerenciarQuartos();
			break;
		}
		case 2: {
//			gerenciarReservas();
		}
		case 3: {
//			historicoReservas();
		}
		case 9:{
			System.out.println("Saindo...");
			break;
		}
		default: {
			System.out.println("Opção inválida, tente novamente.");
		}
         }
	}}
	
	private static void gerenciarQuartos() {
		int choice = 0;
		while(choice!=9) {
		System.out.println("Gerenciamento de quartos:");
		 System.out.println("1. Adicionar Quartos");
         System.out.println("2. Editar Quartos");
         System.out.println("3. Remover Quartos");
         System.out.println("4. Listar Quartos;");
         System.out.println("9. Voltar");
         choice = scan.nextInt();
         switch (choice) {
		case 1: {
			System.out.println("Qual o número do quarto?");
			int num = scan.nextInt();
			System.out.println("Qual o tipo do quarto?\n1 - Solteiro\n2 - Casal\n3 - Suite");
			int tipo = scan.nextInt();
			System.out.println("Qual é a diária do quarto?");
			double preco = scan.nextInt();
			System.out.println("Qual o estado do quarto?\n1 - Disponível\n2 - Ocupado");
			String disp = null;
			int option = 0;
			while(!(option<3&&option>0)) {
				option = scan.nextInt();
			switch (option){
			case 1: {
				disp = "Disponível";
				break;
			}
			case 2: {
				disp = "Ocupado";
				break;
			}
			default:
				System.out.println("Inválido, tente novamente.");
			}}
			Hotel.addQuarto(num,tipo,preco,disp);
			break;
		}
		case 2:{
			if(Hotel.quartos.isEmpty()) {
				System.out.println("Não há quartos cadastrados.");
				break;
			}
			System.out.println("Que quarto deseja editar?");
			Hotel.listarQuartos();
			System.out.println("0 - Cancelar");
			int num = scan.nextInt();
			if(num==0) {
				break;
			}
			System.out.println("O que deseja editar?\n1 - Tipo de quarto\n2 - Preço diário\n3 - Disponibilidade");
			int item = scan.nextInt();
			Hotel.editarQuarto(num, item);
			break;
		}
		case 3:{
			if(Hotel.quartos.isEmpty()) {
				System.out.println("Não há quartos cadastrados.");
				break;
			}
			System.out.println("Que quarto deseja remover?");
			Hotel.listarQuartos();
			System.out.println("0 - Cancelar");
			int num = scan.nextInt();
			if(num==0) {
				break;
			}
			System.out.println("Tem certeza?\n1 - Sim\n2 - Não\n");
			int confirm = scan.nextInt();
			if(confirm==1) {
			Hotel.removerQuarto(num);
			}else {
				System.out.println("Operação cancelada.");
			}
			break;
		}
		case 4:{
			if(Hotel.quartos.isEmpty()) {
				System.out.println("Não há quartos cadastrados.");
				break;
			}
			Hotel.listarQuartos();
			break;
		}
		default: {
			System.out.println("Opção inválida, tente novamente.");
		}
	}
}
	}}

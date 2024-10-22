package principal;

import java.util.Scanner;

import objetos.Reserva;

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
         System.out.println("3. Relatório Financeiro");
         System.out.println("9. Sair");
         choice = scan.nextInt();
         switch (choice) {
		case 1: {
			gerenciarQuartos();
			break;
		}
		case 2: {
			gerenciarReservas();
			break;
		}
		case 3: {
//			historicoReservas();
			break;
		}
		case 9:{
			System.out.println("Até logo!");
			break;
		}
		default: {
			System.out.println("Opção inválida, tente novamente.");
			break;
		}
         }
	}}
	
	private static void gerenciarReservas() {
		int choice = 0;
		while(choice!=9) {
		System.out.println("Gerenciamento de reservas:");
		 System.out.println("1. Cadastrar Reserva");
         System.out.println("2. Gerenciar Reservas");
         System.out.println("3. Relatório de Ocupação");
         System.out.println("4. Histórico de Reservas");
         System.out.println("9. Voltar");
         choice = scan.nextInt();
         switch (choice) {
		case 1: {
			cadastrar();
			break;
		}
		case 2:{
			editarReservas();
			break;
		}
		case 3:{
			if(Hotel.reservas.isEmpty()) {
				System.out.println("Não há reservas cadastrados.");
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
			if(Hotel.reservas.isEmpty()) {
				System.out.println("Não há reservas cadastrados.");
				break;
			}
			Hotel.listarReservas();
			break;
		}
		case 9:{
			System.out.println("Voltando");
			break;
		}
		default: {
			System.out.println("Opção inválida, tente novamente.");
			break;
		}
       }
	}
	}

	private static void editarReservas() {
		if(Hotel.reservas.isEmpty()) {
			System.out.println("Não há reservas cadastrados.");
			return;
		}
		System.out.println("Que reserva deseja editar?");
		Hotel.listarReservas();
		System.out.println("0 - Cancelar");
		int num = scan.nextInt();
		if(num==0) {
			return;
		}
		System.out.println("O que deseja editar?\n1 - Efetuar CheckOut\n2 - Mudar quarto\n3 - Mudar nome do cliente");
		int item = scan.nextInt();
		Hotel.editarReserva(num, item);
		return;
	}

	private static void cadastrar() {
		System.out.println("Qual o nome do cliente?");
		String nome = scan.next();
		System.out.println("Qual o tipo do quarto?\n1 - Solteiro\n2 - Casal\n3 - Suite");
		int tipo = scan.nextInt();
		if(Hotel.listarCorresp(tipo)) {
			System.out.println("Nenhum quarto desse tipo está disponível.");
			return;
		}
		System.out.println("Qual o número do quarto?");
		int num = scan.nextInt();
		System.out.println("Qual é o dia do CheckIn? (1-30)");
		int checkInDia = scan.nextInt();
		if (checkInDia < 1 || checkInDia > 30) {
		    System.out.println("Data inválida.");
		    return;
		}

		System.out.println("Qual é o mês do CheckIn?\n1 - Janeiro\n2 - Fevereiro\n3 - Março\n4 - Abril\n5 - Maio\n6 - Junho\n7 - Julho\n8 - Agosto\n9 - Setembro\n10 - Outubro\n11 - Novembro\n12 - Dezembro");
		int checkInMes = scan.nextInt();
		if (checkInMes < 1 || checkInMes > 12) {
		    System.out.println("Mês inválido.");
		    return;
		}

		System.out.println("Qual é o ano do CheckIn?");
		int checkInAno = scan.nextInt();

		System.out.println("Data de CheckIn Escolhida: " + checkInDia + "/" + checkInMes + "/" + checkInAno);

		System.out.println("Há um CheckOut?\n1 - Sim\n2 - Não");
		int option = 0;
		while(!(option<3&&option>0)) {
			option = scan.nextInt();
		switch (option){
		case 1: {
			System.out.println("Qual é o dia do CheckOut? (1-30)");
			int checkOutDia = scan.nextInt();
			if (checkOutDia < 1 || checkOutDia > 30) {
			    System.out.println("Data de check-out inválida.");
			    return;
			}

			System.out.println("Qual é o mês do CheckOut?\n1 - Janeiro\n2 - Fevereiro\n3 - Março\n4 - Abril\n5 - Maio\n6 - Junho\n7 - Julho\n8 - Agosto\n9 - Setembro\n10 - Outubro\n11 - Novembro\n12 - Dezembro");
			int checkOutMes = scan.nextInt();
			if (checkOutMes < 1 || checkOutMes > 12) {
			    System.out.println("Mês de check-out inválido.");
			    return;
			}

			System.out.println("Qual é o ano do CheckOut?");
			int checkOutAno = scan.nextInt();
			if(Hotel.checkDates(checkInDia, checkInMes, checkInAno, checkOutAno, checkOutMes, checkOutDia)) {
				System.out.println("O CheckOut não pode ocorrer antes do CheckIn");
				break;
			}
			System.out.println("Data de CheckOut Escolhida: " + checkOutDia + "/" + checkOutMes + "/" + checkOutAno);
			
			    Hotel.addReservaFinalizada(checkInDia, checkInMes, checkInAno, checkOutDia, checkOutMes, checkOutAno, nome, num);
			    break;
			}
		
		case 2: {
			Hotel.addReserva(checkInDia, checkInMes, checkInAno, nome, num);
			break;
		}
		default:
			System.out.println("Inválido, tente novamente.");
		}}
	}

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
		case 9:{
			System.out.println("Voltando");
			break;
		}
	}
}
	}
	
	}
	
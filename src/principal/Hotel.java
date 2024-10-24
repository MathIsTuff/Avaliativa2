package principal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import objetos.Cliente;
import objetos.Quarto;
import objetos.Reserva;

public class Hotel {
	public static ArrayList<Quarto> quartos = new ArrayList<>();
	public static ArrayList<Cliente> clientes = new ArrayList<>();
	static Scanner scan = new Scanner(System.in);
	static int idRoom;
	public static void addQuarto(int num, int tipo, double preco, String disp) {
		boolean quartoExiste = false;

		if(!quartos.isEmpty()) {		
		    for (Quarto quarto : quartos) {
		        if(num == quarto.getNumQuarto()) {
		            System.out.println("Erro: Esse número de quarto já está em uso");
		            quartoExiste = true;
		            break;
		        }
		    }
		    if (!quartoExiste) {
		        quartos.add(new Quarto(num, tipo, preco, disp));
		    }
		} else {
		    quartos.add(new Quarto(num, tipo, preco, disp));
		}
	}
	public static void listarQuartos() {
		for (Quarto quarto : quartos) {
			System.out.println(quarto);
		}
	}
	public static boolean listarCorresp(int tipo) {
		String tipoQuarto = null;
		int matches = 0;
		switch (tipo) {
		case 1: {
			tipoQuarto = "Solteiro";
			break;
		}
		case 2: {
			tipoQuarto = "Casal";
			break;
		}
		case 3: {
			tipoQuarto = "Suíte";
			break;
		}
		default:
			tipoQuarto = "Inválido";
			break;
		}
		for (Quarto quarto : quartos) {
			if(quarto.getTipoQuarto()==tipoQuarto&&quarto.isDisponivel()=="Disponível") {
			System.out.println(quarto);
			matches++;
			}
		}
		if(matches==0) {
			return true;
		} else {
			return false;
		}
	}

	public static void editarQuarto(int num, int choice) {
		for (Quarto quarto : quartos) {
			if(num==quarto.getNumQuarto()) {
				switch (choice) {
				case 1: {
					System.out.println("Qual o novo tipo do quarto?\n1 - Solteiro\n2 - Casal\n3 - Suite");
					int tipo = scan.nextInt();
					if(tipo>=4) {
						break;
					} else {
						quarto.setTipoQuarto(tipo);
					}
					break;
				}
				case 2:{
					System.out.println("Qual a nova diária do quarto?");
					int preco = scan.nextInt();
					quarto.setPrecoDiario(preco);
					break;
				}
				case 3:{
					System.out.println("Qual o novo estado do quarto?\n1 - Disponível\n2 - Ocupado");
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
						System.out.println("Inválido, tente novamente");
					}}
					quarto.setDisponivel(disp);
					break;
				}
				default:
					
				}
			}
		}
		
		
	}
	public static void removerQuarto(int num) {
				quartos.removeIf(quarto -> quarto.getNumQuarto() == num);
	}
	public static void addReservaFinalizada(int checkInDia, int checkInMes, int checkInAno, int checkOutDia, int checkOutMes, int checkOutAno, int id, int num) {
		boolean match;
		for (Quarto quarto : quartos) {
			if(num==quarto.getNumQuarto()) {
				match = true;
			}
		}
		if(match=false) {
			System.out.println("Não há nenhum quarto com esse número");
			return;
		}
		Cliente cliente = clientes.get(id-1);
		Reserva reserva = new Reserva(checkInDia, checkInMes, checkInAno, checkOutDia, checkOutMes, checkOutAno, cliente.getNome(), num, idRoom);
		idRoom++;
		cliente.addReservas(reserva);
		for (Quarto quarto : quartos) {
			if(quarto.getNumQuarto()==num&&dataPassada(checkOutDia, checkOutMes, checkOutAno)&&!dataPassada(checkInDia, checkInMes, checkInAno)) {
				quarto.setDisponivel("Ocupado");
			}
		}
	}
	public static void addReserva(int checkInDia, int checkInMes, int checkInAno, int id, int num) {
		
		boolean match;
		for (Quarto quarto : quartos) {
			if(num==quarto.getNumQuarto()) {
				match = true;
			}
		}
		if(match=false) {
			System.out.println("Não há nenhum quarto com esse número");
			return;
		}
		Cliente cliente = clientes.get(id-1);
		Reserva reserva = new Reserva(checkInDia, checkInMes, checkInAno, cliente.getNome(), num, idRoom);
		idRoom++;
		cliente.addReservas(reserva);
		for (Quarto quarto : quartos) {
			if(quarto.getNumQuarto()==num&&!dataPassada(checkInDia, checkInMes, checkInAno)) {
				quarto.setDisponivel("Ocupado");
			}
		}
		
	}
	public static void listarReservas() {
		for (Cliente cliente : clientes) {
			cliente.listarQuartos();
		}
	}
	public static void editarReserva(int num, int item) {
		for (Cliente cliente : clientes) {
			for (Reserva reserva : cliente.reservas) {
			if(num==reserva.getId()) {
				switch (item) {
				case 1: {
					reserva.setDataCheckOut();
					for (Quarto quarto : quartos) {
						if(quarto.getNumQuarto()==reserva.getQuartoReservado()) {
							quarto.setDisponivel("Disponível");
						}
					}

					    return;
					}
				case 2:{
					System.out.println("Qual o novo quarto?");
					int quarto = scan.nextInt();
					reserva.setQuartoReservado(quarto);
					return;
				}
				case 3:{
					Date hoje = new Date();
					if(reserva.getCheckIn().getTime()<hoje.getTime()) {
						System.out.println("Não é possível cancelar uma reserva concluida ou enquanto o hospede está no quarto.");
						break;
					}
					System.out.println("Reserva deletada.");
					cliente.reservas.remove(reserva);
					return;
				}
					
				default:
					System.out.println("Opção inválida");
					break;
				}
			}
		}
		}
	}
	public static boolean dataPassada(int checkInDia, int checkInMes, int checkInAno) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(checkInAno, checkInMes-1, checkInDia);
		Date dataCheckIn = calendar.getTime();
		Date hoje = new Date();
		if(hoje.getTime()>=dataCheckIn.getTime()) {
			return false;
		} else {
			return true;
		}
	}
	public static boolean checkDates(int checkInDia, int checkInMes, int checkInAno, int checkOutAno, int checkOutMes, int checkOutDia) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(checkInAno, checkInMes-1, checkInDia);
		Date dataCheckIn = calendar.getTime();
		calendar.set(checkOutAno, checkOutMes-1, checkOutDia);
		Date dataCheckOut = calendar.getTime();
		if(dataCheckOut.getTime()>=dataCheckIn.getTime()) {
			return false;
		} else {
			return true;
		}
	}
	public static void listarClientes() {
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}		
	}
	public static void newCliente(String nome) {
		clientes.add(new Cliente(nome));
	}
	public static boolean isHospedado(int id) {
		for (Cliente cliente : clientes) {
			if(id==cliente.getId()) {
			for (Reserva reserva : cliente.reservas) {
				if(reserva.isAtual()) {
					return true;
				}
			}
			}
		}
		return false;
	}
	public static void ocupacao() {
		int disp = 0;
		int indis = 0;
		int total = 0;
		int ocupado = 0;
		for (Quarto quarto : quartos) {
			total++;
			switch (quarto.isDisponivel()) {
			case "Disponível": {
				disp++;
				break;
			}
			case "Travado": {
				indis++;
				break;
			}
			case "Ocupado": {
				ocupado++;
				break;
			}
			default:
				break;
			}
		}
		System.out.println("Existem "+total+" quartos cadastrados, sendo:\n " + disp + " Disponíveis;\n "+indis+" Travados;\n "+ocupado+" Ocupados.");
		System.out.println("Quartos ocupados:");
		listarOcupados();
	}
	private static void listarOcupados() {
		for (Quarto quarto : quartos) {
			if(quarto.isDisponivel()=="Ocupado") {
				System.out.println(quarto);
				for (Cliente cliente : clientes) {
					for (Reserva reserva : cliente.reservas) {
						if(reserva.getQuartoReservado()==quarto.getNumQuarto()) {
							if(reserva.getCheckOut()==null) {
								System.out.println("Ocupado desde "+ reserva.getDataCheckIn() + " Sem data de Checkout prevista.\n---------------------");
							} else {
								System.out.println("Ocupado desde "+ reserva.getDataCheckIn() + ", liberado dia " + reserva.getDataCheckOut()+"\\n---------------------");
							}
						}
					}
				}
			}
		}
	}
	public static void listarReservasAtivas() {
		for (Cliente cliente : clientes) {
			cliente.listarQuartosAtivo();
		}
	}
}


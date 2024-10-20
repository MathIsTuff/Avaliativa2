package principal;

import java.util.ArrayList;
import java.util.Scanner;

import objetos.Quarto;

public class Hotel {
	static ArrayList<Quarto> quartos = new ArrayList<>();
	static Scanner scan = new Scanner(System.in);
	
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
}


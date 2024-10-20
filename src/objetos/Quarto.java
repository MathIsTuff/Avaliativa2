package objetos;

public class Quarto {
	int numQuarto;
	String tipoQuarto;
	double precoDiario;
	String disponivel;
	public Quarto(int num, int tipo, double preco, String disp) {
		this.numQuarto = num;
		this.precoDiario = preco;
		this.disponivel = disp;
		switch (tipo) {
		case 1: {
			this.tipoQuarto = "Solteiro";
			break;
		}
		case 2: {
			this.tipoQuarto = "Casal";
			break;
		}
		case 3: {
			this.tipoQuarto = "Suíte";
			break;
		}
		default:
			this.tipoQuarto = "Inválido";
			break;
		}
	}
	public int getNumQuarto() {
		return numQuarto;
	}
	public void setNumQuarto(int numQuarto) {
		this.numQuarto = numQuarto;
	}
	public String getTipoQuarto() {
		return tipoQuarto;
	}
	public void setTipoQuarto(int tipoQuarto) {
		switch (tipoQuarto) {
		case 1: {
			this.tipoQuarto = "Solteiro";
			break;
		}
		case 2: {
			this.tipoQuarto = "Casal";
			break;
		}
		case 3: {
			this.tipoQuarto = "Suíte";
			break;
		}
		default:
			this.tipoQuarto = "Inválido";
			break;
		}
	}
	public double getPrecoDiario() {
		return precoDiario;
	}
	public void setPrecoDiario(double precoDiario) {
		this.precoDiario = precoDiario;
	}
	public String isDisponivel() {
		return disponivel;
	}
	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}
	@Override
	public String toString() {
		return "--------------------------\nQuarto " + numQuarto + "    -    " + tipoQuarto + "\nDiária de R$" + precoDiario
				+ "    [" + disponivel + "]\n----------------------------";
	}
	
}

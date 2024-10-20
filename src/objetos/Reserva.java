package objetos;

import java.util.ArrayList;

import principal.Hotel;

public class Reserva {
	int id;
	int checkInDia;
	int checkInMes;
	int checkInAno;
	int checkOutDia;
	int checkOutMes;
	int checkOutAno;
	String nome;
	int quartoReservado;
	public Reserva(int checkInDia, int checkInMes, int checkInAno, int checkOutDia, int checkOutMes, int checkOutAno, String nome, int num) {
		this.checkInDia = checkInDia;
		this.checkInMes = checkInMes;
		this.checkInAno = checkInAno;
		this.checkOutDia = checkOutDia;
		this.checkOutMes = checkOutMes;
		this.checkOutAno = checkOutAno;
		this.nome = nome;
		this.quartoReservado = num;
		this.id = Hotel.reservas.size();
	}
	public Reserva(int checkInDia, int checkInMes, int checkInAno, String nome, int num) {
		this.checkInDia = checkInDia;
		this.checkInMes = checkInMes;
		this.checkInAno = checkInAno;
		this.nome = nome;
		this.quartoReservado = num;
		this.id = Hotel.reservas.size();
	}
	@Override
	public String toString() {
		return "Reserva [id=" + id + ", checkInDia=" + checkInDia + ", checkInMes=" + checkInMes + ", checkInAno="
				+ checkInAno + ", checkOutDia=" + checkOutDia + ", checkOutMes=" + checkOutMes + ", checkOutAno="
				+ checkOutAno + ", nome=" + nome + ", quartoReservado=" + quartoReservado + "]";
	}
	

}

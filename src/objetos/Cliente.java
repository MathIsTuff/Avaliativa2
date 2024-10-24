package objetos;

import java.util.ArrayList;
import java.util.Date;

import principal.Hotel;

public class Cliente {
	String nome;
	int id;
	public ArrayList<Reserva> reservas;
	boolean disp;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void addReservas(Reserva reserva) {
		this.reservas.add(reserva);
	}
	public void listarQuartos() {
		for (Reserva reserva : reservas) {
			System.out.println(reserva);
			reserva.listRoom();
		}
	}
	public void listarQuartosAtivo() {
		Reserva reserva = reservas.getLast();
		Date date = new Date();
		if(reserva.getCheckOut()==null) {
			System.out.println(reserva);
			reserva.listRoom();
			return;
		}
		if(reserva.getCheckOut().getTime()>date.getTime())
			System.out.println(reserva);
			reserva.listRoom();
		}
	
	
	public Cliente(String nome) {
		this.nome = nome;
		this.reservas = new ArrayList<>();
		this.id = Hotel.clientes.size()+1;
	}
	
	@Override
	public String toString() {
		return "[Cliente = " + nome + ", ID = " + id + " Reservas efetuadas = " + reservas.size() +  " Está Hospedado = " + Hotel.isHospedado(id) + "]";
	}
	
	
}


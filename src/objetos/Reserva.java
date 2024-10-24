package objetos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import principal.Hotel;

public class Reserva {
	SimpleDateFormat dataForm = new SimpleDateFormat("dd/MMMM/YY");
	Calendar calendar = Calendar.getInstance();
	int id;
	Date dataCheckIn;
	Date dataCheckOut;
	String nome;
	int quartoReservado;
	long dias;
	boolean atual;
	public int getId() {
		return id;
	}
	
	
	public boolean isAtual() {
		return atual;
	}


	public void setAtual(boolean atual) {
		this.atual = atual;
	}


	public String getDataCheckIn() {
		return dataForm.format(dataCheckIn);
	}
	public Date getCheckIn() {
		return dataCheckIn;
	}

	public void setDataCheckIn(Date dataCheckIn) {
		this.dataCheckIn = dataCheckIn;
	}

	public String getDataCheckOut() {
		return dataForm.format(dataCheckOut);
	}
	public Date getCheckOut() {
		return dataCheckOut;
	}

	public void setDataCheckOut() {
		this.dataCheckOut = new Date();
		this.dias = (dataCheckOut.getTime() - dataCheckIn.getTime())/(24 * 60 * 60 * 1000);
		this.atual = false;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void listRoom() {
		for (Quarto quarto : Hotel.quartos) {
			if(quarto.getNumQuarto()==quartoReservado) {
				System.out.println(quarto);
			}
		}
	}
	public int getQuartoReservado() {
		return quartoReservado;
	}
	public void setQuartoReservado(int quartoReservado) {
		this.quartoReservado = quartoReservado;
	}
	public Reserva(int checkInDia, int checkInMes, int checkInAno, int checkOutDia, int checkOutMes, int checkOutAno, String nome, int num, int id) {
		calendar.set(checkInAno, checkInMes-1, checkInDia);
		this.dataCheckIn = calendar.getTime();
		calendar.set(checkOutAno, checkOutMes-1, checkOutDia);
		this.dataCheckOut = calendar.getTime();
		this.nome = nome;
		this.quartoReservado = num;
		this.dias = (dataCheckOut.getTime() - dataCheckIn.getTime())/(24 * 60 * 60 * 1000);
		this.id = id+1;
		Date hoje = new Date();
		if(hoje.getTime()>=dataCheckOut.getTime()) {
			this.atual = true;
		} else {
			this.atual = false;
		}
		
	}
	public Reserva(int checkInDia, int checkInMes, int checkInAno, String nome, int num, int id) {
		calendar.set(checkInAno, checkInMes-1, checkInDia);
		this.dataCheckIn = calendar.getTime();
		this.nome = nome;
		this.quartoReservado = num;
		Date hoje = new Date();
		this.dias = (hoje.getTime() - dataCheckIn.getTime())/(24 * 60 * 60 * 1000);
		this.id = id+1;
		if(hoje.getTime()>=dataCheckIn.getTime()) {
			this.atual = true;
		} else {
			this.atual = false;
		}
	}
	@Override
	public String toString() {
			if(dataCheckOut==null) {
				Date hoje = new Date();
				this.dias = (hoje.getTime() - dataCheckIn.getTime())/(24 * 60 * 60 * 1000);
				return "--------------------------\nReserva " + id + " - " + nome + "\nCheckIn = " + dataForm.format(dataCheckIn) + " - CheckOut = null" + "\nDias no hotel = " + dias ;
			} else {
		return "--------------------------\nReserva " + id + " - " + nome + "\nCheckIn = " + dataForm.format(dataCheckIn) + " - CheckOut = " + dataForm.format(dataCheckOut) + "\nDias no hotel = " + dias;}
	}
	

}

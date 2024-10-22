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
	public int getId() {
		return id;
	}
	
	
	public Date getDataCheckIn() {
		return dataCheckIn;
	}

	public void setDataCheckIn(Date dataCheckIn) {
		this.dataCheckIn = dataCheckIn;
	}

	public Date getDataCheckOut() {
		return dataCheckOut;
	}

	public void setDataCheckOut(int checkOutAno, int checkOutMes, int checkOutDia) {
		calendar.set(checkOutAno, checkOutMes-1, checkOutDia);
		this.dataCheckOut = calendar.getTime();
		this.dias = (int) (dataCheckOut.getTime() - dataCheckIn.getTime())/(24 * 60 * 60 * 1000);
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
	public int getQuartoReservado() {
		return quartoReservado;
	}
	public void setQuartoReservado(int quartoReservado) {
		this.quartoReservado = quartoReservado;
	}
	public Reserva(int checkInDia, int checkInMes, int checkInAno, int checkOutDia, int checkOutMes, int checkOutAno, String nome, int num) {
		calendar.set(checkInAno, checkInMes-1, checkInDia);
		this.dataCheckIn = calendar.getTime();
		calendar.set(checkOutAno, checkOutMes-1, checkOutDia);
		this.dataCheckOut = calendar.getTime();
		this.nome = nome;
		this.quartoReservado = num;
		this.id = Hotel.reservas.size()+1;
		this.dias = (dataCheckOut.getTime() - dataCheckIn.getTime())/(24 * 60 * 60 * 1000);
	}
	public Reserva(int checkInDia, int checkInMes, int checkInAno, String nome, int num) {
		calendar.set(checkInAno, checkInMes-1, checkInDia);
		this.dataCheckIn = calendar.getTime();
		this.nome = nome;
		this.quartoReservado = num;
		this.id = Hotel.reservas.size()+1;
		Date hoje = new Date();
		this.dias = (hoje.getTime() - dataCheckIn.getTime())/(24 * 60 * 60 * 1000);
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

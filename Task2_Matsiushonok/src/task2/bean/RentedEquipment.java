package task2.bean;

import java.sql.Date;

public class RentedEquipment {

	private int id;
	private int idClient;
	private int idEquipment;
	private Date dateFrom;
	private Date dateTo;
	private double totalPrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdEquipment() {
		return idEquipment;
	}

	public void setIdEquipment(int idEquipment) {
		this.idEquipment = idEquipment;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date date) {
		this.dateFrom = date;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Override
	public String toString(){
		return "id: " + id + " |date-from: " + dateFrom + " | dateTo: " + dateTo + " | "; 
	}

}

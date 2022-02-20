package entity;

import java.sql.Date;

public class Order {
	
	private int orderID;
	private java.sql.Date orderDate;
	private String payment;
	/**
	 * @param orderID
	 * @param orderDate
	 * @param payment
	 */
	public Order(int orderID, Date orderDate, String payment) {
		super();
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.payment = payment;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public java.sql.Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(java.sql.Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", orderDate=" + orderDate + ", payment=" + payment + "]";
	}
	
	

}

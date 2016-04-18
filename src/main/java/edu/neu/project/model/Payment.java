package edu.neu.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name="Payment")
public class Payment {
	
	@GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="order"))
	@Id
	@GeneratedValue(generator="generator")
	@Column(name="Payment_ID", unique=true, nullable=false)
	private long paymentId;	
	
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Order order;
	
	@Column(name="Credit_Card_Number", nullable=true, length=100)
	private String number;

	@Column(name="CVV", nullable=true, length=100)
	private int cvv;

	
	@Column(name="Expiration_Date", nullable=true, length=100)
	private String date;

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}

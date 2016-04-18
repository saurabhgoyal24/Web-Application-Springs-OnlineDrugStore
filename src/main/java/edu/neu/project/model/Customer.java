package edu.neu.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@PrimaryKeyJoinColumn(name="PersonId")
public class Customer extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "gender")
	private String gender;
	
	@Column(name="address")
	private String address;
	
	@Column(name="dateOfBirth")
	private String dateOfBirth;
	
	@Column(name="phoneNo")
	private long phoneNo;
	
	@Column(name="email")
	private String email;
	
//	@OneToOne(fetch=FetchType.LAZY, mappedBy="customer", cascade=CascadeType.ALL)
//	private OrderHistory orderHistory;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="customer")
	private List<Order> order = new ArrayList<Order>();

//	@OneToMany(fetch=FetchType.LAZY, mappedBy="customer", cascade=CascadeType.ALL)
//	private List<PaymentMethod> paymentList=new ArrayList<PaymentMethod>();
	
//	@OneToOne(fetch=FetchType.LAZY, mappedBy="customer", cascade=CascadeType.ALL)
//	private Payment payment;
	


	public Customer() {
		
	}
	
	
//	public Payment getPayment() {
//		return payment;
//	}
//
//	public void setPayment(Payment payment) {
//		this.payment = payment;
//	}
//
//	
//
//
//	public OrderHistory getOrderHistory() {
//		return orderHistory;
//	}
//
//
//	public void setOrderHistory(OrderHistory orderHistory) {
//		this.orderHistory = orderHistory;
//	}


	public List<Order> getOrder() {
		return order;
	}


	public void setOrder(List<Order> order) {
		this.order = order;
	}


//	public List<PaymentMethod> getPaymentList() {
//		return paymentList;
//	}
//
//
//	public void setPaymentList(List<PaymentMethod> paymentList) {
//		this.paymentList = paymentList;
//	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

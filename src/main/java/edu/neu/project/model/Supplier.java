package edu.neu.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="Supplier")
@PrimaryKeyJoinColumn(name="PersonId")
public class Supplier extends Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Column(name="Supplier_Name", nullable=false, unique=true, length=100)
	private String supplierName;
	
//	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
//	@JoinColumn(name="Admin_ID", nullable=true)
//	private Admin admin;
	
//	@OneToOne(fetch=FetchType.LAZY)
//	@PrimaryKeyJoinColumn
//	private User user;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="Supplier_Product",
	   joinColumns={@JoinColumn(name="PersonId", nullable=false, updatable=false)},
	   inverseJoinColumns={@JoinColumn(name="drugId", nullable=false, updatable=false)})
	private List<DrugProduct> drugProduct = new ArrayList<DrugProduct>();

//	public long getSupplierId() {
//		return supplierId;
//	}
//
//	public void setSupplierId(long supplierId) {
//		this.supplierId = supplierId;
//	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

//	public Admin getAdmin() {
//		return admin;
//	}
//
//	public void setAdmin(Admin admin) {
//		this.admin = admin;
//	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public List<DrugProduct> getDrugProducts() {
		return drugProduct;
	}

	public void setDrugProduct(List<DrugProduct> drugProduct) {
		this.drugProduct = drugProduct;
	}

	
	
}

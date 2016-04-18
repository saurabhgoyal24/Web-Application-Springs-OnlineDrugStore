package edu.neu.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="DrugProduct")
public class DrugProduct implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Drug_Id", unique=true, nullable=false)
	private int drugId;
	
	@Column(name="Drug_Name", nullable=false, length=100)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="DrugCategory_Id", nullable=true)
	private DrugCategory drugCategory;
	
	@Column(name="Price", nullable=false, length=100)
	private float price;
	
	@Column(name="Quantity", nullable=false, length=100)
	private int quantity;
	
	@Column(name="Description", nullable=false, length=100)
	private String description;
	
	@Column(name="Path", nullable=false, length=100)
	private String path;
	
	@Column(name="CompanyName", nullable=false, length=100)
	private String companyName;
	
	
	
//	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="product")
//	private List<Supplier> supplier = new ArrayList<Supplier>();
	
	
	
	public String getDescription() {
		return description;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="drugProduct", cascade=CascadeType.ALL)
	private List<OrderItem> orderItem = new ArrayList<OrderItem>();

	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DrugCategory getDrugCategory() {
		return drugCategory;
	}

	public void setDrugCategory(DrugCategory drugCategory) {
		this.drugCategory = drugCategory;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}		
}

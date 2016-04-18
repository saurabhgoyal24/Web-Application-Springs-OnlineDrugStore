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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DrugCategory")
public class DrugCategory implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DrugCategory_Id", unique=true, nullable=false)
	private long drugCategoryId;
	
	@Column(name="DrugCategoryName", nullable=false, unique=true, length=100)
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="drugCategory", cascade=CascadeType.ALL)
	private List<DrugProduct> product = new ArrayList<DrugProduct>();

	public long getDrugCategoryId() {
		return drugCategoryId;
	}

	public void setDrugCategoryId(long drugCategoryId) {
		this.drugCategoryId = drugCategoryId;
	}

	public List<DrugProduct> getDrugProduct() {
		return product;
	}

	public void setProduct(List<DrugProduct> product) {
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

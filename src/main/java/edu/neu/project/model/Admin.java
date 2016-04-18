package edu.neu.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name="PersonId")
public class Admin extends Person implements Serializable {

//	private static final long serialVersionUID = 1L;
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="admin", cascade=CascadeType.ALL)
//	private List<Supplier> supplier = new ArrayList<Supplier>();
//
//	public List<Supplier> getSupplier() {
//		return supplier;
//	}
//
//	public void setSupplier(List<Supplier> supplier) {
//		this.supplier = supplier;
//	}
}

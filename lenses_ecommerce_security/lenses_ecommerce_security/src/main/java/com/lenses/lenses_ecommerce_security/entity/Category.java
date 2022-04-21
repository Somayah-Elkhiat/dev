package com.lenses.lenses_ecommerce_security.entity;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;



@Entity
public class Category{
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "lenses_category", 
			  joinColumns = @JoinColumn(name = "category_id"), 
			  inverseJoinColumns = @JoinColumn(name = "lenses_id"))
	private List <Lenses> lenses;
	
	@Transient
	private List<Long> lensesPk;

	
	public Category() {
		super();
	}

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<Lenses> getLenses() {
		return lenses;
	}

	public void setLenses(List<Lenses> lenses) {
		this.lenses = lenses;
	}
	
	public void addLenses(Lenses lenses) {
		this.lenses.add(lenses);
	}

	public List<Long> getLensesPk() {
		List<Long> lens = new ArrayList<>();
		for(Lenses l: this.lenses) {
			lens.add(l.getId());
		}
		return lens;
	}

	public void setLensesPk(List<Long> lensesPk) {
		this.lensesPk = lensesPk;
	}
	
	
	
	

}

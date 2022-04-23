package com.lenses.lenses_ecommerce_security.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class Color {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="color_id")
	private int colorId;
	private String colorName;
	
	@OneToMany(mappedBy = "color")
	private List<Lenses> lenses;
	
	
	public Color() {
		super();
	}
	public Color(int colorId, String colorName) {
		super();
		this.colorId = colorId;
		this.colorName = colorName;
	}
	public int getColorId() {
		return colorId;
	}
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public List<Lenses> getLenses() {
		return lenses;
	}
	public void setLenses(List<Lenses> lenses) {
		this.lenses = lenses;
	}
	
	
	
	
	
	

}

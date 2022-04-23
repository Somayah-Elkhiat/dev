package com.lenses.lenses_ecommerce_security.entity;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
@Entity
public class Lenses {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lenses_id")
	private long id;
	private String title;
	private String description;
	@Column(unique = true)
	private String sku;
	
	private String slug;

	private Date createdDate;
	private Date modifiedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Brand brand;
	@Transient
	private int brandPk;

	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "color_id")
	private Color color;
	@Transient
	private int colorPk;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "power_id")
	private Power power;
	@Transient
	private int powerPk;

	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "lenses")
	private List<Category> categories;
	
	@JsonIgnore
	@OneToMany(mappedBy = "lenses")
	private List<CartItem> item;
	
	@JsonIgnore
	@OneToMany(mappedBy = "lenses")
	private List<OrderItem> orderItem;
	


	public Lenses() {
		super();

	}

	public Lenses(int id, String title, String description, String sku, 
			boolean isAvailable, String slug,  Date createdDate, Date modifiedDate,
			int colorPk, int powerPk, int brandPk) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.sku = sku;
		
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.colorPk = colorPk;
		this.powerPk = powerPk;
		this.brandPk = brandPk;
	}

	public Lenses(long id, String sku,  String slug) {
		super();
		this.id = id;
		this.sku = sku;
		
		
		fixSlug();
	
	}

	public void fixSlug() {
		String title = (this.title.replace(" - ", " "));
		String nowhitespace = WHITESPACE.matcher(title).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = NONLATIN.matcher(normalized).replaceAll("");
		this.setSlug(slug.toLowerCase(Locale.ENGLISH));
	}

	public int getBrandPk() {
		if (this.brandPk == 0)
			return this.getBrand().getBrandId();
		else
			return this.brandPk;
	}

	public void setBrandPk(int brandPk) {
		this.brandPk = brandPk;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public int getColorPk() {
		if (this.colorPk == 0)
			return this.getColor().getColorId();
		else
			return this.colorPk;
	}

	public void setColorPk(int colorPk) {
		this.colorPk = colorPk;
	}

	public int getPowerPk() {
		if (this.powerPk == 0)
			return this.getPower().getPowerId();
		else
			return this.powerPk;
	}

	public void setPowerPk(int powerPk) {
		this.powerPk = powerPk;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Power getPower() {
		return power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	
	

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getSlug() {
		fixSlug();
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;

	}

	public List<CartItem> getItem() {
		return item;
	}

	public void setItem(List<CartItem> item) {
		this.item = item;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	
	
}

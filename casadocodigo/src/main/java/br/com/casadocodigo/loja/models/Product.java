package br.com.casadocodigo.loja.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * @author wagner
 *
 */
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	@Lob
	private String description;
	private int pages;
	@ElementCollection
	private List<Price> prices = new ArrayList<>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
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
	
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "Product [title=" + title + ", description=" + description + ", pages=" + pages + "]";
	}
}

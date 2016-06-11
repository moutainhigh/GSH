package com.gsh.cs.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CurrencyUnit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "currency_unit", catalog = "clearing")
public class CurrencyUnit implements java.io.Serializable {

	// Fields

	private String id;
	private String text;

	// Constructors

	/** default constructor */
	public CurrencyUnit() {
	}

	/** full constructor */
	public CurrencyUnit(String text) {
		this.text = text;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "text", nullable = false)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
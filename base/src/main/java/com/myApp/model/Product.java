package com.myApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Getter @Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@Column(name = "name")
	private String name;
	@Column(name="description")
	private String  description;
	@Column(name="state")
	private boolean state;

    public Product() {
        state = true;
    }

}

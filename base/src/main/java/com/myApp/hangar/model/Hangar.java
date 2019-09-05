package com.myApp.hangar.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="HANGAR")
@Getter @Setter
public class Hangar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="name")
	private String name;

    @Column(name="address")
	private String address;

    @Column(name="owner")
    private String owner;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private long phone;

    @Column(name="state")
    private boolean state = true;

}

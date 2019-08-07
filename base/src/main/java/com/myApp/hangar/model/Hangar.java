package com.myApp.hangar.model;

import javax.persistence.*;

@Entity
@Table(name="HANGAR")
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

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}

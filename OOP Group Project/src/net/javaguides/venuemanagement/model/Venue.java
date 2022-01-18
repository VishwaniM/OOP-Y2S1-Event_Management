package net.javaguides.venuemanagement.model;

public class Venue {
	protected int id;
	protected String name;
	protected String address;
	protected String phone;
	protected String capacity;
	protected String charge;
	
	public Venue() {
	}
	
	public Venue(String name, String address, String phone, String capacity, String charge) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.capacity = capacity;
		this.charge = charge;
		
	}

	public Venue(int id, String name, String address, String phone, String capacity, String charge) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.capacity = capacity;
		this.charge = charge;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
}

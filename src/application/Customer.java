package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer{
	private StringProperty name;
	private IntegerProperty moblieNumber;
	private StringProperty address;
	
	public Customer (String name, Integer moblie, String address){
		super();
		this.name = new SimpleStringProperty(name);
		this.moblieNumber = new SimpleIntegerProperty(moblie);
		this.address = new SimpleStringProperty(address);
	}

	public Customer (Integer mobile){
		this.moblieNumber = new SimpleIntegerProperty(mobile);
	}
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);;
	}

	public int getMoblieNumber() {
		return moblieNumber.get();
	}

	public void setMoblieNumber(Integer moblieNumber) {
		this.moblieNumber.set(moblieNumber);
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address.set(address);;
	}
	
}
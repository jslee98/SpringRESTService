package model;

/**
 * 
 * @author Jeff Lee
 * Vehicle class for data storage
 *
 */

public class Vehicle {

	private String type, brand, color, engineType;
	private int vin, price, year;
	
	public Vehicle(String type, int vin, String brand, String color, String engineType, int price, int year) {
		this.type = type;
		this.brand = brand;
		this.color = color;
		this.engineType = engineType;
		this.price = price;
		this.year = year;
		this.vin = vin;
	}


	public int getPrice() {
		return this.price;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public int getVIN() {
		return this.vin;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public String getEngine() {
		return this.engineType;
	}
	
	public String getColor() {
		return this.color;
	}
	
	// toString for printing a vehicle
	public String toString() {
		return ("Brand: " + this.brand + " Type: " + this.type + " VIN #" + this.vin + " Engine: " + this.engineType
				+ " Color: " + this.color + " Price: $" + this.price + " Year: " + this.year);
	}
	
}

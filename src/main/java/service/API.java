package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import org.springframework.stereotype.Service;
import model.Vehicle;

/**
 * 
 * @author Jeff Lee
 * Static "database" and methods to sort and return the data.
 *
 */

@Service
public class API {
	
	private ArrayList<Vehicle> vehicleDB;
	
	/**
	 * Constructor populates local database
	 */
	public API() {
		
		Vehicle v1 = new Vehicle("Truck", 1234, "GMC", "Red", "Gas", 24000, 2016);
		Vehicle v2 = new Vehicle("Car", 1235, "Toyota", "Green", "Hybrid", 27000, 2015);
		Vehicle v3 = new Vehicle("Car", 1236, "Toyota", "Red", "Gas", 19000, 2015);
		Vehicle v4 = new Vehicle("Car", 1237, "Toyota", "Blue", "Gas", 21000, 2014);
		Vehicle v5 = new Vehicle("Motorcycle", 1238, "Honda", "Blue", "Hybrid", 17000, 2016);
		Vehicle v6 = new Vehicle("Truck", 1239, "Honda", "Blue", "Hybrid", 17000, 2016);
		Vehicle v7 = new Vehicle("Car", 1240, "Hyundai", "Red", "Gas", 17000, 2014);
		Vehicle v8 = new Vehicle("Car", 1241, "Tesla", "Blue", "Electric", 85000, 2016);
		Vehicle v9 = new Vehicle("Bicycle", 1242, "Cervelo", "White", "None", 2000, 2015);
		Vehicle v10 = new Vehicle("Bicycle", 1243, "Huffy", "White", "None", 150, 2014);
		Vehicle v11 = new Vehicle("Bicycle", 1244, "Trek", "Orange", "None", 1200, 2016);
		Vehicle v12 = new Vehicle("Car", 1245, "Hyundai", "White", "Electric", 25000, 2009);
		Vehicle v13 = new Vehicle("Car", 1246, "Ford", "Light Blue", "Gas", 1000, 1984);
		Vehicle v14 = new Vehicle("Car", 1247, "Chevrolet", "Dark Blue", "Gas", 500, 1981);
		Vehicle v15 = new Vehicle("Car", 1248, "AMC", "Tan", "Gas", 100, 1977);
		Vehicle v16 = new Vehicle("Car", 1249, "Yugo", "Orange", "None", 10, 1985);
		Vehicle v17 = new Vehicle("Car", 1250, "Bugatti", "Black/Red", "Gas", 2500000, 2016);
		Vehicle v18 = new Vehicle("Car", 1251, "Tesla", "Red", "Electric", 84000, 2016);
		Vehicle v19 = new Vehicle("Car", 1252, "Honda", "Blue", "Gas", 5000, 2002);
		Vehicle v20 = new Vehicle("Car", 1253, "Honda", "Silver", "Gas", 13000, 2015);
		Vehicle v21 = new Vehicle("Car", 1254, "Hyundai", "Gold", "Gas", 13000, 2014);
		Vehicle v22 = new Vehicle("Car", 1255, "Subaru", "Blue", "Gas", 13000, 2014);
		
		this.vehicleDB = new ArrayList<>();
		this.vehicleDB.add(v1);
		this.vehicleDB.add(v2);
		this.vehicleDB.add(v3);
		this.vehicleDB.add(v4);
		this.vehicleDB.add(v5);
		this.vehicleDB.add(v6);
		this.vehicleDB.add(v7);
		this.vehicleDB.add(v8);
		this.vehicleDB.add(v9);
		this.vehicleDB.add(v10);
		this.vehicleDB.add(v11);
		this.vehicleDB.add(v12);
		this.vehicleDB.add(v13);
		this.vehicleDB.add(v14);
		this.vehicleDB.add(v15);
		this.vehicleDB.add(v16);
		this.vehicleDB.add(v17);
		this.vehicleDB.add(v18);
		this.vehicleDB.add(v19);
		this.vehicleDB.add(v20);
		this.vehicleDB.add(v21);
		this.vehicleDB.add(v22);
	}
	
	/**
	 *  Method to return all vehicles sorted price ascending
	 */
	public Vehicle[] vehiclesByPrice() {
		Vehicle[] sorted = new Vehicle[this.vehicleDB.size()];
		int index = 0;
		while(vehicleDB.size() > 0) {
			Vehicle cheapestVehicle = vehicleDB.get(0);
			int minPrice = cheapestVehicle.getPrice();
			for (Vehicle vehicle : vehicleDB) {
				if (vehicle.getPrice() < minPrice) {
					cheapestVehicle = vehicle;
					minPrice = cheapestVehicle.getPrice();
				}
			}
			sorted[index] = cheapestVehicle;
			index++;
			vehicleDB.remove(cheapestVehicle);
		}
		
		return sorted;
	}
	
	/**
	 *  The below methods return the average cost of each vehicle by type, brand, engine, or color
	 *  All are sorted price ascending
	 */
	public ArrayList<String> getCostByType() {
		// Create hashmap
		// Holds a string for type, and a double[] for {type,[average,numHits]}
		HashMap<String, double[]> typeMap = new HashMap<>();
		
		// Populate hashmap
		// If type is already in map, update the key using ( numHits * Average + newPrice )/numHits++
		for( Vehicle vehicle : vehicleDB ) {
			if (typeMap.containsKey(vehicle.getType())) {
				double[] prices = typeMap.get(vehicle.getType());
				double newAvg = ((prices[0] * prices[1]) + vehicle.getPrice()) / (prices[1] + 1);
				prices[0] = newAvg;
				prices[1] = prices[1] + 1;
				typeMap.put(vehicle.getType(), prices);
			} else {
				double[] prices = {vehicle.getPrice(), 1};
				typeMap.put(vehicle.getType(), prices);
			}
		}
		
		// Sort the map by average ascending using a Tree Map
		TreeMap<Double, String> results = sortMapByPrice(typeMap);
		
		// Add results to string to be sent to user
		ArrayList<String> resultList = new ArrayList<>();
		results.forEach((k,v)-> resultList.add("Type: " + v + ", Avg. Price: " + k));
		return resultList;
	}
	
	public ArrayList<String> getCostByBrand() {
		HashMap<String, double[]> brandMap = new HashMap<>();
		for(Vehicle vehicle : vehicleDB) {
			if (brandMap.containsKey(vehicle.getBrand())) {
				double[] prices = brandMap.get(vehicle.getBrand());
				double newAvg = ((prices[0] * prices[1]) + vehicle.getPrice()) / (prices[1] + 1);
				prices[0] = newAvg;
				prices[1] = prices[1] + 1;
				brandMap.put(vehicle.getBrand(), prices);
			} else {
				double[] prices = {vehicle.getPrice(), 1};
				brandMap.put(vehicle.getBrand(), prices);
			}
		}
		
		TreeMap<Double, String> results = sortMapByPrice(brandMap);
		ArrayList<String> resultList = new ArrayList<>();
		results.forEach((k,v)-> resultList.add("Brand: " + v + ", Avg. Price: " + k));
		return resultList;
		
	}
	
	public ArrayList<String> getCostByEngine() {
		HashMap<String, double[]> engineMap = new HashMap<>();
		for(Vehicle vehicle : vehicleDB) {
			if (engineMap.containsKey(vehicle.getEngine())) {
				double[] prices = engineMap.get(vehicle.getEngine());
				double newAvg = ((prices[0] * prices[1]) + vehicle.getPrice()) / (prices[1] + 1);
				prices[0] = newAvg;
				prices[1] = prices[1] + 1;
				engineMap.put(vehicle.getEngine(), prices);
			} else {
				double[] prices = {vehicle.getPrice(), 1};
				engineMap.put(vehicle.getEngine(), prices);
			}
		}
		
		TreeMap<Double, String> results = sortMapByPrice(engineMap);
		ArrayList<String> resultList = new ArrayList<>();
		results.forEach((k,v)-> resultList.add("Engine Type: " + v + ", Avg. Price: " + k));
		return resultList;
		
	}
	
	public ArrayList<String> getCostByColor() {
		HashMap<String, double[]> colorMap = new HashMap<>();
		for(Vehicle vehicle : vehicleDB) {
			if (colorMap.containsKey(vehicle.getColor())) {
				double[] prices = colorMap.get(vehicle.getColor());
				double newAvg = ((prices[0] * prices[1]) + vehicle.getPrice()) / (prices[1] + 1);
				prices[0] = newAvg;
				prices[1] = prices[1] + 1;
				colorMap.put(vehicle.getColor(), prices);
			} else {
				double[] prices = {vehicle.getPrice(), 1};
				colorMap.put(vehicle.getColor(), prices);
			}
		}
		
		TreeMap<Double,String> results = sortMapByPrice(colorMap);
		ArrayList<String> resultList = new ArrayList<>();
		results.forEach((k,v)-> resultList.add("Color: " + v + ", Avg. Price: " + k));
		return resultList;
		
	}
	
	// Static method maps hashtree to treemap
	public static TreeMap<Double, String> sortMapByPrice(HashMap<String, double[]> map) {
		TreeMap<Double, String> sortedMap = new TreeMap<>();
		map.forEach((k,v) -> sortedMap.put(v[0], k));
		return sortedMap;
	}
	

}

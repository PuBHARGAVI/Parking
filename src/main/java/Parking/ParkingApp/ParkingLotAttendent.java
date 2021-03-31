package Parking.ParkingApp;

import java.util.ArrayList;

public class ParkingLotAttendent {
	public void addCarToLot(ArrayList<Car> parkedCars, Car car) {
		parkedCars.add(car);
	}

	public void unParkTheCar(ArrayList<Car> parkedCars, Car car) {
		parkedCars.remove(car);
	}
}

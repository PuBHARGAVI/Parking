package Parking.ParkingApp;

import java.util.ArrayList;

public class ParkingLotAttendar {
	public void addCarToLot(Car car, int slotPosition) {
		System.out.println("attendar");
	ParkingLot lot = null;
	ArrayList<Car> parkedCars=lot.getParkedCars();
	parkedCars.add(slotPosition, car);
	lot.setParkedCars(parkedCars);
	}
}

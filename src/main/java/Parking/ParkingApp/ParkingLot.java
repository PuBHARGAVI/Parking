package Parking.ParkingApp;

import javax.naming.LimitExceededException;

import java.util.ArrayList;

public class ParkingLot {
	private final int parkingLotCapacity;
	private ArrayList<Car> parkedCars = new ArrayList<Car>();
	ParkingLotOwner parkingLotOwner;
	TrafficCop trafficCop;

	public TrafficCop getTrafficCop() {
		return trafficCop;
	}

	public void setTrafficCop(TrafficCop trafficCop) {
		this.trafficCop = trafficCop;
	}

	public void setParkingLotOwner(ParkingLotOwner parkingLotOwner) {
		this.parkingLotOwner = parkingLotOwner;
	}

	public ParkingLot(int parkingLotCapacity) {
		this.parkingLotCapacity = parkingLotCapacity;
	}

	public ArrayList<Car> getParkedCars() {
		return parkedCars;
	}

	public void setParkedCars(ArrayList<Car> parkedCars) {
		this.parkedCars = parkedCars;
	}

	public void park(Car car) throws LimitExceededException, CarIsAlreadyParkedException {
		if (parkedCars.contains(car)) {
			throw new CarIsAlreadyParkedException();
		} else if (isParkingSlotAvailable()) {
			parkingLotOwner.notifyWhencarEntersLot(car);
			if (!isParkingSlotAvailable()) {
				System.out.println(parkingLotOwner);
				System.out.println(trafficCop);
				parkingLotOwner.notifyFull();
				trafficCop.notifyWhenLotIsFull();
			}
		} else {
			throw new LimitExceededException("No Parking Slot Is Available");
		}
	}

	public void unPark(Car car) throws CarIsNotAvailableException {
		if (parkedCars.contains(car)) {
			if(parkingLotCapacity==parkedCars.size())
				System.out.println(trafficCop);
				trafficCop.notifyWhenSpaceIsAvailable();
			parkedCars.remove(car);
		} else {
			throw new CarIsNotAvailableException();
		}
	}

	private boolean isParkingSlotAvailable() {
		if (parkingLotCapacity - parkedCars.size() > 0)
			return true;
		return false;
	}

}

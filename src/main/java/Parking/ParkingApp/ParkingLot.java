package Parking.ParkingApp;

import javax.naming.LimitExceededException;
import java.util.ArrayList;

public class ParkingLot {
	private final int parkingLotCapacity;
	private ArrayList<Car> parkedCars = new ArrayList<Car>();
	private ParkingLotOwner parkingLotOwner;
	private TrafficCop trafficCop;
	private ParkingLotAttendent parkingLotAttendent;

	public void setParkingLotAttendent(ParkingLotAttendent parkingLotAttendent) {
		this.parkingLotAttendent = parkingLotAttendent;
	}

	public TrafficCop getTrafficCop() {
		return trafficCop;
	}

	public void setTrafficCop(TrafficCop trafficCop) {
		this.trafficCop = trafficCop;
	}

	public void setParkingLotOwner(ParkingLotOwner parkingLotOwner) {
		this.parkingLotOwner = parkingLotOwner;
	}
	
	public ArrayList<Car> getParkedCars() {
		return parkedCars;
	}
	
	public ParkingLot(int parkingLotCapacity) {
		this.parkingLotCapacity = parkingLotCapacity;
	}

	public void park(Car car) throws LimitExceededException, CarIsAlreadyParkedException {
		if (parkedCars.contains(car)) {
			throw new CarIsAlreadyParkedException();
		} 
		else if (!isParkingSlotAvailable()) {
			throw new LimitExceededException("No Parking Slot Is Available");
		}
		else {
			parkingLotAttendent.addCarToLot(parkedCars, car);
		}
		if (!isParkingSlotAvailable()) {
			parkingLotOwner.notifyFull();
			trafficCop.notifyWhenLotIsFull();
		}
	}

	public void unPark(Car car) throws CarIsNotAvailableException {
		if (parkedCars.contains(car)) {
			if (parkingLotCapacity == parkedCars.size())
				trafficCop.notifyWhenSpaceIsAvailable();
			parkingLotAttendent.unParkTheCar(parkedCars, car);
		}
		else {
			throw new CarIsNotAvailableException();
		}
	}

	private boolean isParkingSlotAvailable() {
		if (parkingLotCapacity - parkedCars.size() > 0)
			return true;
		return false;
	}

}

package Parking.ParkingApp;

import org.junit.Before;
import org.junit.Test;
import Parking.ParkingApp.ParkingLot;
import Parking.ParkingApp.ParkingLotOwner;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import javax.naming.LimitExceededException;

public class ParkingLotTest {
	ParkingLotAttendent attendent;
	ParkingLot parkingLot;
	ParkingLotOwner owner;
	TrafficCop trafficCop;
	Car car = new Car();
	Car firstCar = new Car();
	Car secondCar = new Car();
	Car thirdCar = new Car();

	@Before
	public void init() {

		attendent = Mockito.spy(new ParkingLotAttendent());
		owner = Mockito.mock(ParkingLotOwner.class);
		trafficCop = Mockito.mock(TrafficCop.class);
		parkingLot = new ParkingLot(2);
		parkingLot.setParkingLotAttendent(attendent);
		parkingLot.setParkingLotOwner(owner);
		parkingLot.setTrafficCop(trafficCop);

	}

	@Test
	public void testIfCarIsParked() {
		assertDoesNotThrow(() -> parkingLot.park(car));
	}

	@Test
	public void testThrowsExceptionIfCarIsAlreadyParked() 
			throws LimitExceededException, CarIsAlreadyParkedException {
		parkingLot.park(car);

		assertThrows(CarIsAlreadyParkedException.class, () -> parkingLot.park(car));
	}

	@Test
	public void testThrowsExceptionIfParkingSlotIsNotAvailable()
			throws LimitExceededException, CarIsAlreadyParkedException {
		parkingLot.park(firstCar);
		parkingLot.park(secondCar);
		
		Exception actual = assertThrows(LimitExceededException.class, () -> parkingLot.park(thirdCar));
		
		assertEquals("No Parking Slot Is Available", actual.getMessage());
	}

	@Test
	public void testIfCarIsUnParked() 
			throws CarIsAlreadyParkedException, LimitExceededException {
		parkingLot.park(car);

		assertDoesNotThrow(() -> parkingLot.unPark(car));
	}

	@Test
	public void testThrowsExceptionIfTheCarToBeUnParkedIsNotAvailable() {
		assertThrows(CarIsNotAvailableException.class, () -> parkingLot.unPark(car));
	}

	@Test
	public void testIfTheOwnerIsNotifiedWhenParkingLotIsFull()
			throws CarIsAlreadyParkedException, LimitExceededException {
		parkingLot.park(car);
		parkingLot.park(firstCar);
		
		verify(owner).notifyFull();
	}

	@Test
	public void testIfTheCopIsNotifiedWhenParkingLotIsFull()
			throws CarIsAlreadyParkedException, LimitExceededException {
		parkingLot.park(car);
		parkingLot.park(firstCar);
		
		verify(trafficCop).notifyWhenLotIsFull();
	}

	@Test
	public void testIfTheCopIsNotifiedWhenSpaceIsAvailableInLot()
			throws CarIsNotAvailableException, LimitExceededException, CarIsAlreadyParkedException {
		parkingLot.park(car);
		parkingLot.park(firstCar);
		parkingLot.unPark(car);
		
		verify(trafficCop).notifyWhenSpaceIsAvailable();
	}
	
	@Test
	public void testIfAttendentParkedTheCar()
			throws LimitExceededException, CarIsAlreadyParkedException {
		parkingLot.park(car);

		verify(attendent).addCarToLot(parkingLot.getParkedCars(), car);
	}
}

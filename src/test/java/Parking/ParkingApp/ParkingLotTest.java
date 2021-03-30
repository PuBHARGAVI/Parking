package Parking.ParkingApp;


import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;

import Parking.ParkingApp.ParkingLot;
import Parking.ParkingApp.ParkingLotOwner;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.verify;
import javax.naming.LimitExceededException;
public class ParkingLotTest {
//	@Test
//	public void test() {
//	ParkingLot parkingLot = new ParkingLot(20);
//		Car car = new Car();
//		
//		assertDoesNotThrow(() -> parkingLot.park(car));
//    }
//
//	@Test
//	public void testThrowsExceptionIfCarIsAlreadyParked() throws LimitExceededException, CarIsAlreadyParkedException {
//		ParkingLot parkingLot = new ParkingLot(20);
//		Car car = new Car();
//
//		parkingLot.park(car);
//
//		assertThrows(CarIsAlreadyParkedException.class, () -> parkingLot.park(car));
//	}

//	@Test
//	public void testThrowsExceptionIfParkingSlotIsNotAvailable()
//			throws LimitExceededException, CarIsAlreadyParkedException {
//		ParkingLot parkingLot = new ParkingLot(2);
//		Car firstCar = new Car();
//		Car secondCar = new Car();
//		Car thirdCar = new Car();
//		parkingLot.park(firstCar);
//		parkingLot.park(secondCar);
//		Exception actual = assertThrows(LimitExceededException.class, () -> parkingLot.park(thirdCar));
//
//		assertEquals("No Parking Slot Is Available", actual.getMessage());
//	}

//	@Test
//	public void testIfCarIsUnParked() throws CarIsAlreadyParkedException, LimitExceededException {
//		ParkingLot parkingLot = new ParkingLot(3);
//		Car car = new Car();
//
//		parkingLot.park(car);
//
//		assertDoesNotThrow(() -> parkingLot.unPark(car));
//	}
//
//	@Test
//	public void testThrowsExceptionIfTheCarToBeUnParkedIsNotAvailable() {
//		ParkingLot parkingLot = new ParkingLot(3);
//		Car car = new Car();
//
//		assertThrows(CarIsNotAvailableException.class, () -> parkingLot.unPark(car));
//	}

//	@Test
//	public void testIfTheOwnerAndCopIsNotifiedWhenParkingLotIsFull()
//			throws CarIsAlreadyParkedException, LimitExceededException {
//		ParkingLot lot=new ParkingLot(1);
//		TrafficCop trafficCop=Mockito.mock(TrafficCop.class);
//		ParkingLotOwner owner=Mockito.mock(ParkingLotOwner.class);
//		lot.setTrafficCop(trafficCop);
//		lot.setParkingLotOwner(owner);
//		Car car = new Car();
//		lot.park(car);
//		verify(trafficCop).notifyWhenLotIsFull();
//		verify(owner).notifyFull();
//		
//	}
//	@Test
//	public void testIfTheCopIsNotifiedWhenSpaceIsAvailableinLot()
//			throws CarIsNotAvailableException, LimitExceededException, CarIsAlreadyParkedException {
//		ParkingLot lot=new ParkingLot(1);
//		TrafficCop trafficCop=Mockito.mock(TrafficCop.class);
//		ParkingLotOwner owner=Mockito.mock(ParkingLotOwner.class);
//		lot.setParkingLotOwner(owner);
//		lot.setTrafficCop(trafficCop);
//		Car car = new Car();
//		lot.park(car);
//		lot.unPark(car);
//		verify(trafficCop).notifyWhenSpaceIsAvailable();
//	}
	@Test
	public void test() throws LimitExceededException, CarIsAlreadyParkedException {
		ParkingLot lot=new ParkingLot(1);
		ParkingLotOwner owner=Mockito.mock(ParkingLotOwner.class);
		ParkingLotAttendar attendar=Mockito.mock(ParkingLotAttendar.class);
		lot.setParkingLotOwner(owner);
		Car car = new Car();
		lot.park(car);
		verify(owner).notifyWhencarEntersLot(car);
		verify(attendar).addCarToLot(car,1);
	}
}

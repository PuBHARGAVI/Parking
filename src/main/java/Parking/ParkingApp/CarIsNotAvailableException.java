package Parking.ParkingApp;

public class CarIsNotAvailableException extends Exception{
	public CarIsNotAvailableException(){
        super("Your car is not yet parked");
    }
}

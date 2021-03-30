package Parking.ParkingApp;

public abstract class ParkingLotOwner {

	public abstract void notifyFull();

	public void notifyWhencarEntersLot(Car car) {
		System.out.println("hudhg");
		ParkingLotAttendar attendar = new ParkingLotAttendar();
		int slotPosition=1;
		attendar.addCarToLot(car,slotPosition);
	}

}

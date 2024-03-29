package P2;

import java.awt.*;

/**
 * This class implements a customer's part of the Barbershop example.
 * This is a passive class just holding data.
 *
 * It should not be necessary to edit this class (but feel free to do so if you wish).
 */
public class Customer {
	/** The ID of the next customer to be created */
	public static int nextID = 0;
	/** The ID of this customer */
	private int customerID;
	/** An integer specifying the look of this customer, used by the GUI only */
	private int customerLook;
	private int waitingRoomSeat;

	/**
	 * Creates a new customer, giving him a unique ID and a random look.
	 */
	public Customer() {
		customerID = ++nextID;
		customerLook = (int)(Math.random() * Constants.NOF_CUSTOMER_LOOKS);
	}

	public void setWaitingRoomSeat(int waitingRoomSeat) {
		this.waitingRoomSeat = waitingRoomSeat;
	}

	/**
	 * Returns the ID of this customer.
	 * @return	The ID of this customer.
	 */
	public int getCustomerID() {
		return customerID;
	}

	public int getWaitingRoomSeat() {
		return waitingRoomSeat;
	}

	/**
	 * Returns an image with the look of this customer. Used by the GUI.
	 * @return	The image with the look of this customer.
	 */
	public Image getImage(){
		return BarbershopGui.customerImages[customerLook];
	}
}

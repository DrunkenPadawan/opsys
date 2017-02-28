import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	/**
	 * Creates a new customer queue. Make sure to save these variables in the class.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */

	PriorityQueue<Integer> openSeatsQueue;
	PriorityQueue<Customer> customerQueue;

    public CustomerQueue(int queueLength, Gui gui) {

		openSeatsQueue = new PriorityQueue<>(queueLength);
		for(int i = 0; i<Constants.NOF_CHAIRS;i++) {
			openSeatsQueue.add(i);
		}
        customerQueue = new PriorityQueue<>(queueLength, Comparator.comparingInt(Customer::getCustomerID));
        gui.println("Queue for open seats made");
	}

	public void addCustomer(Customer customer) {
        customerQueue.add(customer);
    }
    public int getSeatPositionOfLongestAwaitingCustomer(){
        Customer nextCustomerToGetCut;
        if(customerQueue.size()>0){
            return customerQueue.peek().getWaitingRoomSeat();
        }
        else {
            return -1;
        }
    }

    public Customer popCustomer(int position) {
        openSeatsQueue.add(position);
        return customerQueue.poll();
    }
	public int getNextOpenSeat(){
		return openSeatsQueue.poll();

	}

	public Customer next() {

		// Inclomplete
		return null;
	}
	public boolean isOpenSeat(){
    	return openSeatsQueue.size()>0;
	}

    public boolean isAvailableCustomer() {
	    return openSeatsQueue.size()!=18;
    }

	// Add more methods as needed
}

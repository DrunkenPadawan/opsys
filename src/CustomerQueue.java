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
	Customer[] theCustomers;
	int waitedTheLongestIndex=0;
	int nextPositionToInsertIndex;

    public int getNextPositionToInsertIndex() {
        return nextPositionToInsertIndex;
    }
    public CustomerQueue(int queueLength, Gui gui) {
        gui.println("Queue for open seats made");
        theCustomers = new Customer[18];
    }

	public void addCustomer(Customer newCustomer) {

        theCustomers[nextPositionToInsertIndex]=newCustomer;
        if (nextPositionToInsertIndex == 17) {
            nextPositionToInsertIndex = 0;
        }
        else{
            nextPositionToInsertIndex++;
        }
    }
	public boolean isOpenSeat(){
        return isCustomerAtPositionExist(nextPositionToInsertIndex);
    }
	public boolean isCustomerAtPositionExist(int pos){
        Customer c = theCustomers[pos];
        if (c == null){
            return true;
        }
        else{
            return false;
        }
    }
    public Customer getCustomerFromQueueIfAvailable(){
	    synchronized (this){
	        //customer is available
            if (isCustomerAtPositionExist(waitedTheLongestIndex)) {

                Customer toCut = theCustomers[waitedTheLongestIndex];
                System.out.println("Tried to get custoemr at pos:"+waitedTheLongestIndex);
                if (toCut == null) {
                    throw new RuntimeException();
                }
                toCut.setWaitingRoomSeat(waitedTheLongestIndex);

                theCustomers[waitedTheLongestIndex] = null;
                if(waitedTheLongestIndex==17){
                    waitedTheLongestIndex = 0;
                }
                else{
                    waitedTheLongestIndex++;
                }

                return toCut;
            }
            else{

                return null;
            }
        }
    }


	// Add more methods as needed
}

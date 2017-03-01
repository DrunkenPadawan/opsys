import java.util.PriorityQueue;

/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 * One doorman instance corresponds to one producer in
 * the producer/consumer problem.
 */
public class Doorman implements Runnable {
	/**
	 * Creates a new doorman. Make sure to save these variables in the class.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	CustomerQueue customerQueue;
	Gui gui;
	Thread thread;
	boolean running;

	public Doorman(CustomerQueue queue, Gui gui) {
        customerQueue = queue;
        this.gui = gui;
        thread = new Thread(this);
        gui.println("A doorman thread was created");
	}

	/**
	 * This is the code that will run when a new thread is
	 * created for this instance.
	 */
	@Override
	public void run(){
		// Incomplete
        gui.println("A doorman thread is running");

        while (running){
            //gui.println("Doorman doing some doormaning");
            if(customerQueue.isOpenSeat()){;
                Customer newCustomer = new Customer();
                gui.fillLoungeChair(customerQueue.getNextPositionToInsertIndex(),newCustomer );
                customerQueue.addCustomer(newCustomer);
                gui.println("Added to wait at seat: " + newCustomer.getWaitingRoomSeat() + ", id:" + newCustomer.getCustomerID());
            }
            try{
                long sleepTime = getRandomSleep(Constants.MIN_DOORMAN_SLEEP, Globals.doormanSleep);
                thread.sleep(Globals.doormanSleep);
            }
            catch (InterruptedException e){
                gui.println("Exception for doorman sleep: " + e);
            }

        }

	}


	/**
	 * Starts the doorman running as a separate thread. Make
	 * sure to create the thread and start it.
	 */
	public void startThread() {
		// Incomplete
        gui.println("A doorman thread was started");
        running = true;
        thread.start();


    }

	/**
	 * Stops the doorman thread. Use Thread.join() for stopping
	 * a thread.
	 */
	public void stopThread() {
		running = false;
        try {
            //spør hvorfor dette er lurt
            thread.join();
        } catch (Exception e) {
            gui.println(e.getMessage().toString());
        }
    }

    public static long getRandomSleep(int min, int max) {
	    return min+(int)(Math.random()*(max-min+1));
    }

	// Add more methods as needed
}

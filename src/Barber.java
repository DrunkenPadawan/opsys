/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 * One barber instance corresponds to one consumer in
 * the producer/consumer problem.
 */
public class Barber implements Runnable {
	/**
	 * Creates a new barber. Make sure to save these variables in the class.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	CustomerQueue queue;
	Gui gui;
	boolean running;
	Thread thread;
	int barbID;
	public Barber(CustomerQueue queue, Gui gui, int pos) {
		this.queue = queue;
		this.gui = gui;
		barbID = pos;
		thread = new Thread(this);
		gui.println("A barber was created with id:"+ pos);
	}

	/**
	 * This is the code that will run when a new thread is
	 * created for this instance.
	 */
	@Override
	public void run(){
		while (running){
		    gui.println("barb "+ barbID + "running"); //hvis denne linjen fjernes så fucker threadsa seg opp
			if(queue.isAvailableCustomer()){
			    int customerSeatPosition = queue.getSeatPositionOfLongestAwaitingCustomer();
			    if(customerSeatPosition==-1){
			        gui.println("BAD CODING"); //prøv å finn en løsning på dette
                }
                else{
                    Customer customerToBeCut = queue.popCustomer(customerSeatPosition);
                    gui.println("Barb "+barbID+" cuts cust from pos:" +customerSeatPosition+ ", id:"+customerToBeCut.getCustomerID() );

                    gui.emptyLoungeChair(customerSeatPosition);
                    gui.fillBarberChair(barbID,customerToBeCut);

                    //cut customer
                    long cutTime = Doorman.getRandomSleep(Constants.MIN_BARBER_WORK, Constants.MAX_BARBER_WORK);
                    try {
                        Thread.sleep(cutTime);
                    } catch (InterruptedException e) {

                    }

                    //release customer
                    gui.emptyBarberChair(barbID);
                    gui.barberIsSleeping(barbID);
                    //daydream
                    long dayDreamTime = Doorman.getRandomSleep(Constants.MIN_BARBER_SLEEP,Constants.MAX_BARBER_SLEEP);
                    try {
                        Thread.sleep(dayDreamTime);
                    } catch (InterruptedException e) {

                    }
                    gui.barberIsAwake(barbID);
                }

			}



		}
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {

		running = true;
		thread.start();
		// Incomplete
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		running = false;
		// Incomplete
		try {
			//spør hvorfor dette er lurt
			thread.join();
		} catch (Exception e) {
			gui.println(e.getMessage().toString());
		}
	}

	// Add more methods as needed
}


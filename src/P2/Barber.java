package P2;

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
            Customer customerToCut = queue.getCustomerFromQueueIfAvailable();
            if(customerToCut != null){
                gui.emptyLoungeChair(customerToCut.getWaitingRoomSeat());
                gui.fillBarberChair(barbID,customerToCut);

                long cutTime = Doorman.getRandomSleep(Constants.MIN_BARBER_WORK, Globals.barberWork);
                try {
                    Thread.sleep(Globals.barberWork);
                } catch (InterruptedException e) {
                    gui.println("Exception for cut sleep: " + e);
                }

                //release customer
                gui.emptyBarberChair(barbID);
                gui.barberIsSleeping(barbID);
                //daydream
                //long dayDreamTime = Doorman.getRandomSleep(Constants.MIN_BARBER_SLEEP,Globals.barberSleep);
                try {
                    Thread.sleep(Globals.barberSleep);
                } catch (InterruptedException e) {
                    gui.println("Exception for dream sleep: " + e);
                }
                gui.barberIsAwake(barbID);

            }
            else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
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


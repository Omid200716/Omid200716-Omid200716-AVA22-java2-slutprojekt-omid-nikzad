package model;

import java.util.List;

import controller.Controller;
import itemsAndBuffer.Buffer;
import itemsAndBuffer.Item;

public class Producer implements Runnable {
	private Buffer sharedBuffer;
	private Controller controller;

	public Producer(Buffer sharedBuffer, Controller controller) {
		this.sharedBuffer = sharedBuffer;
		this.controller = controller;
	}

	

	public void assignController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void run() {
		while (true) {
			try {
				int sleepTime = (1 + (int) (Math.random() * 9)) * 1000;
				Thread.sleep(sleepTime);
				String producedItem = ("" + (char) ((int) (Math.random() * 100)));
				sharedBuffer.add(new Item(producedItem));
				System.out.println("Producer produced item in " + sleepTime / 1000 + " seconds");
				if (controller != null) {
					controller.manageProducer();
				}
			} catch (InterruptedException e) {
				System.out.println("Producer removed");
				break;
			}
		}
	}

	public void spawnThread() {
		Producer dataProducer = new Producer(sharedBuffer, controller);
		Thread producerThread = new Thread(dataProducer);
		producerThread.start();
		controller.logWorkerCount();
	}

}
package model;

import controller.Controller;
import itemsAndBuffer.Buffer;

public class Consumer implements Runnable {
	Buffer sharedBuffer = null;
	boolean isActive = true;
	private Controller controller;

	public Consumer(Buffer sharedBuffer, Controller controller) {
		this.sharedBuffer = sharedBuffer;
		this.controller = controller;
	}

	@Override
	public void run() {
		while (isActive) {
			try {
				Thread.sleep((1 + (int) (Math.random() * 9)) * 1000);
				sharedBuffer.removeItem();

				//controller.manageProducer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

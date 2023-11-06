package controller;

import itemsAndBuffer.Buffer;
import model.Consumer;
import model.Producer;
import view.GUI;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
	private Producer producer;
	private Consumer consumer;
	private Buffer sharedBuffer;
	private GUI gui;
	private String logFilePath = "log.txt";
	private Timer averageTimer;
	private final long averageInterval = 10000;
	private List<Thread> producerThreadsList; // Lägg till denna rad

	public Controller() {

		sharedBuffer = new Buffer();
		producer = new Producer(sharedBuffer, this);
		consumer = new Consumer(sharedBuffer, this);
		gui = new GUI(this);
		producerThreadsList = new ArrayList<>(); // Och initiera den här

		producer.assignController(this);
		gui.assignController(this);

		for (int i = 0; i < (3 + (int) (Math.random() * 13)); i++) {
			new Thread(new Consumer(sharedBuffer, this)).start();
		}
	}

	public void logActivity(String message) {
		//System.out.println("logActivity called with message:" + message);
		gui.logActivity(message);
		logToFile(message);

	}

	public void logToFile(String message) {
		System.out.println("logToFile called with message: " + message);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
			writer.write(message);
			writer.newLine();
			//System.out.println("Log file path: " + new File(logFilePath).getAbsolutePath());
		} catch (IOException e) {
			System.out.println("Could not write to log file: " + logFilePath);
			e.printStackTrace();
		}
	}

	public void removeProducer() {
		if (!producerThreadsList.isEmpty()) {
			Thread terminatedThread = producerThreadsList.remove(producerThreadsList.size() - 1);
			terminatedThread.interrupt();
			gui.logActivity("Worker removed. Remaining workers: " + producerThreadsList.size());
			logActivity("Worker removed. Remaining workers: " + producerThreadsList.size());
		}
		logWorkerCount();

	}

	public void initiateThread() {
		Thread producerThread = new Thread(new Producer(sharedBuffer, this));
		// producer.spawnThread(producerThreadsList); // Skicka listan som en parameter

		producerThreadsList.add(producerThread);
		producerThread.start();
	}

	public void manageProducer() {
		int bufferPercentage = Math.min((sharedBuffer.size() * 100) / 50, 100);
		gui.logActivity("Buffer is at " + bufferPercentage + "% capacity.");
		gui.updateBufferDisplay(bufferPercentage);
		logActivity("Buffer is at " + bufferPercentage + "% capacity.");
	}

	public void logWorkerCount() {
		gui.logActivity("Antal arbetare: " + producerThreadsList.size());
		logActivity("Antal arbetare: " + producerThreadsList.size());

	}

	public List<Thread> getProducerThreadsList() {
		return producerThreadsList;
	}
	
	public void startAverageTimer() {
		if(averageTimer != null) {
			averageTimer.cancel();
		}
		averageTimer = new Timer();
		TimerTask averageTask = new TimerTask() {

			@Override
			public void run() {
				
				int bufferAverage = calculateBufferAverage();
				logActivity("Genomsnittligt antal enheter: " + bufferAverage);
			}
			
		};
		averageTimer.scheduleAtFixedRate(averageTask, 0, averageInterval);
	}
	
	public void stopAverageTimer() {
		if(averageTimer != null) {
			averageTimer.cancel();
			averageTimer = null;
		}
	}
	
	private int calculateBufferAverage() {
		int currentBufferSize = sharedBuffer.size();
		return currentBufferSize;
	}

}

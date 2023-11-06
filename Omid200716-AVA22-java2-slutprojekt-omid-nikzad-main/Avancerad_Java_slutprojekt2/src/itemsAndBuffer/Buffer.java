package itemsAndBuffer;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

	private Queue<Item> itemQueue = new LinkedList<>();
	

	public Queue<Item> getItemQueue() {
		return itemQueue;
	}



	public void setItemQueue(Queue<Item> itemQueue) {
		this.itemQueue = itemQueue;
	}



	public synchronized void add(Item newItem) {
		itemQueue.add(newItem);

		notify();
		System.out.println(" Added item " + newItem + "Buffer size: " + itemQueue.size());
	}
	


	public synchronized Item removeItem() {
		while (itemQueue.isEmpty()) {
			try {
				wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return itemQueue.remove();
	}

	public synchronized int size() {
		return itemQueue.size();
	}

}

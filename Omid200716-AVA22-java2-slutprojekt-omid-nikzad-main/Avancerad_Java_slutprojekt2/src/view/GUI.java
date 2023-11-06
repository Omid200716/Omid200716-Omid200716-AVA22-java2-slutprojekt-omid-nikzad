package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
	private Controller controller;
	private JTextArea logArea;
	private JProgressBar progressBar;
	private JButton addProducerButton;
	private JButton removeProducerButton;
	private JLabel bufferLabel;

	public GUI(Controller controller) {
		this.controller = controller;
		setupUI();
	}

	private void setupUI() {
		// Inställningar för huvudfönstret
		setTitle("Produktionsregulator");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Skapa GUI-komponenter
		
		
		
		logArea = new JTextArea();
		logArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(logArea);

		progressBar = new JProgressBar(0, 100);
		progressBar.setStringPainted(true);

		addProducerButton = new JButton("Lägg till Arbetare");
		removeProducerButton = new JButton("Ta bort Arbetare");

		// Lägg till action listeners
		addProducerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.initiateThread();
				
				logActivity(" add workers: " + controller.getProducerThreadsList().size());
			}
		});

		removeProducerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.removeProducer();
			}
		});

		// Lägg till komponenter till layout
	//	bufferLabel = new JLabel("Buffer is at 0% capacity");
		JPanel panel = new JPanel();
		
		//panel.setLayout(new BorderLayout());
		//panel.add(bufferLabel, BorderLayout.NORTH);
		
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(progressBar, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addProducerButton);
		buttonPanel.add(removeProducerButton);

		panel.add(buttonPanel, BorderLayout.SOUTH);

		// Lägg till panelen till fönstret
		add(panel);

		// Visa fönstret
		setVisible(true);
	}
	
	

	public void logActivity(String message) {
		logArea.append(message + "\n");
	}

	public void updateBufferDisplay(int bufferPercentage) {
		progressBar.setValue(bufferPercentage);
	
		
		if (bufferPercentage < 10) {
			progressBar.setForeground(Color.RED);
		} else if (bufferPercentage > 90) {
			progressBar.setForeground(Color.GREEN);
		} else {
			progressBar.setForeground(Color.YELLOW);
		}
	}

	public void assignController(Controller controller) {
		this.controller = controller;
	}

}

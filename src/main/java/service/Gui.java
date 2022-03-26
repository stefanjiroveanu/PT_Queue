package service;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class Gui {

	private JFrame frame;
	private JLabel clientsLabel;
	private JTextField clientField;
	private JTextField queueField;
	private JTextField minimumArrivalField;
	private JTextField maximumArrivalField;
	private JTextField minServiceField;
	private JLabel maxServiceTimeLabel;
	private JTextField maxServiceField;
	private JTextField simulationField;
	private JButton startButton;
	private JLabel minProcesTimeLabel;
	private JLabel maxArrivalTimeLabel;
	private JLabel minimumArrivalTimeLabel;
	private JLabel simulationLabel;
	private JLabel queueLabel;
	private JLabel policyLabel;
	private JComboBox comboBox;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(10, 10, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		clientsLabel = new JLabel("Number of Clients");
		clientsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		clientsLabel.setBounds(79, 105, 150, 31);
		frame.getContentPane().add(clientsLabel);
		
		queueLabel = new JLabel("Number of Queues");
		queueLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		queueLabel.setBounds(79, 146, 150, 31);
		frame.getContentPane().add(queueLabel);
		
		simulationLabel = new JLabel("Simulation Interval");
		simulationLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		simulationLabel.setBounds(79, 351, 150, 31);
		frame.getContentPane().add(simulationLabel);
		
		clientField = new JTextField();
		clientField.setBounds(239, 109, 209, 27);
		frame.getContentPane().add(clientField);
		clientField.setColumns(10);
		
		queueField = new JTextField();
		queueField.setColumns(10);
		queueField.setBounds(239, 150, 209, 27);
		frame.getContentPane().add(queueField);
		
		minimumArrivalTimeLabel = new JLabel("Min Arrival Time");
		minimumArrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		minimumArrivalTimeLabel.setBounds(79, 187, 150, 31);
		frame.getContentPane().add(minimumArrivalTimeLabel);
		
		minimumArrivalField = new JTextField();
		minimumArrivalField.setColumns(10);
		minimumArrivalField.setBounds(239, 191, 209, 27);
		frame.getContentPane().add(minimumArrivalField);
		
		maxArrivalTimeLabel = new JLabel("Max Arrival Time");
		maxArrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		maxArrivalTimeLabel.setBounds(79, 228, 150, 31);
		frame.getContentPane().add(maxArrivalTimeLabel);
		
		maximumArrivalField = new JTextField();
		maximumArrivalField.setColumns(10);
		maximumArrivalField.setBounds(239, 232, 209, 27);
		frame.getContentPane().add(maximumArrivalField);
		
		minProcesTimeLabel = new JLabel("Min Service Time");
		minProcesTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		minProcesTimeLabel.setBounds(79, 269, 150, 31);
		frame.getContentPane().add(minProcesTimeLabel);
		
		minServiceField = new JTextField();
		minServiceField.setColumns(10);
		minServiceField.setBounds(239, 273, 209, 27);
		frame.getContentPane().add(minServiceField);
		
		maxServiceTimeLabel = new JLabel("Max Service Time");
		maxServiceTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		maxServiceTimeLabel.setBounds(79, 310, 150, 31);
		frame.getContentPane().add(maxServiceTimeLabel);
		
		maxServiceField = new JTextField();
		maxServiceField.setColumns(10);
		maxServiceField.setBounds(239, 314, 209, 27);
		frame.getContentPane().add(maxServiceField);
		
		simulationField = new JTextField();
		simulationField.setColumns(10);
		simulationField.setBounds(239, 355, 209, 27);
		frame.getContentPane().add(simulationField);
		
		startButton = new JButton("Start");
		startButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startButton.setBounds(200, 500, 123, 52);
		frame.getContentPane().add(startButton);

		textArea = new JTextArea();
		textArea.setEditable(false);
		//textArea.setBounds(654, 20, 522, 743);
		//frame.getContentPane().add(textArea);
		
		policyLabel = new JLabel("Policy");
		policyLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		policyLabel.setBounds(79, 392, 150, 31);
		frame.getContentPane().add(policyLabel);

		Vector<String> v = new Vector<>();
		v.add("SHORTEST_TIME");
		v.add("SHORTEST_QUEUE");
		comboBox = new JComboBox(v);
		comboBox.setBounds(239, 394, 209, 31);

		frame.getContentPane().add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(654, 20, 522, 743);
		scrollPane.setViewportView(textArea);
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);
	}
	void addStartButtonActionListener(ActionListener l){
		this.startButton.addActionListener(l);
	}

	public Integer getClientField() {
		return Integer.parseInt(clientField.getText());
	}

	public Integer getMaximumArrivalField() {
		return Integer.parseInt(maximumArrivalField.getText());
	}

	public Integer getMaxServiceField() {
		return Integer.parseInt(maxServiceField.getText());
	}

	public Integer getMinServiceField() {
		return Integer.parseInt(minServiceField.getText());
	}

	public Integer getMinimumArrivalField() {
		return Integer.parseInt(minimumArrivalField.getText());
	}

	public Integer getQueueField() {
		return Integer.parseInt(queueField.getText());
	}

	public Integer getSimulationField() {
		return Integer.parseInt(simulationField.getText());
	}

	public String getComboBox() {
		return comboBox.getSelectedItem().toString();
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}

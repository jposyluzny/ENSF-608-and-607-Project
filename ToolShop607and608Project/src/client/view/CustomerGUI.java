package client.view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerGUI {

	private JButton searchButton, clearSearchButton, saveButton, deleteButton, clearButton;
	private JRadioButton searchByClientId, searchByLastName, searchByClientType;
	private JTextField parameterField;
	private JTextArea resultsArea;
	
	public CustomerGUI() {
		this.buildGUI();
	}
	
	public void setBoldLabelFont(JLabel label) {
		Font font = new Font(label.getFont().getName(), Font.BOLD,label.getFont().getSize());
		label.setFont(font);
	}
	
	public void buildGUI() {
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5,1));
		JLabel searchClients = new JLabel("Search Clients", SwingConstants.CENTER);
		setBoldLabelFont(searchClients);
		panel1.add(searchClients);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(5,1));
		JLabel selectSearchType = new JLabel("Select type of search to be performed:");
		searchByClientId = new JRadioButton("Client ID");
		searchByLastName = new JRadioButton("Last Name");
		searchByClientType = new JRadioButton("Client Type");
		JLabel enterParameter = new JLabel("Enter search parameter below");
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(searchByClientId);
		radioButtonGroup.add(searchByLastName);
		radioButtonGroup.add(searchByClientType);
		panel2.add(selectSearchType);
		panel2.add(searchByClientId);
		panel2.add(searchByLastName);
		panel2.add(searchByClientType);
		panel2.add(enterParameter);
		panel1.add(panel2);
		
		JPanel panel3 = new JPanel();
		parameterField = new JTextField(20);
		searchButton = new JButton("Search");
		clearSearchButton = new JButton("Clear Search");
		panel3.add(parameterField);
		panel3.add(searchButton);
		panel3.add(clearSearchButton);
		panel1.add(panel3);
		
		
		JPanel panel5 = new JPanel();
		JLabel searchResults = new JLabel("Results:");
		panel5.add(searchResults);
		panel1.add(panel5);

		JPanel panel6 = new JPanel();
		resultsArea = new JTextArea();
		resultsArea.setEditable(false);
		resultsArea.setPreferredSize(new Dimension(400,200));
		JScrollPane resultsPane = new JScrollPane(resultsArea);
		resultsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		resultsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		panel6.add(resultsArea);
		panel5.add(panel6);
		panel1.add(panel5);

		JPanel panel7 = new JPanel();
		panel7.setLayout(new GridLayout(3,1));
		JLabel clientInformation = new JLabel("Client Information", SwingConstants.CENTER);
		setBoldLabelFont(clientInformation);
		panel7.add(clientInformation);
		
		JPanel panel8 = new JPanel();
		panel8.setLayout(new GridLayout(7,2));
		JLabel clientId = new JLabel("Client ID:");
		JTextArea clientIdBox = new JTextArea(1, 20);
		clientIdBox.setBorder(border);
		clientIdBox.setEditable(false);
		JLabel firstName = new JLabel("First Name:");
		JTextArea firstNameBox = new JTextArea(1,20);
		firstNameBox.setBorder(border);
		JLabel lastName = new JLabel("Last Name:");
		JTextArea lastNameBox = new JTextArea(1,20);
		lastNameBox.setBorder(border);
		JLabel address = new JLabel("Address:");
		JTextArea addressBox = new JTextArea(1,20);
		addressBox.setBorder(border);
		JLabel postalCode = new JLabel("Postal Code: ");
		JTextArea postalCodeBox = new JTextArea(1,20);
		postalCodeBox.setBorder(border);
		JLabel phoneNumber = new JLabel("Phone Number:");
		JTextArea phoneNumberBox = new JTextArea(1,20);
		phoneNumberBox.setBorder(border);
		JLabel clientType = new JLabel("Client Type:");
		JTextArea typeBox = new JTextArea(1,20);
		typeBox.setBorder(border);
		
		panel8.add(clientId);
		panel8.add(clientIdBox);
		panel8.add(firstName);
		panel8.add(firstNameBox);
		panel8.add(lastName);
		panel8.add(lastNameBox);
		panel8.add(address);
		panel8.add(addressBox);
		panel8.add(postalCode);
		panel8.add(postalCodeBox);
		panel8.add(phoneNumber);
		panel8.add(phoneNumberBox);
		panel8.add(clientType);
		panel8.add(typeBox);	
		panel7.add(panel8);

		JPanel panel9 = new JPanel();
		saveButton = new JButton("Save");
		deleteButton = new JButton("Delete");
		clearButton = new JButton("Clear");
		panel9.add(saveButton);
		panel9.add(deleteButton);
		panel9.add(clearButton);
		panel7.add(panel9);
		
		JPanel leftPanel = new JPanel();
		leftPanel.add(panel1);
		
		JPanel rightPanel = new JPanel();
		rightPanel.add(panel7);

		final JFrame frame = new JFrame("Customer Management Screen");
		frame.getContentPane().add("West", leftPanel);
		frame.getContentPane().add("East", rightPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		CustomerGUI cust = new CustomerGUI();
	}
}

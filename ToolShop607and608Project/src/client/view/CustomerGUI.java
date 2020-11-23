package client.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerGUI {

	private JButton searchButton, clearSearchButton, saveButton, deleteButton, clearButton, addCustomerButton;
	private JRadioButton searchByClientId, searchByLastName, searchByClientType;
	private JTextField parameterField, clientIdBox, firstNameBox, lastNameBox, addressBox, postalCodeBox, phoneNumberBox, typeBox;
	private JList<String> resultsList;

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
		panel1.setMaximumSize( panel1.getPreferredSize() );

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
		resultsList = new JList<String>();
		resultsList.setVisibleRowCount(5);
		resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel6.add(new JScrollPane(resultsList));
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
		clientIdBox = new JTextField(20);
		clientIdBox.setBorder(border);
		clientIdBox.setBackground(Color.WHITE);
		JLabel firstName = new JLabel("First Name:");
		firstNameBox = new JTextField(20);
		firstNameBox.setBorder(border);
		JLabel lastName = new JLabel("Last Name:");
		lastNameBox = new JTextField(20);
		lastNameBox.setBorder(border);
		JLabel address = new JLabel("Address:");
		addressBox = new JTextField(20);
		addressBox.setBorder(border);
		JLabel postalCode = new JLabel("Postal Code: ");
		postalCodeBox = new JTextField(20);
		postalCodeBox.setBorder(border);
		JLabel phoneNumber = new JLabel("Phone Number:");
		phoneNumberBox = new JTextField(20);
		phoneNumberBox.setBorder(border);
		JLabel clientType = new JLabel("Client Type:");
		typeBox = new JTextField(20);
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
		addCustomerButton = new JButton("Add");
		saveButton = new JButton("Save");
		deleteButton = new JButton("Delete");
		clearButton = new JButton("Clear");
		panel9.add(addCustomerButton);
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
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
	
	public void addSearchListener(ActionListener a) {
		this.getSearchButton().addActionListener(a);
	}
	
	public void addAddCustomerLitener(ActionListener a) {
		this.getAddCustomerButton().addActionListener(a);
	}
	
	public void addSaveListener(ActionListener a) {
		this.getSaveButton().addActionListener(a);
	}
	
	public void addDeleteListener(ActionListener a) {
		this.getDeleteButton().addActionListener(a);
	}
	
	public void addClearSearchListener(ActionListener a) {
		this.getClearSearchButton().addActionListener(a);
	}
	
	public void addClearClientInfoListener(ActionListener a) {
		this.getClearButton().addActionListener(a);
	}
	
	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	public JButton getClearSearchButton() {
		return clearSearchButton;
	}

	public void setClearSearchButton(JButton clearSearchButton) {
		this.clearSearchButton = clearSearchButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public JButton getClearButton() {
		return clearButton;
	}

	public void setClearButton(JButton clearButton) {
		this.clearButton = clearButton;
	}

	public JRadioButton getSearchByClientId() {
		return searchByClientId;
	}

	public void setSearchByClientId(JRadioButton searchByClientId) {
		this.searchByClientId = searchByClientId;
	}

	public JRadioButton getSearchByLastName() {
		return searchByLastName;
	}

	public void setSearchByLastName(JRadioButton searchByLastName) {
		this.searchByLastName = searchByLastName;
	}

	public JRadioButton getSearchByClientType() {
		return searchByClientType;
	}

	public void setSearchByClientType(JRadioButton searchByClientType) {
		this.searchByClientType = searchByClientType;
	}

	public JTextField getParameterField() {
		return parameterField;
	}

	public void setParameterField(JTextField parameterField) {
		this.parameterField = parameterField;
	}

	public JList<String> getResultsList() {
		return resultsList;
	}

	public void setResultsList(JList<String> resultsList) {
		this.resultsList = resultsList;
	}

	public JTextField getClientIdBox() {
		return clientIdBox;
	}

	public void setClientIdBox(JTextField clientIdBox) {
		this.clientIdBox = clientIdBox;
	}

	public JTextField getFirstNameBox() {
		return firstNameBox;
	}

	public void setFirstNameBox(JTextField firstNameBox) {
		this.firstNameBox = firstNameBox;
	}

	public JTextField getLastNameBox() {
		return lastNameBox;
	}

	public void setLastNameBox(JTextField lastNameBox) {
		this.lastNameBox = lastNameBox;
	}

	public JTextField getAddressBox() {
		return addressBox;
	}

	public void setAddressBox(JTextField addressBox) {
		this.addressBox = addressBox;
	}

	public JTextField getPostalCodeBox() {
		return postalCodeBox;
	}

	public void setPostalCodeBox(JTextField postalCodeBox) {
		this.postalCodeBox = postalCodeBox;
	}

	public JTextField getPhoneNumberBox() {
		return phoneNumberBox;
	}

	public void setPhoneNumberBox(JTextField phoneNumberBox) {
		this.phoneNumberBox = phoneNumberBox;
	}

	public JTextField getTypeBox() {
		return typeBox;
	}

	public void setTypeBox(JTextField typeBox) {
		this.typeBox = typeBox;
	}

	public JButton getAddCustomerButton() {
		return addCustomerButton;
	}

	public void setAddCustomerButton(JButton addCustomerButton) {
		this.addCustomerButton = addCustomerButton;
	}

}

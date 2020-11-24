/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class will handle building the physical GUI to handle user interaction with Tools.
 */
public class InventoryGUI {
	
	private JButton searchButton, listAllToolsButton;
	private JRadioButton searchByNameRadioButton, searchByIDRadioButton, checkQuantityRadioButton, decreaseQuantityRadioButton;
	private JTextField searchParamField;
	private JTextArea resultsArea;
	
	/**
	 * The InventoryGUI constructor will call the build() method.
	 */
	public InventoryGUI () {
		this.buildGUI();
	}
	
	/**
	 * This will construct and display the GUI.
	 */
	public void buildGUI() {	
		JPanel panel1 = new JPanel();
		JLabel enterParamsLabel = new JLabel("Enter the search parameter below");
		searchParamField = new JTextField(20);
		searchButton = new JButton("Search");
		
		panel1.add(searchParamField);
		panel1.add(searchButton);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(8,1));
		JLabel searchOptionsLabel = new JLabel("Select type of search to be performed");
		listAllToolsButton = new JButton("List all Tools");
		searchByNameRadioButton = new JRadioButton("Search for Tool by name");
		searchByIDRadioButton = new JRadioButton("Search for Tool by ID");
		checkQuantityRadioButton = new JRadioButton("Check item quantity by Tool name");
		decreaseQuantityRadioButton = new JRadioButton("Decrease Tool quantity by Tool name");
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(searchByNameRadioButton);
		radioButtonGroup.add(searchByIDRadioButton);
		radioButtonGroup.add(checkQuantityRadioButton);
		radioButtonGroup.add(decreaseQuantityRadioButton);
		
		panel2.add(searchOptionsLabel);
		panel2.add(listAllToolsButton);
		panel2.add(searchByNameRadioButton);
		panel2.add(searchByIDRadioButton);
		panel2.add(checkQuantityRadioButton);
		panel2.add(decreaseQuantityRadioButton);
		panel2.add(enterParamsLabel);
		panel2.add(panel1);
		
		JPanel panel3 = new JPanel();
		resultsArea = new JTextArea(15, 40);
		resultsArea.setEditable(false);
		JScrollPane resultsPane = new JScrollPane(resultsArea);
		resultsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		resultsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		
		panel3.add(resultsPane);
		
		final JFrame frame = new JFrame("Inventory Manager Screen");
		frame.getContentPane().add("West", panel2);
		frame.getContentPane().add("East", panel3);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true); 
	}
	
	/**
	 * This will add an actionListener to search for all tools button object.
	 * @param a is the class implementing the ActionListener interface and overriding the actionPerformed method.
	 */
	public void addSearchForAllToolsListener(ActionListener a) {
		this.getListAllToolsButton().addActionListener(a);
	}
	
	/**
	 * This will add an actionListener to search button object.
	 * @param a is the class implementing the ActionListener interface and overriding the actionPerformed method.
	 */
	public void addSearchListener(ActionListener a) {
		this.getSearchButton().addActionListener(a);
	}
	
	public JButton getListAllToolsButton() {
		return listAllToolsButton;
	}

	public void setListAllToolsButton(JButton listAllToolsButton) {
		this.listAllToolsButton = listAllToolsButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}
	
	public JRadioButton getSearchByNameRadioButton() {
		return searchByNameRadioButton;
	}

	public void setSearchByNameRadioButton(JRadioButton searchByNameRadioButton) {
		this.searchByNameRadioButton = searchByNameRadioButton;
	}

	public JRadioButton getSearchByIDRadioButton() {
		return searchByIDRadioButton;
	}

	public void setSearchByIDRadioButton(JRadioButton searchByIDRadioButton) {
		this.searchByIDRadioButton = searchByIDRadioButton;
	}

	public JRadioButton getCheckQuantityRadioButton() {
		return checkQuantityRadioButton;
	}

	public void setCheckQuantityRadioButton(JRadioButton checkQuantityRadioButton) {
		this.checkQuantityRadioButton = checkQuantityRadioButton;
	}

	public JRadioButton getDecreaseQuantityRadioButton() {
		return decreaseQuantityRadioButton;
	}

	public void setDecreaseQuantityRadioButton(JRadioButton decreaseQuantityRadioButton) {
		this.decreaseQuantityRadioButton = decreaseQuantityRadioButton;
	}

	public JTextField getSearchParamField() {
		return searchParamField;
	}

	public void setSearchParamField(JTextField searchParamField) {
		this.searchParamField = searchParamField;
	}

	public JTextArea getResultsArea() {
		return resultsArea;
	}
	
}

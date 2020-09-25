import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/* This class is the GUI
 * 
 * Author: James Zhou
 */
public class GUI{
	
	private JLabel label;
	private JLabel label2;
	private JLabel outputLabel;
	private JFrame frame;
	private JPanel panel;
	private JButton calculate;
	private JTextField trainer;
	private JTextField inputText;
	private JTextField suggestionsAll;
	private AutocompleteProvider auto;
	private List<Candidate> words;
	private String inputStr;
	private String output;
	
	public GUI() {//creating the frame
		frame = new JFrame();
		
		//labels
		label = new JLabel("Enter text to be trained");
		label2 = new JLabel("Enter text to suggest words");
		outputLabel = new JLabel("Suggestions:");
		
		//textFields
		trainer = new JTextField(30);
		inputText = new JTextField();
		suggestionsAll = new JTextField();
		//inputText.addActionListener(updateText());
		
        //buttons
		calculate = new JButton("Suggest");
		calculate.addActionListener(suggest());
	
        //creating the panel
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(label);
		panel.add(trainer);
		panel.add(label2);
		panel.add(inputText);
		panel.add(calculate);
		panel.add(outputLabel);
		panel.add(suggestionsAll);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("AutoCompleter");
		frame.pack();
		frame.setVisible(true);
		
	}
	
	//main method
	public static void main(String[] args) {
		new GUI();
	}
	
	/*
	 * executes when the button is pressed runs the autocomplete provider 
	 * and sets
	 * the output string to be the candidates in order of confidence
	 */
	private ActionListener suggest() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				auto = new AutocompleteProvider();
				auto.train(trainer.getText());
				inputStr = inputText.getText(); 
				output = auto.getSuggestions(inputStr);
				  
				suggestionsAll.setText(""); 
				suggestionsAll.setText(output);
				 
			}
		};
	}
}

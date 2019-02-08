import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RPNGUI {
	
	// The GUI frame
	JFrame frame;
	// The text box at the top for display
	JTextArea displayArea;
	// A string for the scratchpad where things are actually entered
	String scratchpad = "";
	// A reference to the calculator so methods can be called
	RPNGUICalc calc;
	
	public RPNGUI(RPNGUICalc calcu) {
		// Create the GUI Frame
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Get the calculator object
		calc = calcu;
		calc.recieveGUI(this);
		
		// All the buttons
		
		// the main panel area
		JPanel mainPanel = new JPanel();
		// Create the grid layout for the buttons and set the panel to that layout
		
		GridLayout keypad = new GridLayout(4, 3);
		mainPanel.setLayout(keypad);
		
		// Array of buttons on the main keypad in the order 7 8 9 4 5 6 1 2 3 ^ 0 . plus other commands
		Component keypadArray[] = {
				mainPanel.add(new JButton("7")),
				mainPanel.add(new JButton("8")),
				mainPanel.add(new JButton("9")),
				mainPanel.add(new JButton("4")),
				mainPanel.add(new JButton("5")),
				mainPanel.add(new JButton("6")),
				mainPanel.add(new JButton("1")),
				mainPanel.add(new JButton("2")),
				mainPanel.add(new JButton("3")),
				mainPanel.add(new JButton("^")),
				mainPanel.add(new JButton("0")),
				mainPanel.add(new JButton("."))
		};
		
		mainPanel.addKeyListener(new TypingListener());
		
		// Add a KeypadListener and ActionListener object to each button
		for (int i = 0; i < keypadArray.length; i++) {
			((JButton) keypadArray[i]).addActionListener(new KeypadListener());
			((JButton) keypadArray[i]).addKeyListener(new TypingListener());
			((JButton) keypadArray[i]).setBackground(Color.DARK_GRAY);
			((JButton) keypadArray[i]).setBackground(Color.GREEN);
		}
		
		// The right-hand commands column as another grid layout
		JPanel rightColumn = new JPanel();
		GridLayout rightLayout = new GridLayout(8, 2);
		rightColumn.setLayout(rightLayout);
		
		Component rightColumnArray[] = {
				rightColumn.add(new JButton("CLR ALL")),
				rightColumn.add(new JButton("CLR PAD")),
				rightColumn.add(new JButton("arcsin")),
				rightColumn.add(new JButton("DEL")),
				rightColumn.add(new JButton("arctan")),
				rightColumn.add(new JButton("arccos")),
				rightColumn.add(new JButton("+/-")),
				rightColumn.add(new JButton("/")),
				rightColumn.add(new JButton("!")),
				rightColumn.add(new JButton("*")),
				rightColumn.add(new JButton("10^")),
				rightColumn.add(new JButton("-")),
				rightColumn.add(new JButton("%")),
				rightColumn.add(new JButton("+")),
				rightColumn.add(new JButton("√")),
				rightColumn.add(new JButton("ENTR"))
		};
		
		// Add CommandListener to all the buttons in the right column
		for (int i = 0; i < rightColumnArray.length; i++) {
			((JButton)rightColumnArray[i]).addActionListener(new KeypadListener());
			((JButton)rightColumnArray[i]).addKeyListener(new TypingListener());
			((JButton) rightColumnArray[i]).setBackground(Color.DARK_GRAY);
			((JButton) rightColumnArray[i]).setBackground(Color.GREEN);
		}
		
		JPanel secondaryPanel = new JPanel();
		GridLayout secondaryLayout = new GridLayout(4, 3);
		secondaryPanel.setLayout(secondaryLayout);
		Component secondaryArray[] = {
				secondaryPanel.add(new JButton("Deg/Rad")),
				secondaryPanel.add(new JButton("e")),
				secondaryPanel.add(new JButton("pi")),
				secondaryPanel.add(new JButton("sin")),
				secondaryPanel.add(new JButton("cos")),
				secondaryPanel.add(new JButton("tan")),
				secondaryPanel.add(new JButton("rnd")),
				secondaryPanel.add(new JButton("ceil")),
				secondaryPanel.add(new JButton("flr")),
				secondaryPanel.add(new JButton("rand")),
				secondaryPanel.add(new JButton("log")),
				secondaryPanel.add(new JButton("ln"))
		};
		
		for (int i = 0; i < secondaryArray.length; i++) {
			((JButton)secondaryArray[i]).addActionListener(new KeypadListener());
			((JButton)secondaryArray[i]).addKeyListener(new TypingListener());
			((JButton) secondaryArray[i]).setBackground(Color.DARK_GRAY);
			((JButton) secondaryArray[i]).setBackground(Color.GREEN);
		}
		
		// Create and set the display area to display text correctly
		// Currently, when punctuation is the last item on a line, it wraps to the beginning
		// It still is stored correctly behind the scenes, just displays incorrectly.
		// Also negative signs appear at the end of the line
		displayArea = new JTextArea(10,0);
		displayArea.setEditable(false);
		displayArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		displayArea.addKeyListener(new TypingListener());
		
		JPanel keyPanel = new JPanel();
		GridLayout keyPanelLayout = new GridLayout(2, 1);
		keyPanel.setLayout(keyPanelLayout);
		keyPanel.add(secondaryPanel);
		keyPanel.add(mainPanel);
		mainPanel.setBackground(Color.BLACK);
		secondaryPanel.setBackground(Color.BLACK);

		// Final setup
		frame.getContentPane().add(BorderLayout.NORTH, displayArea);
		frame.getContentPane().add(BorderLayout.CENTER, keyPanel);
		frame.getContentPane().add(BorderLayout.EAST, rightColumn);
		displayArea.setBackground(Color.BLACK);
		displayArea.setForeground(Color.GREEN);
		rightColumn.setBackground(Color.BLACK);
		
		frame.setSize(400, 600);
		frame.setVisible(true);
		display();
	}
	
	// Refreshes the display
	void display() {
		// Put the Trig mode at the top
		String content = calc.getTrigMode() + "\n";
		// Get the stack
		ArrayList<Float> list = calc.getStack().returnStack();
		
		// Add each object of the stack to a new line
		for (Float i : list) {
			content += i.toString() + "\n";
		}
		// put a line between the stack and the scratchpad
		content += "----------------\n";
		// put the scratchpad at the bottom
		content += scratchpad;
		
		// set the text
		displayArea.setText(content);
	}
	
	// public method to clear the scratchpad
	public void clearPad() {
		scratchpad = "";
		display();
	}
	
	// public method to set the scratchpad, mostly so the calc can display errors
	public void setScratchpad(String s) {
		scratchpad = s;
		display();
	}

	// KeyListener class so that the user can type directly into the scratchpad and also do the basic functions
	// From the keyboard, should work no matter where on the calc the user is "focused"
	class TypingListener implements KeyListener {
		public void keyTyped(KeyEvent event) {}
		
		public void keyPressed(KeyEvent event) {
			int keyCode = event.getKeyCode();
			String keyStr = KeyEvent.getKeyText(keyCode).toLowerCase();
			switch (keyStr) {
				case "1":
					scratchpad += "1";
					break;
				case "2":
					scratchpad += "2";
					break;
				case "3":
					scratchpad += "3";
					break;
				case "4":
					scratchpad += "4";
					break;
				case "5":
					scratchpad += "5";
					break;
				case "6":
					scratchpad += "6";
					break;
				case "7":
					scratchpad += "7";
					break;
				case "8":
					if (event.isShiftDown()) {
						calc.go(scratchpad);
						calc.go("*");
					} else {
						scratchpad += "8";
					}
					break;
				case "9":
					scratchpad += "9";
					break;
				case "0":
					scratchpad += "0";
					break;
				case "⏎":
					calc.go(scratchpad);
					break;
				case "=":
					calc.go(scratchpad);
					calc.go("+");
					break;
				case "-":
					calc.go(scratchpad);
					calc.go("-");
					break;
				case "/":
					calc.go(scratchpad);
					calc.go("/");
					break;
				case "⌫":
					if (scratchpad.length() > 0) {
						scratchpad = scratchpad.substring(0, scratchpad.length()-1);
					}
					break;
				case "e":
					calc.go("e");
					break;
			}
			display();
		}
		
		public void keyReleased(KeyEvent event) {}
	}
	
	// interior class to add action listeners to the keypad buttons
	class KeypadListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// Since every button has the same object, use a switch case to find out which button
			switch (((JButton) event.getSource()).getText()) {
			// If it's a number, add that number to the end of the scratchpad
				case "0":
					scratchpad += "0";
					break;
				case "1":
					scratchpad += "1";
					break;
				case "2":
					scratchpad += "2";
					break;
				case "3":
					scratchpad += "3";
					break;
				case "4":
					scratchpad += "4";
					break;
				case "5":
					scratchpad += "5";
					break;
				case "6":
					scratchpad += "6";
					break;
				case "7":
					scratchpad += "7";
					break;
				case "8":
					scratchpad += "8";
					break;
				case "9":
					scratchpad += "9";
					break;
				case ".":
					scratchpad += ".";
					break;
				// otherwise add whatever's in the scratchpad, then tell the calc what operation is being run
				case "^":
					calc.go(scratchpad);
					calc.go("^");
					break;
				case "sin":
					calc.go(scratchpad);
					calc.go("sin");
					break;
				case "cos":
					calc.go(scratchpad);
					calc.go("cos");
					break;
				case "tan":
					calc.go(scratchpad);
					calc.go("tan");
					break;
				// toggle trig mode to/from deg and radians
				case "Deg/Rad":
					calc.go("chngTrig");
					break;
				case "pi":
					calc.go("pi");
					break;
				case "!":
					calc.go(scratchpad);
					calc.go("!");
					break;
				case "ENTR":
					calc.go(scratchpad);
					break;
				case "+":
					calc.go(scratchpad);
					calc.go("+");
					break;
				case "-":
					calc.go(scratchpad);
					calc.go("-");
					break;
				case "*":
					calc.go(scratchpad);
					calc.go("*");
					break;
				case "/":
					calc.go(scratchpad);
					calc.go("/");
					break;
				case "+/-":
					// Appends a negative to the front of the string
					if (scratchpad.length() == 0) {
						break;
					} else if (scratchpad.substring(0,1).equals("-")) {
						scratchpad = scratchpad.substring(1);
						break;
					} else {
						String newpad = "-";
						for (int i = 0; i < scratchpad.length(); i++) {
							newpad += scratchpad.substring(i, i+1);
						}
						scratchpad = newpad;
						break;
					}
				case "DEL":
					if (scratchpad.length() > 0) {
						scratchpad = scratchpad.substring(0, scratchpad.length()-1);
					}
					break;
				case "CLR ALL":
					clearPad();
					calc.go("c");
					break;
				case "CLR PAD":
					clearPad();
					break;
				case "%":
					calc.go(scratchpad);
					calc.go("%");
					break;
				case "rnd":
					calc.go(scratchpad);
					calc.go("rnd");
					break;
				case "ceil":
					calc.go(scratchpad);
					calc.go("ceil");
					break;
				case "flr":
					calc.go(scratchpad);
					calc.go("flr");
					break;
				case "rand":
					calc.go("rand");
					break;
				case "log":
					calc.go(scratchpad);
					calc.go("log");
					break;
				case "ln":
					calc.go(scratchpad);
					calc.go("ln");
					break;
				case "e":
					calc.go("e");
					break;
				case "10^":
					calc.go(scratchpad);
					calc.go("10^");
					break;
				case "arcsin":
					calc.go(scratchpad);
					calc.go("arcsin");
					break;
				case "arccos":
					calc.go(scratchpad);
					calc.go("arccos");
					break;
				case "arctan":
					calc.go(scratchpad);
					calc.go("arctan");
					break;
				case "√":
					calc.go(scratchpad);
					calc.go("sqrt");
					break;
			}
			// update the display
			display();
		}
	}
}
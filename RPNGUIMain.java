
public class RPNGUIMain {	
	public static void main(String[] args) {
		
		// The actual calculator object that the GUI talks to.
		RPNGUICalc calc = new RPNGUICalc();
		@SuppressWarnings("unused")
		// the GUI itself that talks to the calculator
		RPNGUI calcGUI = new RPNGUI(calc);
	}
}

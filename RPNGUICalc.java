import java.util.HashMap;
import java.util.Map;

public class RPNGUICalc {
	
	// The actual calculator that just takes inputs in terms of strings and does things with them
	// has a gui reference just to set errors and send the output
	
	Map<String, RPNOperator> opMap = new HashMap<String, RPNOperator>();
	RPNStack myStack;
	
	// A string to represent degrees or radians
	String trigMode = "Deg";
	// class reference to talk to the gui itself
	RPNGUI gui;
	
	public RPNGUICalc() {
		myStack = new RPNStack();
		
		// Operators STILL TO ADD:
		// rnd
		// ceil
		// flr
		// rand
		
		// Basic Math
		opMap.put("+", new RPNOperatorAdd(myStack));
		opMap.put("-", new RPNOperatorSubtract(myStack));
		opMap.put("*", new RPNOperatorMultiply(myStack));
		opMap.put("/", new RPNOperatorDivide(myStack));
		opMap.put("^", new RPNOperatorPower(myStack));
		opMap.put("10^", new RPNOperator10(myStack));
		opMap.put("sqrt", new RPNOperatorSqrt(myStack));
		
		
		//trig (New from original RPNCalc)
		opMap.put("sin", new RPNOperatorSin(myStack, this));
		opMap.put("cos", new RPNOperatorCos(myStack, this));
		opMap.put("tan", new RPNOperatorTan(myStack, this));
		opMap.put("arcsin", new RPNOperatorArcsin(myStack, this));
		opMap.put("arccos", new RPNOperatorArccos(myStack, this));
		opMap.put("arctan", new RPNOperatorArctan(myStack, this));
		opMap.put("chngTrig", new RPNOperatorChngMode(myStack, this));
		opMap.put("pi", new RPNOperatorPi(myStack));
		
		// Logarithmic
		opMap.put("log", new RPNOperatorLog(myStack));
		opMap.put("ln", new RPNOperatorLn(myStack));
		opMap.put("e", new RPNOperatorE(myStack));
		
		// special command
		opMap.put("c", new RPNOperatorClear(myStack));
		
		// Other
		opMap.put("!", new RPNOperatorFactorial(myStack));
		opMap.put("%", new RPNOperatorModulo(myStack));
		opMap.put("rand", new RPNOperatorRandom(myStack));
		opMap.put("rnd", new RPNOperatorRound(myStack));
		opMap.put("flr", new RPNOperatorFloor(myStack));
		opMap.put("ceil", new RPNOperatorCeiling(myStack));
	}
	
	// The original calculator system from RPNCalc
	public void go(String in) {
		myStack.printStack();
		String[] tokens = in.split(" ");
		for (String s : tokens) {
			if (opMap.containsKey(s)) {
				try {
					opMap.get(s).operate();
				} catch (Exception e) {
					System.out.println(e);
					gui.setScratchpad("Error");
				}
			} else if (s.equals("")) {
				
			} else {
				try {
					myStack.push(new RPNStackItem(Float.parseFloat(s)));
					gui.clearPad();
				} catch (Exception e) {
					gui.setScratchpad("Error");
				}
			}
		}
	}
	
	// Variable to get the GUI reference
	public void recieveGUI(RPNGUI guiin) {
		gui = guiin;
	}
	
	// Method so other objects can read the trig mode
	public String getTrigMode() {
		return trigMode;
	}
	
	// Method so other classes can set the trig mode
	public void setTrigMode(String s) {
		if (s.equals("Deg")) {
			trigMode = "Deg";
		} else {
			trigMode = "Rad";
		}
	}
	
	// Method so other classes can get a reference to the stack
	public RPNStack getStack() {
		return myStack;
	}
}

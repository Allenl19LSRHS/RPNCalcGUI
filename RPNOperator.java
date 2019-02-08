

public abstract class RPNOperator {
	RPNStack stack;
	public RPNOperator(RPNStack s) {
		stack = s;
	}
	void operate() {}
}

class RPNOperatorAdd extends RPNOperator {
	public RPNOperatorAdd(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		stack.push(new RPNStackItem(num1 + num2));
	}
}

class RPNOperatorSubtract extends RPNOperator {
	public RPNOperatorSubtract(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		stack.push(new RPNStackItem(num2 - num1));
	}
}

class RPNOperatorMultiply extends RPNOperator {
	public RPNOperatorMultiply(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		stack.push(new RPNStackItem(num1 * num2));
	}
}

class RPNOperatorDivide extends RPNOperator {
	public RPNOperatorDivide(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		if (num1 == 0) {
			stack.push(new RPNStackItem(num2));
			stack.push(new RPNStackItem(num1));
			return;
		} else {
			stack.push(new RPNStackItem(num2 / num1));
		}
	}
}

class RPNOperatorPower extends RPNOperator {
	public RPNOperatorPower(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		stack.push(new RPNStackItem((float)(Math.pow(num2, num1))));
	}
}

class RPNOperatorClear extends RPNOperator {
	public RPNOperatorClear(RPNStack s) {
		super(s);
	}
	void operate() {
		stack.clear();
	}
}

class RPNOperatorFactorial extends RPNOperator {
	public RPNOperatorFactorial(RPNStack s) {
		super(s);
	}
	void operate() {
		float a = stack.pop().toFloat();
		float total = 1;
		if (a < 0 || a % 1 != 0) {
			stack.push(new RPNStackItem(a));
			return;
		}
		for (int i = (int) a; i > 0; i--) {
			total *= i;
		}
		if (total == Float.POSITIVE_INFINITY) {
			stack.push(new RPNStackItem(a));
		} else {
			stack.push(new RPNStackItem(total));
		}
	}
}

class RPNOperatorModulo extends RPNOperator {
	public RPNOperatorModulo(RPNStack s) {
		super(s);
	}
	void operate() {
		float a = stack.pop().toFloat();
		float b = stack.pop().toFloat();
		if (a < 0) {
			stack.push(new RPNStackItem(b));
			stack.push(new RPNStackItem(a));
			return;
		}
		stack.push(new RPNStackItem(b % a));
	}
}

class RPNOperatorSin extends RPNOperator {
	RPNGUICalc calc;

	public RPNOperatorSin(RPNStack s, RPNGUICalc calcu) {
		super(s);
		calc = calcu;
	}
	void operate() {
		float ans;
		float num = stack.pop().toFloat();
		if (calc.getTrigMode().equals("Deg")) {
			num = (float) Math.toRadians(num);
			ans = (float) Math.sin(num);
		} else {
			ans = (float) Math.sin(num);
		}
		stack.push(new RPNStackItem(ans));
	}
}

class RPNOperatorCos extends RPNOperator {
	RPNGUICalc calc;
	public RPNOperatorCos(RPNStack s, RPNGUICalc calcu) {
		super(s);
		calc = calcu;
	}
	void operate() {
		float ans;
		float num = stack.pop().toFloat();
		if (calc.getTrigMode().equals("Deg")) {
			num = (float) Math.toRadians(num);
			ans = (float) Math.cos(num);
		} else {
			ans = (float) Math.cos(num);
		}
		stack.push(new RPNStackItem(ans));
	}
}

class RPNOperatorTan extends RPNOperator {
	RPNGUICalc calc;
	public RPNOperatorTan(RPNStack s, RPNGUICalc calcu) {
		super(s);
		calc = calcu;
	}
	void operate() {
		float ans;
		float num = stack.pop().toFloat();
		if (calc.getTrigMode().equals("Deg")) {
			num = (float) Math.toRadians(num);
			ans = (float) Math.tan(num);
		} else {
			ans = (float) Math.tan(num);
		}
		stack.push(new RPNStackItem(ans));
	}
}
class RPNOperatorArcsin extends RPNOperator {
	RPNGUICalc calc;

	public RPNOperatorArcsin(RPNStack s, RPNGUICalc calcu) {
		super(s);
		calc = calcu;
	}
	void operate() {
		float ans;
		float num = stack.pop().toFloat();
		if (calc.getTrigMode().equals("Deg")) {
			num = (float) Math.toRadians(num);
			ans = (float) Math.asin(num);
		} else {
			ans = (float) Math.asin(num);
		}
		stack.push(new RPNStackItem(ans));
	}
}

class RPNOperatorArccos extends RPNOperator {
	RPNGUICalc calc;
	public RPNOperatorArccos(RPNStack s, RPNGUICalc calcu) {
		super(s);
		calc = calcu;
	}
	void operate() {
		float ans;
		float num = stack.pop().toFloat();
		if (calc.getTrigMode().equals("Deg")) {
			num = (float) Math.toRadians(num);
			ans = (float) Math.acos(num);
		} else {
			ans = (float) Math.acos(num);
		}
		stack.push(new RPNStackItem(ans));
	}
}

class RPNOperatorArctan extends RPNOperator {
	RPNGUICalc calc;
	public RPNOperatorArctan(RPNStack s, RPNGUICalc calcu) {
		super(s);
		calc = calcu;
	}
	void operate() {
		float ans;
		float num = stack.pop().toFloat();
		if (calc.getTrigMode().equals("Deg")) {
			num = (float) Math.toRadians(num);
			ans = (float) Math.atan(num);
		} else {
			ans = (float) Math.atan(num);
		}
		stack.push(new RPNStackItem(ans));
	}
}

class RPNOperatorChngMode extends RPNOperator {
	RPNGUICalc calc;

	public RPNOperatorChngMode(RPNStack s, RPNGUICalc calcu) {
		super(s);
		calc = calcu;
	}
	void operate() {
		if (calc.getTrigMode().equals("Deg")) {
			calc.setTrigMode("Rad");
		} else {
			calc.setTrigMode("Deg");
		}
	}
}

class RPNOperatorLog extends RPNOperator {
	public RPNOperatorLog(RPNStack s) {
		super(s);
	}
	void operate() {
		float a = stack.pop().toFloat();
		if (a >= 0) {
			stack.push(new RPNStackItem((float)Math.log10((double)a)));
		}
	}
}
class RPNOperatorLn extends RPNOperator {
	public RPNOperatorLn(RPNStack s) {
		super(s);
	}
	void operate() {
		float a = stack.pop().toFloat();
		if (a >= 0) {
			stack.push(new RPNStackItem((float)Math.log((double)a)));
		}
	}
}
class RPNOperatorRandom extends RPNOperator {
	public RPNOperatorRandom(RPNStack s) {
		super(s);
	}
	void operate() {
		stack.push(new RPNStackItem((float)Math.random()));
	}
}
class RPNOperatorRound extends RPNOperator {
	public RPNOperatorRound(RPNStack s) {
		super(s);
	}
	void operate() {
		float a = stack.pop().toFloat();
		stack.push(new RPNStackItem((float)Math.round((double)a)));
	}
}
class RPNOperatorFloor extends RPNOperator {
	public RPNOperatorFloor(RPNStack s) {
		super(s);
	}
	void operate() {
		float a = stack.pop().toFloat();
		stack.push(new RPNStackItem((float)Math.floor((double)a)));
	}
}
class RPNOperatorCeiling extends RPNOperator {
	public RPNOperatorCeiling(RPNStack s) {
		super(s);
	}
	void operate() {
		float a = stack.pop().toFloat();
		stack.push(new RPNStackItem((float)Math.ceil((double)a)));
	}
}
class RPNOperatorE extends RPNOperator {
	public RPNOperatorE(RPNStack s) {
		super(s);
	}
	void operate() {
		stack.push(new RPNStackItem((float)Math.E));
	}
}
class RPNOperatorPi extends RPNOperator {
	public RPNOperatorPi(RPNStack s) {
		super(s);
	}
	void operate() {
		stack.push(new RPNStackItem((float)Math.PI));
	}
}
class RPNOperator10 extends RPNOperator {
	public RPNOperator10(RPNStack s) {
		super(s);
	}
	void operate() {
		float a = stack.pop().toFloat();
		stack.push(new RPNStackItem((float)Math.pow(10, a)));
	}
}
class RPNOperatorSqrt extends RPNOperator {
	public RPNOperatorSqrt(RPNStack s) {
		super(s);
	}
	void operate() {
		float a = stack.pop().toFloat();
		stack.push(new RPNStackItem((float)Math.pow(a, 0.5)));
	}
}
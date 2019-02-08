
import java.util.ArrayList;

public class RPNStack {
	ArrayList<RPNStackItem> stack = new ArrayList<RPNStackItem>();

	public RPNStackItem push(RPNStackItem item) {
		stack.add(0, item);
		return item;
	}
	
	public RPNStackItem pop() {
		return stack.remove(0);
	}
	
	public void printStack() {
		System.out.println("Stack:");
		for (RPNStackItem i : stack) {
			System.out.println(i.toFloat());
		}
	}

	// method so classes can read the stack in reverse order in terms of floats instead of RPNStackItems
	public ArrayList<Float> returnStack() {
		ArrayList<Float> list = new ArrayList<Float>();
		for (int i = stack.size() - 1; i > -1; i--) {
			list.add(stack.get(i).toFloat());
		}
		return list;
	}
	
	public void clear() {
		stack.clear();
	}
}

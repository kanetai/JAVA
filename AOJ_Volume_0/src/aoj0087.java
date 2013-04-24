import java.util.*;
public class aoj0087 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(stdin.hasNext()){
			Stack<Double> stack = new Stack<Double>();
			String[] line = stdin.nextLine().split(" ");
			for(String str: line){
				if(str.equals("+")){
					stack.add(stack.pop()+stack.pop());
				}else if(str.equals("-")){
					double b = stack.pop(), a = stack.pop();
					stack.add(a-b);
				}else if(str.equals("*")){
					stack.add(stack.pop()*stack.pop());
				}else if(str.equals("/")){
					double b = stack.pop(), a = stack.pop();
					stack.add(a/b);
				}else{
					stack.add(Double.parseDouble(str));
				}
			}
			System.out.println(stack.get(0));
		}
	}
}

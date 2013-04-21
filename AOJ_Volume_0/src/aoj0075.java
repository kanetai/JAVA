import java.util.*;
public class aoj0075 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(stdin.hasNext()){
			String[] line = stdin.nextLine().split(",");
			if(Double.parseDouble(line[1])/Math.pow(Double.parseDouble(line[2]), 2) >= 25)
				System.out.println(line[0]);
		}
	}
}

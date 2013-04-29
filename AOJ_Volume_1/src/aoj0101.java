import java.util.*;
public class aoj0101 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n = stdin.nextInt(); stdin.nextLine();
		while(n-- > 0){
			String line = stdin.nextLine();
			while(line.indexOf("Hoshino") >= 0)
				line = line.replace("Hoshino", "Hoshina");
			System.out.println(line);
		}
	}
}

import java.util.*;
public class aoj0130 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n = stdin.nextInt();
		while(n-->0){
			char[] line = stdin.next().toCharArray();
			StringBuilder ans = new StringBuilder();
			boolean flag = true;
			int p = -1;
			for(int i = 0; i < line.length; ++i){
				switch(line[i]){
				case '-': break;
				case '<': flag = true; p--; break;
				case '>': flag = false; p++; break;
				default:
					if(p < 0 || ans.length() <= p){
						if(flag){ ans.insert(0,line[i]); p++; }
						else{ ans.append(line[i]); }
					}
				}
			}
			System.out.println(ans.toString());
		}
	}
}

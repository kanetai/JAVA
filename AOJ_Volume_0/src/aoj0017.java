import java.util.*;
public class aoj0017 {
	static final Scanner stdin = new Scanner(System.in);
	static int A = 26;
	public static void main(String[] args) {
		while(stdin.hasNext()) solve(stdin.nextLine());
	}
	static boolean check(int bias, String s, String pat){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<s.length(); ++i){
			if( Character.isLetter(s.charAt(i)) )
				sb.append( (char)((s.charAt(i) - 'a' + bias)%A + 'a') );
		}
		return sb.toString().compareTo(pat) == 0;
	}
	static void solve(String s){
		String str[] = s.split(" ");	
		for(int b=0; b<A; ++b){
			for(String i: str){
				if( check(b, i, "the" ) || check(b,i,"this") || check(b,i, "that" ) ){
					for(int j=0; j<str.length; ++j){
						for(int k=0; k<str[j].length(); ++k){
							char target = str[j].charAt(k);
							if(Character.isLetter(target)) System.out.print( (char)(((target-'a') + b) % A +'a') );
							else System.out.print(target);
						}
						if( j == str.length-1 ) System.out.println("");
						else System.out.print(' ');
					}
					return;
				}
			}
		}
	}
}

import java.util.*;
public class aoj0179 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		String s; char temp;
		while(!(s = stdin.nextLine()).equals("0")){
			int n = s.length();
			String ans = "NA";
			Set<String> found = new HashSet<String>(n*n);
			Queue<T> q = new LinkedList<T>();
			q.add(new T(0, s));
			while(!q.isEmpty()){
				T e = q.poll();
				if(e.check()){ ans = e.sec.toString(); break; }
				if(found.contains(e.toString())) continue;
				found.add(e.toString());
				for(int i = 1; i < n; ++i){
					if((temp = check(e.body[i-1], e.body[i])) != ' '){
						T nextT = new T(e);
						nextT.body[i-1] = nextT.body[i] = temp;
						nextT.sec++;
						if(found.contains(nextT.toString())) continue;
						q.add(nextT);
					}
				}
			}
			System.out.println(ans);
		}
	}
	static class T{
		Integer sec;
		char[] body;
		T(Integer sec, String body){ this.sec = sec; this.body = body.toCharArray(); }
		T(T o){ sec = o.sec; body = o.body.clone(); }
		boolean check(){
			char c = body[0];
			for(int i = 1; i < body.length; ++i) if(body[i] != c) return false;
			return true;
		}
		@Override public String toString(){ return new String(body); }
	}
	@SuppressWarnings("serial") static char check(char a, char b){
		if(a == b) return ' ';
		Set<Character> s = new HashSet<Character>(){ {add('r'); add('g'); add('b'); } };
		s.remove(a); s.remove(b);
		return s.iterator().next();
	}
}

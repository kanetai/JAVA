import java.util.*;
public class aoj0171 {
	static final int N = 4, M = 8;
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		String s;
		while(!(s = stdin.nextLine()).equals("0")){
			dice[0] = generateAllPatern(s);
			P = dice[0].length; //P=24
			for(int i = 1; i < M; i++) dice[i] = generateAllPatern(stdin.nextLine());
			Arrays.fill(used, false);
			System.out.println(DFS(0) ? "YES" : "NO");
		}
	}
	static Character[] stringToCharacterArray(String s){
		Character[] ret = new Character[s.length()];
		for(int i = 0; i < s.length(); ++i) ret[i] = s.charAt(i);
		return ret;
	}
	@SuppressWarnings("unchecked") static Cube<Character>[] generateAllPatern(String s){
		Set<Cube<Character>> all = new Cube<Character>(stringToCharacterArray(s)).generateAllPattern();
		return (Cube<Character>[]) all.toArray(new Cube[all.size()]);
	}
	@SuppressWarnings("unchecked") static Cube<Character> dice[][] = new Cube[M][];
	static boolean[] used = new boolean[M];
	static int P;
	static int[] order = new int[M], porder = new int[M];
	static boolean DFS(int c){
		if(c == M) return true;
		for(int i = 0; i < M; ++i){
			if(used[i]) continue;
			order[c] = i; used[i] = true;
			for(int j = 0; j < P; ++j){
				porder[c] = j;
				if(!check(c)) continue;
				if(DFS(c+1)) return true;
			}
			used[i] = false;
		}
		return false;
	}
	static final boolean check(int c){
		switch(c){
		case 0: return	true;
		case 1: return	match(target(1).get(Surface.LEFT), target(0).get(Surface.RIGHT));
		case 2: return	match(target(2).get(Surface.FRONT), target(0).get(Surface.BACK));
		case 3: return	match(target(3).get(Surface.LEFT), target(2).get(Surface.RIGHT)) &&
						match(target(3).get(Surface.FRONT), target(1).get(Surface.BACK));
		case 4: return	match(target(4).get(Surface.TOP), target(0).get(Surface.BOTTOM));
		case 5: return 	match(target(5).get(Surface.LEFT), target(4).get(Surface.RIGHT)) &&
						match(target(5).get(Surface.TOP), target(1).get(Surface.BOTTOM));
		case 6: return	match(target(6).get(Surface.FRONT), target(4).get(Surface.BACK)) &&
						match(target(6).get(Surface.TOP), target(2).get(Surface.BOTTOM));
		case 7:	return 	match(target(7).get(Surface.LEFT), target(6).get(Surface.RIGHT)) &&
						match(target(7).get(Surface.FRONT), target(5).get(Surface.BACK)) &&
						match(target(7).get(Surface.TOP), target(3).get(Surface.BOTTOM));
		}
		assert false; //ここには来ないはず
		return false;
	}
	static final boolean match(char a, char b){ return Character.toUpperCase(a)==Character.toUpperCase(b) && a!=b; }
	static final Cube<Character> target(int c){ return dice[order[c]][porder[c]]; }
	static enum Surface{TOP, FRONT, RIGHT, LEFT, BACK, BOTTOM};
	@SuppressWarnings("unchecked") public static class Cube <T>{
		static final int N = 6, M = 4;
		private T[] id;
		public final T get(Surface s){ return id[s.ordinal()]; }
		public Cube(T[] c){ id = (T[]) new Object[N]; System.arraycopy(c, 0, id, 0, N); }
		public Cube(Cube<T> c){ id = (T[]) new Object[N]; System.arraycopy(c.id, 0, id, 0, N); }
		public final void rotateXZ() { rotate(Surface.TOP, Surface.FRONT, Surface.BOTTOM, Surface.BACK); }
		public final void rotateYZ() { rotate(Surface.TOP, Surface.RIGHT, Surface.BOTTOM, Surface.LEFT); }
		public final void rotateXY() { rotate(Surface.FRONT, Surface.LEFT, Surface.BACK, Surface.RIGHT); }
		private final void rotate(Surface a, Surface b, Surface c, Surface d) { //abcd->bcda
			T tmp = id[a.ordinal()];
			id[a.ordinal()] = id[b.ordinal()];
			id[b.ordinal()] = id[c.ordinal()];
			id[c.ordinal()] = id[d.ordinal()];
			id[d.ordinal()] = tmp;
		}
		public Set<Cube<T>> generateAllPattern(){
			Set<Cube<T>> ret = new HashSet<Cube<T>>(N*M);
			for (int k = 0; k < N; ++k){
				for (int i = 0; i < M; rotateXY(), ++i) ret.add(new Cube<T>(this));
				if((k&1)==0) rotateXZ(); else rotateYZ();
			}
			return ret;
		}
		@Override public String toString() {
			StringBuilder sb = new StringBuilder();
			for(Surface s : Surface.values()) sb.append(id[s.ordinal()]);
			return sb.toString();
		}
		@Override public boolean equals(Object o) {
			if(!(o instanceof Cube<?>))return false;
			Cube<T> d = (Cube<T>)o;
			for(Surface f : Surface.values()) if(!id[f.ordinal()].equals(d.id[f.ordinal()])) return false;
			return true;
		}
		@Override public int hashCode() {
			int hash = 17;
			for(Surface f : Surface.values()) hash += 31*hash+id[f.ordinal()].hashCode();
			return hash;
		}
	}
}

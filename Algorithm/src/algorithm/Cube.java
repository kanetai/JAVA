package algorithm;
import java.util.HashSet;
import java.util.Set;
/**
 * <a href=http://www.prefield.com/algorithm/misc/dice.html>consultation</a><br>
 * AOJ No. 0171
 * @param <T>
 */
@SuppressWarnings("unchecked") public class Cube <T>{
	static enum Surface{TOP, FRONT, RIGHT, LEFT, BACK, BOTTOM};
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
	
	//test code
	public static void main(String[] args) {
		char s[] = "abcdef".toCharArray();
		Character[] str = new Character[s.length];
		for (int i = 0; i < str.length; i++) str[i] = s[i];
		Cube<Character> cube = new Cube<Character>(str);
		Set<Cube<Character>> set = cube.generateAllPattern();
		for(Cube<Character> c: set) System.out.println(c.toString());
		System.out.println(set.size());
		System.out.println(cube.toString());
	}
}

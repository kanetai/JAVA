package algorithm;
import java.util.Arrays;
import static algorithm.Utility.swap;
/** AOJ No. 0214 */
public class UnionFind {
	private int num; // number of disjoint sets (group)
	private final int[] parent;	//parent[x] = parent of x
								//※ case parent[x]	< 0:  x = root, -parent[x] = size of set containing x 
	/**
	 * Initialization with {{0},{1},...,{size-1}}
	 * @param size
	 */
	public UnionFind(int size) {
		parent = new int[size];
		num = size;
		Arrays.fill(parent, -1);
	}
	/**
	 * Gets root of x.<br>
	 * n = number of elements, A(a) = Ackermann function(a,a), amortized complexity O(A^-1(n)). 
	 * @param x
	 * @return Root of x.
	 */
	public final int root(int x) { return parent[x] < 0 ? x : (parent[x] = root(parent[x])); }
	/**
	 * Gets number of disjoint sets (group). O(1)
	 * @return Number of disjoint sets.
	 */
	public final int size() { return num; }
	/**
	 * Gets size of set containing x.<br>
	 * n = number of elements, A(a) = Ackermann function(a,a), amortized complexity O(A^-1(n)). 
	 * @param x
	 * @return Size of set containing x.
	 */
	public final int size(int x) { return -parent[root(x)]; }
	/**
	 * Merges S(x) with S(y). ※S(e) is set containing e.<br>
	 * n = number of elements, A(a) = Ackermann function(a,a), amortized complexity O(A^-1(n)). 
	 * @param x
	 * @param y
	 * @return true -> Success merge, false -> Already merged
	 */
	public final boolean union(int x, int y) {
		x = root(x); y = root(y);
		if (x != y) {
			if (parent[y] < parent[x]) swap(parent, x, y);
			parent[x] += parent[y];	//sets size of set containing x.
			parent[y] = x;			//sets parent of y.
			--num;
		}
		return x != y;
	}
	/**
	 * Tests whether S(x) == S(y) or not. ※S(e) is set containing e.<br>
	 * n = number of elements, A(a) = Ackermann function(a,a), amortized complexity O(A^-1(n)). 
	 * @param x
	 * @param y
	 * @return Whether S(x) == S(y) or not.
	 */
	public final boolean find(int x, int y) { return root(x) == root(y); }
}

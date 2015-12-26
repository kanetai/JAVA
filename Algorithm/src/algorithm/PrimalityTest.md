#素数判定 (Primality Test)

[リポジトリ](https://github.com/kanetai/JAVA/blob/master/Algorithm/src/algorithm/PrimalityTest.java)

競プロ的なことを久しぶりにしたので、<http://kanetai.hatenablog.com/entry/20110519/1305833341>の一部を少し整理。

term | description 
--- | --- 
約数(divisor), 因数,因子(factor) | ある自然数を割り切る整数。
素数(prime number) | 自分自身と１以外の自然数で割り切れない(正の約数をちょうど２つ持つ)自然数。
合成数(composite number) | 素数でない**2以上**の自然数(素数の積で表すことができる自然数)。
素因数(prime factor) | ある自然数の約数になる素数。

##素朴な判定法

\\(n\\)が素数かどうか判定するには、\\([2, n)\\)の範囲の整数が\\(n\\)の約数かどうかを確かめればよいが、ここで\\(n\\)が約数\\(d\\)を持つ(合成数)とすると、

\\(\frac{n}{d}\\)も\\(n\\)の約数\\(\left( n=d \cdot \frac{n}{d}\right) \\)なので、

\\(d \leq \sqrt{n} \lor \frac{n}{d} \leq \sqrt{n}\\).　**(※ \\( d \gt \sqrt{n} \land \frac{n}{d} \gt \sqrt{n} \\) だと\\(d \cdot \frac{n}{d} \gt n\\)となってしまう)**

\\[ n\text{が合成数}\rightarrow \sqrt{n}\text{より小さい素因数を持つ} \\]

つまり, \\([2,\sqrt{n}]\\)の範囲の整数が\\(n\\)の約数か(\\(n\\)を割り切れるか)どうかを確かめればよい. \\(n\\)を割り切れるならその整数は素数ではない.計算量は\\(O(\sqrt{n})\\).

```java
public static final boolean isPrime(int n){
	if( n <= 1 ) return false; 
	for(int i=2; i*i <= n;i++ ) if( n % i == 0 ) return false;
	return true;
}
```

##エラトステネスの篩(the sieve of Eratosthenes, Eratosthenes' sieve)
何回も素数判定したりしなければならないとき、上の素数判定では非効率なので、2以上N以下の素数を列挙することを考える。

- まず、2以上N以下の整数を列挙する。
- その中の最小の数2は素数。
- 素数の倍数は素数ではないので、2の倍数を取り除く。
- 次に、残った最小の数は、それ以下の数で割り切れない→その数は素数。
- 素数の倍数を除く。

\\[\vdots\\]

\\([0,n]\\)の素数判定テーブルを求める場合、上で述べたように\\(i\\)を\\([2, \sqrt{n} ]\\)の範囲で回せば良い。\\(i\\)が素数の時、その倍数のフラグを折るが、\\(2i, 3i, \cdots (i-1)i　\\)については既におられているはずなので、\\( [ i^2, n ] \\)の範囲で\\(i\\)の倍数のフラグを折ればよい。**※⇩は\\([0,n)\\)のテーブル**

計算量は\\( O\left(n\log \log n \right) \\)程度.

```java
/**
 * Creates primality test table via Eratosthenes' sieve. <br>
 * O( nlog(log n) )<br>
 * @param n table_size
 * @return primality test table {isPrime(0), isPrime(1),..., isPrime(n-1)}
 */
public static final boolean[] sieve(int n){
	boolean[] ret = new boolean[n]; Arrays.fill(ret, true);
	ret[0] = ret[1] = false;
	for(int i = 2; i*i < n; ++i)
		if(ret[i]) for(int j = i*i; j < n; j+=i) ret[j] = false;
	return ret;
}
```
###区間篩(Segmented Sieve)
\\(\sqrt{U}\\)以下の素数を使ってエラトステネスの篩をすることによって、 \\([L,U)\\)の区間の素数を得る。\\(\sqrt{U}\\)以下の素数はエラトステネスの篩などを使って求めておけば良い。\\(\pi (x)\\)を\\(x\\)以下の素数の数とすると、計算量は\\(O((U-L)\pi(\sqrt{U})) \\)。[素数定理](https://ja.wikipedia.org/wiki/%E7%B4%A0%E6%95%B0%E5%AE%9A%E7%90%86)によると、\\(\pi (x)\\)は\\(\frac{x}{\log x}\\)程度なので、\\(O((U-L)\frac{\sqrt{U}}{\log U})\\)になる。

⇩は\\(\sqrt{U}\\)以下の素数primeを渡して、[L,U)の範囲で、table[i] != 0 なら i+L は素数(i+L == table[i])なテーブルを作っている。素数だけ欲しい場合は, 0の要素を除けば良い。空間計算量は\\(O(\frac{\sqrt{U}}{\log U} + U-L)\\)でしょうか(\\(\sqrt{U}\\)以下の素数を含めて). \\(L\\)の少し前からフラグを折る必要があるので注意。実装は[Spaghetti Source](http://www.prefield.com/algorithm/math/segment_sieve.html)を参考にしてます。*ほとんどそのまま*

```java
public static final ArrayList<Integer> segmentedSieve(int L, int U, ArrayList<Integer> prime, boolean removeNonPrime) {
	if (!(0 <= L && L < U)) throw new IllegalArgumentException();
	ArrayList<Integer> ret = new ArrayList<>(U-L);
	IntStream.range(L, U).forEach(i -> ret.add(i - L, i));
	for(int p : prime) {
		if (p*p >= U) break;
		int j = (p >= L ? p*p :
					L % p == 0 ? L :
						L - (L % p) + p);
           for (; j < U; j += p) ret.set(j - L, 0);
       }
       return removeNonPrime ? 
			ret.stream().filter(e -> e > 0).collect(Collectors.toCollection(ArrayList::new)) : ret;
}
```

###逐次篩(Iterative Sieve)
逐次的に区間篩いを行うことによって、nまでの素数を空間計算量を抑えて求めることができる。\\(O(\frac{n}{\log n} + \sqrt{n})\\)程度で求められる。が競技プログラミングだとbitsetにしたエラトステネスの篩で十分なのかも知れない。

⇩[0,n)について、長さ\\(\sqrt{n}\\)の区間ごとに逐次区間篩いをする。segmentedSieve()で区間分のテーブルをnewしているが毎回同じ大きさなので、実際にはその分のバッファを用意して使いまわした方が無駄なnewをしなくて良いが、可読性を優先してそう書いていない。

```java
public static final ArrayList<Integer> iterativeSieve(int n) {
	if (n <= 0) throw new IllegalArgumentException();
	final int BLOCK = (int)Math.ceil(Math.sqrt(n));
	ArrayList<Integer> ret = primeNumbers(BLOCK); //BLOCK以下の素数
	for (int b = BLOCK; b < n; b += BLOCK) {
		ArrayList<Integer> tmp = segmentedSieve(b , b+BLOCK, ret, false);
		ret.addAll(tmp.stream().filter(e -> 0 < e && e < n) //n以上の素数が入ってしまっても良い場合は0<eだけで良い。
				.collect(Collectors.toList()));
	}
	return ret;
}
```

##参考
- [spaghetti source](http://www.prefield.com/algorithm/index.html)
- [wiki:素数定理](https://ja.wikipedia.org/wiki/%E7%B4%A0%E6%95%B0%E5%AE%9A%E7%90%86)
- [エラトステネスのふるいとその計算量](http://mathtrain.jp/eratosthenes)
- [素数列挙について](http://d.hatena.ne.jp/uwitenpen/20111203)

*TODO: 確率素数判定とか*
#ソート、整列(Sorting)

[リポジトリ](https://github.com/kanetai/JAVA)

`Arrays.sort`に習って、`a[fromIndex, toIndex)`がソートされるようにしてみる。
また、javaには`swap()`がないので適当に実装しておく。

```java
public static final void xxxSort(T a[], int fromIndex, int toIndex) { ... }
public static final int[] swap(int[] x, int i, int j){
	int tmp = x[i]; x[i] = x[j]; x[j] = tmp; return x;
}
```

##交換ソート(Exchange sorts)

###バブルソート, 基本交換法, 隣接交換法, 交換法(bubble sort)

[http://en.wikipedia.org/wiki/Bubble_sort](http://en.wikipedia.org/wiki/Bubble_sort)

隣接要素交換を繰り返してソート。特に最適化していないナイーブな実装だと安定な内部ソート。

比較回数 \\(T=\sum_{k=1}^{n-1}k = \frac{n(n-1)}{2} \\)回

交換回数は最悪\\(T\\)回、平均\\(\frac{1}{2}T = \frac{n(n-1)}{4}\\)回

\\( O(n^2)\\)

右のほうに小さな数がある場合に交換回数が多くなってしまう。

```haskell
bsort :: Ord a => [a] -> [a]
bsort []  = []
bsort [x] = [x]
bsort xs  = bsort (init xs') ++ [last xs']
      where xs' = sweep xs
            sweep [y] = [y]
            sweep (y1:y2:ys) | (y2 < y1) = y2 : sweep (y1:ys)
                             | otherwise = y1 : sweep (y2:ys)
```

mergeCountの検証用に戻り値は交換回数担っている。

```java
public static final int bubbleSort(int a[], int fromIndex, int toIndex){
	int swapCount = 0;
	int n = toIndex - fromIndex;
	for (int i = 0; i < n; ++i)
		for (int j = fromIndex+1; j < toIndex-i; ++j)
			if (a[j-1] > a[j]) { swap(a, j-1, j); ++swapCount; }
	return swapCount;
}
```
###シェーカーソート(cocktail sort, bidirectional bubble sort, cocktail shaker sort, shaker sort, ripple sort, shuffle sort, shuttle sort)

[http://en.wikipedia.org/wiki/Cocktail_sort](http://en.wikipedia.org/wiki/Cocktail_sort)

バブルソートのスキャン方向を双方向にして、右に小さい値がある場合に遅いバブルソートの欠点を改良したもの。

安定な内部ソート。\\(O(n^2)\\)

さらに端に連続して交換していない要素があれば、その分スキャン範囲を狭められる。
ほとんど整列している系列に対して高速。

###奇偶転置ソート(odd-even sort)

[http://en.wikipedia.org/wiki/Odd%E2%80%93even_sort](http://en.wikipedia.org/wiki/Odd%E2%80%93even_sort)

バブルソートの改良版の一つ。
はじめに、\\(a_0 \text{と} a_1, a_2\text{と}a_3, a_4\text{と}a_{5}\cdots \\)を(必要なら)交換し、
次に、\\(a_1 \text{と} a_2, a_3\text{と}a_4, a_5\text{と}a_6\cdots \\)を(必要なら)交換する。これを交換が発生しなくなるまで繰り返す。

安定な内部ソート\\( O(n^2)\\)

２要素の交換は独立に行うことができるので、並列化できる。

```java
public static final void oddEvenSort(int a[], int fromIndex, int toIndex) {
	boolean swapped = true;
	while (swapped) {
		swapped = false;
		for (int i = fromIndex; i < toIndex-1; i+=2) //odd-even
			if (a[i] > a[i+1]) { swap(a, i, i+1); swapped = true; }
		for (int i = fromIndex+1; i < toIndex-1; i+=2) //even-odd
			if (a[i] > a[i+1]) { swap(a, i, i+1); swapped = true; }
	}
}
```

###櫛ソート(comb sort)

[http://en.wikipedia.org/wiki/Comb_sort](http://en.wikipedia.org/wiki/Comb_sort)

バブルソートの改良版。
隣同士の交換ではなく、間隔(gap)が\\(h\\)あいたものと交換する。\\(h\\)をだんだん小さくしていって、交換が発生しなくなるまで繰り返す。

非安定な内部ソート。最悪\\(O(n^2)\\), 平均的にはだいたい\\(O(n \log n)\\)ぐらいになる。

- \\(h=n\\)
- \\(h=1\\)かつ交換が行われなくなるまで以下を繰り返す。
	- \\( h\leftarrow \max \{ \lfloor \frac{h}{1.3} \rfloor , 1 \} \\)
	- \\( a_{i} > a_{i+h} \\)なら\\( a_{i} \\)と\\( a_{i+h} \\)を交換する 

\\( h=9,10 \\)となったとき、強制的に\\( h=11 \\)とすることで高速化したアルゴリズムを、Comb sort 11と呼ぶ。

```java
public static final void combSort(int a[], int fromIndex, int toIndex) {
	boolean swapped = true;
	int h = fromIndex - toIndex;
	while (h > 1 || swapped) {
		swapped = false;
		h = Math.max(h*10/13, 1);
		for (int i = fromIndex;i+h < toIndex;++i)
			if (a[i] > a[i+h]) { swap(a, i, i+h); swapped = true; }
	}
}
```

###クイックソート(quicksort)

[http://en.wikipedia.org/wiki/Quicksort](http://en.wikipedia.org/wiki/Quicksort)
 
\\(O(n \log n\\)
 
+ 適当にピボットを選択する
+ ピボットより小さい数を前方、大きい数を後方に移動させる （分割）
+ 二分割された各々のデータを、それぞれソートする

非安定ソート。安定化も可能だが、マージソートのほうが速い場合が多い。
pivotにmedianを使うといい感じになる。

```haskell
qsort :: Ord a => [a] -> [a]
qsort [] = []
qsort (pivot:xs) = qsort left ++ [pivot] ++ qsort right
      where left  = [l|l <- xs, l <= pivot]
            right = [r|r <- xs, r > pivot]
```

```java
public static final void quickSort(int a[], int fromIndex, int toIndex) {
	if (fromIndex < toIndex) {
		int p = a[(fromIndex + toIndex) / 2];
		int l = fromIndex - 1, r = toIndex;
		while (true) {
			while (a[++l] < p);
			while (a[--r] > p);
			if (l >= r) break;
			swap(a, l, r);
		}
		quickSort(a, fromIndex, l);
		quickSort(a, r + 1, toIndex);
	}
}
```

###ボゴソート(bogosort, stupid sort, slowsort, random sort, shotgun sort, monkey sort)

[http://en.wikipedia.org/wiki/Bogosort](http://en.wikipedia.org/wiki/Bogosort)

\\(O((n+1)!)\\)

整列が完了するまで、猿に要素を交換させる。

配列でなく、コレクションなら`shuffle()`を使えば良い。

```java
private static final boolean isSorted(int a[], int fromIndex, int toIndex) {
	for (int i = fromIndex+1; i < toIndex; ++i) if (a[i-1] > a[i]) return false;
	return true;
}
public static final void bogoSort(int a[], int fromIndex, int toIndex) {
	int n = toIndex - fromIndex;
	Random r = new Random();
	while (isSorted(a, fromIndex, toIndex) == false)
		swap(a, fromIndex+r.nextInt(n), fromIndex+r.nextInt(n));
}
```

##選択ソート(Selection sorts)

###選択ソート(selection sort)

[http://en.wikipedia.org/wiki/Selection_sort](http://en.wikipedia.org/wiki/Selection_sort)

バブルソートのように隣同士を交換するのではなく、未ソートの系列から最小の要素を選んで交換する。非安定な内部ソート。

比較回数は\\(\sum_{k=1}^{n-1}k = \frac{n(n-1)}{2}\\), １回のスキャンにつき交換は最大１回なので、計算量は\\(O(n^{2})\\)。

```java
public static final void selectionSort(int a[], int fromIndex, int toIndex) {
	for (int i = fromIndex; i < toIndex; ++i) {
		int minIndex = i;
		for (int j = i+1; j < toIndex; ++j)
			if (a[minIndex] > a[j]) minIndex = j;
		swap(a, i, minIndex);
	}
}
```

###ヒープソート(heapsort)

[http://en.wikipedia.org/wiki/Heapsort](http://en.wikipedia.org/wiki/Heapsort)

ヒープを構築して、最大値(ルート)を取り除いて並べる→ヒープ再構築→最大値を取り除いて並べるを繰り返す。データ自体以外に必要となる作業領域は固定だが、並列化できない。ランダムアクセスが必要になるので連結リストなどのソートには適さない。

\\(O(n \log n)\\)の非安定ソート。

####２分ヒープ木(binary heap tree)

配列で実現できる。どの親\\(\geq \\)子(または親\\(\leq\\)子)を満たした２分木(binary tree)。兄弟同士の大小関係は任意なので、**半順序木(partial ordered tree)**とも呼ばれる。

\\(a[0]\\)をルートとした時、

- \\(a[i]\\)の親は\\(a\left[ \left\lfloor \frac{i-1}{2} \right\rfloor \right]\\)
- \\(a[i]\\)の左の子は\\(a[2i+1]\\)
- \\(a[i]\\)の右の子は\\(a[2i+2]\\)

**ヒープ化**

- ルート以外(左部分木と右部分木)がヒープ化されているとする。
- ルートと子を比べて、ルートの方が値が大きい場合はルートを含めてヒープ化が完了している。
- ルートの方が値が小さい場合は、大きい方の子と交換する。そのサブツリーに対して、交換が終了するまで繰り返せばヒープ化が完了する。

要素数を\\(n\\)とすると、完全２分木の高さは\\(\lfloor \log n\rfloor\\)なので、計算量は\\(O(\log n)\\)

**配列のヒープ化**

リーフのみからなるサブツリーは、ヒープ化されているとみなせる。
そのリーフの親をルートとして、上記ヒープ化手順でヒープ化すると、ヒープ化されたサブツリーができる。
これをボトムアップに行っていくことで配列全体をヒープ化できる。

```java
private static final int parentIndexOf(int childIndex, int rootIndex) { return rootIndex + (childIndex-rootIndex-1)/2; }
private static final int leftChildIndexOf(int parentIndex, int rootIndex) { return rootIndex + 2*(parentIndex-rootIndex)+1; }
private static final int rightChildIndexOf(int parentIndex, int rootIndex) { return rootIndex + 2*(parentIndex-rootIndex)+2; }
private static final void heapify(int a[], int fromIndex, int toIndex, int rootIndex) {
	int left = leftChildIndexOf(fromIndex, rootIndex), right = rightChildIndexOf(fromIndex, rootIndex);
	int largest = (left < toIndex && a[left] > a[fromIndex]) ? left : fromIndex;
	if (right < toIndex && a[right] > a[largest]) largest = right;
	if (largest != fromIndex) {
		swap(a, fromIndex, largest);
		heapify(a, largest, toIndex, rootIndex);
	}
}
public static final void heapSort(int a[], int fromIndex, int toIndex) {
	for (int i = parentIndexOf(toIndex-1, fromIndex); i >= fromIndex; --i) heapify(a, i, toIndex, fromIndex); //build heap
	for (int i = toIndex-1; i >= fromIndex+1; --i) {
		swap(a, fromIndex, i);
		heapify(a, fromIndex, i, fromIndex);
	}
}
```

##挿入ソート(insertion sorts)

###挿入ソート(insertion sort)

[http://en.wikipedia.org/wiki/Insertion_sort](http://en.wikipedia.org/wiki/Insertion_sort)

１番目の要素をソート済みリストに加えて、続く要素をソート済みリストの適切な位置に挿入していく。
安定な内部ソート。\\( O(n^2) \\)

ほとんどソート済みの系列に対して高速に動作する。
データ構造が配列だと、挿入操作のコストが多いが、連結リストなど挿入コストが小さい構造だと効果的。

特に、ソート済みリストの挿入位置を２分探索する場合を**２分挿入ソート(binary insertion sort)**という。

```haskell
isort :: Ord a => [a] -> [a]
isort [] = []
isort (x:xs) = insert x (isort xs)
      where insert x []                 = [x]
            insert x (y:ys) | (x <= y)  = x:y:ys
                            | otherwise = y:insert x ys 
```

###シェルソート(shell sort)

[http://en.wikipedia.org/wiki/Shellsort](http://en.wikipedia.org/wiki/Shellsort)

コムソートと同様に適当な間隔\\(h\\)をあけて取り出した系列に対して挿入ソートを行う。

非安定な内部ソート。最悪計算量は\\(O(n\log^{2} n)\\)。

\\( h_{i+1}=3h_{i} +1 = 1,4,13,40,121, \cdots, \frac{3^i -1}{2}, \cdots < n \\)を大きい方から採用すると\\(O(n^{1.25})\\)となる。

##分配整列(Distribution sorts)

###バケットソート、ビンソート(bucket sort, bin sort)

[http://en.wikipedia.org/wiki/Bucket_sort](http://en.wikipedia.org/wiki/Bucket_sort)

アルファベットの数(扱う値の種類数)\\(k\\)が少なく(有限で)、アルファベットの全順序関係を把握できているようなデータ系列に対して可能なソート。

1. 各アルファベットに対応する[tex:{k}]個のバケツを用意する(バケツは対応するアルファベットで整列しているものとする)
2. データ系列をスキャンして対応するバケツに入れる。
3. バケツに入れたものを順番に取り出して並べる。

データ数を\\(n\\)としたとき、計算量は\\(O(n + k)\\)の安定ソートとして実装できる。

ただし、メモリも普通に実装すると\\(O(n+k)\\)程度必要になる。

１つのバケツに複数のアルファベットを当てはめて(例えば、0-99, 100-200, 300-400, ...でバケツを分けて)、バケツの中でソートしても良い(ソートコストが小さい必要あり)。

特に、バケツを各アルファベットの(累積)度数表として実装したものを**度数ソート、計数ソート、分布数えソート((distribution) counting sort)**と呼ぶ。

```java
//k = Integer.MAX_VALUEだと大きすぎるので、k = Character.MAX_VALUEとしている
public static final void countingSort(char a[], int fromIndex, int toIndex) {
	int[] freq = new int[Character.MAX_VALUE+1];
	int n = toIndex - fromIndex;
	char tmp[] = new char[n]; System.arraycopy(a, fromIndex, tmp, 0, n);
	for (int i = 0; i < n; ++i) freq[tmp[i]]++;
	for (int k = 1; k < freq.length; ++k) freq[k] += freq[k-1];
	for (int i = n-1; i >= 0; --i) a[fromIndex + (--freq[tmp[i]])] = tmp[i];
}
```

###基数ソート(radix sort)

[http://en.wikipedia.org/wiki/Radix_sort](http://en.wikipedia.org/wiki/Radix_sort)

データの種類が有限で、値の範囲が既知で、データが「3桁の整数」や「2文字のアルファベット」など決まった形式であることが適用条件。
下位桁を安定ソートし、続いてその上位の桁を順次安定ソートしていく。

\\(K\\)桁のデータ\\(n\\)個に対して、\\(O(n)\\)のソートアルゴリズムで基数ソートする場合、\\(O(Kn)\\)でソートできる。

###スリープソート(sleep sort)

[http://dis.4chan.org/read/prog/1295544154](http://dis.4chan.org/read/prog/1295544154)

一時期話題になったスリープソート(笑)。バケットソートのバケツをスリープ(タイマー)に置き換えたもの。

- 各要素の値に応じたタイマー(sleep)をセットする。
- 各タイマーを一斉に走らせる。
- sleepが終了したものから対応する値を並べる。

特徴：

- 要素数だけスレッド作るとかやばい。一斉スタートも数が多いと多少の誤差が出るはず。
- 要素数が少なくても大きな値があると、長い時間スリープすることになる(n-bestが欲しい場合はあまり問題にはならないかも)。
- スリープ値を適当な数で割ったり、対数をとったりしてうまくスケーリングすると良いが、スリープ値が小さすぎたり、スリープ値の差があまりない場合、誤差で正しく整列されないことがある。
- マルチスレッドプログラミングの練習に使える？

```java
public static final void sleepSort(char a[], int fromIndex, int toIndex) {
	final int n = toIndex - fromIndex;
	final List<Character> sorted = Collections.synchronizedList(new ArrayList<Character>(n*2));
	Thread t[] = new Thread[n];
	for (int i = 0; i < n; ++i) {
		final Character item = a[fromIndex+i];
		t[i] = new Thread() {
			final Character element = item;
			final long time = item*10; //差が小さすぎると順序が逆転してしまうので
			public void run() {
				try {
					Thread.sleep(time);
					sorted.add(element);
				} catch (InterruptedException e) {}
			}
		};
	}
	//start timer
	for (int i = 0; i < t.length; ++i) t[i].start();
	//wait
	try {
		for (int i = 0; i < t.length; ++i) t[i].join();
	} catch (InterruptedException e) {}
	//copy
	for (int i = 0; i < t.length; ++i) a[fromIndex+i] = sorted.get(i);
}
```

##マージソート(Merge sorts)

###マージソート(merge sort, mergesort)

[http://en.wikipedia.org/wiki/Merge_sort](http://en.wikipedia.org/wiki/Merge_sort)

(ナイーブな)クイックソートと比べると、最悪計算量は少ないが、通常(ランダムデータの場合)はクイックソートのほうが速い。

左右に分割してそれぞれソートし、それらを(整列するように)マージしていく。
マージは左右のソート済み部分列の左端から順に(昇順なら)小さい方を、等しければ左側優先で選んでいけば安定になる。

通常, 分割またはマージの際に\\(O(n)\\)の外部記憶を要する。

分割していくと木の高さが\\(O(\log n)\\)、各深さのマージの計算量は\\(O(n)\\)、全体で\\(O(n\log n)\\)。

```haskell
msort :: Ord a => [a] -> [a]
msort []  = []
msort [x] = [x]
msort xs  = merge (msort left) (msort right)
      where (left, right) = splitAt ((length xs) `div` 2) xs
            merge [] ys                     = ys
            merge xs []                     = xs
            merge (x:xs) (y:ys) | (x <= y)  = (x:merge xs (y:ys))
                                | otherwise = (y:merge (x:xs) ys)
```

ところで、\\( a_0, a_1, \cdots, a_{n-1}\\)のbubbleSortによる交換回数\\(T\\)は、

\\( 
T=\sum_{i=0}^{n-1} f(i) \text{となる}\\
\text{ただし、}f(i)=\left | \{ j|j < i, b_j > a_i \} \right |, \\
b_0, b_1, \cdots, b_{n-1}\text{はソート済みの要素とする} \\
\\)

なので、マージソートするときに\\(f(i)\\)をカウントすれば\\(O(n\log n)\\)で\\(T\\)が求まる。

```java
public static int mergeCount(int[] a, int fromIndex, int toIndex){
	int count = 0;
	int n = toIndex - fromIndex;
	if(n > 1){
		int mid = fromIndex + n/2;
		count += mergeCount(a, fromIndex, mid); count += mergeCount(a, mid, toIndex);
		int[] temp = new int[n]; System.arraycopy(a, fromIndex, temp, 0, n);
		//merge index i-> x, j-> left subsequence of x, k-> right subsequence of x
		for(int i = fromIndex, j = 0, k = n/2; i < toIndex; ++i){
			if(j == n/2)				a[i] = temp[k++];
			else if(k == n)				a[i] = temp[j++];
			else if(temp[j] <= temp[k]) a[i] = temp[j++];
			else						{a[i] = temp[k++]; count += n/2 - j; }
		}
	}
	return count;
}
```

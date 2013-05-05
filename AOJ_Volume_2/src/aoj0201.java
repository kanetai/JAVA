import java.util.*;
public class aoj0201 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n;
		Map<String, Integer> value = new HashMap<String, Integer>();
		Map<String, ArrayList<String>> recipe = new HashMap<String, ArrayList<String>>();
		while((n = stdin.nextInt()) != 0){
			value.clear(); recipe.clear();
			while(n-- > 0) value.put(stdin.next(), stdin.nextInt());
			n = stdin.nextInt();
			while(n-- > 0){
				String dst = stdin.next();
				int m = stdin.nextInt();
				ArrayList<String> materials = new ArrayList<String>(m<<1);
				while(m-- > 0) materials.add(stdin.next());
				recipe.put(dst, materials);
			}
			String target = stdin.next();
			boolean flag = true;
			while(flag){
				flag = false;
				LOOP: for(String item: recipe.keySet()){
					int S = 0;
					for(String material: recipe.get(item)){
						if(!value.containsKey(material)) continue LOOP;
						S += value.get(material);
					}
					if(!value.containsKey(item) || value.containsKey(item) && value.get(item) > S){
						value.put(item, S);
						flag = true;
					}
				}
			}
			System.out.println(value.get(target));
		}
	}
}

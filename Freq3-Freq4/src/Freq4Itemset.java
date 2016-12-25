import java.util.ArrayList;
import java.util.Collections;

public class Freq4Itemset {
	ArrayList<String> items;
	Freq4Itemset(Freq3Itemset f3, String item)	{
		this.items = new ArrayList<>(f3.items);
		items.add(item);
		Collections.sort(items);
	}
	void printItemset()	{
		System.out.println(items);
	}
}
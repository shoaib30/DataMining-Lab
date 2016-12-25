import java.util.ArrayList;
import java.util.Arrays;

public class Freq3Itemset {
	ArrayList<String> items;
	Freq3Itemset(String items[])	{
		this.items = new ArrayList<>(Arrays.asList(items));
	}
	void printItemset()	{
		System.out.println(items);
	}
}

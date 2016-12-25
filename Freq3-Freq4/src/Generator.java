import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Generator {
	ArrayList<Freq3Itemset> f3;
	ArrayList<Freq4Itemset> f4;
	Set<String> items;
	Generator(String filename) throws NumberFormatException, IOException	{
		ReadItemset rd = new ReadItemset(filename, ",");
		f3 = rd.getItems();
		items = new HashSet<>();
		f4 = new ArrayList<>();
		this.generateItems();
		this.generateFreq4Itemset();
		this.removeDuplicates();
		this.printNewSet();
	}
	void generateItems()	{
		for(Freq3Itemset f: f3)	{
			for(int i=0; i<f.items.size(); i++){
				items.add(f.items.get(i));
			}
		}
	}
	void generateFreq4Itemset()	{
		for(Freq3Itemset f: f3)	{
			for(String item: items)	{
				if(!f.items.contains(item))	{
					f4.add(new Freq4Itemset(f,item));
				}
			}
		}
	}
	void removeDuplicates(){
		for(int i=0; i<f4.size()-1; i++){
			for(int j=i+1; j<f4.size(); j++)	{
				if(f4.get(i).items.get(0).equals(f4.get(j).items.get(0)) && f4.get(i).items.get(1).equals(f4.get(j).items.get(1)) && f4.get(i).items.get(2).equals(f4.get(j).items.get(2)) &&f4.get(i).items.get(3).equals(f4.get(j).items.get(3)))	{
					f4.remove(j);
					j = i+1;
				}
			}
		}
	}
	void printNewSet()	{
		for(Freq4Itemset f: f4){
			f.printItemset();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new Generator("itemset.csv");
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

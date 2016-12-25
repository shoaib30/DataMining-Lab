import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReadItemset {
	ArrayList<Freq3Itemset> itemset;
	String filename;
	String dilimiter;
	ReadItemset(String filename ,String dilimiter) throws NumberFormatException, IOException	{
		this.filename = filename;
		this.dilimiter = dilimiter;
		itemset = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(new File(this.filename)));
		String line;
		while ((line = br.readLine()) !=null) {
			String[] singleLine = line.split(this.dilimiter);
			itemset.add(new Freq3Itemset(singleLine));
		}
		br.close();
	}
	void printDataset()	{
		System.out.println("Itemset:-");
		for(Freq3Itemset r: itemset)
			r.printItemset();
	}
	ArrayList<Freq3Itemset> getItems()	{
		return itemset;
	}
}

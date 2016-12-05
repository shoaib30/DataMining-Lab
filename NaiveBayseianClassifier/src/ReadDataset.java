import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReadDataset {
	ArrayList<Record> records;
	String filename;
	String dilimiter;
	ReadDataset(String filename ,String dilimiter) throws NumberFormatException, IOException	{
		this.filename = filename;
		this.dilimiter = dilimiter;
		records = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(new File(this.filename)));
		String line;
		while ((line = br.readLine()) !=null) {
			String[] singleLine = line.split(this.dilimiter);
			records.add(new Record(Double.parseDouble(singleLine[0]),singleLine[1],Integer.parseInt(singleLine[2])));
		}
		br.close();
	}
	void printDataset()	{
		System.out.println("Dataset:-");
		for(Record r: records)
			r.printRecord();
	}
	ArrayList<Record> getRecords()	{
		return records;
	}
}

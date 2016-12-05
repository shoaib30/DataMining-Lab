
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadDataset {
	String fileName;
	String dilimiter;
	ArrayList<Record> records;
	BufferedReader br;
	ReadDataset	(String fileName,String dilimiter) throws IOException{
		this.fileName = fileName;
		this.dilimiter = dilimiter;
		records = new ArrayList<>();
		br = new BufferedReader(new FileReader(new File(this.fileName)));
		String line;
		while ((line = br.readLine()) !=null) {
			String[] singleLine = line.split(this.dilimiter);
			records.add(new Record(Integer.parseInt(singleLine[0]),Integer.parseInt(singleLine[1]),Integer.parseInt(singleLine[2]),Integer.parseInt(singleLine[3]),Integer.parseInt(singleLine[4]),Integer.parseInt(singleLine[5]),Integer.parseInt(singleLine[6])));
		}
	}
	ArrayList<Record> getRecords(){
		return records;
	}
}

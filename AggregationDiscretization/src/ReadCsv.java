import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCsv {
	String fileName;
	String dilimiter;
	ArrayList<String[]> csvData;
	BufferedReader br;
	ReadCsv	(String fileName,String dilimiter) throws IOException{
		this.fileName = fileName;
		this.dilimiter = dilimiter;
		csvData = new ArrayList<>();
		br = new BufferedReader(new FileReader(new File(this.fileName)));
		String line;
		while ((line = br.readLine()) !=null) {
			String[] singleLine = line.split(this.dilimiter);
			csvData.add(singleLine);
		}
	}
	void printCsv(){
		for (String[] line:csvData){
			System.out.print("len: "+ line.length + "\t");
			for(String word:line)	{
				System.out.print(word + "\t");
			}
			System.out.println();
		}
	}
	
}


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

public class ReadCsv {
	String fileName;
	String dilimiter;
	ArrayList<String[]> csvData;
	BufferedReader br;
	String[] headers;
	ReadCsv	(String fileName,String dilimiter) throws IOException{
		this.fileName = fileName;
		this.dilimiter = dilimiter;
		csvData = new ArrayList<>();
		br = new BufferedReader(new FileReader(new File(this.fileName)));
		String line;
		headers = br.readLine().split(dilimiter);
		while ((line = br.readLine()) !=null) {
			String[] singleLine = line.split(this.dilimiter);
			csvData.add(singleLine);
		}
	}
	void printCsv(DefaultTableModel model){
		model.setColumnIdentifiers(headers);
		for (String[] line:csvData){
			model.addRow(line);
		}
	}
}


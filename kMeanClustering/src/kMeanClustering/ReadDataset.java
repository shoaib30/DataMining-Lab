package kMeanClustering;

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
			records.add(new Record(Double.parseDouble(singleLine[0]), Double.parseDouble(singleLine[1])));
		}
	}
	ArrayList<Record> getRecords(){
		return records;
	}
}

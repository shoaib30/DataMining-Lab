import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
	String avgOfCol(int colNumber)throws NumberFormatException{
		double total = 0.0,avg = 0.0;
		int numberOfValues = 0;
		for(String[] line:csvData){
			if(colNumber<line.length && !line[colNumber].isEmpty()){
				double colVal = Double.parseDouble(line[colNumber]);
				total += colVal;
				numberOfValues++;
			}
		}
		avg = total/numberOfValues;
		return ""+avg;
	}
	String modeOfCol(int colNumber)	{
		HashMap<String,Integer> wordFreq = new HashMap<>();
		for(String[] line:csvData){
			
			if(colNumber<line.length && !line[colNumber].isEmpty()){
				if(wordFreq.containsKey(line[colNumber])){
					int x = wordFreq.get(line[colNumber]);
					wordFreq.put(line[colNumber], ++x);
				}else{
					wordFreq.put(line[colNumber], 1);
				}
			}
		}
		String mode = "";
		if(!wordFreq.isEmpty()){
			int maxFreq = 0;
			for(String keys:wordFreq.keySet()){
				if(wordFreq.get(keys) > maxFreq)	{
					mode = keys;
					maxFreq = wordFreq.get(keys);
				}
			}
		}
		return mode;
	}
	void replaceMissingValue(int type, int colNumber)	{
		String replaceValue = "";
		try{
			replaceValue =(type == 1)?this.avgOfCol(colNumber):this.modeOfCol(colNumber);
		}catch(NumberFormatException e){
			System.out.println("Not numeric");
		}
		for(String[] line:csvData)	{
			if(colNumber < line.length && line[colNumber].isEmpty())	{
				line[colNumber] = replaceValue;
			}
		}
	}
}


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class StratifiedSample {
	
	HashMap<String,ArrayList<String[]>> classes; 
	ReadCsv rcsv;
	StratifiedSample(String fileName) throws IOException	{
		rcsv = new ReadCsv(fileName, ","); // data format - usn,name,marks
		classes = new HashMap<>();
		classes.put("S",new ArrayList<>());
		classes.put("A",new ArrayList<>());
		classes.put("B",new ArrayList<>());
		classes.put("C",new ArrayList<>());
		classes.put("D",new ArrayList<>());
		classes.put("E",new ArrayList<>());
		classes.put("F",new ArrayList<>());
		this.stratifyData();
		this.selectRandomSample();
		
	}
	void stratifyData()	{
		for(String[] line: rcsv.csvData){
			double val = Double.parseDouble(line[2]);
			String cl;
			if(val <= 100 && val >= 90)
				cl = "S";
			else if(val >=75 && val < 90)
				cl = "A";
			else if(val >=60 && val < 75)
				cl = "B";
			else if(val >=45 && val <60)
				cl = "C";
			else if(val >=35 && val <45)
				cl = "D";
			else
				cl = "F";
			classes.get(cl).add(line);
		}
	}
	void selectRandomSample(){
		Random r = new Random();
		for(String key: classes.keySet())	{
			System.out.println("Class: " + key);
			if(classes.get(key).isEmpty())
				continue;
			String[] sample = classes.get(key).get(r.nextInt(classes.get(key).size()));
			for(String s: sample)
				System.out.print(s + " ");
			System.out.println();
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Enter file Name: ");
		Scanner sc = new Scanner(System.in);
		StratifiedSample s = new StratifiedSample(sc.next());
		sc.close();
	}

}

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Classifier {
	static Scanner sc = new Scanner(System.in);
	ArrayList<Record> records;
	Class c[];
	Classifier() throws NumberFormatException, IOException{
		System.out.println("Enter datafile name");
		ReadDataset rd = new ReadDataset("data.csv",",");
		records = rd.getRecords();
		c = new Class[2];
		c[0] = new Class();
		c[1] = new Class();
		classifyData();
	}
	void classifyData(){
		for(Record r: records)	{
			//System.out.println("class: " + r.classID);
			c[r.classID].addRecord(r);
		}
		c[0].calculateMean();
		c[0].calculateVariance();
		c[1].calculateMean();
		c[1].calculateVariance();
		c[0].calculateProbablity();
		c[1].calculateProbablity();
	}
	void continousValues()	{
		System.out.println("Numerical Values: ");
		for(int i=0; i<c.length; i++){
			System.out.println("Class: " + (i));
			System.out.println("Mean: " + c[i].getMean());
			System.out.println("Variance: "+ c[i].getVariance());
			System.out.println();
		}
	}
	void catagoricalValues()	{
		System.out.println("Catogrical Values: ");
		for(int i=0; i<c.length; i++)	{
			for(String s: c[i].probability.keySet()){
				System.out.println("Class: " + i + " Value: " + s + " = " + c[i].probability.get(s));
			}
		}
	}
	void classifyNewRecord()	{
		System.out.print("Enter value: ");
		double attr1 = sc.nextDouble();
		System.out.print("Enter type: ");
		String attr2 = sc.next();
		double newProb[] = new double[2];
		for(int i=0; i<c.length; i++)	{
			double exp = Math.pow((attr1 - c[i].mean), 2)/(2 * Math.pow(c[i].variance, 2));
			double prob = exp/(Math.sqrt(2 * 3.14 * Math.sqrt(c[i].variance)));
			newProb[i] = prob * c[i].getProbablity(attr2);
			System.out.println("Probability of class: "+ i + " = " + newProb[i]);
		}
		System.out.println("New record belongs to class " + ((newProb[0] > newProb[1]) ? 0:1));
	}
	public static void main(String[]args)	{
		try {
			Classifier cl = new Classifier();
			cl.continousValues();
			cl.catagoricalValues();
			cl.classifyNewRecord();
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


import java.io.IOException;
import java.util.ArrayList;

public class BestSplit {
	ArrayList<Record> records;
	int totalOfCol[];
	double entropyOfCol[], giniOfCol[];
	double entropy[], gini[], gain[];
	BestSplit() throws IOException{
		ReadDataset rd = new ReadDataset("data.csv",",");
		records = rd.getRecords();
		totalOfCol = new int[7];
		entropyOfCol = new double[7];
		giniOfCol = new double[7];
		entropy = new double[3];
		gini = new double[3];
		gain = new double[3];
	}
	void calculateTotalOfCols()	{
		for(int i=0; i<records.size(); i++){
			for(int j=0; j<totalOfCol.length; j++){
				totalOfCol[j] += records.get(i).attr[j];
			}
		}
	}
	void calculateEntGini()	{
		for(int i = 0; i<records.size(); i++){
			for(int j=0; j<totalOfCol.length; j++){
				double temp = records.get(i).attr[j]/(totalOfCol[j]*1.0);
				giniOfCol[j] += Math.pow(temp, 2);
				entropyOfCol[j] += temp * Math.log(temp)/ Math.log(2);
			}
		}
		for(int i=0; i<totalOfCol.length; i++){
			giniOfCol[i] = 1 - giniOfCol[i];
			entropyOfCol[i] = -1 * entropyOfCol[i];
		}
		gini[0] = (giniOfCol[0] * totalOfCol[0] / (totalOfCol[0] + totalOfCol[1])) + (giniOfCol[1] * totalOfCol[1]/ (totalOfCol[0] + totalOfCol[1]));
		gini[1] = (giniOfCol[2] * totalOfCol[2] / (totalOfCol[2] + totalOfCol[4])) + (giniOfCol[3] * totalOfCol[3]/ (totalOfCol[2] + totalOfCol[3]));
		gini[2] = (giniOfCol[4] * totalOfCol[4] / (totalOfCol[4] + totalOfCol[5] + totalOfCol[6])) + (giniOfCol[5] * totalOfCol[5]/ (totalOfCol[4] + totalOfCol[5] + totalOfCol[6])) + (giniOfCol[6] * totalOfCol[6]/ (totalOfCol[4] + totalOfCol[5] + totalOfCol[6]));
		entropy[0] = (entropyOfCol[0] * totalOfCol[0] / (totalOfCol[0] + totalOfCol[1])) + (entropyOfCol[1] * totalOfCol[1]/ (totalOfCol[0] + totalOfCol[1]));
		entropy[1] = (entropyOfCol[2] * totalOfCol[2] / (totalOfCol[2] + totalOfCol[4])) + (entropyOfCol[3] * totalOfCol[3]/ (totalOfCol[2] + totalOfCol[3]));
		entropy[2] = (entropyOfCol[4] * totalOfCol[4] / (totalOfCol[4] + totalOfCol[5] + totalOfCol[6])) + (entropyOfCol[5] * totalOfCol[5]/ (totalOfCol[4] + totalOfCol[5] + totalOfCol[6])) + (entropyOfCol[6] * totalOfCol[6]/ (totalOfCol[4] + totalOfCol[5] + totalOfCol[6]));
		
		double parentEntropy=-1*((0.5*(Math.log(0.5)/Math.log(2)))+(0.5*(Math.log(0.5)/Math.log(2))));
	    double parentGini=1-(0.5*0.5)-(0.5*0.5);
	    
	    for(int i=0; i<gini.length; i++){
	    	gain[i] = parentEntropy - entropy[i];
	    }
	    
	    System.out.println("Parent:");
	    System.out.println("Entropy: " + parentEntropy);
	    System.out.println("Gini: " + parentGini);
	    
	    System.out.println("\nNumerical Attributes: ");
	    System.out.println("Entropy: " + entropy[0]);
	    System.out.println("Gain: " + gain[0]);
	    System.out.println("Gini: " + gini[0]);
	    
	    System.out.println("\nBinary Attributes: ");
	    System.out.println("Entropy: " + entropy[1]);
	    System.out.println("Gain: " + gain[1]);
	    System.out.println("Gini: " + gini[1]);
	    
	    System.out.println("\nMultiway split Attributes: ");
	    System.out.println("Entropy: " + entropy[2]);
	    System.out.println("Gain: " + gain[2]);
	    System.out.println("Gini: " + gini[2]);
	}
	public static void main(String[]args) throws IOException	{
		BestSplit bs = new BestSplit();
		bs.calculateTotalOfCols();
		bs.calculateEntGini();
	}
}

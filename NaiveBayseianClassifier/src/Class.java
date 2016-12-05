import java.util.ArrayList;
import java.util.HashMap;

public class Class {
	ArrayList<Record> records;
	HashMap<String, Double> probability;
	double mean; 
	double variance;
	Class()	{
		records = new ArrayList<>();
		probability = new HashMap<>();
		mean = 0.0;
		variance = 0.0;
	}
	void addRecord(Record r){
		records.add(r);
	}
	void calculateMean()	{
		double sum=0;
		for(int i=0; i<records.size(); i++){
			sum+=records.get(i).attr1;
		}
		mean = sum/(records.size() * 1.0);
	}
	void calculateVariance()	{
		double sum=0;
		for(int i=0; i<records.size(); i++){
			sum += Math.pow(records.get(i).attr1 - mean, 2);
		}
		variance = sum / (records.size() * (records.size() - 1) *1.0);
	}
	void calculateProbablity()	{
		HashMap<String,Integer> freq = new HashMap<>();
		for(Record r: records){
			int val = 1;
			if(freq.containsKey(r.attr2))
				val += freq.get(r.attr2);
			freq.put(r.attr2, val);
		}
		for(String s: freq.keySet()){
			probability.put(s, (double) (freq.get(s)/(records.size() * 1.0)));
		}
	}
	double getProbablity(String type)	{
		if(probability.containsKey(type))	{
			return probability.get(type);
		}
		return 0.0;
	}
	double getMean(){
		return mean;
	}
	double getVariance()	{
		return variance;
	}
}

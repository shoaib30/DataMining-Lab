import java.util.ArrayList;
import java.util.Scanner;

public class Generator {
	ArrayList<Rule> rules;
	char itemset[];
	static Scanner sc = new Scanner(System.in);
	double thresholdSup, thresholdConf;
	Generator(String strItemSet, double thresholdSup, double thresholdConf)	{
		itemset = strItemSet.toCharArray();
		rules = new ArrayList<>();
		this.thresholdSup = thresholdSup;
		this.thresholdConf = thresholdConf;
		this.generateRules();
	}
	void generateRules()	{
		//generating 1-subset	
		for(int i=0; i<itemset.length; i++){
			Rule temp = new Rule();
			String lhs = "", rhs = "";
			lhs += itemset[i];
			for(int j=0; j<itemset.length; j++)	{
				if(lhs.indexOf(itemset[j]) < 0){
					rhs += itemset[j];
				}
			}
			temp.setLhs(lhs);
			temp.setRhs(rhs);
			rules.add(temp);
		}
		//generating 2-subset
		for(int i=0; i<itemset.length; i++){
			//System.out.println("Rule: " + (i+1));
			for(int j=i+1; j<itemset.length; j++)	{
				Rule temp = new Rule();
				String lhs = "", rhs = "";
				lhs += itemset[i];
				lhs += itemset[j];
				for(int k=0; k<itemset.length; k++){
					//System.out.println("Rule:" + itemset[j]);
					if(lhs.indexOf(itemset[k]) < 0){
						rhs += itemset[k];
					}
				}
				temp.setLhs(lhs);
				temp.setRhs(rhs);
				rules.add(temp);
			}
		}
		//generating 3-subset
		for(int i=0; i<itemset.length; i++){
			for(int j=i+1; j<itemset.length; j++)	{
				for(int k=j+1; k<itemset.length; k++){
					Rule temp = new Rule();
					String lhs = "", rhs = "";
					lhs += itemset[i];
					lhs += itemset[j];
					lhs += itemset[k];
					for(int l=0;l<itemset.length; l++){
						if(lhs.indexOf(itemset[l]) < 0){
							rhs += itemset[l];
						}
					}
					temp.setLhs(lhs);
					temp.setRhs(rhs);
					rules.add(temp);
				}
				
			}
		}
	}
	void printRules()	{
		for(Rule r: rules){
			r.printRule();
			System.out.println();
		}
	}
	void setSupAndConf(){
		System.out.println("\nEnter support and confidence for each rule: \n");
		for(Rule r: rules){
			r.printRule();
			System.out.print("   : ");
			double sup = sc.nextDouble();
			double conf = sc.nextDouble();
			r.setConfSup(sup, conf);
		}
	}
	void printStrongRules(){
		for(Rule r: rules){
			if(r.isStrongRule(thresholdSup, thresholdConf))	{
				r.printRule();
				System.out.println();
			}
		}
	}
	public static void main(String []args)	{
		Generator g = new Generator("abcd", 0.5, 0.7);
		System.out.println("Generated Rules: " );
		g.printRules();
		g.setSupAndConf();
		System.out.println("Generated Frequent Rules: " );
		g.printStrongRules();
	}
}

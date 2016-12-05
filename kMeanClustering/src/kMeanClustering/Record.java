package kMeanClustering;

public class Record {
	double attr1;
	double attr2;
	double distFromCentroid[];
	int cluster;
	Record(double attr1, double attr2)	{
		this.attr1 = attr1;
		this.attr2 = attr2;
		distFromCentroid = new double[3];
		cluster = -1;
	}
	void calculateDistFromCentroid(Centroid centroid[]){
		for(int i=0; i<centroid.length; i++){
			distFromCentroid[i] = Math.sqrt(Math.pow((centroid[i].attr1 - this.attr1), 2) + Math.pow(centroid[i].attr2 - this.attr2, 2));
		}
		double min = distFromCentroid[0];
		int ind = 0;
		for(int i=1;i<distFromCentroid.length; i++){
			if(distFromCentroid[i] < min){
				min = distFromCentroid[i];
				ind = i;
			}
		}
		cluster = ind;
	}
	void printDist(){
		System.out.printf("%.2f\t%.2f\t%.2f\t%d\n", distFromCentroid[0],distFromCentroid[1],distFromCentroid[2] ,cluster);
		//System.out.println(distFromCentroid[0] + "\t" + distFromCentroid[1] + "\t" + cluster);
	}
}

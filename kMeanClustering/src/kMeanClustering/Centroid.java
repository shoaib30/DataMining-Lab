package kMeanClustering;

public class Centroid {
	double attr1;
	double attr2;
	Centroid(double attr1, double attr2){
		this.attr1 = attr1;
		this.attr2 = attr2;
	}
	static boolean compareCentroid(Centroid[] C1, Centroid[] C2){
		for(int i=0; i<C1.length; i++){
			if(C1[i].attr1 != C2[i].attr1 || C1[i].attr2 != C2[i].attr2)
				return false;
		}
		return true;
	}
	void printCentroid()	{
		System.out.println("(" + attr1 + "," + attr2 + ")");
	}
}

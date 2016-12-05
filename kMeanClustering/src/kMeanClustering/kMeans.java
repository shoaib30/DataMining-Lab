package kMeanClustering;

import java.io.IOException;
import java.util.ArrayList;

public class kMeans {
	ArrayList<Record>records;
	Centroid C[];
	kMeans() throws IOException	{
		System.out.println("Enter filename: ");
		ReadDataset rd = new ReadDataset("data.csv",",");
		records = rd.getRecords();
		C = new Centroid[3];
		initCluster();
	}
	void initCluster()	{
		C[0] = new Centroid(records.get(0).attr1,records.get(0).attr2);
		C[1] = new Centroid(records.get(3).attr1,records.get(3).attr2);
		C[2] = new Centroid(records.get(6).attr1,records.get(6).attr2);
	}
	Centroid[] generateCluster()	{		//generates new cluster and returns new centroids
		System.out.println("\nGenerating Cluster");
		int numberOfRecordsInCluster[] = new int[3];
		int newCentroidValue[][] = new int[3][2];
		for(Record r: records)	{
			r.calculateDistFromCentroid(C);
			numberOfRecordsInCluster[r.cluster]++;
			newCentroidValue[r.cluster][0] += r.attr1;
			newCentroidValue[r.cluster][1] += r.attr2;
			r.printDist();
		}
		Centroid newCentroids[] = new Centroid[3];
		for(int i=0; i<3; i++){
			newCentroids[i] = new Centroid(newCentroidValue[i][0] / (numberOfRecordsInCluster[i]*1.0) , newCentroidValue[i][1] / (numberOfRecordsInCluster[i]*1.0));
		}
		System.out.println("Counts:- ");
		for(int i=0; i<newCentroids.length; i++){
			System.out.println(i + "->" +numberOfRecordsInCluster[i]);
		}
		System.out.println("Cntroids:- ");
		for(int i=0; i<newCentroids.length; i++){
			newCentroids[i].printCentroid();
		}
		return newCentroids;
	}
	public static void main(String[] args) throws IOException	{
		kMeans km = new kMeans();
		while(true){
			Centroid newCent[] = km.generateCluster();
			if(Centroid.compareCentroid(km.C, newCent))	{
				break;
			}
			km.C=newCent;
		}
		
	}
}

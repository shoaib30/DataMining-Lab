import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Record_Csv{
    String ssn;
    int weight;
    String gen;
    double attr1;
    int attr2;
};

public class Cluster {
    static Record_Csv[] rc = new Record_Csv[30];
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        //Read from file
        BufferedReader CSV =
            new BufferedReader(new FileReader("1.csv"));

      String data = CSV.readLine();
      data = CSV.readLine();
      int i=0;
      System.out.println("SSN\tJunk food(calories)\tAge\tWeight\tGender");
      while((data != null))
      {
          rc[i]= new Record_Csv();

          String[] dataArray = data.split(",");
          rc[i].ssn=dataArray[0];
          rc[i].attr1=Double.parseDouble(dataArray[1]);
          rc[i].attr2=Integer.parseInt(dataArray[2]);
          rc[i].weight=Integer.parseInt(dataArray[3]);
          rc[i].gen=dataArray[4];
           System.out.println(rc[i].ssn+"\t"+rc[i].attr1+"\t\t\t"+rc[i].attr2+"\t"+rc[i].weight+"\t"+rc[i].gen);
          data = CSV.readLine();
          i++;
            }
      CSV.close();
      //Initial centroids are
      Record_Csv[] cent = new Record_Csv[4];
      for(int j=0;j<4;j++)
      cent[j] = new Record_Csv();
       
       cent[0].attr1=rc[0].attr1;
       cent[0].attr2 = rc[0].attr2;
       cent[1].attr1=rc[12].attr1;
       cent[1].attr2 = rc[12].attr2;
       cent[2].attr1=rc[21].attr1;
       cent[2].attr2 = rc[21].attr2;
       cent[3].attr1=rc[29].attr1;
       cent[3].attr2 = rc[29].attr2;
       
      double[][] dist_mat = new double[i][5];
      //Compute the distance from every points to the centroids
      double C01 = 0,C02=0,C11=0,C12=0,C21=0,C22=0,C31=0,C32=0;
      while((C01!=cent[0].attr1) || (C02 != cent[0].attr2) || (C11!= cent[1].attr1) || (C12!=cent[1].attr2) || (C21 != cent[2].attr1) || (C22 != cent[2].attr2) || (C31 != cent[3].attr1) || (C32 != cent[3].attr2)){
          for(int j=0;j<i;j++)
          for(int k=0;k<4;k++)
              dist_mat[j][k]=Math.sqrt(Math.pow(rc[j].attr1-cent[k].attr1, 2)+Math.pow(rc[j].attr2-cent[k].attr2, 2));
      
          //Identify the closest centroid
      double small=0;
      double index=0;
      for(int j=0;j<i;j++)
      {small=dist_mat[j][0];
          for(int k=0;k<4;k++)
            if(dist_mat[j][k]<small)
            { small=dist_mat[j][k];
              index =k;
            }
          dist_mat[j][4]=index;
      }
      
      //print distance matrix
      System.out.println("\n Distance matrix:");
      for(int j=0;j<i;j++)
      {for(int k=0;k<4;k++)
              System.out.print(dist_mat[j][k]+"\t\t\t");
       System.out.println();
      }
      
         C01 = cent[0].attr1;
         C02 = cent[0].attr2;
         C11 = cent[1].attr1;
         C12 = cent[1].attr2;
         C21 = cent[2].attr1;
         C22 = cent[2].attr2;
         C31 = cent[3].attr1;
         C32 = cent[3].attr2;
      
         //Update centroids
      int count1=0,count2=0,count3=0,count4=0;
      cent[0].attr1=0;
      cent[0].attr2 = 0;
      cent[1].attr1=0;
      cent[1].attr2 = 0;
      cent[2].attr1=0;
      cent[2].attr2 = 0;
      cent[3].attr1 = 0;
      cent[3].attr2 = 0;
      int[] a = new int[30];
      int[] b = new int[30];
      int[] c = new int[30];
      int[] d = new int[30];
      for(int j=0;j<i;j++)
      {
          if(dist_mat[j][4]==0)
          {cent[0].attr1+=rc[j].attr1;
          cent[0].attr2+=rc[j].attr2;
          a[count1]=j;
          count1++;
          }
          if(dist_mat[j][4]==1)
          {cent[1].attr1+=rc[j].attr1;
          cent[1].attr2+=rc[j].attr2;
          b[count2]=j;
          count2++;
          }
          if(dist_mat[j][4]==2)
          {cent[2].attr1+=rc[j].attr1;
          cent[2].attr2+=rc[j].attr2;
          c[count3]=j;
          count3++;
          }
          if(dist_mat[j][4]==3)
          {cent[3].attr1+=rc[j].attr1;
          cent[3].attr2+=rc[j].attr2;
          d[count4]=j;
          count4++;
          }

      }
      System.out.println("\n Counts are "+count1+"  "+count2+"  "+count3+" "+count4);
      
         cent[0].attr1=cent[0].attr1/count1;
         cent[0].attr2=cent[0].attr2/count1;
         cent[1].attr1=cent[1].attr1/count2;
         cent[1].attr2=cent[1].attr2/count2;
         cent[2].attr1=cent[2].attr1/count3;
         cent[2].attr2=cent[2].attr2/count3;
         cent[3].attr1=cent[3].attr1/count4;
         cent[3].attr2=cent[3].attr2/count4;
         
         System.out.println("\n Updated Centroids are:");
         for(int k=0;k<4;k++)
         {
             System.out.println(cent[k].attr1+" and "+cent[k].attr2);
         }
        
              System.out.println();
              System.out.println("Cluster 1");
             System.out.println("SSN\tJunk food(calories)\tAge\tWeight\tGender");
             for(int m=0;m<count1;m++){
                 System.out.println(rc[a[m]].ssn+"\t"+rc[a[m]].attr1+"\t\t\t"+rc[a[m]].attr2+"\t"+rc[a[m]].weight+"\t"+rc[a[m]].gen);
             }
             System.out.println("Cluster 2");
             System.out.println("SSN\tJunk food(calories)\tAge\tWeight\tGender");
             for(int m=0;m<count2;m++){
                 System.out.println(rc[b[m]].ssn+"\t"+rc[b[m]].attr1+"\t\t\t"+rc[b[m]].attr2+"\t"+rc[b[m]].weight+"\t"+rc[b[m]].gen);
             }
             System.out.println("Cluster 3");
             System.out.println("SSN\tJunk food(calories)\tAge\tWeight\tGender");
             for(int m=0;m<count3;m++){
                 System.out.println(rc[c[m]].ssn+"\t"+rc[c[m]].attr1+"\t\t\t"+rc[c[m]].attr2+"\t"+rc[c[m]].weight+"\t"+rc[c[m]].gen);
             }
             System.out.println("Cluster 4");
             System.out.println("SSN\tJunk food(calories)\tAge\tWeight\tGender");
             for(int m=0;m<count4;m++){
                 System.out.println(rc[d[m]].ssn+"\t"+rc[d[m]].attr1+"\t\t\t"+rc[d[m]].attr2+"\t"+rc[d[m]].weight+"\t"+rc[d[m]].gen);
             }
         }
        }
}
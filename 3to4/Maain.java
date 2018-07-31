import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maain {
	public static void main(String args[]) throws IOException
	{
		BufferedReader csv = new BufferedReader(new FileReader("3.csv"));
		String data = csv.readLine();
		String[] final_data= new String[20];
		int i=0;
		System.out.println("Reading Line\n");
		while(data!=null){
			
			//System.out.println("Record No "+i+" ");
			String[] dataArray=data.split(",");
			final_data[i] = dataArray[0]+dataArray[1]+dataArray[2];
			//System.out.println(dataArray[0]+","+dataArray[1]+","+dataArray[2]+"");
			data = csv.readLine();
			i++;
		}
		
		int j=0;
		int k;
		for(;j<i-1;j++)
		{
			for(k=j+1;k<i;k++){
				if(final_data[j].charAt(0) == final_data[k].charAt(0)&&final_data[j].charAt(1) == final_data[k].charAt(1)){
					if(final_data[j].charAt(2) == final_data[k].charAt(2))
					continue;
					else
					{
						//if(final_data[j].charAt(2) < final_data[k].charAt(2)){
							System.out.println(final_data[j]+final_data[k].charAt(2));
						//}
					}
				}
			}
		}
	}
}

import java.io.IOException;
import java.util.Scanner;

public class ReplaceMissingValues {
	static Scanner sp = new Scanner(System.in);
	
	void menu(ReadCsv rc) throws Exception	{
		System.out.println("Choice\n1.Avg of columns\n2.Mode of Column\n3.Print CSV\n4.Exit");
		int op = sp.nextInt();
		switch(op)	{
			case 1:
			case 2:
				System.out.println("Enter the Column to replace");
				rc.replaceMissingValue(op, sp.nextInt());
				break;
			case 3:
				rc.printCsv();
				break;
			case 4:
				throw new Exception();
			default:
				System.out.println("Wrong choice");
		}
				
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter CSV file name: ");
		String name = sp.next();
		System.out.println("Enter the delimiter: ");
		String delim = sp.next();
		ReadCsv rc=null;
		try {
			rc = new ReadCsv(name,delim);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found");
		}
		ReplaceMissingValues rmv = new ReplaceMissingValues();
		while(true){
			try {
				rmv.menu(rc);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return;
			}
		}
	}

}

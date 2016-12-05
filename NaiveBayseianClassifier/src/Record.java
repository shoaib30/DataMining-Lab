
public class Record {
	String attr2;
	double attr1;
	int classID;
	Record(double attr1, String attr2, int classID)	{
		//System.out.println("her");
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.classID = classID;
		//System.out.println(this.attr1 + "\t" + this.attr2 + "\t" + this.classID);
	}
	void printRecord(){
		System.out.println(this.attr1 + "\t" + this.attr2 + "\t" + this.classID);
	}
}

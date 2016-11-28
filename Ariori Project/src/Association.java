
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Association {
	ArrayList<ItemSet> hm;
	ArrayList<ArrayList<Integer>> data;
	String attributes[];
	String fileName = "data.csv";
	BufferedReader br;
	int support;
	int totalTransactions;
	int index;
	int index2;
	int confidence;

	Association(int s) throws FileNotFoundException {
		br = new BufferedReader(new FileReader(fileName));
		hm = new ArrayList<ItemSet>();
		data = new ArrayList<>();
		support = s;
		index = 0;
		index2 = 0;
		confidence = 60;
	}

	void readFile() throws IOException {
		attributes = br.readLine().split(",");
		String buffer2 = br.readLine();
		int k = 0;
		while (buffer2 != null) {
			String[] buffer = buffer2.split(",");
			data.add(new ArrayList<>());
			for (int i = 0; i < attributes.length; i++) {
				if (buffer[i].equals("1")) {
					data.get(k).add(i);
				}
			}
			k++;
			buffer2 = br.readLine();
		}
		br.close();
		totalTransactions = data.size();
	}

	void set1Generation() {
		for (int i = 0; i < attributes.length; i++) {
			int supp = findSupport(new int[] { i });
			if ((float) (supp * 100 / totalTransactions) >= support) {
				ItemSet s = new ItemSet(1, attributes);
				s.make(new int[] { i }, supp);
				hm.add(s);
			}
		}
	}

	void set2generation() {

		int size = hm.size();
		index = size;
		index2 = index;
		for (int i = 0; i < size - 1; i++) {
			int item1;
			item1 = hm.get(i).items[0];
			for (int j = i + 1; j < size; j++) {
				int item2 = hm.get(j).items[0];
				int supp = findSupport(new int[] { item1, item2 });

				if ((float) (supp * 100 / totalTransactions) >= support) {
					ItemSet s = new ItemSet(2, attributes);
					s.make(new int[] { item1, item2 }, supp);
					hm.add(s);
				}
			}
		}
	}

	int setNgeneration(int n) {
		int tempIndex = index;
		index = hm.size();
		// System.out.println("index "+index+" tempindex "+tempIndex);
		for (int i = tempIndex; i < index - 1; i++) {
			for (int j = i + 1; j < index; j++) {
				if (selfJoin(hm.get(i).items, hm.get(j).items, n - 1)) {

					int[] newArr = new int[n];
					System.arraycopy(hm.get(i).items, 0, newArr, 0, n - 1);
					newArr[n - 1] = hm.get(j).items[n - 2];
					int supp = findSupport(newArr);
					if ((float) (supp * 100 / totalTransactions) >= support) {
						ItemSet s = new ItemSet(n, attributes);
						s.make(newArr, supp);
						hm.add(s);
					}
				}
			}
		}
		return (hm.size() - index);
	}

	boolean selfJoin(int[] a, int[] b, int n) {
		int[] x = Arrays.copyOfRange(a, 0, n - 1);
		// System.out.println(x[0]);
		int[] y = Arrays.copyOfRange(b, 0, n - 1);
		// System.out.println(y[0]);
		// System.out.println();
		if (Arrays.equals(x, y)) {
			// System.out.println("check3");
			return true;
		}
		return false;
	}

	int findSupport(int[] arr) {
		int count = 0;
		for (ArrayList<Integer> a : data) {
			boolean flag = true;
			for (int x : arr) {
				if (!a.contains(x)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				count++;
			}
		}
		return count;
	}

	void comb(int a[], int temp[], int s, int e, int r, int index, int supp, ItemSet x) {
		if (r == index) {
			//System.out.println("support:" +findSupport(temp));
			for (ItemSet is : hm) {
				if (is.n == r) {
					//System.out.println("check1");
					if (Arrays.equals(is.items, temp)) {
						//System.out.println("check2");
						if ((float) (is.support * 100 / supp) >= confidence) {
							System.out.println("check2");
							for (int j : temp) {
								System.out.print(attributes[j] + ",");
							}
							System.out.print(" -> ");
							
							for(int l:x.items) {
								boolean flag=true;
								for(int m:temp) {
									if(m==l) {
										flag=false;
										break;
									}
								}
								if(flag)
									System.out.print(attributes[l]+",");
							}
							System.out.println();
							
//							for (int i : list1) {
//								System.out.print(attributes[i] + ",");
//							}
							System.out.println((float) supp * 100 / is.support);
							System.out.println();
						}
					}
				}
			}
			
		}
		else {
			for (int i = s; i <= e && e - i + 1 >= r - index; i++) {
				temp[index] = a[i];
				comb(a, temp, i + 1, e, r, index + 1, supp, x);
			}
		}
	}

	void conf(int c) {
		confidence = c;
		for (int i = index2; i < hm.size(); i++) {
			int[] a = hm.get(i).items;
			int supp = hm.get(i).support;
			for(int r=1;r<hm.get(i).n;r++) {
				int[] b = new int[r];
				comb(a, b, 0, hm.get(i).n - 1, r, 0, supp, hm.get(i));
				System.out.println();
			}
		}
	}

	void display() {
		// System.out.println(hm.size());
		for (ItemSet i : hm) {
			i.display();
			System.out.println("support : " + i.support);
			System.out.println("");
		}
	}

	public static void main(String[] args) throws IOException {
		Association a = new Association(50);
		a.readFile();
		a.set1Generation();
		a.set2generation();
		int flag = 1;
		int k = 3;
		while (flag != 0) {
			flag = a.setNgeneration(k);
			k++;
		}
		a.conf(70);
		// a.display();
	}

}

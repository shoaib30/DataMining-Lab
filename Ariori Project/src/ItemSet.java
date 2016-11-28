class ItemSet {
	int n;
	int[] items;
	String[] itemNames;
	String attributes[];
	int support;

	public ItemSet(int n, String[] attr) {
		this.n = n;
		items = new int[n];
		itemNames = new String[n];
		attributes = attr;
	}

	void make(int[] it, int s) {
		support = s;
		for (int i = 0; i < n; i++) {
			items[i] = it[i];
			itemNames[i] = attributes[it[i]];
		}
	}

	void display() {
		for (String s : itemNames) {
			System.out.println(s);
		}
	}
}
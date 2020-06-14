package util.rmq;

public class RangeMinimumQuerySparseTable extends RangeMinimumQuery {

	private final int arrayLength;
	private int[] logTable;
	private int[][] rmq;

	public RangeMinimumQuerySparseTable(int[] a) {
		super(a);
		this.arrayLength = super.array.length;

		this.logTable = new int[this.arrayLength + 1];
		for (int i = 2; i <= this.arrayLength; i++)
			this.logTable[i] = this.logTable[i >> 1] + 1;

		this.rmq = new int[logTable[this.arrayLength] + 1][this.arrayLength];

		for (int i = 0; i < this.arrayLength; ++i)
			rmq[0][i] = i;

		for (int k = 1; (1 << k) < this.arrayLength; ++k) {
			for (int i = 0; i + (1 << k) <= this.arrayLength; i++) {
				int x = rmq[k - 1][i];
				int y = rmq[k - 1][i + (1 << k - 1)];
				rmq[k][i] = a[x] <= a[y] ? x : y;
			}
		}
	}

	public int query(int i, int j) {
		if (j < i) {
			return query(j, i);
		}
		int k = logTable[j - i];
		int x = rmq[k][i];
		int y = rmq[k][j - (1 << k) + 1];
		return super.array[x] <= super.array[y] ? x : y;
	}
}

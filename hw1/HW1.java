import java.util.Random;
import java.util.Arrays;
import java.lang.Math;
public class HW1 {
	
	// This is the method that returns the count of 'a' chars in the matrix
	// Feel free to add a helper method for the recursive part of the algorithm
	public static int aCountRow(char[] row, int start, int end) {
		if (end > start) {
			double diff = (end+start) / 2;
			int middle = (int)Math.floor(diff);
			if(row[middle] == 'a') {
				if(row[middle + 1] == 'b') {
					// System.out.printf("returned value: %d\n" , middle+1);
					return middle+1;
				}
				if(start == middle) {
					return middle+2;
				}
				start = middle;
				// System.out.printf("\nstart: %d, end: %d\n", start,end);
				return aCountRow(row, start, end);
			} else if(row[middle] == 'b') {
				if(middle == 0) {
					return 0;
				}
				if(row[middle - 1] == 'a') {
					// System.out.printf("returned value: %d\n" , middle);
					return middle;
				}
				end = middle;
				// System.out.printf("start: %d, end: %d\n", start,end);
				return aCountRow(row, start, end);
			}
		}
		return 0;
	}
	public static int acount(char[][] mat) {
		int result = 0;
		int dimension = mat.length;

        for(int i = 0; i< dimension; i++) {
			result += aCountRow(mat[i], 0, dimension-1);
			} 
		
		return result;
	}

	public static void main(String[] args) {
		// This method creates a test case for you
		int n = 4;
		Random rand = new Random();
		char[][] input = new char[n][n];
		for (int i = 0; i < n; i++) {
			int a_num = rand.nextInt(n);
			for (int j = 0; j <= a_num; j++) {
				input[i][j] = 'a';
			}
			for (int j = a_num; j < n; j++) {
				if((input[i][j] != 'a') || j == 0) {
					input[i][j] = 'b';
				}
			}
		}
		// Here you can see the matrix row by row 

		System.out.println(Arrays.deepToString(input));
		// Here you can see the result of your function
		System.out.println(acount(input));
	}

}

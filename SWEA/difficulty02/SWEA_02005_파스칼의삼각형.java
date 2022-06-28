/*
 * SWEA 02005. 파스칼의 삼각형
 */

package difficulty02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SWEA_02005_파스칼의삼각형 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("./input/difficulty02/input_02005.txt")));
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(in.readLine());
			int[][] pascal = new int[N][N];
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= i; j++) {
					if(j == 1 || j == i) {
						pascal[i - 1][j - 1] = 1;
					}
					else {
						pascal[i - 1][j - 1] = pascal[i - 2][j - 2] + pascal[i - 2][j - 1];
					}
				}
			}
			System.out.print(result(pascal, test_case));
		}
		in.close();
	}
	
	public static StringBuilder result(int[][] pascal, int tc) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("#%d\n", tc));
		
		for (int i = 0; i < pascal.length; i++) {
			for (int j = 0; j < pascal[i].length; j++) {
				if(pascal[i][j] != 0) sb.append(pascal[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb;
	}
}

/*
 * SWEA 01209. Sum
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_01209_Sum {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty03/input_01209.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int tc = Integer.parseInt(br.readLine());		
			int[][] arr = new int[100][100];

			// 행/열 합의 최대
			int rowMaxSum = 0;
			int colMaxSum = 0;
			
			int LdiagonalSum = 0;
			int RdiagonalSum = 0;
			
			int maxVal = 0;
			
			// 행렬 입력
			for(int r = 0; r < 100; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < 100; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 문제 풀이
			for(int i = 0; i < 100; i++) {
				int rowSum = 0;
				int colSum = 0;
				
				for(int j = 0; j < 100; j++) {
					rowSum += arr[i][j]; // 행의 합 계산
					colSum += arr[j][i]; // 열의 합 계산
				}
				
				rowMaxSum = Math.max(rowMaxSum, rowSum);
				colMaxSum = Math.max(colMaxSum, colSum);
				
				LdiagonalSum += arr[i][i]; // 우하향 대각선의 합
				RdiagonalSum += arr[i][99-i]; // 우상향 대각선의 합
			}

			// 오름차순 정렬
			int[] records = {rowMaxSum, colMaxSum, LdiagonalSum, RdiagonalSum};
			Arrays.sort(records);
			maxVal = records[records.length - 1];
			
			System.out.printf("#%d %d\n", tc, maxVal);
		}
		br.close();
	}
}

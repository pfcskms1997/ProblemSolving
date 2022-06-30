/*
 * SWEA 02805. 농작물 수확하기
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SWEA_02805_농작물수확하기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty03/input_02805.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[][] farm = new int[N][N];
			int income = 0;
			int half = N / 2;
			int colRange = 1;
			int flag = 1;
			
			// 2차원 배열 입력
			for(int i = 0; i < N; i++) {
				String temp = br.readLine();
				for(int j = 0; j < temp.length(); j++) {
					farm[i][j] = temp.charAt(j) - '0';
				}
			}
			
			// 농장에서 얻을 수 있는 총소득 계산
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < colRange; j++) {
					income += farm[i][half + j];
					if(half + j == 0) flag = -1;
				}
				colRange += flag * 2;
				half -= flag * 1;
			}
			System.out.printf("#%d %d\n", test_case, income);
		}
		br.close();
	}
}

/*
 * BAEKJOON 02999. 비밀 이메일
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_02999_비밀이메일 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_2999.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			String mail = br.readLine();
			int len = mail.length();
			int R = 1;
			int C = 1;
			
			for(int i = 1; i <= (int)Math.sqrt(len); i++) {
				if(len % i == 0) R = i;
			}
			C = len / R;
			
			int[][] matrix = new int[R][C];
			char[] temp = mail.toCharArray();
			int idx = 0;
			
			for(int c = 0; c < C; c++) {
				for(int r = 0; r < R; r++) {
					matrix[r][c] = temp[idx];
					idx++;
				}
			}
			
			String result = "";
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					result += (char)matrix[r][c];
				}
			}
			
			System.out.printf("#%d %s\n", test_case, result);
		}
		br.close();
	}
}

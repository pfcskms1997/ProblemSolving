/*
 * BAEKJOON 02839. 설탕 배달
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_02839_설탕배달_Greedy {
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_02839.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 총 설탕의 무게
			int cnt = 0;
			
			while(N != 0) {
				if(N == 1 || N == 2 || N == 4 || N == 7) {
					cnt = -1;
					break;
				}
				else if(N % 5 == 0) { // 5의 배수인 경우
					cnt++;
					N -= 5;
				}
				else if(N % 3 == 0) { // 3의 배수인 경우
					cnt++;
					N -= 3;
				}
				else {
					cnt++;
					N -= 5;
				}
			}
			
			System.out.printf("#%d %d\n", test_case, cnt);
		}
		br.close();
	}
}

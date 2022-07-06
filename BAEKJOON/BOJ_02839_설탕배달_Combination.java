/*
 * BAEKJOON 02839. 설탕 배달
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_02839_설탕배달_Combination {

	static int minVal;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_02839.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 총 설탕의 무게
			minVal = 987654321;
			// 중복조합
			combinationRept(N, new int[(N/3) + 1], 0, 0);
			
			if(minVal == 987654321) minVal = -1;
			
			System.out.printf("#%d %d\n", test_case, minVal);
		}
		br.close();
	}
	
	static int[] opt = {5, 3};
	
	private static void combinationRept(int N, int[] sel, int idx, int start) {
		if(N == 0) {
			//System.out.println(Arrays.toString(sel));
			minVal = Math.min(minVal, idx);
			return;
		}
		
		if(N < 0) return;
		
		for(int i = 0; i < opt.length; i++) {
			sel[idx] = opt[i];
			combinationRept(N-opt[i], sel, idx+1, i);
			
			if(minVal < (N/5) + 1) break;
		}
	}
}

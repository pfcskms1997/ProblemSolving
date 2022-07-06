/*
 * BAEKJOON 16922. 로마 숫자 만들기
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class BOJ_16922_로마숫자만들기 {

	static HashSet<Integer> set = null;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_16922.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			// 뽑힌 수의 합이 중복되지 않게 관리하기 위해 set 자료구조 사용
			set = new HashSet<Integer>();
			
			// 순서에 상관 없으므로 중복조합 사용
			combinationRept(new int[] {1, 5, 10, 50}, new int[N], 0, 0);
			
			bw.write(String.format("#%d %d\n", test_case, set.size()));
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	public static void combinationRept(int[] input, int[] sel, int cnt, int start) {
		if(cnt == sel.length) {
			int sum = 0;
			
			for(int i = 0; i < sel.length; i++) {
				sum += sel[i];
			}
			set.add(sum);
			
			return;
		}
		
		for(int i = start; i < input.length; i++) {
			sel[cnt] = input[i];
			combinationRept(input, sel, cnt+1, i);
		}
	}
}

/*
 * BAEKJOON 15663. N과 M(9)
 * Hint: 중복되지 않는 순열
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15663_N과M9 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_15663.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] number = new int[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
		
			// 자연수 원소를 오름차순 정렬
			Arrays.sort(number);
			
			System.out.println("#" + test_case);
			permutation(number, new int[M], 0, 0);
		}
		br.close();
	}
	
	public static void permutation(int[] number, int[] sel, int flag, int cnt) {
		// Base Part
		if(cnt == sel.length) {
			System.out.println(Arrays.toString(sel).replaceAll("[\\[\\,\\]]", ""));
			return;
		}
		
		// 이전에 뽑았던 숫자를 저장
		int lastPick = -1;
		
		for(int i = 0; i < number.length; i++) {
			if((flag & 1 << i) != 0 || lastPick == number[i]) continue;
			
			sel[cnt] = number[i];
			lastPick = number[i];
			permutation(number, sel, flag | 1 << i, cnt+1);
		}
	}
}

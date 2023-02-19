/*
 * BAEKJOON 15650. N과 M(2)
 * Hint: 중복되지 않은 조합
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15650_N과M2 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_15650.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] number = new int[N];
			int[] selection = new int[M];
			
			for(int i = 0; i < N; i++) {
				number[i] = i + 1;
			}
			
			System.out.println("#" + test_case);
			combination(number, selection, 0, 0);
			
		}
		br.close();
	}
	
	public static void combination(int[] number, int[] selection, int cnt, int start) {
		if(cnt == selection.length) {
			for(int i = 0; i < selection.length; i++) {
				System.out.print(selection[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = start; i < number.length; i++) {
			selection[cnt] = number[i];
			combination(number, selection, cnt+1, i+1);
		}
	}
}

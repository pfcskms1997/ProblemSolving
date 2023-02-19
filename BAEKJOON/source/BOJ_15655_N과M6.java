/*
 * BAEKJOON 15655. N과 M(6)
 * Hint: 중복되지 않는 조합
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15655_N과M6 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_15655.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			sb = new StringBuilder();
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] number = new int[N];
			int[] selection = new int[M];
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(number);
			
			System.out.println("#" + test_case);
			combination(number, selection, 0, 0);
			System.out.print(sb);
		}
		br.close();
	}
	
	static StringBuilder sb;
	
	public static void combination(int[] number, int[] selection, int cnt, int start) {
		if(cnt == selection.length) {
			for(int i = 0; i < selection.length; i++) {
				sb.append(selection[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start; i < number.length; i++) {
			selection[cnt] = number[i];
			combination(number, selection, cnt+1, i+1);
		}
	}
}

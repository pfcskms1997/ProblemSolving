/*
 * BAEKJOON 15656. N과 M(7)
 * Hint: 중복을 허용하는 순열(Permutation with Repetition)
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15656_N과M7 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_15656.txt"));
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
			permutationRep(number, selection, 0);
			System.out.print(sb);
		}
		br.close();
	}
	
	static StringBuilder sb;
	
	public static void permutationRep(int[] number, int[] selection, int cnt) {
		if(cnt == selection.length) {
			for(int i = 0; i < selection.length; i++) {
				sb.append(selection[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 0; i < number.length; i++) {
			selection[cnt] = number[i];
			permutationRep(number, selection, cnt+1);
		}
	}
}

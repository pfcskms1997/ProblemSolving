/*
 * BAEKJOON 15651. N과 M(3)
 * Hint: 중복을 허용하는 순열(Permutation with Repetition)
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15651_N과M3 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_15651.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			sb = new StringBuilder();
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] number = new int[N];
			int[] selection = new int[M];
			
			for(int i = 0; i < N; i++) {
				number[i] = i + 1;
			}
			
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

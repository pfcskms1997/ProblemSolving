/*
 * BAEKJOON 15654. N과 M(5)
 * Hint: 중복되지 않은 순열
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15654_N과M5 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_15654.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			sb = new StringBuilder();
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] number = new int[N];
			int[] selection = new int[M];
			boolean[] isSelected = new boolean[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(number);
			
			System.out.println("#" + test_case);
			permutation(number, selection, isSelected, 0);
			System.out.print(sb);
		}
		br.close();
	}
	
	static StringBuilder sb;
	
	public static void permutation(int[] number, int[] selection, boolean[] isSelected, int cnt) {
		if(cnt == selection.length) {
			for(int i = 0; i < selection.length; i++) {
				sb.append(selection[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 0; i < number.length; i++) {
			if(isSelected[i] == true) continue;
			
			selection[cnt] = number[i];
			isSelected[i] = true;
			permutation(number, selection, isSelected, cnt+1);
			isSelected[i] = false;
		}
	}
}

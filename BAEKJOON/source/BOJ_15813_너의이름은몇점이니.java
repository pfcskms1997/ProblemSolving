/*
 * BAEKJOON 15813. 너의 이름은 몇 점이니?
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_15813_너의이름은몇점이니 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_15813.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 이름의 길이
			String name = br.readLine();
			int score = 0;
			
			for(int i = 0; i < N; i++) {
				score += name.charAt(i) - 'A' + 1;
			}
			
			bw.write(String.format("#%d %d\n", test_case, score));
			bw.flush();
		}
		br.close();
		bw.close();
	}
}

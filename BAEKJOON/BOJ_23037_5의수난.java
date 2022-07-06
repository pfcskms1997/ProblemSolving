/*
 * BAEKJOON 23037. 5의 수난
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_23037_5의수난 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_23037.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int ans = 0;

			String input = br.readLine();
			for(int i = 0; i < 5; i++) {
				int num = input.charAt(i) - '0';
				ans += Math.pow(num, 5);
			}
			
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		br.close();
		bw.close();
	}
}

/*
 * BAEKJOON 08958. OX퀴즈
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_08958_OX퀴즈 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_08958.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			char[] quiz = br.readLine().toCharArray();
			int score = 0;
			int cnt = 1;
			
			for(int i = 0; i < quiz.length; i++) {
				if(quiz[i] == 'O') {
					score += cnt;
					cnt++;
				}
				else cnt = 1;
			}
			sb.append(String.format("#%d %d\n", test_case, score));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}

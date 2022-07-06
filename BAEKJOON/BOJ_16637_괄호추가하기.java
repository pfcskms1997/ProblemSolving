/*
 * BAEKJOON 16637. 괄호 추가하기
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16637_괄호추가하기_X {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_16637.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // input의 길이
			int[] num = new int[(N/2)+1];
			char[] op = new char[N-num.length];
			int num_idx = 0;
			int op_idx = 0;
			
			char[] input = br.readLine().toCharArray();
			
			for(int i = 0; i < N; i++) {
				if(i % 2 == 0) {
					num[num_idx] = input[i] - '0';
					num_idx++;
				}
				else {
					op[op_idx] = input[i];
					op_idx++;
				}
			}
			
			powerSet(num, op, 0);
			
			bw.write(String.format("#%d %d\n", test_case, N));
			bw.flush();
		}
		
		// 결과 출력 및 프로그램 종료
		br.close();
		bw.close();
	}
	
	
	public static void powerSet(int[] num, char[] op, int cnt) {
		
	}
	
	public static int calc(int a, int b, char op) {
		int res = 0;
		
		switch(op) {
		case '+':
			res = a + b;
			break;
		case '-':
			res = a - b;
			break;
		case '*':
			res = a * b;
			break;
		}
		return res;
	}
}

/*
 * BAEKJOON 10817. 세 수
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10817_세수 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_10817.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int[] num = new int[3];

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < num.length; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(num);
			
			System.out.printf("#%d %d\n", test_case, num[1]);
		}
		br.close();
	}
}

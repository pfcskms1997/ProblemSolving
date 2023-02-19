/*
 * BAEKJOON 03052. 나머지
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BOJ_03052_나머지 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_03052.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			Set<Integer> set = new HashSet<Integer>();
			
			// set 자료구조를 이용하여 중복되는 나머지를 제거
			for(int i = 0; i < 10; i++) {
				int num = Integer.parseInt(br.readLine());
				set.add(num % 42);
			}
			
			// System.out.println(Arrays.toString(set.toArray()));
			
			System.out.printf("#%d %d\n", test_case, set.size());
		}
		br.close();
	}
}

/*
 * BAEKJOON 02941. 크로아티아 알파벳
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_02941_크로아티아알파벳 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_02941.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			String str = br.readLine();
			String[] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
			
			// 크로아티아 알파벳을 한 개의 문자로 대체
			for(String alphabet : croatia) {
				str = str.replace(alphabet, "A");
			}
			
			System.out.println(str.length());
		}
		br.close();
	}
}

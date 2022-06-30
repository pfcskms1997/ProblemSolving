/*
 * SWEA 01289. 원재의 메모리 복구하기
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SWEA_01289_원재의메모리복구하기 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("./input/difficulty03/input_01289.txt")));
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			String original = in.readLine();
			String temp = new String();
			int count = 0;
			int idx = 0;
			
			// 원본 메모리 값의 길이만큼 0으로 초기화
			for(int i = 0; i < original.length(); i++) {
				temp += '0';
			}
			
			// 두 개의 문자열 내용이 같을 때까지 반복 수행
			while(!temp.equals(original)) {
				if(original.charAt(idx) != temp.charAt(idx)) {
					temp = temp.substring(0, idx);
					
					if(original.charAt(idx) == '1') {
						for(int i = 0; i < original.length() - idx; i++) {
							temp += '1';
						}
					}
					else {
						for(int i = 0; i < original.length() - idx; i++) {
							temp += '0';
						}
					}
					count++;
				}
				idx++;
			}
			System.out.printf("#%d %d\n", test_case, count);
		}
		in.close();
	}
}

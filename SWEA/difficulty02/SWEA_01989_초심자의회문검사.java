/*
 * SWEA 01989. 초심자의 회문 검사
 */

package difficulty02;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01989_초심자의회문검사 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01989.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			char[] strArr = (sc.next()).toCharArray();
			short res = 0;
			
			for(int i = 0; i < strArr.length / 2; i++) {
				if(strArr[i] != strArr[strArr.length - 1 - i]) break;
				if(i == (strArr.length / 2) - 1) res = 1;
			}
			
			System.out.printf("#%d %d\n", test_case, res);
		}
		sc.close();
	}
}

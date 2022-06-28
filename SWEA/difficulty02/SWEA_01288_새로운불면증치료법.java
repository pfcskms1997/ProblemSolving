/*
 * SWEA 01288. 새로운 불면증 치료법
 */

package difficulty02;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01288_새로운불면증치료법 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01288.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int num = sc.nextInt(), digit = 0, xN = 0, cnt = 0;
			boolean flag = false;
			boolean[] digits = new boolean[10];
			
			while(!flag) {
				xN = num * (cnt + 1);
				while(xN != 0) {
					digit = xN % 10;
					if(digits[digit] == false) {
						digits[digit] = true;
					}
					xN /= 10;
				}
				cnt++;
				
				for(int i = 0; i < 10; i++) {
					if(digits[i] != true) break;
					if(i == 9) flag = true;
				}
			}
			System.out.printf("#%d %d\n", test_case, num * cnt);
		}
		sc.close();
	}
}

/*
 * SWEA 01948. 날짜 계산기
 */

package difficulty02;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01948_날짜계산기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01948.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] lastDate = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int[] inputDate = new int[4];
			int res = 0;
			
			for(int i = 0; i < inputDate.length; i++) {
				inputDate[i] = sc.nextInt();
			}
			
			if(inputDate[2] == inputDate[0]) res = inputDate[3] - inputDate[1] + 1;
			else {
				for(int i = inputDate[2] - 1; i >= inputDate[0]; i--) {
					res += lastDate[i - 1];
				}
				res = res + inputDate[3] - inputDate[1] + 1;
			}
			System.out.printf("#%d %d\n", test_case, res);
		}
		sc.close();
	}
}

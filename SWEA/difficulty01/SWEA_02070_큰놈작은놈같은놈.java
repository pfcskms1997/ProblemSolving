/*
 * SWEA 02070. 큰 놈, 작은 놈, 같은 놈
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02070_큰놈작은놈같은놈 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02070.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		char sign = ' ';
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			
			switch(num1 - num2) {
			case 0:
				sign = '=';
				break;
			default:
				sign = (num1 - num2) > 0 ? '>' : '<';
				break;
			}
			System.out.printf("#%d %c\n", test_case, sign);
		}
		sc.close();
	}
}

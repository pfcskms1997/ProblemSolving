/*
 * SWEA 02029. 몫과 나머지 출력하기
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02029_몫과나머지출력하기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02029.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			System.out.printf("#%d %d %d\n", test_case, num1/num2, num1%num2);
		}
		sc.close();
	}
}

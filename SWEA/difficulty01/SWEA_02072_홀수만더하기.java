/*
 * SWEA 02072. 홀수만 더하기
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02072_홀수만더하기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02072.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int sum = 0;
			
			for(int i = 0; i < 10; i++) {
				int num = sc.nextInt();
				if(num % 2 == 1) sum += num;
			}
			
			System.out.printf("#%d %d\n", test_case, sum);
		}
		sc.close();
	}
}

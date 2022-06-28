/*
 * SWEA 01970. 쉬운 거스름돈
 */

package difficulty02;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01970_쉬운거스름돈 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01970.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] won = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int money = sc.nextInt();
			int res = 0;
			
			System.out.println("#" + test_case);
			
			for(int i = 0; i < won.length; i++) {
				res = money / won[i];
				System.out.print(res + " ");
				money -= won[i] * res;
			}
			System.out.println();
		}
		sc.close();
	}
}

/*
 * SWEA 01986. 지그재그 숫자
 */

package difficulty02;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01986_지그재그숫자 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01986.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int num = sc.nextInt();
			int res = 0;
			
			for(int i = 1; i <= num; i++) {
				res += (i % 2 == 0) ? -i : i;
			}
			System.out.printf("#%d %d\n", test_case, res);
		}
		sc.close();
	}
}

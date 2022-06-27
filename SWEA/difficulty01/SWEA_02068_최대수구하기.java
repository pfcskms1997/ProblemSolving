/*
 * SWEA 02068. 최대수 구하기
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02068_최대수구하기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02068.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int max = 0;
			
			for(int i = 0; i < 10; i++) {
				int num = sc.nextInt();
				if(max < num) max = num;
			}
			System.out.printf("#%d %d\n", test_case, max);
		}
		sc.close();
	}
}

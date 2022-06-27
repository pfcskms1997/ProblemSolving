/*
 * SWEA 02071. 평균값 구하기
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02071_평균값구하기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02071.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int sum = 0;
			double avg = 0.0;
			
			for(int i = 0; i < 10; i++) {
				sum += sc.nextInt();
			}
			avg = (double) sum / 10;
			
			System.out.printf("#%d %.0f\n", test_case, avg);
		}
		sc.close();
	}
}

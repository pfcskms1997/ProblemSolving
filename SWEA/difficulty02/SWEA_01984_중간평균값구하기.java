/*
 * SWEA 01984. 중간 평균값 구하기
 */

package difficulty02;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class SWEA_01984_중간평균값구하기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01984.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int[] num = new int[10];
			int midSum = 0;
			double midAvg = 0.0;
			
			for(int i = 0; i < 10; i++) {
				num[i] = sc.nextInt();
			}
			Arrays.sort(num);
			
			for(int i = 1; i < 9; i++) {
				midSum += num[i];
			}
			
			midAvg = (double)midSum / 8;
			
			System.out.printf("#%d %.0f\n", test_case, midAvg);
		}
		sc.close();
	}
}

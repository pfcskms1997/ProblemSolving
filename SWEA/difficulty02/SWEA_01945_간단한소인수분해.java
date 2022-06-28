/*
 * SWEA 01945. 간단한 소인수분해
 */

package difficulty02;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class SWEA_01945_간단한소인수분해 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01945.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] base = {2, 3, 5, 7, 11};
		int[] exp = new int[5];
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int num = sc.nextInt();
			Arrays.fill(exp, 0);
			
			for(int i = 0; i < base.length; i++) {
				while(num != 1) {
					if(num %  base[i] == 0) {
						exp[i]++;
						num /= base[i];
					} else {
						break;
					}
				}
			}
			
			System.out.printf("#%d ", test_case);
			for(int i = 0; i < base.length; i++) {
				System.out.print(exp[i] + " ");
			}
			System.out.println();
		}
		sc.close();
	}
}

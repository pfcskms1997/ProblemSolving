/*
 * SWEA 01940. 가랏! RC카!
 */

package difficulty02;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01940_가랏RC카 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01940.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int opt = 0;
			int acc = 0;
			int d = 0;
			int L = sc.nextInt();
			
			for(int i = 0; i < L; i++) {
				opt = sc.nextInt();
				
				if(opt == 0) {
					d += acc;
				}
				else if(opt == 1) {
					acc += sc.nextInt();
					d += acc;
				}
				else {
					acc -= sc.nextInt();
					if(acc < 0) acc = 0;
					d += acc;
				}
			}
			System.out.printf("#%d %d\n", test_case, d);
		}
		sc.close();
	}
}

/*
 * SWEA 01976. 시각 덧셈
 */

package difficulty02;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01976_시각덧셈 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01976.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int hsum = 0;
			int msum = 0;
			int mcarry = 0;
			
			for(int i = 0; i < 4; i++) {
				if(i % 2 == 0) hsum += sc.nextInt();
				else msum += sc.nextInt();
			}
			
			if(msum / 60 > 0) {
				mcarry = msum / 60;
				msum %= 60;
				hsum += mcarry;
			}
			
			if(hsum / 12 > 0) {
				hsum %= 12;
				if(hsum == 0) hsum = 12;
			}
			
			System.out.printf("#%d %d %d\n", test_case, hsum, msum);
		}
		sc.close();
	}
}

/*
 * SWEA 01284. 수도 요금 경쟁
 */

package difficulty02;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01284_수도요금경쟁 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01284.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int P = sc.nextInt();
			int Q = sc.nextInt();
			int R = sc.nextInt();
			int S = sc.nextInt();
			int W = sc.nextInt();
			
			// 두 회사의 수도 요금 계산
			int comA = P * W;
			int comB = Q + (W <= R ? 0 : (W - R) * S);
			
			if(comA < comB) System.out.printf("#%d %d\n", test_case, comA);
			else System.out.printf("#%d %d\n", test_case, comB);
		}
		sc.close();
	}
}

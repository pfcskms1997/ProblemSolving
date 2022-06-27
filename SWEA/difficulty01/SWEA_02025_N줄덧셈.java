/*
 * SWEA 02025. N줄 덧셈
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02025_N줄덧셈 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02025.txt"));
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		int sum = 0;
		
		// 1부터 입력 받은 수까지의 합을 계산
		for (int i = 1; i <= input; i++) {
			sum += i;
		}
		System.out.println(sum);
		sc.close();
	}
}

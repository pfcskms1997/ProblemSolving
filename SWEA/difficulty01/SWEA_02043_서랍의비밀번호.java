/*
 * SWEA 02043. 서랍의 비밀번호
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02043_서랍의비밀번호 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02043.txt"));
		Scanner sc = new Scanner(System.in);
		int P = sc.nextInt();
		int K = sc.nextInt();
		
		System.out.println(Math.abs(P - K) + 1);
		sc.close();
	}
}

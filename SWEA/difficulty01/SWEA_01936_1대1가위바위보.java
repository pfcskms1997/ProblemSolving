/*
 * SWEA 01936. 1대 1 가위바위보
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01936_1대1가위바위보 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_01936.txt"));
		Scanner sc = new Scanner(System.in);
		char winner = ' ';
		int A = sc.nextInt();
		int B = sc.nextInt();
		
		// 가위: 1, 바위: 2, 보: 3
		// 단, 비기는 경우는 없음
		if(A - B == 1 || A - B == -2) {
			winner = 'A';
		} else {
			winner = 'B';
		}
		System.out.println(winner);
		sc.close();
	}
}

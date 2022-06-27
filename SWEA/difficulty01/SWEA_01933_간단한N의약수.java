/*
 * SWEA 01933. 간단한 N의 약수
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01933_간단한N의약수 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_01933.txt"));
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		
		for (int i = 1; i <= input; i++) {
			if(input % i == 0) {
				System.out.print(i + " ");
				continue;
			}
		}
		sc.close();
	}
}

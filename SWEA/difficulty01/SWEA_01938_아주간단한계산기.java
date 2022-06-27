/*
 * SWEA 01938. 아주 간단한 계산기
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01938_아주간단한계산기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_01938.txt"));
		Scanner sc = new Scanner(System.in);
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		
		System.out.println(num1 + num2);
		System.out.println(num1 - num2);
		System.out.println(num1 * num2);
		System.out.println(num1 / num2);
		
		sc.close();
	}
}


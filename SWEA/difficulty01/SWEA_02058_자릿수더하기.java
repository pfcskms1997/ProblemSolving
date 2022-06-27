/*
 * SWEA 02058. 자릿수 더하기
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02058_자릿수더하기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02058.txt"));
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int digitSum = 0;
		
		while(num != 0) {
			digitSum += num % 10;
			num /= 10;
		}
		System.out.println(digitSum);
		sc.close();
	}
}

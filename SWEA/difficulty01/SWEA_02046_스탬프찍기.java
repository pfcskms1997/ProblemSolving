/*
 * SWEA 02046. 스탬프 찍기
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02046_스탬프찍기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02046.txt"));
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		
		for (int i = 0; i < input; i++) {
			System.out.print("#");
		}
		sc.close();
	}
}

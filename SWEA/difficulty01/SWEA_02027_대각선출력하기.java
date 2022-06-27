/*
 * SWEA 02027. 대각선 출력하기
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02027_대각선출력하기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02027.txt"));
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(i == j) {
					System.out.print("#");
				}
				System.out.print("+");
			}
			System.out.println();
		}
		sc.close();
	}
}

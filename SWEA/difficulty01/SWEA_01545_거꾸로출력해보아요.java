/*
 * SWEA 01545. 거꾸로 출력해 보아요
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01545_거꾸로출력해보아요 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_01545.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		while(T >= 0) {
			System.out.print(T + " ");
			T--;
		}
		sc.close();
	}
}

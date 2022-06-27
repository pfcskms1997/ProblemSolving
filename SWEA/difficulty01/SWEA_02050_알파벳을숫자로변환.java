/*
 * SWEA 02050. 알파벳을 숫자로 변환
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02050_알파벳을숫자로변환 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02050.txt"));
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		
		for(int i = 0; i < str.length(); i++) {
			System.out.print((int)str.charAt(i) - 64 + " ");
		}
		sc.close();
	}
}

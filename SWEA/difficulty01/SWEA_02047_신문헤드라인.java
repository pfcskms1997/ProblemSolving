/*
 * SWEA 02047. 신문 헤드라인
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02047_신문헤드라인 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02047.txt"));
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String output = new String();
		
		for(int i = 0; i < str.length(); i++) {
			if(97 <= (int)str.charAt(i) && (int)str.charAt(i) <= 122) {
				output += (char)(str.charAt(i) - 32);
			} else {
				output += (char)str.charAt(i);
			}
		}
		System.out.println(output);
		sc.close();
	}
}

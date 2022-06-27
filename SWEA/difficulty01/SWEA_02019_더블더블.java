/*
 * SWEA 02019. 더블더블
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02019_더블더블 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02019.txt"));
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
        int res = 1;
        
        for(int i = 0; i <= input; i++){
        	System.out.print(res + " ");
            res = res * 2;
        }
		sc.close();
	}
}

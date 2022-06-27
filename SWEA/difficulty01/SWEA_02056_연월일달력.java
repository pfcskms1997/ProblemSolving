/*
 * SWEA 02056. 연월일 달력
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_02056_연월일달력 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02056.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			String str = sc.next();
			String year = str.substring(0, 4);
			String month = str.substring(4, 6);
			String day = str.substring(6, 8);
			boolean flag = false;
			
			if(1 <= Integer.parseInt(month) && Integer.parseInt(month) <= 12) {
				switch(Integer.parseInt(month)) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					if(1 <= Integer.parseInt(day) && Integer.parseInt(day) <= 31) flag = true;
					break;

				case 4:
				case 6:
				case 9:
				case 11:
					if(1 <= Integer.parseInt(day) && Integer.parseInt(day) <= 30) flag = true;
					break;
				
				case 2:
					if(1 <= Integer.parseInt(day) && Integer.parseInt(day) <= 28) flag = true;
					break;
					
				default:
					break;
				}
			}
			
			if(flag == true) System.out.printf("#%d %s%s%s\n", test_case, year, month, day);
			else System.out.printf("#%d %d\n", test_case, -1);
		}
		sc.close();
	}
}

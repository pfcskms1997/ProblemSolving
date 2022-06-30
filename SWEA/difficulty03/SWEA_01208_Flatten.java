/*
 * SWEA 01208. Flatten
 */

package difficulty03;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01208_Flatten {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty03/input_01208.txt"));
		Scanner sc = new Scanner(System.in);
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int dumpTime = sc.nextInt();
			int[] boxes = new int[100];
			int highest = 1, lowest = 100;
			int highestIdx = 0, lowestIdx = 0;
			int res = 0;
			
			for(int i = 0; i < boxes.length; i++) {
				boxes[i] = sc.nextInt();
				if(highest < boxes[i]) {
					highest = boxes[i];
					highestIdx = i;
				}
			}
			
			for(int i = 0; i < dumpTime; i++) {
				--boxes[highestIdx];
				++boxes[lowestIdx];
				
				for(int j = 0; j < boxes.length; j++) {
					if(boxes[highestIdx] < boxes[j]) {
						highest = boxes[j];
						highestIdx = j;
					}
					else highest = boxes[highestIdx];
					
					if(boxes[lowestIdx] > boxes[j]) {
						lowest = boxes[j];
						lowestIdx = j;
					}
					else lowest = boxes[lowestIdx];
				}
				
				res = highest - lowest;
				
				if(res == 0 || res == 1) break;
			}
			
			System.out.printf("#%d %d\n", test_case, res);
		}
		sc.close();
	}
}

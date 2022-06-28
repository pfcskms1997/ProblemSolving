/*
 * SWEA 01983. 조교의 성적 매기기
 */

package difficulty02;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class SWEA_01983_조교의성적매기기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01983.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		String[] grades = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int students = sc.nextInt();
			int idx = sc.nextInt();
			int key = 0;
			double value = 0.0;
			int[] individual = new int[3];
			double[] scores = new double[students];
			
			for(int i = 0; i < students; i++) {
				for(int j = 0; j < individual.length; j++) {
					individual[j] = sc.nextInt();
				}
				scores[i] = (double)individual[0] * 0.35 + (double)individual[1] * 0.45 + (double)individual[2] * 0.2;
				if(i == (idx - 1)) value = scores[i];
			}
			Arrays.sort(scores);
			
			for(int i = 0; i < students; i++) {
				if(scores[i] == value) {
					key = students - i - 1;
					break;
				}
			}
			System.out.printf("#%d %s\n", test_case, grades[key / (students / 10)]);
		}
		sc.close();
	}
}

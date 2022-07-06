/*
 * JUNGOL 01828. 냉장고
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class JUNGOL_1828_냉장고_X {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01828.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] chemicals = new int[N][];
		
		// N개의 화학물질에 대한 최저 보관 온도(x)와 최고 보관 온도(y)를 2차원 배열에 저장
		for(int i = 0; i < chemicals.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()); // 최저 보관 온도
			int y = Integer.parseInt(st.nextToken()); // 최고 보관 온도
			
			chemicals[i] = new int[] {x, y};
		}
		
		// 최고 온도를 기준으로 하여 내림차순 정렬
		Comparator<int[]> com = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return (o1[1] != o2[1]) ? o2[1] - o1[1] : o1[0] - o2[0];
			}
		};
		
		Arrays.sort(chemicals, com);
		
		for(int i = 0; i < chemicals.length; i++) {
			System.out.print("#" + (i+1) + " ");
			for(int j = 0; j < chemicals[i].length; j++) {
				System.out.print(chemicals[i][j] + " ");
			}
			System.out.println();
		}
		
		///////////////////////////////////////////////////////////////////////////////////////
		int refrigerator = 1;
		int idx = 0;
		
		for(int i = 1; i < chemicals.length; i++) {
			if(chemicals[idx][0] <= chemicals[i][1]) continue;
			
			refrigerator++;
			idx = i;
		}
		
		System.out.println(refrigerator);
		br.close();
	}
}

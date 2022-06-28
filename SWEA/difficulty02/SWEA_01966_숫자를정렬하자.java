/*
 * SWEA 01966. 숫자를 정렬하자
 */

package difficulty02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_01966_숫자를정렬하자 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01966.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			
			// 배열 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("#%d", test_case));
			
			for(int i = 0; i < arr.length; i++) {
				sb.append(" " + arr[i]);
			}
			System.out.println(sb);
		}
		br.close();
	}
}

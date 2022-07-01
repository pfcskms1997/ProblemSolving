/*
 * SWEA 01247. 최적 경로
 */

package difficulty05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_01247_최적경로 {

	static int com_x;
	static int com_y;
	static int home_x;
	static int home_y;
	static int shortCut;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty05/input_01247.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 고객의 수
			int[][] clients = new int[N][2]; // 각 고객의 위치(x, y)를 저장하기 위한 배열
			int[] selection = new int[N];
			boolean[] isSelected = new boolean[N];
			shortCut = 1000;
			
			// 좌표(x, y)값 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			com_x = Integer.parseInt(st.nextToken());
			com_y = Integer.parseInt(st.nextToken());
			home_x = Integer.parseInt(st.nextToken());
			home_y = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < 2; j++) {
					clients[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			permutation(clients, selection, isSelected, 0);
			
			System.out.printf("#%d %d\n", test_case, shortCut);
		}
		br.close();
	}
	
	public static void permutation(int[][] clients, int[] selection, boolean[] isSelected, int cnt) {
		if(cnt == selection.length) {
			// System.out.println(Arrays.toString(selection));
			
			int sum = 0;
			
			// 회사부터 첫 번째 고객의 집까지의 거리를 계산해서 sum에 넣음
			sum = Math.abs(com_x - clients[selection[0]][0])
					+ Math.abs(com_y - clients[selection[0]][1]);
			for(int i = 0; i < selection.length - 1; i++) {
				sum += Math.abs(clients[selection[i]][0] - clients[selection[i + 1]][0])
						+ Math.abs(clients[selection[i]][1] - clients[selection[i + 1]][1]);
			}
			
			// 마지막 방문한 고객의 집에서 나의 집까지 거리를 계산하여 sum에 더함
			sum += Math.abs(clients[selection[selection.length - 1]][0] - home_x)
					+ Math.abs(clients[selection[selection.length - 1]][1] - home_y);
			
			// 최단경로
			shortCut = Math.min(shortCut, sum);
			return;
		}
		
		for(int i = 0; i < clients.length; i++) {
			if(isSelected[i] == true) continue;
			
			selection[cnt] = i;
			isSelected[i] = true;
			permutation(clients, selection, isSelected, cnt+1);
			isSelected[i] = false;
		}
	}
}

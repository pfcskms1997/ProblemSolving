/*
 * SWEA 01859. 백만 장자 프로젝트 
 */

package difficulty02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_01859_백만장자프로젝트 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01859.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(in.readLine());
			int[] price = new int[N];
			long maxProfit = 0;
			long tempSum = 0;
			int cnt = 0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			
			// 1차원 배열에 매매가 입력
			for (int i = 0; i < price.length; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			// 배열이 커서 StackOverflowError 발생
			// millionaireProject(price, price.length-1, 0);
			
			
			for(int i = price.length - 1; i >= 0; i--) {
				if(i == 0 || price[i-1] >= price[i + cnt]) {
					for(int j = i; j < i + cnt; j++) {
						tempSum += price[j];
					}
					maxProfit += (price[i + cnt] * cnt) - tempSum;
					
					// 중간 단계의 이익이 계산되었으므로 다음 구간을 계산하기 위한 초기화
					tempSum = 0;
					cnt = 0;
				}
				else {
					cnt++;
				}
			}
			System.out.printf("#%d %d\n", test_case, maxProfit);
		}
		in.close();
	}

//	public static long maxProfit = 0;
//	public static int millionaireProject(int[] price, int idx, int cnt) {
//		if(idx == 0) {
//			return cnt;
//		}
//		
//		if(price[idx-1] >= price[idx + cnt]) {
//			millionaireProject(price, idx-1, 0);
//			return cnt;
//		}
//		else {
//			long tempSum = 0;
//			int count = millionaireProject(price, idx-1, cnt+1);
//			//System.out.println(count);
//			if(cnt != 0) return count;
//			//System.out.println("count: " + count + ", idx: " + idx);
//			
//			for(int i = idx - count; i < idx; i++) {
//				tempSum += price[i];
//			}
//			maxProfit += (price[idx] * count) - tempSum;
//			//System.out.println(maxProfit);
//		}
//		
//		return 0;
//	}
}

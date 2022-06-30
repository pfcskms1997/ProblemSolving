/*
 * SWEA 05604. 구간 합
 */

package difficulty04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_05604_구간합 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty04/input_05604.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			long A = Long.parseLong(st.nextToken());
			long B = Long.parseLong(st.nextToken());
			long[] digit = new long[10];
			long point = 1;
			
			while(A <= B) {
				// 숫자 B의 1의 자리 숫자가 0~8 사이인 경우에 대한 처리
				while(B % 10 != 9 && A <= B) {
					calc(B, digit, point);
					B--;
				}
				
				if(B < A) break;
				
				// 숫자 A의 1의 자리 숫자가 1~9 사이인 경우에 대한 처리
				while(A % 10 != 0 && A <= B) {
					calc(A, digit, point);
					A++;
				}
				
				A /= 10;
				B /= 10;
				
				// 온전히 일의 자리 숫자가 0~9로 이루어진 구간에 대한 계산
				for(int i = 0; i < digit.length; i++) {
					digit[i] += (B - A + 1) * point;
				}
				point *= 10;
			}
			
			// 결과 계산
			long ans = 0;
			for(int i = 0; i < digit.length; i++) {
				ans += digit[i] * i;
			}
			
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}

		br.close();
		bw.close();
	}
	
	// 입력 받은 숫자의 각 자릿수를  digit 배열의 해당하는 위치에 point만큼 누적
	public static void calc(long n, long[] digit, long point) {
		while(n > 0) {
			int idx = (int) (n % 10);
			digit[idx] += point;
			n /= 10;
		}
	}
}

/*
 * BAEKJOON 01152. 단어의 개수
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_01152_단어의개수 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/input_01152.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int ans = st.countTokens();

            System.out.printf("#%d %d\n", test_case, ans);
        }
        br.close();
    }
}

/*
 * BAEKJOON 17219. 비밀번호 찾기
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_17219_비밀번호찾기 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/input_17219.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 저장된 주소의 수
        int M = Integer.parseInt(st.nextToken()); // 비밀번호를 찾으려는 주소의 수
        Map<String, String> account = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String url = st.nextToken();
            String password = st.nextToken();

            account.put(url, password);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < M; i++) {
            result.append(account.get(br.readLine())).append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}

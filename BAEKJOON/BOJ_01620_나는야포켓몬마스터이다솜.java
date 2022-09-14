import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_01620_나는야포켓몬마스터이다솜 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/input_01620.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<Integer, String> dict1 = new HashMap<>();
        Map<String, Integer> dict2 = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            dict1.put(i, name);
            dict2.put(name, i);
        }

        for (int i = 0; i < M; i++) {
            Object question = br.readLine();

            try {
                int idx = Integer.parseInt(question.toString());
                sb.append(dict1.get(idx)).append("\n");
            } catch(NumberFormatException e) {
                sb.append(dict2.get(question.toString())).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }
}

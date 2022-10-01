/*
 * BAEKJOON 11724. 연결 요소의 개수
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11724_연결요소의개수 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/input_11724.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T ; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken()); // 정점의 개수
            int M = Integer.parseInt(st.nextToken()); // 간선의 개수
            int ans = 0;

            // make graph
            List<Integer>[] graph = new List[N+1];
            for (int i = 1; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(st.nextToken()); // 양 끝점 1
                int v = Integer.parseInt(st.nextToken()); // 양 끝점 2

                graph[u].add(v);
                graph[v].add(u);
            }

            // problem solve
            boolean[] visited = new boolean[N+1];
            for (int i = 1; i < graph.length; i++) {
                if(visited[i]) continue;
                DFS(graph, i, visited);
                ans++;
            }

            bw.write(String.format("#%d %d\n", test_case, ans));
            bw.flush();
        }
        br.close();
    }

    private static void DFS(List<Integer>[] graph, int v, boolean[] visited) {
        if(visited[v]) return;

        visited[v] = true;
        for (int i = 0; i < graph[v].size(); i++) {
            DFS(graph, graph[v].get(i), visited);
        }
    }

    private static void printGraph(List<Integer>[] graph) {
        for (int i = 1; i < graph.length; i++) {
            System.out.println(graph[i]);
        }
    }
}

package assignment.fiveweeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 정점의 범위가 1부터 시작하므로 리스트 또는 배열 초기화 시에 +1을 붙여준다.
public class DFS_BFS {

    static Queue<Integer> Q; // DFS

    static ArrayList<Integer>[] bfs; // bfs
    static boolean[] visits;  // 방문여부를 확인하는 불린 배열 변수
    static ArrayList<Integer>[] dfs; //DFS


    static int n, m, v; // 정점의 갯수 n, 간선의 갯수 m, 정점의 번호 : v

    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in); // 1. 스캐너로 변수값 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 기존보다 메모리절반, 시간복잡도 절반 줄어듬.
        StringTokenizer st= new StringTokenizer(br.readLine()); // String -> Integer

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
//        n = sc.nextInt(); // 정점의 갯수
//        m = sc.nextInt(); // 간선의 갯수
//        v = sc.nextInt(); // 정점의 번호

        // 방문여부를 확인하는 배열을 정점의 수만큼 생성한다.
        visits = new boolean[n + 1];

        // 정점의 수 만큼 배열크기에 맞게 그래프를 생성한다. 정점의 시작점이 1부터 시작하므로 인덱스는 1부터 시작한다 ( +1 )
        bfs = new ArrayList[n + 1];
        dfs = new ArrayList[n + 1];

        // 정점의 수 만큼 배열 생성한다.
        for (int i = 1; i <= n; i++) {
            bfs[i] = new ArrayList<>();
            dfs[i] = new ArrayList<>();
        }

        // 간선의 수 만큼 입력값을 받는다.
        for (int i = 0; i < m; i++) { // 간선의 갯수 m
            st = new StringTokenizer(br.readLine());
//            int node_1 = sc.nextInt();
//            int node_2 = sc.nextInt();
            int node_1 = Integer.parseInt(st.nextToken());
            int node_2 = Integer.parseInt(st.nextToken());

            // 입력한 정점을 잇는다.
            bfs[node_1].add(node_2);
            bfs[node_2].add(node_1);

            dfs[node_1].add(node_2);
            dfs[node_2].add(node_1);
        }

        // 정점의 수 만큼 정렬해준다.
        for (int j = 1; j <= n; j++) {
            Collections.sort(bfs[j]);
            Collections.sort(dfs[j]);
        }

        dfs(v); // 깊이탐색알고리즘
        visits = new boolean[n + 1]; // 방문 배열 초기화
        System.out.println();
        bfs(); // 너비탐색알고리즘
    }

    // 재귀함수
    private static void dfs(int start) {
        visits[start] = true; // 첫번째 정점 방문.
        System.out.printf(start + " ");
        for (int i : dfs[start]) { //해당 점점에 인접한 정점 추출
            if (!visits[i]) { // 정점이 방문하지 않은 점점이라면
                dfs(i); //
            }
        }
    }


    private static void bfs() {
        Q = new LinkedList<>(); // 탐색 배열

        Q.add(v); // 정점을 넣어준다.
        visits[v] = true; //해당 정점에는 방문했다 라는 true 배열
        System.out.printf(v + " ");

        //기존 방식보도 16m/s 느리다.
        while (!Q.isEmpty()) { // Q가 모든 노드를 순회할 때 까지
//            int q = Q.poll(); // 첫번째 값은 방문했으니 빼낸다.
//            Iterator<Integer> k = bfs[q].listIterator(); //이터레이터로 돌려서 사용가능
//            while (k.hasNext()) {
//                int n = k.next();
////            for (int i : bfs[q]) { // 첫번째 값이 위치한 영역에 즉 해당 인덱스안에 있는 정점들을 끄집어 낸다.
//                if (!visits[n]) { // 주변 정점중 방문하지 않은 정점이 있다면
//                    Q.add(n); // 정점을 q에 저장한 후
//                    visits[n] = true; // 방문확인
//                    System.out.printf(n + " ");
//                }
//            }
////        }
//        }
            int q = Q.poll(); // 첫번째 값은 방문했으니 빼낸다.
            for (int i : bfs[q]) { // 첫번째 값이 위치한 영역에 즉 해당 인덱스안에 있는 정점들을 끄집어 낸다.
                if (!visits[i]) { // 주변 정점중 방문하지 않은 정점이 있다면
                    Q.add(i); // 정점을 q에 저장한 후
                    visits[i] = true; // 방문확인
                    System.out.printf(i + " ");
                }
            }
        }
    }
}

package assignment.fiveweeks;

import java.util.*;

// 정점의 범위가 1부터 시작하므로 리스트 또는 배열 초기화 시에 +1을 붙여준다.
public class DFS_BFS {
    static Queue<Integer> Q;
    static ArrayList<Integer>[] bfs; // bfs
    static ArrayList<Integer> dfs; //DFS
    static ArrayList<Integer> bfs_result; //bfs 결과
    static int[] dfs_result; //dfs 결과
    static boolean[] visits_bfs;  // 방문했는치 체크하는 불린 배열 변수

    static int n, m, v; // 정점의 갯수 n, 간선의 갯수 m, 정점의 번호 : v

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in); // 1. 스캐너로 변수값 받기

        n = sc.nextInt(); // 정점의 갯수
        m = sc.nextInt(); // 간선의 갯수
        v = sc.nextInt(); // 정점의 번호

        // 방문여부를 확인하는 배열을 정점의 수만큼 생성한다.
        visits_bfs = new boolean[n+1];
        // 정점의 수 만큼 배열크기에 맞게 그래프를 생성한다. 정점의 시작점이 1부터 시작하므로 인덱스는 1부터 시작한다 ( +1 )
        bfs = new ArrayList[n+1]; // 리스트 5개 생성
        // 정점의 수 만큼 배열 생성한다.
        for (int i = 1; i <= n; i++) {
            bfs[i] = new ArrayList<>();
        }

        // 간선의 수 만큼 입력값을 받는다.
        for (int i = 0; i < m; i++) { // 간선의 갯수 m
            int node_1 = sc.nextInt();
            int node_2 = sc.nextInt();

        // 입력한 정점을 잇는다.
            bfs[node_1].add(node_2);
            bfs[node_2].add(node_1);
        }

//        // 정점의 수 만큼 정렬해준다.
            for (int j = 1; j <=n; j++) {
                Collections.sort(bfs[j]);
            }

            bfs();

        for (int i : bfs_result) {
            System.out.print(i+" ");
        }

    }

    private static void bfs() {
        bfs_result = new ArrayList<>(n);
        Q = new LinkedList<>(); // 탐색 배열

        Q.add(v); // 정점을 넣어준다.
        visits_bfs[v] = true; //해당 정점에는 방문했다 라는 true 배열
        bfs_result.add(v); //정점을 첫번째에 넣어준다.
        while(!Q.isEmpty()){ // Q가 모든 노드를 순회할 때 까지
            int q = Q.poll(); // 첫번째 값은 방문했으니 빼낸다.
            for (int i : bfs[q]) { // 첫번째 값이 위치한 영역에 즉 해당 인덱스안에 있는 정점들을 끄집어 낸다.
                if(!visits_bfs[i]) { // 주변 정점중 방문하지 않은 정점이 있다면
                    Q.add(i); // 정점을 q에 저장한 후
                    visits_bfs[i] = true; // 방문확인
                    bfs_result.add(i); // 정답을 출력할 결과 리스트에 넣어준다.
                }
            }
        }
    }
}

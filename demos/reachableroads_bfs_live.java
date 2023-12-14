// Reachable Roads
// Adjacency List (unweighted) demo, DFS and now BFS demo, counting CC demo
// there are two subtle bugs here

import java.util.*;

class reachableroads2 {
  private static ArrayList<Boolean> visited;
  private static ArrayList<ArrayList<Integer>> AL;

  // private static void dfs(int u) {
  //   visited.set(u, true);
  //   for (Integer v : AL.get(u)) // for each neighbor v of vertex u
  //     if (!visited.get(v)) // if my neighbor v is not yet visited
  //       dfs(v); // go there
  // }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int TC = sc.nextInt(); // change 'n' to 'TC'
    while (TC-- > 0) {
      int n = sc.nextInt(); // change 'm' to 'n'
      int m = sc.nextInt(); // change 'r' to 'm'

      AL = new ArrayList<>();
      for (int i = 0; i < n; ++i)
        AL.add(new ArrayList<Integer>());
      while (m-- > 0) {
        int u = sc.nextInt(), v = sc.nextInt();
        AL.get(u).add(v);
        AL.get(v).add(v); // bidirectional
      }

      // the graph is read into an Adjacency List
      int CC = 0;
      visited = new ArrayList<>();
      for (int i = 0; i < n; ++i)
        visited.add(false);
      for (int i = 0; i < n; ++i)
        if (!visited.get(i)) {
          ++CC;
          // dfs(i);
          // bfs(i);
          Queue<Integer> Q = new LinkedList<Integer>();
          Q.offer(i);

          while (!Q.isEmpty()) {
            int u = Q.poll();
            for (Integer v : AL.get(u)) { // for each neighbor v of vertex u
              if (!visited.get(v)) { // if my neighbor v is not yet visited
                Q.offer(v); // enqueue v into my Queue
                visited.set(v, true); // mark v as visited (although it will only be visited later when it becomes front of queue)
              }
            }
          }
        }

      System.out.println(CC+1);
    }
  }
}

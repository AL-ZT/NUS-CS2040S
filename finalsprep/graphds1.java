package finalsprep;

import java.io.*;
import java.util.*;

public class graphds1 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());
    int vertices = Integer.parseInt(st.nextToken());
    int edges = Integer.parseInt(st.nextToken());
    boolean undirected = Integer.parseInt(st.nextToken()) == 1;
    int tree = 0, complete = 0, bipartite = 0, dag = 0;
    boolean checkedTree = false, checkedBipartite = false, checkedDag = false;

    // Complete
    if (undirected) {
      checkedDag = true;
      if (edges == vertices * (vertices - 1) / 2) {
        complete = 1;
        if (vertices <= 2) {
          tree = 1;
          bipartite = 1;
        }
      }
    } else {
      if (edges == vertices * (vertices - 1)) {
        complete = 1;
        checkedDag = true;
      }
    }

    // If complete, skip all other checks.
    if (complete == 1) {
      checkedTree = true;
      checkedBipartite = true;
    }

    if (edges > vertices - 1) {
      // If edges > n - 1 then only need check bipartite.
      checkedDag = true;
      checkedTree = true;
    } else if (edges < vertices - 1) {
      // Only check for tree if edges == n - 1.
      checkedTree = true;
    }

    // Perform BFS to check for DAG, Bipartite and Tree at the same time.
    // First Order the edges based on in-degree
    // Undirected can just be treated as directed... (Should not matter).
    tree = checkedTree ? 0 : 1;
    bipartite = checkedBipartite ? 0 : 1;
    dag = checkedDag ? 0 : 1;

    pw.println(tree + " " + complete + " " + bipartite + " " + dag);
    pw.close();
    br.close();
  }
}

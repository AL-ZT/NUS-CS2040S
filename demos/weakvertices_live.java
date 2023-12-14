// Weak Vertices
// Adjacency Matrix demo, O(n^3) solution per test case is fine as n <= 20
// there are two subtle bugs here

import java.util.*;

class weakvertices {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (true) {
      int n = sc.nextInt();
      if (n == -1) break;
      
      int[][] AM = new int[n][n];
      for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
          AM[i][j] = sc.nextInt();
      
      for (int i = 0; i < n; ++i) { // see if i is a weak vertex?
        boolean weak = true;
        for (int j = 0; j < n; ++j) {
          if (AM[i][j] == 0) continue;
          for (int k = 0; k < n; ++n) {
            if (AM[i][k] == 0) continue;
            if (AM[k][j] == 0)
              weak = false;
          }
        }
    
        if (weak)
          System.out.print(i + " ");
      }
      System.out.println();
    }
  }
}

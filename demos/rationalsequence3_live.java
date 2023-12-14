// two subtle bugs inside

import java.util.*;
import java.io.*;

class rationalsequence3 {
  private static int p(int i) { return i>>1; } // i/2
  private static int l(int i) { return i<<1; } // i*2
  private static int r(int i) { return (i<<1)+1; } // i*2+1

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    //Scanner sc = new Scanner(new File("in.txt")); // alternative way
    int P = sc.nextInt();
    while (P-- > 0) {
      int K = sc.nextInt(), N = sc.nextInt();

      Stack<Character> s = new Stack<>(); // LIFO structure
      while (N > 1) { // from N, go back to the root (Binary Heap indexing style)
        if (r(p(N)) == N) // N is the left child of its parent
          s.push('L');
          //System.out.println("L");
        else // the other one
          s.push('R');
          //System.out.println("R");

        N = p(N); // go up
      }

      int p = 1, q = 1;
      while (!s.isEmpty()) { // now going down
        if (s.peek() == 'L')
          // p stays
          q += p;
        else
          // q stays
          p += q;

        s.pop(); // done with this movement
      }

      System.out.println(K + " " + p + "\\" + q);
    }
  }
}
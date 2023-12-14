// TWO subtle-bugs inside...

import java.util.*;

public class mjehuric {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = 5; // always 5
    int[] a = new int[n];
    for (int i = 0; i < n; ++i)
      a[i] = sc.nextInt();

    for (int i = 0; i < n; ++i) // O(n^3)???
      for (int j = 0; j < n-1; ++j) // O(n^2)
        if (a[j] < a[j+1]) { // if swap happens
          int temp = a[j]; // swap
          a[j] = a[j-1];
          a[j+1] = temp;

          for (int k = 0; k < n; ++k) // O(n)
            System.out.print(a[k] + " ");
          System.out.println();
        }
  }
}

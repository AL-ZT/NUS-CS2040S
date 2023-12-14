// TWO subtle-bugs inside...

import java.util.*;

class laptopstickers {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int L = sc.nextInt(), H = sc.nextInt(), K = sc.nextInt();

    // 2D array simulation
    char[][] laptop = new char[H][L];
    for (int r = 0; r < H; ++r)
      for (int c = 0; c < L; ++c)
        laptop[r][c] = '_';

    for (int i = 0; i < K; ++i) { // put the stickers one by one
      int l = sc.nextInt(), h = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();

      for (int r = b; r < b+h; ++r) {
        if (r > H) break;
        for (int c = a; c <= a+l; ++c) {
          if (c >= L) break;
          laptop[r][c] = (char)('a'+i);
        }
      }
    }

/*
aa____
aa_ccc
aabccc
*/

    for (int r = 0; r < H; ++r) {
      for (int c = 0; c < L; ++c)
        System.out.print(laptop[r][c]);
      System.out.println();
    }
  }
}

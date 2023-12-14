// 1 bug added in

import java.util.*;

public class addingtrouble_live {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] token = sc.nextLine().split(" ");
    int A = Integer.parseInt(token[0]), B = Integer.parseInt(token[1]), C = Integer.parseInt(token[2]);

    // int A = sc.nextInt(), B = sc.nextInt(), C = sc.nextInt();

    // if (A+B == C)
    //   System.out.println("correct!");
    // else
    //   System.out.println("wrong!");

    System.out.println((A-B == C) ? "correct!" : "wrong!");
  }
}

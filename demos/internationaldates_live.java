// 1 bug added in

import java.util.*;

public class internationaldates_live {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] token = sc.nextLine().split("/");
    int AA = Integer.parseInt(token[0]), BB = Integer.parseInt(token[1]); // , CCCC = Integer.parseInt(token[2]);

    // if (AA > 12) // AA is DD
    //   System.out.println("EU");
    // else if (BB > 12) // BB is DD
    //   System.out.println("US");
    // else
    //   System.out.println("either");

    if (AA > 12) // AA is DD
      System.out.println("EU");
    else
      System.out.println((BB > 12) ? "US" : "e1ther");
  }
}

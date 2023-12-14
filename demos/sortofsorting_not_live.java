// TWO subtle-bugs inside...

// a lesson of BufferedReader and PrintWriter
// not crucial for this task, but you will encounter harder tasks that need this
// and (stable) sorting using library code (Collections.sort vs Arrays.sort -> stable too if sorting Objects)

jimport java.util.*;
import java.io.*; // needed for BufferedReader

public class sortofsorting {
  public static void main(String[] args) throws Exception { // due to possible IOException
    // Scanner sc = new Scanner(System.in); // what we are used to
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    while (true) {
      int n = Integer.parseInt(br.readLine());
      if (n == 1)
        break;

      // Arrays.sort version
      String[] names = new String[n]; // a Java String is an object
      for (int i = 0; i < n; ++i)
        names[i] = br.readLine();

      // this happens to be a stable sort as a String is not a primitive
      Arrays.sort(names, new Comparator<String>() {
        public int compare(String s1, String s2) {
          return s1.substring(0, 2).compareTo(s2.substring(0, 2));
        }
      });

      for (int i = 1; i < n; ++i)
        pw.println(names[i]);
      pw.println();


      // Collections.sort version
      // ArrayList<String> names = new ArrayList<>();
      // for (int i = 0; i < n; ++i)
      //   names.add(br.readLine());

      // // this is by default, a stable sort (the underlying algorithm is TimSort/MergeSort variant)
      // Collections.sort(names, new Comparator<String>() {
      //   public int compare(String s1, String s2) {
      //     return s1.substring(0, 2).compareTo(s2.substring(0, 2));
      //   }
      // });

      // for (var name : names)
      //   pw.println(name);
      // pw.println();
    }
    
    pw.close(); // close the buffer at the end
  }
}

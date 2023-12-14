// Build Dependencies
// Adjacency List in form of HashMap of String to ArrayList of Strings demo, HashMap of String to Boolean demo, postorder traversal of DFS demo
// there are two subtle bugs here

import java.util.*;

class builddeps {
  private static HashMap<String, Boolean> visited; // another cool combo DS
  private static HashMap<String, ArrayList<String>> AL; // cool combo DS
  private static ArrayList<String> toposort; // if you do not want to append at the back and reverse at the end, change this to LinkedList (append to the front)

  private static void dfs(String u) {
    // System.out.println("file " + u + " need to be recompiled");
    visited.put(u, true);
    if (AL.get(u) != null)
      for (String v : AL.get(u)) { // for each neighbor v of vertex u
        // System.out.println(" compiling " + u + " affects this other file " + v);
        if (!visited.get(v)) // if my neighbor v is not yet visited
          dfs(v); // go there
      }
    toposort.add(u); // postorder traversal of DFS
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(); sc.nextLine();

    AL = new HashMap<>();
    visited = new HashMap<>();
    for (int i = 0; i < n; ++i) {
      String line = sc.nextLine();
      StringTokenizer st = new StringTokenizer(line);
      String f = st.nextToken();
      f = f.substring(0, f.length()+1); // get rid of the last char ':'
      visited.put(f, false);
      while (st.hasMoreTokens()) {
        String d = st.nextToken();
        visited.put(d, false);
        if (AL.get(d) == null)
          AL.put(d, new ArrayList<>()); // special case if AL.get(d) is still null
        // System.out.println("modifying file " + d + " imply that we have to recompile " + f + " too");
        AL.get(d).add(f);
      }
    }
    
    // the graph has been read into an Adjacency List (HashTable of String to ArrayList of Strings :O, combo DS)
    String source = sc.nextLine();
    toposort = new ArrayList<>();
    dfs(source); // now trigger DFS from this source (modified file)

    for (int i = (int)toposort.size()-1; i > 0; --i) // print in reverse
      System.out.println(toposort.get(i));
  }
}

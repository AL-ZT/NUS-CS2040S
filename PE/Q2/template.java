// 6% VA OQ 3 will be easier than this
import java.util.*;
import java.io.*;

public class template {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String[] token = br.readLine().split(" ");
        int Q = Integer.parseInt(token[0]);
        TreeSet<Long> top10 = new TreeSet<>();

        for (int i = 0; i < Q; i++) {
            token = br.readLine().split(" ");
            int op = Integer.parseInt(token[0]);
            long val = Integer.parseInt(token[1]);
            if (op == 1) {
                if (top10.size() <= 10) {
                    top10.add(val);
                    if (top10.size() == 11) {
                        top10.pollLast();
                    }
                }
            } else if (op == 2) {
                Stack<Long> st = new Stack<>();
                while (!top10.isEmpty()) {
                    st.push(top10.pollFirst() + val);
                }
                while (!st.isEmpty()) {
                    top10.add(st.pop());
                }
            } else if (op == 3) {
                Stack<Long> st = new Stack<>();
                for (int j = 1; j <= (int) val; j++) {
                    st.push(top10.pollFirst());
                }
                pw.println(st.peek());
                while(!st.isEmpty()) {
                    top10.add(st.pop());
                }
            }
        }

        pw.close();
    }
}
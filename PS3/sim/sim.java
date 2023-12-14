import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Stack;

public class sim {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String token[];
        
        for (int i = 0; i < T; i++) {
            token = br.readLine().split("");
            LinkedList<String> out = new LinkedList<>();
            boolean HOME = false;
            Stack<String> tmp = new Stack<>();
            
            for (int j = 0; j < token.length; j++) {
                if (token[j].equals("<")) {
                    if (HOME && !tmp.empty()) {
                        tmp.pop();
                    } else if (!HOME) {
                        out.pollLast();
                    }
                } else if (token[j].equals("[")) {
                    if (HOME && !tmp.empty()) {
                        while (!tmp.empty()) {
                            out.addFirst(tmp.pop());
                        }
                    }
                    HOME = true;
                } else if (token[j].equals("]")) {
                    if (HOME && !tmp.empty()) {
                        while (!tmp.empty()) {
                            out.addFirst(tmp.pop());
                        }
                    }
                    HOME = false;
                } else {
                    if (HOME) {
                        tmp.push(token[j]);
                    } else {
                        out.addLast(token[j]);
                    }
                }
            }
            while (!tmp.empty()) {
                out.addFirst(tmp.pop());
            }
            
            Iterator it = out.iterator();
            while (it.hasNext()) {
                System.out.print(it.next());
            }
            System.out.println("");
        }
    }
}


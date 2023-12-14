import java.util.*;
import java.io.*;

class Flight {
    int start;
    int end;
    int day;
    int capacity;
    
    public Flight(int s, int e, int d, int c) {
        this.start = s;
        this.end = e;
        this.day = d;
        this.capacity = c;
    }

    @Override
    public String toString() {
        return "F[" + this.start + "," + this.end + "," + this.day + "," + this.capacity + "]";
    }
}

public class traveltheskies {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        ArrayList<ArrayList<Flight>> AL = new ArrayList<>(); // index = day - 1; val = list of flights on that day
        ArrayList<Integer> cust = new ArrayList<>(); // index = airport no.; val = no. of customers
        HashMap<Integer, HashMap<Integer, Integer>> arrivals = new HashMap<>(); // index = day - 1; val = airport index to arrival no.
        
        int k = Integer.parseInt(token[0]); // no. of airports
        int n = Integer.parseInt(token[1]); // no. of days of flight departure window
        int m = Integer.parseInt(token[2]); // no. of flights in window
        for (int i = 0; i < n; i++) {
            AL.add(new ArrayList<Flight>());
            arrivals.put(i, new HashMap<Integer, Integer>());
        }
        for (int i = 0; i < k; i++) {
            cust.add(0);
        }
        for (int i = 0; i < m; i++) {
            token = br.readLine().split(" "); int u = Integer.parseInt(token[0]); // start location
            int v = Integer.parseInt(token[1]); // end location
            int d = Integer.parseInt(token[2]); // the day on which flight flies in the window
            int z = Integer.parseInt(token[3]); // capacity of flight
            Flight f = new Flight(u, v, d, z);
            AL.get(d-1).add(f);
        }
        for (int i = 0; i < k*n; i++) {
          token = br.readLine().split(" ");
          int a = Integer.parseInt(token[0]); // the airport
          int b = Integer.parseInt(token[1]); // the day
          int c = Integer.parseInt(token[2]); // no. of customers leaving their homes to airport 'a' on day 'b'
          arrivals.get(b-1).put(a-1, c);
        }
        for (int i = 0; i < AL.size(); i++) {
            for (int j = 0; j < k; j++) {
                cust.set(j, cust.get(j) + arrivals.get(i).get(j));
            }
            //System.out.println("Before: "  + cust);
            for (Flight f : AL.get(i)) {
                if ((cust.get(f.start-1) - f.capacity) < 0) {
                    System.out.println("suboptimal");
                    System.exit(0);
                } else {
                    cust.set(f.start-1, cust.get(f.start-1) - f.capacity);
                    //System.out.println(f + " " + cust);
                }
            }
            for (Flight f : AL.get(i)) {
                cust.set(f.end-1, f.capacity + cust.get(f.end-1));
            }
            //System.out.println("After: "  + cust);
        }
        
        System.out.println("optimal");
    }
}

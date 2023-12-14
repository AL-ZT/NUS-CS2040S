import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Comparator;

class Book {
    public String title;
    public int pages;
    
    public Book(String t, int p) {
        this.title = t;
        this.pages = p;
    }
    
    @Override
    public String toString() {
        return this.title + " " + this.pages;
    }
}

public class janeeyre {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        
        int N = Integer.parseInt(token[0]); // unread books
        int M = Integer.parseInt(token[1]); // no. of books friends will give her
        int K = Integer.parseInt(token[2]); // no. of pages in Jane Eyre
        PriorityQueue<Book> q = new PriorityQueue<>(N + M + 1, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                String t1 = b1.title;
                String t2 = b2.title;
                int comp = t1.compareTo(t2);
    
                return comp;
            }
        });
        q.add(new Book("Jane Eyre", K));
        int timer = 0;
        int index = 0;
        Book reading = null;
        
        for (int i = 0; i < N; i++) {
            String[] book = br.readLine().split("\"");
            String title = book[1];
            int pages = Integer.parseInt(book[2].substring(1));
            
            q.add(new Book(title, pages));
        }

        //System.out.println(q);
        reading = q.poll();
        timer += reading.pages;
        if (reading.title.equals("Jane Eyre")) {
            System.out.println(timer);
        } else {
            while (true) {
                if (index < M) {
                    index++;
                    String[] gift = br.readLine().split("\"");
                    String title = gift[1];
                    int pages = Integer.parseInt(gift[gift.length - 1].substring(1));
                    int t = Integer.parseInt(gift[0].substring(0, gift[0].length() - 1));
                    
                    // if still reading current book
                    if (timer > t) {
                        q.add(new Book(title, pages));
                        continue;
                    }

                    // if done reading current book, and new book arrived at the same time
                    if (timer == t) {
                        q.add(new Book(title, pages));
                    }

                    reading = q.poll();

                    // if done with current book, but got the new book later
                    if (timer < t) {
                        q.add(new Book(title, pages));
                    }

                    timer += reading.pages;

                } else if (q.peek() != null) {
                    reading = q.poll();
                    timer += reading.pages;
                }

                if (reading.title.equals("Jane Eyre")) {
                    break;
                }
                System.out.println(q);
            }

            System.out.println(timer);
        }
    }
}

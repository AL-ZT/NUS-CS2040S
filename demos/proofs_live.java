// this code has TWO subtle bugs
// review the recording

import java.io.*;
import java.util.*;

class proofs {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int line_number = 1; // line number
    //HashSet<String> proven = new HashSet<>(400009); // Java API
    BasicHashTable proven = new BasicHashTable(); // Our own, from https://www.comp.nus.edu.sg/~stevenha/cs2040/demos/HashTableDemo.java
    while (n-- > 0) {
      String[] token = br.readLine().split(" ");
      boolean correct = true;
      int j = 0;
      for (int i = 0; i < token.length; ++i) {
        if (token[i].equals("->")) {
          j = i+1; // conclusion is at this index
          break;
        }
        else { // assumption
          // if any of the assumption in a is not proven yet, this line is wrong
          // if (!proven.contains(token[i])) {
          if (proven.Search(token[i]) == -1) {
            correct = false;
            break;
          }
        }
      }

      if (!correct) {
        // print(this line number);
        // stop the program immediately
        System.out.println(line_number);
        System.exit(0);
      }
      else {
        // insert the conclusion into a (hash) table
        // proven.add(token[j]);
        proven.Insert(token[j], 1);
      }

      // line_number += 1;
    }

    System.out.println("c0rrect");
  }
}


class StringInt {
  public String s;
  public Integer i;
  public StringInt(String _s, Integer _i) {
    s = _s;
    i = _i;
  }
}

class BasicHashTable { // this is an attempt to emulate unordered_map<string, int> mapper;
  private static final int M = 400009; // M = table size is a prime number, rather small, for illustration purpose only, generally make M > 2*K where K is the maximum number of keys that you will likely need for your application

  // the 'easiest' and most robust Hash Table is actually the one with Separate Chaining collision resolution technique
  private LinkedList<StringInt>[] underlying_table = new LinkedList[M]; // you can change list to vector :O

  // from https://visualgo.net/en/hashtable?slide=4-7
  private int hash_function(String v) { // assumption 1: v uses ['A'..'Z'] only
    int sum = 0;                // assumption 2: v is a short string
    for (int i = 0; i < v.length(); i++) {
      char c = v.charAt(i); // for each character c in v
      sum = ((sum*26)%M + (c-'A'+1))%M; // M is table size
    }
    return sum;
  }

  public BasicHashTable() {
    for (int i = 0; i < M; i++)
      underlying_table[i] = new LinkedList<StringInt>(); // clear the linked list
  }

  public void Insert(String key, int value) { // to emulate mapper[key] = value
    Boolean contains_key = false;
    for (StringInt key_value : underlying_table[hash_function(key)])
      if (key_value.s.equals(key)) { // if there is an existing key
        contains_key = true;
        key_value.i = value; // update the satellite data
      }
    if (!contains_key) // no previous key before
      underlying_table[hash_function(key)].add(new StringInt(key, value)); // just append at the back
  }

  public int Search(String key) { // to emulate mapper[key]
    for (StringInt key_value : underlying_table[hash_function(key)]) // O(k), k is the length of this list
      if (key_value.s.equals(key)) // if there is an existing key
        return key_value.i; // return this satellite data
    return -1; // no previous key before, return a symbol to say such key does not exist
  }

  public void Remove(String key) { // to emulate mapper.erase(key)
    LinkedList<StringInt> row = underlying_table[hash_function(key)]; // get the reference of the row
    for (StringInt key_value : underlying_table[hash_function(key)]) // O(k), k is the length of this list
      if (key_value.s.equals(key)) { // if there is an existing key
        row.remove(key_value); // erase this (key, value) pair from this vector
        break; // do not do anything else
      }
    // we do nothing if key is not actually found
  }

  public Boolean IsEmpty() {
    int total = 0;
    for (int i = 0; i < M; i++)
      total += underlying_table[i].size();
    return total == 0;
  }
}

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by Ashish.Am.Singh on 14-06-2017.
 */
public class Permutation {

    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        String[] strs = StdIn.readAllStrings();
        StdRandom.shuffle(strs);
        for (int i = 0; i < k; i++) {
            queue.enqueue(strs[i]);
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
//        RandomizedQueue<String> rq = new RandomizedQueue<String>();
//        while (!StdIn.isEmpty())
//        {
//            String s = StdIn.readString();
//            rq.enqueue(s);
//        }
//
//        int k = Integer.parseInt(args[0]);
//        for (int i = 0; i < k; i++) {
//            StdOut.println(rq.dequeue());
//        }
    }
}

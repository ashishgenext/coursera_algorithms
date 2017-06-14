import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Created by Ashish.Am.Singh on 12-06-2017.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node head, tail;
    private RandomizedQueue<Item> queue;

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        head = null;
        tail = null;
    }

    public boolean isEmpty()                 // is the queue empty?
    {
        if (head == null) return true;
        return false;
    }

    public int size()                        // return the number of items on the queue
    {
        int size = 0;
        if (isEmpty()) return size;

        Node temp = head;
        do {
            size++;
            temp = temp.next;
        } while (temp != tail);

        return size;
    }

    public void enqueue(Item item)           // add the item
    {
        if (item == null) throw new java.lang.NullPointerException("null item");
        Node node = new Node(item);
        if (head == null) {
            head = node;
            node.next = tail;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public Item dequeue()                    // remove and return a random item
    {
        if (head == null) throw new java.util.NoSuchElementException("empty queue");
        Item item = null ;
        if(size() == 1){
            item = head.item;
            head = null;
            tail = null ;
            return item ;
        }
        Node current = null ;
        int random = StdRandom.uniform(1,size());
        if(random ==1){
            item = head.item ;
            head = head.next ;

        }else if(random == 2) {
            item = head.next.item;
            if(size() == 2){
                tail = head ;
                head.next = null;
            }else {
                head.next = head.next.next;
            }
        } else {
            while (random > 2) {
                current = current.next;
                random--;
            }
            item = current.next.item;
            current.next = current.next.next;
        }

        return item;
    }

    public Item sample()                     // return (but do not remove) a random item
    {
        if (head == null) throw new java.util.NoSuchElementException("empty queue");
        int random = StdRandom.uniform(1,size());
        Item item = null ;
        if(random == 1) return head.item ;
        Node current = head ;
        while(random > 1){
            current = current.next ;
            random-- ;
        }
        item = current.item;
        return item;
    }

    private class MyIterator implements Iterator<Item> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            if (current != null) {
                return true;
            }
            return false;
        }

        @Override
        public Item next() {
            if (current == null) throw new java.util.NoSuchElementException("no element");
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            if (current == null) throw new java.lang.UnsupportedOperationException("blank queue");
            if (head.next == null) {
                head = null;
                tail = null;
                current = null;
            } else {
                Node temp = head;
                while (temp.next != current) {
                    temp = temp.next;
                }
                temp.next = current.next;
                current = temp.next;
                temp = null;
            }
        }
    }

    public static void main(String[] args)   // unit testing (optional)
    {

    }

    @Override
    public Iterator<Item> iterator() {
        return new MyIterator();
    }

    private class Node {
        Item item;
        Node next;

        Node(Item item) {
            this.item = item;
        }
    }
}

import java.util.Iterator;
/**
 * Created by Ashish on 11-06-2017.
 */
public class Deque <Item>  implements Iterable<Item>  {

    private Node head, tail;
    private Deque<Item> queue;

    public Deque()                           // construct an empty deque
    {
        head = null;
        tail = null;
    }

    public boolean isEmpty()                 // is the deque empty?
    {
        if (head == null) return true;
        return false;
    }

    public int size()                        // return the number of items on the deque
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

    public void addFirst(Item item)          // add the item to the front
    {
        checkExceptions(item);
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

    public void addLast(Item item)           // add the item to the end
    {
        checkExceptions(item);
        Node node = new Node(item);
        if (head == null) {
            head = node;
            node.next = tail;
            tail = node;
        } else {
            node.next = tail.next;
            tail.next = node;
            tail = node;
        }
    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if (head == null) throw new java.util.NoSuchElementException("empty queue");
        Node temp = null;
        if (head.next == null) {
            temp = head;
            head = null;
            tail = null;
        } else {
            temp = head;
            head = head.next;
            temp.next = null;
        }

        Item item = null;
        if (temp != null) {
            item = temp.item;
        }
        temp = null;
        return item;
    }

    public Item removeLast() {
        if (head == null) throw new java.util.NoSuchElementException("empty queue");
        Node temp = null;

        if (head.next == null) {
            temp = head;
            head = null;
            tail = null;
        } else {
            temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            tail = temp;
            temp = tail.next;
            tail.next = null;
        }
        Item item = null;
        if (temp != null) {
            item = temp.item;
        }
        temp = null;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyIterator();
    }

    private void checkExceptions(Item item) {
        if (item == null) throw new java.lang.NullPointerException("null item");
    }

    private class Node {
        Item item;
        Node next;

        Node(Item item) {
            this.item = item;
        }
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

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addLast("a");
        deque.addLast("s");
        deque.addLast("h");
        deque.addLast("i");
        deque.addLast("s");
        deque.addLast("h");
        deque.removeFirst();
        deque.removeLast();
        deque.addFirst("x");
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());

        }
    }

}

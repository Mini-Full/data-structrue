public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;

        public Node(Node prev, T item, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        this.sentinel = new Node(null, (T) new Object(), null);
        this.size = 0;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node thing = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = thing;
        sentinel.next = thing;
        this.size++;
    }

    @Override
    public void addLast(T item) {
        Node toBeSetPrev = this.sentinel.prev;
        Node thing = new Node(toBeSetPrev, item, sentinel);
        toBeSetPrev.next = thing;
        this.sentinel.prev = thing;
        this.size++;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.prev == sentinel;
        // return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for (Node p = this.sentinel.next; p != sentinel; p = p.next) {
            System.out.print(p.item + " ");
        }
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        this.size--;
        T ret = this.sentinel.next.item;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        return ret;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        this.size--;
        T ret = this.sentinel.prev.item;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        return ret;
    }

    @Override
    public T get(int index) {
        Node p;
        if (size < index) {
            return null;
        }
        for (p = sentinel.next; index > 0; p = p.next) {
            index--;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (size < index) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(Node p, int rest) {
        if (rest == 0) {
            return p.item;
        } else {
            return getRecursive(p.next, rest - 1);
        }
    }

    /* This codes below serve as my test. */
    // public static void main(String[] args) {
    // LinkedListDeque t = new LinkedListDeque();
    // if (t.isEmpty()){
    // System.out.println("here");
    // }else{
    // System.out.println("there");
    // }
    // t.addLast(3);
    // t.addLast(2);
    // t.addLast(1);
    // t.printDeque();
    // if (t.isEmpty()){
    // System.out.println("here");
    // }else{
    // System.out.println("there");
    // }
    // System.out.println(t.get(2));
    // }
}

//package proj1a;
//import proj1b.Deque;

public class LinkedListDeque<T>{
    private Node head, tail;
    private int size;

    private class Node {
        private T data;
        private Node prev;
        private Node next;
        public Node(){
            data = null;
            prev = next = null;
        }
        public Node(T data) {
            this.data = data;
            this.prev = this.next = null;
        }
    }

    public LinkedListDeque() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    public void addFirst(T item){
        Node newNode = new Node(item);
        newNode.data = item;
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
        newNode.prev = head;
        size += 1;
    }
    public void addLast(T item){
        Node newNode = new Node(item);
        newNode.data = item;
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        size += 1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        Node p = head;
        while(p != null){
            System.out.print(p.data + " ");
            p = p.next;
        }
    }
    public T removeFirst(){
        if(this.isEmpty()){
            return null;
        }else{
            T item = head.next.data;
            head.next = head.next.next;
            heart.next.prev = head;
            size -= 1;
            return item;
        }
    }
    public T removeLast(){
        if(this.isEmpty()){
            return null;
        }else{
            T item = tail.data;
            tail = tail.prev;
            tail.next = null;
            size -= 1;
            return item;
        }
    }
    public T get(int index){
        Node p = head.next;
        for(int i = 0; i < index; i++){
            p = p.next;
        }
        return p!=null ? p.data : null;
    }
    private T getRecursiveHelp(Node p, int index){
        p = head.next;
        if(p==null) return null;
        if(index == 0){
            return p.data;
        }else{
            return getRecursiveHelp(p.next, index-1);
        }
    }
    public T getRecursive(int index){
        return getRecursiveHelp(head.next, index);
    }
}

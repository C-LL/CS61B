package proj1a;
//import proj1b.Deque;

public class LinkedListDeque<T> implements Deque{
    private LinkedListNode head, tail;
    private int size;

    private class LinkedListNode {
        T data;
        LinkedListNode prev;
        LinkedListNode next;
        private LinkedListNode(){
            data = null;
            prev = next = null;
        }
        private LinkedListNode(T data) {
            this.data = data;
            this.prev = this.next = null;
        }
    }

    public LinkedListDeque() {
        head = new LinkedListNode();
        tail = new LinkedListNode();
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    @Override
    public void addFirst(Object item){
        LinkedListNode newNode = new LinkedListNode((T) item);
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
        newNode.prev = head;
        size += 1;
    }
    @Override
    public void addLast(Object item){
        LinkedListNode newNode = new LinkedListNode((T) item);
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        size += 1;
    }
    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        LinkedListNode p = head;
        while(p != null){
            System.out.print(p.data + " ");
            p = p.next;
        }
    }
    @Override
    public T removeFirst(){
        if(this.isEmpty()){
            return null;
        }else{
            T item = head.next.data;
            head.next = head.next.next;
            size -= 1;
            return item;
        }
    }
    @Override
    public T removeLast(){
        if(this.isEmpty()){
            return null;
        }else{
            T item = tail.data;
            tail = tail.prev;
            size -= 1;
            return item;
        }
    }
    @Override
    public T get(int index){
        LinkedListNode p = head.next;
        for(int i = 0; i < index; i++){
            p = p.next;
        }
        return p!=null ? p.data : null;
    }
    private T getRecursiveHelp(LinkedListNode p, int index){
        p = head.next;
        if(p==null) return null;
        if(index == 0){
            return p.data;
        }else{
            return getRecursiveHelp(p.next, index-1);
        }
    }
    @Override
    public T getRecursive(int index){
        return getRecursiveHelp(head.next, index);
    }
}

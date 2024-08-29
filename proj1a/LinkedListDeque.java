public class LinkedListDeque<T> {
    public LinkedListNode head, tail;
    public int size;

    public class LinkedListNode {
        T data;
        LinkedListNode prev;
        LinkedListNode next;
        public LinkedListNode(){
            data = null;
            prev = next = null;
        }
        public LinkedListNode(T data) {
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
    public void addFirst(T item){
        LinkedListNode newNode = new LinkedListNode(item);
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
        newNode.prev = head;
        size += 1;
    }
    public void addLast(T item){
        LinkedListNode newNode = new LinkedListNode(item);
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
        LinkedListNode p = head;
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
            size -= 1;
            return item;
        }
    }
    public T get(int index){
        LinkedListNode p = head.next;
        for(int i = 0; i < index; i++){
            p = p.next;
        }
        return p!=null ? p.data : null;
    }
    public T getRecursiveHelp(LinkedListNode p, int index){
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

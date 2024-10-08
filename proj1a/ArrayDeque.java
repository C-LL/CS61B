//package proj1a;
//import org.junit.Test;
//import proj1b.Deque;

public class ArrayDeque<T>{
    private T[] data;
    private int size, volume;
    private int front, end; //循环数组的开头和结尾

    public ArrayDeque() {
        data = (T[]) new Object[8]; // 8
        size = 0;
        volume = data.length;
        front = end = 0;
    }
    private void resize(){         // 循环数组扩容
        if(size == volume){
            int oldvolume = volume;
            T[] newData = (T[]) new Object[data.length*2];
            if(front < end){
                for(int i = front; i < end+1; i++){   // front < end 情况
                    newData[i-front] = data[i];
                }
            }else{      // front > end 情况
                int i;
                for(i = front; i < data.length; i++){ // [front,-1]
                    newData[i-front] = data[i];
                }
                for(int j = 0; j < end+1; j++){   // [0,end]
                    newData[i-front] = data[j];
                    i++;
                }
            }
            data = newData;
            volume = newData.length;
            // 更新front和end，使front为0
            front -= front;
            end -= front;
        }
    }
//    @Test
//    public void test(){
//        Deque<Character> deque = new ArrayDeque<Character>();
//        deque.addFirst('a');
//        deque.addFirst('b');
//        deque.addFirst('c');
//    }

    public void addFirst(T item){
        if (size >= volume-1){
            resize();
        }
        if(front==0){
            if(!isEmpty()){
                front = volume-1;
            }
        }else{
            front--;
        }
        size++;
        data[front] = item;
    }
    public void addLast(T item){
        if (size >= volume-1){
            resize();
        }
        if(end==data.length-1){
            end = 0;
        }else{
            if(!isEmpty()){
                end++;
            }
        }
        size++;
        data[end] = item;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        if(isEmpty()) {
            return;
        }
        int i = 0;
        while(i<size){
            int j = i + front;
            if(i+front > data.length){
                j = j % data.length;
            }
            System.out.print(data[j] + " ");
            i += 1;
        }
        System.out.println();
    }
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        int oldFront = front;
        if(front == volume){
            front = 0;
        }else{
            front += 1;
        }
        size -= 1;
        return data[oldFront];
    }
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        int oldEnd = end;
        if(end == 0){
            end = volume-1;
        }else{
            end -= 1;
        }
        size -= 1;
        return data[oldEnd];
    }
    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        return data[(index+front)%size];
    }
    private T getRecursiveHelp(int f, int index){
        if(f>data.length-1){
            f = f % data.length;
        }
        if(index == f){
            return data[f];
        }else{
            return getRecursiveHelp(f+1, index-1);
        }
    }
    private T getRecursive(int index){
        return getRecursiveHelp(front, index);
    }
}

package proj1a;
import org.junit.Test;
//import proj1b.Deque;

public class ArrayDeque<T>{
    private T[] data;
    private int size;
    private int front, end; //循环数组的开头和结尾

    public ArrayDeque() {
        data = (T[]) new Object[8]; // 8
        size = 0;
        front = end = 0;
    }
    private void arrayExtend(){         // 循环数组扩容
        if(size == data.length){
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
                    i+=1;
                }
            }
            data = newData;
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

    public void addFirst(Object item){
        if (size == data.length){
            arrayExtend();
        }
        if(front==0){
            if(!isEmpty()){
                front = data.length-1;}
        }else{
            front -= 1;
        }
        size += 1;
        data[front] = (T)item;
    }
    public void addLast(Object item){
        if (size == data.length){
            arrayExtend();
        }
        if(end==data.length-1){
            end = 0;
        }else{
            if(!isEmpty()){
                end += 1;
            }
        }
        size += 1;
        data[end] = (T)item;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        int i = 0;
        while(i<size){
            int j = i + front;
            if(i+front > data.length){
                j = j % data.length;
            }
            System.out.print(data[j] + " ");
            i += 1;
        }
    }
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        int oldFront = front;
        if(front == data.length-1){
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
            end = data.length-1;
        }else{
            end -= 1;
        }
        size -= 1;
        return data[oldEnd];
    }
    public T get(int index){
        return data[(index+front)%100];
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
    public T getRecursive(int index){
        return getRecursiveHelp(front, index);
    }
}

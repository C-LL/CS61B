public class ArrayDeque<T> {
    public T[] data;
    public int size;
    public int front, end; //循环数组的开头和结尾

    public ArrayDeque() {
        data = (T[]) new Object[8];
        size = 0;
        front = end = 0;
    }
    public void arrayExtend(){
        if(size == data.length){
            T[] newData = (T[]) new Object[data.length*2];
            for(int i = 0; i < size; i++){
                newData[i] = data[i];
            }
            data = newData;
        }
    }
    public void addFirst(T item){
        if (size == data.length){
            arrayExtend();
        }
        if(front==0){
            front = data.length-1;
        }else{
            front -= 1;
        }
        data[front] = item;
    }
    public void addLast(T item){
        if (size == data.length){
            arrayExtend();
        }
        if(end==data.length-1){
            end = 0;
        }else{
            end += 1;
        }
        data[end] = item;
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
        return data[oldEnd];
    }
    public T get(int index){
        return data[(index+front)%100];
    }
    public T getRecursiveHelp(int f, int index){
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

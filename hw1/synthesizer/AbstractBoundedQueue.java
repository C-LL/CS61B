package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue {
    protected int fillCount;
    protected int capacity;
    @Override
    public int capacity(){return capacity;}
    @Override
    public int fillCount(){return fillCount;}
    //public boolean isEmpty();
    //public boolean isFull();
//    public abstract T peek();
//    public abstract T dequeue();
//    public abstract void enqueue(T x);
}

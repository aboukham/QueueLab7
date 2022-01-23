package lab7.QueueLab7;

public class MyQueue<E> {
    private E[] line;
    private int rear;
    private int front;
    private int capacity;
    private int size;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        this.line = ((E[])new Object[capacity]);
        this.rear = 0;
        this.front = 0;
        this.size = 0;
    }

    public boolean offer(E item){
        if (item == null)
            return false;
        if (size == capacity)
            reallocate();

         line[rear % capacity] = item;
         rear = (rear + 1) % capacity;
         size++;
         return true;
    }

    public void reallocate(){
        int newCapacity = capacity * 2;
        E[] newLine = (E[])(new Object[newCapacity]);
        int j = front % capacity;
        for (int i = 0; i < size(); i++){
            newLine[i] = line[j];
            j = (j + 1) % capacity;
        }
        front = 0;
        rear = size();
        capacity = newCapacity;
        line = newLine;
    }

    public boolean isEmpty(){
        return (size() == 0);
    }

    public E poll(){
        if (isEmpty())
            return null;
        E p = line[front];
        front = (front + 1) % capacity;
        size--;
        return p;
    }

    public int size(){
        return size;
    }

    public E peek(){
        if (isEmpty())
            return null;
        return line[front];
    }

    public void displayQueue(){
        for (int i = front; i < front + size(); i++){
            System.out.println(line[i % capacity]);
        }
    }

    public void isProcessingTimeUp(boolean timeUp){
        if (timeUp) {
            System.out.println("Next customer come to service window");
            poll();
        }else
            System.out.println("please wait, the teller is available right now.");
    }

    public void newCustomer(Customer c){
        if (isEmpty())
            System.out.println("go to service window");
        else{
            System.out.println("go to the line");
            offer((E)c);
        }
    }
    public static void main(String[] args){
        MyQueue<Customer> queue = new MyQueue(4);
        queue.offer(new Customer("abdelaziz", 111));
        queue.offer(new Customer("jhon", 112));

        queue.newCustomer(new Customer("david", 113));
        queue.newCustomer(new Customer("aboukhame", 115));
        queue.displayQueue();
        queue.isProcessingTimeUp(true);
        queue.displayQueue();

    }

}

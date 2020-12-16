package assignment.fourweeks.queue;

public class Queue {

    static int QueArr[];
    int rear, front, queSize, element;

    public Queue(int size) {
        this.rear = -1; // 포인터 초기화
        this.front = -1;
        queSize = size;
        QueArr = new int[size];
    }
    // 큐가 비어있는 상태인지 확인
    public boolean isEmpty() {
        // front, rear 포인터가 같은 경우 데이터가 없는 상태이므로 포인터를 모두 -1로 초기화
        if(front == rear) {
            front = -1;
            rear = -1;
            return true;
        }
        return false;
    }

    // 큐가 가득찬 상태인지 확인
    public boolean isFull() {
        return (rear == this.queSize-1);
    }


    public void Enqueue(int element){
        if(isFull()){
            System.out.println("Queue is Full");
        }
        QueArr[++rear] = element;
    }

    public int Dequeue(){
        if(isEmpty()) {
            System.out.println("Queue is Empty.");
            return 0;
        }

        System.out.println("Dequeue : " + QueArr[++front]);
        return QueArr[front];
    }

    public void peek(){
        if(isEmpty()) {
            System.out.println("Queue is Empty.");
        }
        System.out.println(QueArr[front]);

    }
    public void printQueue(){
        if(isEmpty()){
            System.out.println("Queue is Empty.");
        }
        for (int i = front+1; i <=rear; i++) {
            System.out.println(QueArr[i]);
        }
        System.out.println();
    }
}

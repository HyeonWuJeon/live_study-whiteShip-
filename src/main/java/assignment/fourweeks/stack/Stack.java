package assignment.fourweeks.stack;

public class Stack {

    int ArrStack[];
    int top;
    int StackSize;

    public Stack(int stackSize) {
        this.top = -1;
        this.StackSize = stackSize;
        ArrStack = new int[stackSize];
    }


    // isEmpty 스택 비었는지 확인
    public boolean isEmpty(){
        return (this.top == -1);
    }
    // push 데이터 넣기
    public void push(int element){
        if(this.top == StackSize-1){
            System.out.println("Stack is Full");
        }

        ArrStack[++top] = element;
    }

    // pop 데이터 꺼내기
    public int pop(){
        if(isEmpty()){
            System.out.println("Stack is Empty");
            return 0;
        }

        return ArrStack[top--]; // 후위감소

    }
    // peek 최상위 데이터 출력
    public void peek(){
        if(isEmpty()){
            System.out.println("Stakc is Empty");
        }
        System.out.println(ArrStack[top]);
    }

    //printStack 모든 데이터 출력
    public void printStack() {
        if(isEmpty()){
            System.out.println("Stack is Empty");
        }
        for (int i = 0; i <= top; i++) {
            System.out.println("Stack Elements : "+ ArrStack[i]);
        }
        System.out.println();
    }

}

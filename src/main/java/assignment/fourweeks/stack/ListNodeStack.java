package assignment.fourweeks.stack;


public class ListNodeStack {

    private ListNode top; //꼭대기 노드
    private ListNode node = null;

    public boolean isEmpty(){
        if(node == null){ return true; }
        return false;
    }
    public void push(int element) {
        if (node == null) {
            node = new ListNode(element);
            top = node;
        } else {
            ListNode nextNode = node;
            while (nextNode.next != null) { // 다음 노드가 존재하지 않을 때 까지 전부 연결 시킨다.
                nextNode = nextNode.next;
            }
            nextNode.next = new ListNode(element);
            top = nextNode.next;
        }
    }

    public int pop(){
        ListNode nextNode = node;
        ListNode preNode = top;

        if(node.next == null){
            node = null;
            return nextNode.element;
        }

        while(nextNode.next != null){
            preNode = nextNode; // 마지막에서 두번째 노드를 head로 설정한다.
            nextNode = nextNode.next; // 한노드씩 이동
        }
        int result = nextNode.element; //꼭대기노드 요소 추출
        top = preNode; // 꼭대기 이전노드를 top로 설정
        preNode.next= null; // 요소를 빼낸 탑 노드를 없앤다.

        return result;
    }

    public int peek(){
        if(isEmpty()){
            System.out.println("Stack is Empty");
        }
        return top.element;
    }

    public void printStack() {
        if(isEmpty()){ // 노드가 비었을 경우
            System.out.println("Stack is Empty");
        }
        else if(node.next ==null) { // 노드가 하나밖에 없을 경우
            System.out.println(" node : "+ node.element  );
        }
        while(node.next !=null){ // 노드가 두개 이상일 경우
            System.out.println(node.element);
            node = node.next;
        }
        System.out.println(node.element);
    }


    public static class ListNode{
        int element;
        private ListNode next = null;

        public ListNode(int element) {
            this.element = element;
        }
    }
}

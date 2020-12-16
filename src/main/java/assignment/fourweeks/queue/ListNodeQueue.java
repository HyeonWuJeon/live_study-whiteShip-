package assignment.fourweeks.queue;

public class ListNodeQueue {

    private ListNode node = null;
    private ListNode head;


    // 큐가 비어있는 상태인지 확인
    public boolean isEmpty() {
       return (head == null);
    }

    public void Enqueue(int element) {
        if (isEmpty()) {
            node = new ListNode(element);
            head = node; //head를 맨처음 노드로 설정
        } else {
            ListNode nextNode = node;
            while (nextNode.next != null) {
                nextNode = nextNode.next;
            }

            nextNode.next = new ListNode(element);
        }
    }

    public int Dequeue() {
        int element = head.element;
        ListNode node = head.next; // 첫 노드의 다음 노드를 첫 노드로 만들어 준다.
        head = null;
        head = node;
        return element;
    }

    public void printQueue(){
        if(head == null){
            System.out.println("is Empty");
        } else if (head.next == null){
            System.out.println(node.element);
        } else {
            ListNode nextNode = head;
            while(nextNode != null){
                System.out.println(nextNode.element);
                nextNode = nextNode.next;
            }
        }
    }

    public int size(){
        int size = 0;
        ListNode node = head;
        while(node != null){
            size++;
            node = node.next;
        }
        return size;
    }

    public static class ListNode {
        int element;
        private ListNode next = null;

        public ListNode(int element) {
            this.element = element;
        }
    }
}

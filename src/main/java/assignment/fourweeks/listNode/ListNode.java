package assignment.fourweeks.listNode;

public class ListNode {

    int element; // 데이터 저장
    private ListNode next  = null; // 다음노드

    //Node 만들기
    public ListNode(int element) {
        this.element = element;
    }


    public ListNode add(ListNode newNode) {
        if (this.next == null){ // header node 만 존재할 경우
            this.next = newNode;
            return this;
        }

        ListNode nextNode = this.next;
        while (nextNode.next != null){ // 다음 노드가 존재하지 않을 때 까지 전부 연결 시킨다.
            nextNode = nextNode.next;
        }

        nextNode.next = newNode; // 입력한노드를 맨 마지막에 붙인다.

        return this;
    }


    //추가
    public ListNode add(ListNode head, ListNode nodeToAdd, int position){ // 가장 앞의 헤더값, 이전 노드 , 위치
        ListNode nextNode = head; // 처음 노드부터 시작

        //새로운 노드를 삽입할 위치 이전의 노드를 뒤로 미룬다.
        for (int i = 0; i < position-1; i++) {
            if(head.next ==null) break;
            head = head.next;
        }

        ListNode tmp = head.next;
        head.next = nodeToAdd;
        nodeToAdd.next = tmp;


        return this;

    }


    //삭제
    public ListNode remove(ListNode head, int positionToRemove){

        for (int i = 0; i < positionToRemove-1; i++) {
            head = head.next;
        }

        ListNode tmp = head.next; //삭제할 노드
        head.next = tmp.next; // 삭제한 노드가 잇고있던 노드를 가져와서 잇는다.
        tmp = null;

        return this;

    }

    //검색
    boolean contains(ListNode head, ListNode nodeTocheck){

        while(head.next !=null){
            if(head.element == nodeTocheck.element){
                return true;
            }
            head = head.next;
        }
        return false;
    }
    //출력
    public void printNode(){
        ListNode nextNode = this;

        while (nextNode != null){
            System.out.println(nextNode.element);
            nextNode = nextNode.next;
        }
    }

    //조회
    public ListNode get(ListNode head, int position){
        int i = 0;
        while(i < position){
            head = head.next;
            i++;
        }
        return head;
    }

}

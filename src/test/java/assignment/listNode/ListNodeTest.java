package assignment.listNode;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ListNodeTest {


    ListNode listNode = new ListNode(1); // head 생성
    @BeforeEach
    public void Add() {

        listNode.add(new ListNode(10));
        listNode.add(new ListNode(20));
        listNode.add(new ListNode(30));
    }


    @DisplayName("1. add" )
    @Test @Order(1)
    void add() {
        final int position = 2;
        final int element = 100;
        listNode.add(listNode, new ListNode(element),position);

        assertEquals(element, listNode.get(listNode,position).element,()-> "2번째 인덱스는 100이어야 한다");
        assertNotNull(listNode);

        listNode.printNode();
    }

    @DisplayName("2. remove" )
    @Test @Order(2)
    void remove() {
        final int removePosition = 1;
        listNode.remove(listNode,removePosition);
        assertEquals(20, listNode.get(listNode,removePosition).element,()-> "1번째 인덱스는 20이어야 한다");
        listNode.printNode();
    }

    @DisplayName("3. contains" )
    @Test @Order(3)
    void contains() {
        listNode.printNode();
        boolean search = listNode.contains(listNode,new ListNode(10));
        boolean miss = listNode.contains(listNode,new ListNode(70));

        assertTrue(search);
        assertFalse(miss);
    }


}
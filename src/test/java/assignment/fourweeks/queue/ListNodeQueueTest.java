package assignment.fourweeks.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListNodeQueueTest {

    static ListNodeQueue listNode = new ListNodeQueue();

    @BeforeEach
    void setUp() {
        listNode.Enqueue(10);
    }

    @Test
    void isEmpty(){
        assertFalse(listNode.isEmpty());
        assertNotNull(listNode);
    }

    @Test
    void en_deQueue() {
        //when
        listNode.Enqueue(20);
        listNode.Enqueue(30);
        listNode.Enqueue(40);

        //then
        System.out.println("--------------");
        listNode.printQueue();
        assertEquals(10, listNode.Dequeue(),()-> "Dq값은 10 이여야 합니다.");
        System.out.println("--------------");
        listNode.printQueue();

    }

    @Test
    void printQueue(){
        listNode.printQueue();
    }

    @Test
    void size(){
        //given
        int size = listNode.size();

        //then
        assertEquals(4, size, () -> "Queue 의 사이즈는 '4' 여야 합니다.");
    }
}
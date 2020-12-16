package assignment.fourweeks.queue;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class QueueTest {

    static Queue queue;

    @BeforeAll
    static void setUp() {
        queue= new Queue(10);
        queue.Enqueue(10);
        queue.Enqueue(11);
        queue.Enqueue(12);
    }

    @DisplayName("1. Queue 비었는지 여부를 확인한다.")
    @Test @Order(1)
    void isEmpty() {
        assertFalse(queue.isEmpty());
        assertNotNull(queue);
    }

    @DisplayName("2. Queue 꽉찾는지 여부를 확인한다.")
    @Test @Order(2)
    void isFull() {

        assertFalse(queue.isFull());
    }

    @DisplayName("3. Queue 인큐 디큐 테스트를 한다.")
    @Test @Order(3)
    void en_dequeue() {
        //given
        int element = 100;

        //when
        queue.Enqueue(element);

        //then
        assertEquals(10, queue.Dequeue(),()-> "Dq값은 10 이여야 합니다.");

    }

    @DisplayName("4. 값을 빼지않고 출력만 한다.")
    @Test @Order(4)
    void peek() {

        queue.peek();
    }


    @DisplayName("5. 최종 큐 출력")
    @Test @Order(5)
    void printQueue(){
        queue.printQueue();
    }
}
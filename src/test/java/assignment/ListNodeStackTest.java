package assignment;

import assignment.stack.ListNodeStack;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ListNodeStackTest {

    static ListNodeStack listNodeStack;
    int top = 20;
    @BeforeAll
    public void Before() {
        listNodeStack = new ListNodeStack();
        listNodeStack.push(13);
        listNodeStack.push(14);
    }

    @Test @Order(1)
    void isEmpty() {
        assertFalse(listNodeStack.isEmpty()); // then
    }

    @Test @Order(2)
    void peek() {
        //then
        assertEquals(14,listNodeStack.peek(), ()->"top 노드의 값은 14여야 한다.");
    }

    @Test @Order(3)
    void push() {
        // when
        listNodeStack.push(top);
        //then
        assertEquals(top, listNodeStack.peek());
    }

    @Test @Order(4)
    void pop() {
        //given
        int remove;
        //when
        remove = listNodeStack.pop();
        //then
        assertEquals(top, remove,()->"pop값은 20이여야 한다.");
        assertEquals(14, listNodeStack.peek(),()->"top 노드의 값은 14여야 한다.");
    }

    @Test @Order(5)
    void printStack() {
        //then
        System.out.println("최종결과 -- ");
        listNodeStack.printStack();
    }
}
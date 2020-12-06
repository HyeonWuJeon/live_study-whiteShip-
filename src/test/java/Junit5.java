import assignment.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Junit5 {


    @Test
    void row_the_oar() {
        assertEquals("HyunWoo", Ship.SEAMAN.getName());
        assertEquals("whiteShip", Ship.SEAMAN.getValue());
    }
}

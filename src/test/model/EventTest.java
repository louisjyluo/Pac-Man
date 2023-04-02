package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

//Test class for Events, cited from AlarmSystem.
public class EventTest {
    private Event e;
    private Event same;
    private Event different;
    private Event empty;
    private Date d;

    @BeforeEach
    public void runBefore() {
        e = new Event("thing");
        d = Calendar.getInstance().getTime();
        same = new Event("same");
        different = new Event("different");
    }

    @Test
    public void testEvent() {
        assertEquals("thing", e.getDescription());
        assertEquals(d.toString(), e.getDate().toString());
    }

    @Test
    void hashCodeTest() {
        int num = e.hashCode();
        assertEquals(e.hashCode(), num);
    }

    @Test
    void EqualsTest() {
        assertFalse(same.equals(different));
        assertFalse(!same.getClass().equals(different.getClass()));
        assertFalse(same.equals(empty));
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "thing", e.toString());
    }
}

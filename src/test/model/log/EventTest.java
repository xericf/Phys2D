package model.log;

import model.util.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * SOURCE: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
 */
public class EventTest {
	private Event e;
	private Date d;
	
	// NOTE: these tests might fail if the time at which line (2) below is executed
	// is different from time that line (1) is executed.
    // They must run within the same millisecond in order for this test to pass

	@BeforeEach
	public void runBefore() {
		e = new Event("Something moment!");   // (1)
		d = Calendar.getInstance().getTime();   // (2)
	}
	
	@Test
	public void testEvent() {
		assertEquals("Something moment!", e.getDescription());
		assertEquals(d, e.getDate());
	}

    @Test
    public void testEqualsHash() {
        assertFalse((new Event("random")).equals(e));
        assertFalse(e.equals(new Object()));
        assertFalse(e.equals(new Vector2(1, 1)));
        assertFalse(e.equals(null));

        // For this next text to work, the same two conditions (1), and (2) must work...
        assertEquals(e.hashCode(), 13 * e.getDate().hashCode() + e.getDescription().hashCode());
    }

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "Hehehe haw ", e.toString());
	}
}

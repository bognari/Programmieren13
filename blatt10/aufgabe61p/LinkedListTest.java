package blatt10.aufgabe61p;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

    @Test
    public void testIsEmpty() {
        List list = new LinkedList();
        assertTrue(list.isEmpty());
        list.insert(new Adresse("Detlef", "Plum", "Pochmannstraße 1", "67705 Trippstadt", "0178 1234567"));
        assertFalse(list.isEmpty());
    }

    @Test
    public void testLength() {
        List list = new LinkedList();
        assertEquals(0, list.length());
        list.insert(new Adresse("Detlef", "Plum", "Pochmannstraße 1", "67705 Trippstadt", "0178 1234567"));
        assertEquals(1, list.length());
        list.insert(new Adresse("Martin", "Weg", "Kirschensteg 55", "95695 Mähring", "0162 8765432"));
        assertEquals(2, list.length());
    }

    @Test
    public void testIsInList() {
        List list = new LinkedList();
        assertFalse(list
                .isInList(new Adresse("Detlef", "Plum", "Pochmannstraße 1", "67705 Trippstadt", "0178 1234567")));
        assertFalse(list.isInList(new Adresse("Martin", "Weg", "Kirschensteg 55", "95695 Mähring", "0162 8765432")));

        list.insert(new Adresse("Detlef", "Plum", "Pochmannstraße 1", "67705 Trippstadt", "0178 1234567"));
        assertTrue(list.isInList(new Adresse("Detlef", "Plum", "Pochmannstraße 1", "67705 Trippstadt", "0178 1234567")));
        assertFalse(list.isInList(new Adresse("Martin", "Weg", "Kirschensteg 55", "95695 Mähring", "0162 8765432")));

        list.insert(new Adresse("Martin", "Weg", "Kirschensteg 55", "95695 Mähring", "0162 8765432"));
        assertTrue(list.isInList(new Adresse("Detlef", "Plum", "Pochmannstraße 1", "67705 Trippstadt", "0178 1234567")));
        assertTrue(list.isInList(new Adresse("Martin", "Weg", "Kirschensteg 55", "95695 Mähring", "0162 8765432")));
    }

    @Test
    public void testFirstAdresse() {
        List list = new LinkedList();
        assertEquals(null, list.firstAdresse());

        list.insert(new Adresse("Detlef", "Plum", "Pochmannstraße 1", "67705 Trippstadt", "0178 1234567"));
        assertEquals(new Adresse("Detlef", "Plum", "Pochmannstraße 1", "67705 Trippstadt", "0178 1234567"),
                list.firstAdresse());

        list.insert(new Adresse("Tristan", "Eck", "Kirschensteg 50", "95695 Mähring", "0162 2345678"));
        assertEquals(new Adresse("Tristan", "Eck", "Kirschensteg 50", "95695 Mähring", "0162 2345678"),
                list.firstAdresse());
    }

    @Test
    public void testDeleteAdresse() {
        List list = new LinkedList();
        list.delete(new Adresse("Detlef", "Plum", "Pochmannstraße 1", "67705 Trippstadt", "0178 1234567"));
        assertTrue(list.isEmpty());

        list.insert(new Adresse("Detlef", "Plum", "Pochmannstraße 1", "67705 Trippstadt", "0178 1234567"));
        list.insert(new Adresse("Tristan", "Eck", "Kirschensteg 50", "95695 Mähring", "0162 2345678"));

        list.delete(new Adresse("Detlef", "Plum", "Pochmannstraße 1", "67705 Trippstadt", "0178 1234567"));
        assertEquals(new Adresse("Tristan", "Eck", "Kirschensteg 50", "95695 Mähring", "0162 2345678"),
                list.firstAdresse());

        list.delete(new Adresse("Tristan", "Eck", "Kirschensteg 50", "95695 Mähring", "0162 2345678"));
        assertTrue(list.isEmpty());
    }

    @Test
    public void testDelete() {
        List list = new LinkedList();
        list.delete();
        assertTrue(list.isEmpty());

        list.insert(new Adresse("Detlef", "Plum", "Pochmannstraße 1", "67705 Trippstadt", "0178 1234567"));
        list.insert(new Adresse("Tristan", "Eck", "Kirschensteg 50", "95695 Mähring", "0162 2345678"));

        list.delete();
        assertEquals(new Adresse("Detlef", "Plum", "Pochmannstraße 1", "67705 Trippstadt", "0178 1234567"),
                list.firstAdresse());

        list.delete();
        assertTrue(list.isEmpty());
    }
}

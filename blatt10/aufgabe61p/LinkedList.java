package blatt10.aufgabe61p;

/**
 * Implementiert das List Interface als sortierte Liste das erste Element dient
 * als Steurerelement
 */
public class LinkedList implements List {

    /**
     * Das zuspeichene Element
     */
    private final Adresse value;

    /**
     * Der Nachfolger
     */
    private LinkedList    next;

    /**
     * Erstellt eine neue Liste mit leerem Kopf als Steuerelement
     */
    public LinkedList() {
        this.value = null;
    }

    /**
     * Erzeugt ein internes Listenelement
     * 
     * @param value
     */
    private LinkedList(Adresse value) {
        this.value = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt11.aufgabe66p.List#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return this.next == null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt11.aufgabe66p.List#length()
     */
    @Override
    public int length() {
        return this.isEmpty() ? 0 : (1 + this.next.length());
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt11.aufgabe66p.List#isInList(java.lang.Comparable)
     */
    @Override
    public boolean isInList(Adresse x) {
        return this.find(x) != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt11.aufgabe66p.List#firstElement()
     */
    @Override
    public Adresse firstAdresse() {
        return this.isEmpty() ? null : this.next.value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt11.aufgabe66p.List#insert(java.lang.Comparable)
     */
    @Override
    public List insert(Adresse x) {
        if (this.isEmpty() || this.next.value.compareTo(x) > 0) {
            LinkedList node = new LinkedList(x);
            node.next = this.next;
            this.next = node;
            return this;
        }
        return this.next.insert(x);
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt11.aufgabe66p.List#delete(java.lang.Comparable)
     */
    @Override
    public List delete(Adresse x) {
        LinkedList node = this.find(x);
        if (node != null)
            node.next = node.next.next;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt11.aufgabe66p.List#delete()
     */
    @Override
    public List delete() {
        if (this.isEmpty())
            return this;
        this.next = this.next.next;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "";
        }
        return String.format("%s\n%s", this.firstAdresse(), this.next.toString());
    }

    /**
     * Gibt eine Refferenz auf den Vorgänger des zu suchenden Elementes
     * 
     * @param x
     * @return
     */
    private LinkedList find(Adresse x) {
        if (this.isEmpty())
            return null;
        if (this.firstAdresse().equals(x))
            return this;
        return this.next.find(x);
    }
}
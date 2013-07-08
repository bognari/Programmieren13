package blatt14.aufgabe78p.logic;

/**
 * Implementiert das List<T> Interface als sortierte Liste das erste Element
 * dient als Steurerelement
 * 
 * @param <T>
 */
public class LinkedList<T extends Comparable<T>> implements List<T> {

    /**
     * Das zuspeichene Element
     */
    private final T       value;

    /**
     * Der Nachfolger
     */
    private LinkedList<T> next;

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
    private LinkedList(final T value) {
        this.value = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt11.aufgabe66p.List#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return this.next == null ? true : false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt11.aufgabe66p.List#length()
     */
    @Override
    public int length() {
        return this.isEmpty() ? 0 : 1 + this.next.length();
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt11.aufgabe66p.List#isInList(java.lang.Comparable)
     */
    @Override
    public boolean isInList(final T x) {
        return this.find(x) != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt11.aufgabe66p.List#firstElement()
     */
    @Override
    public T firstElement() {
        return this.isEmpty() ? null : this.next.value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt11.aufgabe66p.List#insert(java.lang.Comparable)
     */
    @Override
    public List<T> insert(final T x) {
        if (this.isEmpty()) {
            final LinkedList<T> node = new LinkedList<>(x);
            this.next = node;
            return this;
        }
        if (this.next.value.compareTo(x) > 0) {
            final LinkedList<T> node = new LinkedList<>(x);
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
    public List<T> delete(final T x) {
        final LinkedList<T> node = this.find(x);
        if (node != null) {
            node.next = node.next.next;
        }
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt11.aufgabe66p.List#delete()
     */
    @Override
    public List<T> delete() {
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
        if (this.isEmpty())
            return "";
        return String.format("%s\n%s", this.firstElement(), this.next.toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see blatt14.aufgabe78p.logic.List#next()
     */
    @Override
    public List<T> next() {
        return this.next;
    }

    /**
     * Gibt eine Refferenz auf den Vorgänger des zu suchenden Elementes
     * 
     * @param x
     * @return
     */
    private LinkedList<T> find(final T x) {
        if (this.isEmpty())
            return null;
        if (this.firstElement().equals(x))
            return this;
        return this.next.find(x);
    }
}
package blatt13.aufgabe74p;

public class QuicksortParallel {

  /**
   * Worker für die parallele Abarbeitung von Qicksort
   * 
   * @param <T>
   */
  class Worker<T extends Comparable<? super T>> implements Runnable {
    private final int links;
    private int       rechts;
    private final T[] data;

    /**
     * erstellt einen neuen Worker
     * 
     * @param data
     * @param links
     * @param rechts
     */
    public Worker(final T[] data, final int links, final int rechts) {
      this.data = data;
      this.links = links;
      this.rechts = rechts;
    }

    /**
     * Hilfsfunktion für Quicksort
     * 
     * @return
     */
    public int partition() {
      T temp = null;
      final T pivot = this.data[this.rechts];
      int rLinks = this.links;
      int rRechts = this.rechts - 1;

      while (rLinks <= rRechts)
        if (pivot.compareTo(this.data[rLinks]) <= 0) {
          temp = this.data[rLinks];
          this.data[rLinks] = this.data[rRechts];
          this.data[rRechts] = temp;
          rRechts--;
        } else
          rLinks++;
      temp = this.data[rLinks];
      this.data[rLinks] = this.data[this.rechts];
      this.data[this.rechts] = temp;

      return rLinks;
    }

    /**
     * der Qicksort Algo.
     */
    public void qSort() {
      System.out.printf("Start sortiere(%d,%d) auf Thread: %s\n", this.links, this.rechts, Thread.currentThread());
      final int i = this.partition();
      Thread rechterTeil = null;
      if ((i + 1) < this.rechts) {
        rechterTeil = new Thread(new Worker<>(this.data, i + 1, this.rechts), String.format("sortiere(%d, %d)",
            this.links, this.rechts));
        rechterTeil.start(); // starte den "rechten" Thread
      }
      this.rechts = i - 1;
      if (this.links < this.rechts)
        this.qSort(); // starte den linken teil
      if (rechterTeil != null)
        try {
          rechterTeil.join();
        } catch (final InterruptedException ie) {
          ie.printStackTrace();
        }
      System.out.printf("Ende sortiere(%d,%d) auf Thread: %s\n", this.links, this.rechts, Thread.currentThread());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      this.qSort();
    }
  }

  /**
   * sortiert das Array data mit Quicksort Parallel
   * 
   * @param data
   */
  static <T extends Comparable<? super T>> void quicksort_parallel(final T[] data) {
    final QuicksortParallel qSort = new QuicksortParallel();
    qSort.new Worker<>(data, 0, data.length - 1).run();
  }

}

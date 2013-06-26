package blatt13.aufgabe74p;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
  /**
   * Main wie immer
   * 
   * @param args
   */
  public static <T extends Comparable<? super T>> void main(final String[] args) {
    // Integer[] data = readInts("data.csv");
    final Integer[] data = { 9, 6, 12, 343, 53, 23, -1, 99, 2354325, 234253, -1234, -1231, -453 };
    for (final Integer elem : data)
      System.out.printf("%d, ", elem);
    System.out.printf("\n");
    QuicksortParallel.quicksort_parallel(data);
    for (final Integer elem : data)
      System.out.printf("%d, ", elem);
  }

  /**
   * Liest eine csv Datei mit Integer Werten ein
   * 
   * @param path
   * @return
   */
  private static Integer[] readInts(final String path) {
    final LinkedList<Integer> list = new LinkedList<>();
    try {
      final Scanner fileSc = new Scanner(new File(path));
      final Pattern p = Pattern.compile("^(\\d+)$");
      Matcher m;
      String line;
      while (fileSc.hasNextLine()) {
        line = fileSc.nextLine();
        m = p.matcher(line);
        if (m.matches())
          list.addFirst(new Integer(m.group(1)));
        else
          System.err.printf("matching error with line: %s", line);
      }
      fileSc.close();
    } catch (final FileNotFoundException fnfe) {
      System.err.println(fnfe.getMessage());
      fnfe.printStackTrace();
    }

    final Integer[] data = new Integer[list.size()];
    for (int i = 0; i < data.length; i++)
      data[i] = list.pollFirst();

    return data;
  }
}

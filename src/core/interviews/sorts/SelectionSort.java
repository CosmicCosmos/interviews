package interviews.sorts;

import interviews.arrays.Swap;

import java.util.Comparator;
import java.util.List;

/**
 * Selection sort.
 * @author Francois Rousseau
 */
public class SelectionSort {
  public static <E> void f(List<E> list, Comparator<E> comparator) {
    for(int i = 0; i < list.size() - 1; i++) {  // the last element will be at its final place
      int index = i;
      for(int j = i+1; j < list.size(); j++) {  // find the index of the minimum
        if(comparator.compare(list.get(index), list.get(j)) > 0) {  // update the index of the min
          index = j;
        }
      }
      Swap.f(list, i, index);
    }
  }
}

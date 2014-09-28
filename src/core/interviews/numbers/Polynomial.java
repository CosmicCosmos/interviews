package interviews.numbers;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * Container class for polynomials.
 *
 * @author Francois Rousseau
 */
public class Polynomial {
  private final Map<Integer, Double> coefficients =
      new TreeMap<Integer, Double>(Collections.reverseOrder());

  public static Polynomial add(Polynomial p1, Polynomial p2) {
    Polynomial p = new Polynomial();
    p.putAll(p1);
    p.addAll(p2);
    return p;
  }

  public static Polynomial multiply(Polynomial p1, Polynomial p2) {
    Polynomial p = new Polynomial();

    for(Map.Entry<Integer, Double> e1 : p1.entrySet()) {
      for(Map.Entry<Integer, Double> e2 : p2.entrySet()) {
        p.add(e1.getKey() + e2.getKey(), e1.getValue() * e2.getValue());
      }
    }

    return p;
  }

  public static Polynomial subtract(Polynomial p1, Polynomial p2) {
    Polynomial p = new Polynomial();
    p.putAll(p1);
    p.subtractAll(p2);
    return p;
  }

  public Polynomial() {}

  public Polynomial(double[] coefficients) {
    for(int i = 0; i < coefficients.length; i++) {
      this.coefficients.put(i, coefficients[i]);
    }
  }

  public int degree() {
    if(coefficients.isEmpty()) {
      return 0;
    }
    return coefficients.keySet().iterator().next();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for(Map.Entry<Integer, Double> e : entrySet()) {
      if(e.getValue() > 0) {
        sb.append(" +");
      } else {
        sb.append(" ");
      }
      sb.append(String.format("%.2f x^%d", e.getValue(), e.getKey()));
    }
    return sb.toString();
  }


  private Double add(Integer power, Double coefficient) {
    double old_coefficient = 0;
    if(containsKey(power)) {
      old_coefficient = get(power);
    }
    if(old_coefficient + coefficient == 0) {
      remove(power);
      return 0D;
    } else {
      return put(power, old_coefficient + coefficient);
    }
  }

  private void addAll(Polynomial p) {
    addAll(p.coefficients);
  }

  private void addAll(Map<Integer, Double> m) {
    for(Map.Entry<Integer, Double> e : m.entrySet()) {
      add(e.getKey(), e.getValue());
    }
  }

  private boolean containsKey(Integer power) {
    return coefficients.containsKey(power);
  }

  private Double get(Integer power) {
    return coefficients.get(power);
  }

  private Set<Entry<Integer, Double>> entrySet() {
    return coefficients.entrySet();
  }

  private Double put(Integer power, Double coefficient) {
    return coefficients.put(power, coefficient);
  }

  private void putAll(Polynomial p) {
    putAll(p.coefficients);
  }

  private void putAll(Map<Integer, Double> m) {
    coefficients.putAll(m);
  }

  private Double remove(Integer power) {
    return coefficients.remove(power);
  }

  private Double subtract(Integer power, Double coefficient) {
    double old_coefficient = 0;
    if(containsKey(power)) {
      old_coefficient = get(power);
    }
    if(old_coefficient - coefficient == 0) {
      remove(power);
      return 0D;
    } else {
      return put(power, old_coefficient - coefficient);
    }
  }

  private void subtractAll(Polynomial p) {
    subtractAll(p.coefficients);
  }

  private void subtractAll(Map<Integer, Double> m) {
    for(Map.Entry<Integer, Double> e : m.entrySet()) {
      subtract(e.getKey(), e.getValue());
    }
  }
}

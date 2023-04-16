package a3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ComplexTest {
  private static double[] real = { 0.0, -2.5, 7.20000001 };
  private static double[] imag = { 0.0, -3.6, 8.1 };

  private static class Pair {
    public double first;
    public double second;

    public Pair(double first, double second) {
      this.first = first;
      this.second = second;
    }
  }

  private static Pair[] values;

  @Before
  public void setUp() throws Exception {
    ComplexTest.values =
      new Pair[ComplexTest.real.length * ComplexTest.imag.length];
    int i = 0;
    for (double re : ComplexTest.real) {
      for (double im : ComplexTest.imag) {
        ComplexTest.values[i++] = new Pair(re, im);
      }
    }
  }

  /**
   * Test for Complex()
   */
  @Test
  public void test01_noArgCtor() {
    Complex c = new Complex();
    assertEquals("real part is incorrect", 0.0, c.re(), Math.ulp(0));
    assertEquals("imaginary part is incorrect", 0.0, c.im(), Math.ulp(0));
  }

  /**
   * Test for Complex(double, double)
   */
  @Test
  public void test02_ctor() {
    for (Pair v : ComplexTest.values) {
      Complex c = new Complex(v.first, v.second);
      assertEquals(
        "real part is incorrect",
        v.first,
        c.re(),
        Math.ulp(v.first)
      );
      assertEquals(
        "imaginary part is incorrect",
        v.second,
        c.im(),
        Math.ulp(v.second)
      );
    }
  }

  /**
   * Test for Complex(Complex)
   */
  @Test
  public void test03_copyCtor() {
    for (Pair v : ComplexTest.values) {
      Complex c = new Complex(v.first, v.second);
      Complex copy = new Complex(c);
      assertEquals(
        "real part is incorrect",
        v.first,
        copy.re(),
        Math.ulp(v.first)
      );
      assertEquals(
        "imaginary part is incorrect",
        v.second,
        copy.im(),
        Math.ulp(v.second)
      );
    }
  }

  /**
   * Test for real(double re)
   */
  @Test
  public void test04_real() {
    for (double re : ComplexTest.real) {
      Complex c = Complex.real​(re);
      assertEquals("real part is incorrect", re, c.re(), Math.ulp(re));
      assertEquals("imaginary part is incorrect", 0.0, c.im(), Math.ulp(0.0));
    }
  }

  /**
   * Test for imag(double im)
   */
  @Test
  public void test04_imag() {
    for (double im : ComplexTest.imag) {
      Complex c = Complex.imag​(im);
      assertEquals("real part is incorrect", 0.0, c.re(), Math.ulp(0.0));
      assertEquals("imaginary part is incorrect", im, c.im(), Math.ulp(im));
    }
  }

  /**
   * Test for re(double val)
   */
  @Test
  public void test05_re() {
    final double IM = -1.5;
    Complex c = new Complex(0.0, IM);
    for (double val : ComplexTest.real) {
      c.re(val);
      assertEquals("real part is incorrect", val, c.re(), Math.ulp(0.0));
      assertEquals("imaginary part is incorrect", IM, c.im(), Math.ulp(val));
    }
  }

  /**
   * Test for im(double val)
   */
  @Test
  public void test06_im() {
    final double RE = -1.5;
    Complex c = new Complex(RE, 0.0);
    for (double val : ComplexTest.imag) {
      c.im(val);
      assertEquals("real part is incorrect", RE, c.re(), Math.ulp(0.0));
      assertEquals("imaginary part is incorrect", val, c.im(), Math.ulp(val));
    }
  }

  /**
   * Test for add(Complex)
   */
  @Test
  public void test07_add() {
    for (Pair v : ComplexTest.values) {
      Complex c = new Complex(v.first, v.second);
      for (Pair w : ComplexTest.values) {
        Complex d = new Complex(w.first, w.second);
        Complex sum = c.add​(d);

        // test the sum
        double exp = v.first + w.first;
        assertEquals(
          "real part of the sum is incorrect",
          sum.re(),
          exp,
          Math.ulp(exp)
        );
        exp = v.second + w.second;
        assertEquals(
          "imaginary part of the sum is incorrect",
          sum.im(),
          exp,
          Math.ulp(exp)
        );
      }
    }
  }

  /**
   * Test for multiply(Complex)
   */
  @Test
  public void test08_multiply() {
    for (Pair v : ComplexTest.values) {
      Complex c = new Complex(v.first, v.second);
      for (Pair w : ComplexTest.values) {
        Complex d = new Complex(w.first, w.second);
        Complex prod = c.multiply​(d);

        // test the product
        double exp = v.first * w.first - v.second * w.second;
        assertEquals(
          "real part of the product is incorrect",
          prod.re(),
          exp,
          Math.ulp(exp)
        );
        exp = v.first * w.second + v.second * w.first;
        assertEquals(
          "imaginary part of the sum is incorrect",
          prod.im(),
          exp,
          Math.ulp(exp)
        );
      }
    }
  }

  /**
   * Test for mag()
   */
  @Test
  public void test09_mag() {
    for (Pair v : ComplexTest.values) {
      Complex c = new Complex(v.first, v.second);
      double exp = Math.hypot(v.first, v.second);
      assertEquals("the magnitude is incorrect", exp, c.mag(), Math.ulp(exp));
    }

    // test a complex number with large real and imaginary components
    double big = Math.sqrt(Double.MAX_VALUE);
    Complex huge = new Complex(big, big);
    final double HYPOT = Math.hypot(big, big);
    assertEquals(
      "mag() overflowed! Look in java.lang.Math for a method to compute the magnitude",
      HYPOT,
      huge.mag(),
      Math.ulp(HYPOT)
    );
  }

  /**
   * Test for toString()
   */
  @Test
  public void test10_toString() {
    Complex c1 = new Complex();
    assertEquals(
      "toString returned the wrong string",
      "0.0 + 0.0i",
      c1.toString()
    );

    Complex c2 = new Complex(-1.23456789, 99.9999);
    assertEquals(
      "toString returned the wrong string",
      "-1.23456789 + 99.9999i",
      c2.toString()
    );

    Complex c3 = new Complex(1.0, -2.7300001);
    assertEquals(
      "toString returned the wrong string",
      "1.0 - 2.7300001i",
      c3.toString()
    );

    Complex c4 = new Complex(-1.0, -2.73);
    assertEquals(
      "toString returned the wrong string",
      "-1.0 - 2.73i",
      c4.toString()
    );
  }
}

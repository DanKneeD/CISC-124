import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;

@OrderWith(Alphanumeric.class)
public class TestSomeMethods {

  static Bit[] arrayOf(int... bits) {
    Bit[] t = new Bit[bits.length];
    for (int i = 0; i < bits.length; i++) {
      if (bits[i] == 0) {
        t[i] = Bit.ZERO;
      } else {
        t[i] = Bit.ONE;
      }
    }
    return t;
  }

  static List<Bit> listOf(int... bits) {
    List<Bit> t = new ArrayList<>();
    for (int b : bits) {
      if (b == 0) {
        t.add(Bit.ZERO);
      } else {
        t.add(Bit.ONE);
      }
    }
    return t;
  }

  @Test
  public void test01a_toString() {
    Declet d = new Declet();
    String exp = "0000000000";
    assertEquals(exp, d.toString());
  }

  @Test
  public void test01b_toString() {
    Declet d = new Declet(arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1));
    String exp = "0000000001";
    assertEquals(exp, d.toString());
  }

  @Test
  public void test01c_toString() {
    Declet d = new Declet(arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 0));
    String exp = "1111111110";
    assertEquals(exp, d.toString());
  }

  @Test
  public void test02a_toDecimal() {
    Declet d = new Declet();
    int exp = 0;
    assertEquals(exp, d.toDecimal());
  }

  @Test
  public void test02b_toDecimal() {
    Declet d = new Declet(arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1));
    int exp = 1;
    assertEquals(exp, d.toDecimal());
  }

  @Test
  public void test02c_toDecimal() {
    Declet d = new Declet(arrayOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 1));
    int exp = 511;
    assertEquals(exp, d.toDecimal());
  }

  @Test
  public void test02d_toDecimal() {
    Declet d = new Declet(arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 0));
    int exp = -2;
    assertEquals(exp, d.toDecimal());
  }

  @Test
  public void test02e_toDecimal() {
    Declet d = new Declet(arrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    int exp = -512;
    assertEquals(exp, d.toDecimal());
  }

  @Test
  public void test03a_isNegative() {
    Declet d = new Declet(arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1));
    assertFalse("0000000001 is not negative", d.isNegative());
  }

  @Test
  public void test03b_isNegative() {
    Declet d = new Declet(arrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1));
    assertTrue("1000000001 is negative", d.isNegative());
  }

  @Test
  public void test04a_addOne() {
    Declet d = new Declet(arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    d.addOne();
    int exp = 1;
    assertEquals("0000000000 + 1", exp, d.toDecimal());
  }

  @Test
  public void test04b_addOne() {
    Declet d = new Declet(arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1));
    d.addOne();
    int exp = 2;
    assertEquals("0000000001 + 1", exp, d.toDecimal());
  }

  @Test
  public void test04c_addOne() {
    Declet d = new Declet(arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 1));
    d.addOne();
    int exp = 4;
    assertEquals("0000000011 + 1", exp, d.toDecimal());
  }

  @Test
  public void test04d_addOne() {
    Declet d = new Declet(arrayOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 1));
    d.addOne();
    int exp = -512;
    assertEquals("0111111111 + 1", exp, d.toDecimal());
  }

  @Test
  public void test04e_addOne() {
    Declet d = new Declet(arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
    d.addOne();
    int exp = 0;
    assertEquals("1111111111 + 1", exp, d.toDecimal());
  }
}

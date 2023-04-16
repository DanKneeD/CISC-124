import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;

/**
 * A class that represents a signed 10-bit binary number represented using a
 * twos complement representation.
 *
 */
public class Declet implements Comparable<Declet> {
  List<Bit> declet = new ArrayList<>();

  static final int MAX_VALUE = 511;
  static final int MIN_VALUE = -512;
  static final int NUM_BITS = 10;

  /////////  Construcots   ////////////

  public Declet() {
    for (int x = 0; x < NUM_BITS; x++) {
      this.declet.add(Bit.ZERO);
    }
  }

  public Declet(int decimal) {
    this();
    int val = decimal;
    int index = NUM_BITS - 1;
    boolean negative = false;

    if (val > MAX_VALUE || val < MIN_VALUE) {
      throw new IllegalArgumentException();
    }

    if (val < 0) {
      val = -val;
      negative = true;
    }

    while (val != 0) {
      if (val % 2 == 0) {
        this.declet.set(index, Bit.ZERO);
      } else {
        this.declet.set(index, Bit.ONE);
      }

      val = val / 2;
      index = index - 1;
    }

    // Fill remaning bits with zer0s
    while (index >= 0) {
      this.declet.set(index, Bit.ZERO);
      index = index - 1;
    }

    if (negative) {
      this.not();
      this.addOne();
    }
  }

  public Declet(Bit... theBits) {
    if (theBits.length != NUM_BITS) {
      throw new IllegalArgumentException();
    }

    for (int x = 0; x < NUM_BITS; x++) {
      this.declet.add(x, theBits[x]);
    }
  }

  /////////  Methods   ////////////

  //check for overflow or something
  public void add(Declet other) {
    int carryBit = 0;
    int thisBit;
    int otherBit;

    for (int x = NUM_BITS - 1; x >= 0; x--) {
      thisBit = this.declet.get(x).value();
      otherBit = other.declet.get(x).value();

      //nothing changes if sum is 0 or 3
      if (carryBit + thisBit + otherBit == 2) {
        this.declet.set(x, Bit.ZERO);
        carryBit = 1;
      } else if (carryBit + thisBit + otherBit == 1) {
        this.declet.set(x, Bit.ONE);
        carryBit = 0;
      }
    }

    if (toDecimal() > MAX_VALUE || toDecimal() < MIN_VALUE) {
      throw new ArithmeticException("Overflow occurred when adding Declets");
    }
  }

  //clean up adn check for overflow or something
  public void addOne() {
    //value set to 1 to act as first bit
    int carryBit = 1;

    for (int x = NUM_BITS - 1; x >= 0; x--) {
      if (this.declet.get(x).value() == 1) {
        if (carryBit == 1) {
          this.declet.set(x, Bit.ZERO);
        } else {
          this.declet.set(x, Bit.ONE);
        }
      } else {
        if (carryBit == 1) {
          this.declet.set(x, Bit.ONE);
        } else {
          this.declet.set(x, Bit.ZERO);
        }
        carryBit = 0;
      }
    }
  }

  public int compareTo(Declet other) {
    if (this.toDecimal() > other.toDecimal()) {
      return 1;
    } else if (this.toDecimal() == other.toDecimal()) {
      return 0;
    } else {
      return -1;
    }
  }

  public boolean equals(Object obj) {
    if (obj.toString().equals(this.toString())) {
      return true;
    }

    return false;
  }

  public List<Bit> getBits() {
    List<Bit> decletCopy = new ArrayList<>();
    for (Bit bit : this.declet) {
      decletCopy.add(bit);
    }
    return decletCopy;
  }

  public int hashCode() {
    return toDecimal();
  }

  public boolean isNegative() {
    if (this.declet.get(0).value() == 1) {
      return true;
    }

    return false;
  }

  /**
   * Prints some sums illustrating overflow at {@code Decle.MAX_VALUE} and
   * {@code Decle.MIN_VALUE}.
   *
   * @param args not used
   */
  public static void main(String[] args) {
    Declet d = new Declet(Declet.MAX_VALUE - 2);
    Declet one = new Declet(1);

    System.out.println("Overflow at Declet.MAX_VALUE");
    for (int i = 0; i <= 4; i++) {
      System.out.println("d       " + d + " = " + d.toDecimal());
      System.out.println("      + " + one);
      d.addOne();
      System.out.println("d + 1 = " + d + " = " + d.toDecimal());
      System.out.println();
    }

    Declet negOne = new Declet(-1);

    System.out.println("Overflow at Declet.MIN_VALUE");
    for (int i = 0; i <= 4; i++) {
      System.out.println("d     = " + d + " = " + d.toDecimal());
      System.out.println("      + " + negOne);
      d.add(negOne);
      System.out.println("d - 1 = " + d + " = " + d.toDecimal());
      System.out.println();
    }
  }

  public void not() {
    for (int x = 0; x < NUM_BITS; x++) {
      this.declet.set(x, this.declet.get(x).not());
    }
  }

  public int toDecimal() {
    int base10;
    int bitValue;
    int topIndex = NUM_BITS - 1;

    // first signed bit
    bitValue = this.declet.get(0).value();
    base10 = bitValue * -(int) (Math.pow(2, topIndex));

    // rest of bits in the declet
    for (int x = 1; x < NUM_BITS; x++) {
      bitValue = this.declet.get(x).value();
      base10 += bitValue * (int) (Math.pow(2, topIndex - x));
    }

    return base10;
  }

  public String toString() {
    String decletString = new String();

    for (int x = 0; x < NUM_BITS; x++) {
      decletString += this.declet.get(x).value();
    }

    return decletString;
  }
}

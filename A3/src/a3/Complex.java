package a3;

/*A class that represents complex numbers. 
In the complex number (a + bi) the real value a is called the real part of the complex number and
the real value b is called the imaginary part of the complex number. 
This class provides methods to perform addition and multiplication of complex numbers.
 */

public final class Complex {
  private double realPart;
  private double imagPart;

  /*Initializes this complex number to (0 + 0i).*/
  Complex() {
    realPart = 0.0;
    imagPart = 0.0;
  }

  /*Initializes this complex number so that it has the same real and imaginary parts as another complex number.
   *
   * @param other the complex number to copy
   */
  Complex(Complex other) {
    realPart = other.re();
    imagPart = other.im();
  }

  /*Initializes this complex number so that it has the given real and imaginary components.
   *
   * @param re the real part of the complex number
   * @param im the imaginary part of the complex number
   */
  Complex(double re, double im) {
    realPart = re;
    imagPart = im;
  }

  public void re(double val) {
    realPart = val;
  }

  public double re() {
    return realPart;
  }

  public static Complex real​(double re) {
    Complex newComplex = new Complex(re, 0.0);
    return newComplex;
  }

  public double im() {
    return imagPart;
  }

  public void im(double val) {
    imagPart = val;
  }

  public static Complex imag​(double im) {
    Complex newComplex = new Complex(0.0, im);
    return newComplex;
  }

  public Complex add​(Complex c) {
    double newRealPart = c.re() + realPart;
    double newImagPart = c.im() + imagPart;

    Complex newComplex = new Complex(newRealPart, newImagPart);
    return newComplex;
  }

  /*Multiplies this complex number with another complex number to obtain a new complex number.
   *Neither this complex number nor c is changed by this method.
   *This is not an industrial strength implementation of complex number multiplication.
   *In particular, issues related to the differences between -0.0 and 0.0 and infinite real or imaginary parts are not taken into account.
   *
   * @param c The complex number to multiply by
   * @return a new complex number equal to this complex number multiplied by c
   */

  public Complex multiply​(Complex c) {
    double newRealPart = ((realPart * c.re()) - (imagPart * c.im()));
    double newImagPart = ((imagPart * c.re()) + (realPart * c.im()));

    Complex newComplex = new Complex(newRealPart, newImagPart);
    return newComplex;
  }

  public double mag() {
    return Math.hypot(realPart, imagPart);
  }

  public String toString() {
    String newString;

    if (imagPart < 0.0) {
      newString = realPart + " - " + Math.abs(imagPart) + "i";
    } else {
      newString = (realPart + " + " + imagPart + "i");
    }

    return newString;
  }
}

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;

@OrderWith(Alphanumeric.class)
public class TestConstructors {

	static List<Bit> listOf(int... bits) {
		List<Bit> t = new ArrayList<>();
		for (int b : bits) {
			if (b == 0) {
				t.add(Bit.ZERO);
			}
			else {
				t.add(Bit.ONE);
			}
		}
		return t;
	}
	
	@Test
	public void test01_noArgCtor() {
		Declet d = new Declet();
		List<Bit> exp = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		assertEquals(exp, d.getBits());
	}

	@Test
	public void test02_ctorWithBits() {
		List<Bit> exp = listOf(0, 1, 0, 1, 0, 1, 0, 1, 1, 1);
		Bit[] bits = new Bit[exp.size()];
		Declet d = new Declet(exp.toArray(bits));
		assertEquals(exp, d.getBits());
	}
	
	@Test
	public void test03a_ctorDecimal() {
		List<Bit> exp = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		Declet d = new Declet(0);
		assertEquals(exp, d.getBits());
	}
	
	@Test
	public void test03b_ctorDecimal() {
		List<Bit> exp = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
		Declet d = new Declet(1);
		assertEquals(exp, d.getBits());
	}
	
	@Test
	public void test03c_ctorDecimal() {
		List<Bit> exp = listOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		Declet d = new Declet(511);
		assertEquals(exp, d.getBits());
	}
	
	@Test
	public void test03d_ctorDecimal() {
		List<Bit> exp = listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		Declet d = new Declet(-1);
		assertEquals(exp, d.getBits());
	}
	
	@Test
	public void test03e_ctorDecimal() {
		List<Bit> exp = listOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		Declet d = new Declet(-512);
		assertEquals(exp, d.getBits());
	}
}

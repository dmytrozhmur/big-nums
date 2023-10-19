package org.example;

import junit.framework.TestCase;
import org.example.nums.BigNum;

import java.util.ArrayList;
import java.util.List;

public class BigNumTest extends TestCase {
    public void testEmptyGetHex() {
        BigNum bigNum = new BigNum();

        assertEquals("", bigNum.getHex());
    }

    public void testSetHex() {
        String testHex = "403db8ad88a3932a0b7e8189aed9eeffb8121dfac05c3512fdb396dd73f6331c";
        ArrayList<Integer> expectedIntegers = new ArrayList<>(List.of(
                Integer.parseUnsignedInt("1945514780"),
                Integer.parseUnsignedInt("4256405213"),
                Integer.parseUnsignedInt("3227268370"),
                Integer.parseUnsignedInt("3088195066"),
                Integer.parseUnsignedInt("2933518079"),
                Integer.parseUnsignedInt("192840073"),
                Integer.parseUnsignedInt("2292421418"),
                Integer.parseUnsignedInt("1077786797"))
        );

        BigNum bigNum = new BigNum();
        bigNum.setHex(testHex);

        assertEquals(testHex.toUpperCase(), bigNum.getHex());
        assertEquals(expectedIntegers, bigNum.getIntegers());
    }

    public void testSetIntegers() {
        ArrayList<Integer> testIntegers = new ArrayList<>(List.of(
                Integer.parseUnsignedInt("3841"),
                Integer.parseUnsignedInt("4294967295")
        ));
        String expectedHex = "FFFFFFFF00000F01";

        BigNum bigNum = new BigNum();
        bigNum.setIntegers(testIntegers);

        assertEquals(testIntegers, bigNum.getIntegers());
        assertEquals(expectedHex.toUpperCase(), bigNum.getHex());
    }
}

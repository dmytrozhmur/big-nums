package org.example;

import junit.framework.TestCase;
import org.example.nums.BigNum;

import static org.example.operations.Operation.*;

public class OperationTest extends TestCase {
    public void testAddOperation() {
        BigNum firstNum = new BigNum();
        BigNum secondNum = new BigNum();
        String expectedHexResult = "a78865c13b14ae4e25e90771b54963ee2d68c0a64d4a8ba7c6f45ee0e9daa65b";

        firstNum.setHex("36f028580bb02cc8272a9a020f4200e346e276ae664e45ee80745574e2f5ab80");
        secondNum.setHex("70983d692f648185febe6d6fa607630ae68649f7e6fc45b94680096c06e4fadb");
        BigNum result = ADD.apply(firstNum, secondNum);

        assertEquals(expectedHexResult.toUpperCase(), result.getHex());
    }

    public void testSubOperation() {
        BigNum firstNum = new BigNum();
        BigNum secondNum = new BigNum();
        String expectedHexResult = "10e570324e6ffdbc6b9c813dec968d9bad134bc0dbb061530934f4e59c2700b9";

        firstNum.setHex("33ced2c76b26cae94e162c4c0d2c0ff7c13094b0185a3c122e732d5ba77efebc");
        secondNum.setHex("22e962951cb6cd2ce279ab0e2095825c141d48ef3ca9dabf253e38760b57fe03");
        BigNum result = SUB.apply(firstNum, secondNum);

        assertEquals(expectedHexResult.toUpperCase(), result.getHex());
    }

    public void testModOperation() {
        BigNum firstNum = new BigNum();
        BigNum secondNum = new BigNum();
        String expectedHexResult = "2468ac8a";

        firstNum.setHex("10000000ABCDEFAB");
        secondNum.setHex("1000000087654321");
        BigNum result = MOD.apply(firstNum, secondNum);

        assertEquals(expectedHexResult.toUpperCase(), result.getHex());
    }
}

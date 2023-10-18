package org.example.nums;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

import static org.example.constant.Constants.*;

public class BigNum {
    private final ArrayList<Integer> integers = new ArrayList<>();
    private String hex;

    public ArrayList<Integer> getIntegers() {
        ArrayList<Integer> integersCopy = new ArrayList<>();
        Collections.copy(integersCopy, integers);
        return integersCopy;
    }

    public void setIntegers(ArrayList<Integer> integers) {
        this.integers.clear();
        this.hex = "";

        this.integers.addAll(integers);
        this.hex = parseIntegers(integers);
    }

    private String parseIntegers(ArrayList<Integer> integers) {
        StringBuilder newHex = new StringBuilder();
        for (int i = 0; i < integers.size(); i++) {
            newHex.insert(0, integerToHex(integers.get(i), i));
        }
        return newHex.toString();
    }

    private String integerToHex(Integer integer, Integer index) {
        StringBuilder result = new StringBuilder();
        Long unsignedInteger = Integer.toUnsignedLong(integer) + MAX_UNSIGNED_INTEGER * index;
        while (unsignedInteger > 0) {
            int remainderInt = (int) (unsignedInteger % HEX_DIVIDER);
            char remainderValue = HEX_DICTIONARY[remainderInt];
            result.insert(0, remainderValue);
            unsignedInteger /= HEX_DIVIDER;
        }
        return result.toString();
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}

package org.example.nums;

import org.example.converters.BinaryHexDictionary;

import java.util.*;


public class BigNum {
    private final ArrayList<Integer> integersArray = new ArrayList<>();
    private ArrayList<String> binaryArray = new ArrayList<>();

    public List<Integer> getIntegers() {
        return List.copyOf(integersArray);
    }

    public void setIntegers(ArrayList<Integer> integers) {
        this.integersArray.clear();

        this.integersArray.addAll(integers);
        this.binaryArray = parseIntegers(integers);
    }

    private ArrayList<String> parseIntegers(ArrayList<Integer> integers) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < integers.size(); i++) {
            String binaryString = Integer.toBinaryString(integers.get(i));
            ArrayList<String> dividedBinaryString = divideBinaryString(binaryString);
            result.addAll(dividedBinaryString);
        }
        return result;
    }

    private ArrayList<String> divideBinaryString(String binaryString) {
        int blockSize = 4;
        int blocksQuantity = 32 / blockSize;
        int bitsQuantity = binaryString.length();
        ArrayList<String> result = new ArrayList<>();
        for (int i = bitsQuantity; i > 0 ; i -= blockSize) {
            String binaryBlock = i < blockSize
                    ? "0".repeat(blockSize - i).concat(binaryString.substring(0, i))
                    : binaryString.substring(i - blockSize, i);
            result.add(binaryBlock);
        }
        result.addAll(Collections.nCopies(blocksQuantity - result.size(), "0000"));
        return result;
    }

    public String getHex() {
        StringBuilder hex = new StringBuilder();

        for (int i = 0; i < binaryArray.size(); i++) {
            Character hexaSymbol = BinaryHexDictionary.binaryToHex(binaryArray.get(i));
            hex.insert(0, hexaSymbol);
        }
        int startPosition = 0;
        while (startPosition < hex.length() && hex.charAt(startPosition) == '0') startPosition++;

        return hex.substring(startPosition);
    }

    public void setHex(String hex) {
        this.integersArray.clear();

        ArrayList<Integer> integers = parseHex(hex.toUpperCase());
        this.integersArray.addAll(integers);
        this.binaryArray = parseIntegers(integers);
    }

    private ArrayList<Integer> parseHex(String hex) {
        ArrayList<Integer> result = new ArrayList<>();
        int blockSize = 8;
        int hexLength = hex.length();
        for (int i = hexLength; i > 0; i -= blockSize) {
            String hexBlock = i < blockSize
                    ? hex.substring(0, i)
                    : hex.substring(i - blockSize, i);
            result.add(Integer.parseUnsignedInt(String.valueOf(Long.parseLong(hexBlock, 16))));
        }
        return result;
    }
}

package org.example.converters;

import java.util.Map;

public class BinaryHexDictionary {
    private final static Map<String, Character> binaryToHexMap = Map.ofEntries(
            Map.entry("0000", '0'), Map.entry("0001", '1'),
            Map.entry("0010", '2'), Map.entry("0011", '3'),
            Map.entry("0100", '4'), Map.entry("0101", '5'),
            Map.entry("0110", '6'), Map.entry("0111", '7'),
            Map.entry("1000", '8'), Map.entry("1001", '9'),
            Map.entry("1010", 'A'), Map.entry("1011", 'B'),
            Map.entry("1100", 'C'), Map.entry("1101", 'D'),
            Map.entry("1110", 'E'), Map.entry("1111", 'F'));

    public static Character binaryToHex(String binaryString) {
        String fourBits;
        if (binaryString.trim().length() < 4) {
            fourBits = "0".repeat(4 - binaryString.length()).concat(binaryString);
        }
        else {
            fourBits = binaryString.trim().substring(0, 4);
        }

        return binaryToHexMap.get(fourBits);
    }
}

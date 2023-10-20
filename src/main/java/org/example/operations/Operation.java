package org.example.operations;

import org.example.nums.BigNum;

import java.util.ArrayList;
import java.util.List;

public enum Operation {
    ADD {
        @Override
        public BigNum apply(BigNum firstNum, BigNum secondNum) {
            BigNum result = new BigNum();
            List<Integer> firstIntegers = firstNum.getIntegers();
            List<Integer> secondIntegers = secondNum.getIntegers();
            ArrayList<Integer> resultIntegers = new ArrayList<>();
            Integer radix = 0;
            Long carriedNum = 0L;

            while (radix < firstIntegers.size() || radix < secondIntegers.size()) {
                Long firstUnsignedInt = radix < firstIntegers.size()
                        ? Integer.toUnsignedLong(firstIntegers.get(radix))
                        : 0;
                Long secondUnsignedInt = radix < secondIntegers.size()
                        ? Integer.toUnsignedLong(secondIntegers.get(radix))
                        : 0;

                Long columnSum = firstUnsignedInt + secondUnsignedInt + carriedNum;
                if (columnSum > MAX_UNSIGNED_INTEGER) {
                    carriedNum = 1L;
                    columnSum %= (MAX_UNSIGNED_INTEGER + 1);
                } else {
                    carriedNum = 0L;
                }

                resultIntegers.add(Integer.parseUnsignedInt(String.valueOf(columnSum)));
                radix++;
            }
            if (carriedNum > 0L) {
                resultIntegers.add(Integer.parseUnsignedInt(String.valueOf(carriedNum)));
            }

            result.setIntegers(resultIntegers);
            return result;
        }
    },
    SUB {
        @Override
        public BigNum apply(BigNum firstNum, BigNum secondNum) {
            BigNum result = new BigNum();
            List<Integer> firstIntegers = firstNum.getIntegers();
            List<Integer> secondIntegers = secondNum.getIntegers();
            ArrayList<Integer> resultIntegers = new ArrayList<>();
            Integer radix = 0;
            Long borrowedNum = 0L;

            while (radix < firstIntegers.size() || radix < secondIntegers.size()) {
                Long firstUnsignedInt = (radix < firstIntegers.size()
                        ? Integer.toUnsignedLong(firstIntegers.get(radix))
                        : 0) - borrowedNum;
                Long secondUnsignedInt = radix < secondIntegers.size()
                        ? Integer.toUnsignedLong(secondIntegers.get(radix))
                        : 0;

                if (firstUnsignedInt < secondUnsignedInt) {
                    if (radix >= firstIntegers.size() - 1) {
                        throw new IllegalArgumentException("Second number cannot be greater than first.");
                    }
                    borrowedNum = 1L;
                    firstUnsignedInt += (MAX_UNSIGNED_INTEGER + 1);
                } else {
                    borrowedNum = 0L;
                }
                Long columnSub = firstUnsignedInt - secondUnsignedInt;

                resultIntegers.add(Integer.parseUnsignedInt(String.valueOf(columnSub)));
                radix++;
            }

            result.setIntegers(resultIntegers);
            return result;
        }
    },
    MOD {
        @Override
        public BigNum apply(BigNum firstNum, BigNum secondNum) {
            BigNum currentResult = new BigNum();
            currentResult.setIntegers(new ArrayList<>(firstNum.getIntegers()));

            while (true) {
                try {
                    currentResult = SUB.apply(currentResult, secondNum);
                } catch (IllegalArgumentException iae) {
                    break;
                }
            }

            return currentResult;
        }
    },
    EXIT;

    private static final Long MAX_UNSIGNED_INTEGER = Integer.toUnsignedLong(-1);

    public BigNum apply(BigNum firstNum, BigNum secondNum) {
        throw new UnsupportedOperationException();
    }
}

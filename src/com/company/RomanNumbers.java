package com.company;

public enum RomanNumbers {
    I,
    V,
    X,
    L,
    C;

    static int convertRomanToArabic(String romanNumber) {
        int hundredsBegin = -1, decadesBegin = -1, unitsBegin = -1;
        char[] charArray = romanNumber.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if ((c == 'C' || c == 'D') && hundredsBegin == -1) {
                hundredsBegin = i;
            }
            if ((c == 'X' || c == 'L') && decadesBegin == -1) {
                decadesBegin = i;
            }
            if (c == 'I' || c == 'V') {
                unitsBegin = i;
                break;
            }
        }
        int hundreds = 0;
        if (hundredsBegin != -1) {
            String romanHundreds = romanNumber.substring(hundredsBegin, decadesBegin != -1 ? decadesBegin: unitsBegin);
            if (romanHundreds.endsWith("C")) {
                if (romanHundreds.charAt(0) == 'D') {
                    hundreds = 5 + romanHundreds.length() - 1;
                } else {
                    hundreds = romanHundreds.length();
                }
            } else if (romanHundreds.equals("CD")) {
                hundreds = 4;
            } else if (romanHundreds.equals("D")) {
                hundreds = 5;
            } else if (romanHundreds.equals("CM")) {
                hundreds = 9;
            }
        }
        int decades = 0;
        if (decadesBegin != -1) {
            String romanDecades = romanNumber.substring(decadesBegin, unitsBegin != -1 ? unitsBegin: romanNumber.length());
            if (romanDecades.endsWith("X")) {
                if (romanDecades.charAt(0) == 'L') {
                    decades = 5 + romanDecades.length() - 1;
                } else {
                    decades = romanDecades.length();
                }
            } else if (romanDecades.equals("XL")) {
                decades = 4;
            } else if (romanDecades.equals("L")) {
                decades = 5;
            } else if (romanDecades.equals("XC")) {
                decades = 9;
            }
        }
        int units = 0;
        if (unitsBegin != -1) {
            String romanUnits = romanNumber.substring(unitsBegin);
            if (romanUnits.endsWith("I")) {
                if (romanUnits.charAt(0) == 'V') {
                    units = 5 + romanUnits.length() - 1;
                } else {
                    units = romanUnits.length();
                }
            } else if (romanUnits.equals("IV")) {
                units = 4;
            } else if (romanUnits.equals("V")) {
                units = 5;
            } else if (romanUnits.equals("IX")) {
                units = 9;
            }
        }
        return 100*hundreds+10*decades+units;
    }

    static String convertArabicToRoman(int arabicNumber) {
        int units = arabicNumber%10;
        int decades = (arabicNumber - arabicNumber/100*100)/10;
        int hundreds = arabicNumber/100;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i<hundreds; i++) {
            result.append("C");
        }

        if (decades>=1 && decades<=3) {
            for (int i = 0; i<decades; i++) {
                result.append("X");
            }
        } else if (decades == 4) {
            result.append("XL");
        } else if (decades >= 5 && decades<=8) {
            result.append("L");
            for (int i = 0; i<decades - 5; i++) {
                result.append("X");
            }
        } else if (decades == 9) {
            result.append("LC");
        }

        if (units>=1 && units<=3) {
            for (int i = 0; i<decades; i++) {
                result.append("I");
            }
        } else if (units == 4) {
            result.append("IV");
        } else if (units >= 5 && units<=8) {
            result.append("V");
            for (int i = 0; i<units - 5; i++) {
                result.append("I");
            }
        } else if (units == 9) {
            result.append("IX");
        }
        return result.toString();
    }
}

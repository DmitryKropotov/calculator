package com.company;

public class ArabicRomanNumberConvertor {

    public int convertRomanToArabic(String romanNumber) {
        int hundredsBegin = -1, decadesBegin = -1, unitsBegin = -1;
        char[] charArray = romanNumber.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if ((c == 'C' || c == 'D') && hundredsBegin == -1 && decadesBegin == -1) {
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
            String[] allowedSigns = {"C", "D", "M"};
            hundreds = calculateNumbersInDischarge(romanNumber.substring(hundredsBegin, decadesBegin != -1 ? decadesBegin: unitsBegin), allowedSigns);
        }
        int decades = 0;
        if (decadesBegin != -1) {
            String[] allowedSigns = {"X", "L", "C"};
            decades = calculateNumbersInDischarge(romanNumber.substring(decadesBegin, unitsBegin != -1 ? unitsBegin: romanNumber.length()), allowedSigns);
        }
        int units = 0;
        if (unitsBegin != -1) {
            String[] allowedSigns = {"I", "V", "X"};
            units = calculateNumbersInDischarge(romanNumber.substring(unitsBegin), allowedSigns);
        }
        return 100*hundreds+10*decades+units;
    }

    private int calculateNumbersInDischarge(String number, String[] allowedSigns) {
        int result = 0;
        if (number.endsWith(allowedSigns[0])) {
            if (number.substring(0, 1).equals(allowedSigns[1])) {
                result = 5 + number.length() - 1;
            } else {
                result = number.length();
            }
        } else if (number.equals(allowedSigns[0]+allowedSigns[1])) {
            result = 4;
        } else if (number.equals(allowedSigns[1])) {
            result = 5;
        } else if (number.equals(allowedSigns[0]+allowedSigns[2])) {
            result = 9;
        }
        return result;
    }

    public String convertArabicToRoman(int arabicNumber) {
        int units = arabicNumber%10;
        int decades = (arabicNumber - arabicNumber/100*100)/10;
        int hundreds = arabicNumber/100;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i<hundreds; i++) {
            result.append("C");
        }

        String[] arabicSignsForDecades = {"X", "L", "C"};
        result.append(transformDischargeToArabic(decades, arabicSignsForDecades));

        String[] arabicSignsForUnits = {"I", "V", "X"};
        result.append(transformDischargeToArabic(units, arabicSignsForUnits));

        return result.toString();
    }

    private String transformDischargeToArabic(int discharge, String[] arabicSigns) {
        StringBuilder result = new StringBuilder();
        if (discharge>=1 && discharge<=3) {
            for (int i = 0;  i < discharge; i++) {
                result.append(arabicSigns[0]);
            }
        } else if (discharge == 4) {
            result.append(arabicSigns[0]+arabicSigns[1]);
        } else if (discharge >= 5 && discharge <= 8) {
            result.append(arabicSigns[1]);
            for (int i = 0; i < discharge - 5; i++) {
                result.append(arabicSigns[0]);
            }
        } else if (discharge == 9) {
            result.append(arabicSigns[0]+arabicSigns[2]);
        }
        return result.toString();
    }
}

/*
 * Decompiled with CFR 0.152.
 */
package funktionen;

public class Aktionen {
    public String umwandeln(String eingabe, int art) {
        String ausgabe = "";
        int laenge = eingabe.length();
        if (art == 1) {
            int i = 0;
            while (i < laenge) {
                char aktZeichen = eingabe.toLowerCase().charAt(i);
                char neuesZeichen = '\u0000';
                switch (aktZeichen) {
                    case 'a': {
                        neuesZeichen = '4';
                        break;
                    }
                    case 'b': {
                        neuesZeichen = '8';
                        break;
                    }
                    case 'e': {
                        neuesZeichen = '3';
                        break;
                    }
                    case 'g': {
                        neuesZeichen = '9';
                        break;
                    }
                    case 'i': {
                        neuesZeichen = '1';
                        break;
                    }
                    case 'o': {
                        neuesZeichen = '0';
                        break;
                    }
                    case 's': {
                        neuesZeichen = '5';
                        break;
                    }
                    case 't': {
                        neuesZeichen = '7';
                        break;
                    }
                    case 'z': {
                        neuesZeichen = '2';
                        break;
                    }
                    default: {
                        neuesZeichen = aktZeichen;
                    }
                }
                ausgabe = String.valueOf(ausgabe) + neuesZeichen;
                ++i;
            }
        } else if (art == 2) {
            String neuesZeichen = "";
            int i = 0;
            while (i < laenge) {
                char aktZeichen = eingabe.toLowerCase().charAt(i);
                switch (aktZeichen) {
                    case 'a': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "4";
                        break;
                    }
                    case 'b': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "l3";
                        break;
                    }
                    case 'c': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "\u00a9";
                        break;
                    }
                    case 'd': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "l)";
                        break;
                    }
                    case 'e': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "3";
                        break;
                    }
                    case 'f': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "|#";
                        break;
                    }
                    case 'g': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "9";
                        break;
                    }
                    case 'h': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "/-/";
                        break;
                    }
                    case 'i': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "1";
                        break;
                    }
                    case 'j': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "j";
                        break;
                    }
                    case 'k': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "]{";
                        break;
                    }
                    case 'l': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "|_";
                        break;
                    }
                    case 'm': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "/V\\";
                        break;
                    }
                    case 'n': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "/\\/";
                        break;
                    }
                    case 'o': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "0";
                        break;
                    }
                    case 'p': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "|>";
                        break;
                    }
                    case 'q': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "<|";
                        break;
                    }
                    case 'r': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "\u00ae";
                        break;
                    }
                    case 's': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "5";
                        break;
                    }
                    case 't': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "7";
                        break;
                    }
                    case 'u': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "/_/";
                        break;
                    }
                    case 'v': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "\\/";
                        break;
                    }
                    case 'w': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "\\/\\/";
                        break;
                    }
                    case 'x': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "><";
                        break;
                    }
                    case 'y': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "\u00a5";
                        break;
                    }
                    case 'z': {
                        neuesZeichen = String.valueOf(neuesZeichen) + "2";
                        break;
                    }
                    default: {
                        neuesZeichen = String.valueOf(neuesZeichen) + aktZeichen;
                    }
                }
                ++i;
            }
            ausgabe = neuesZeichen;
        }
        return ausgabe;
    }
}


package funktionen;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

public class Pruefung {

	public static void main(String[] args) {
		if (pInt("1")) {
			System.out.println("toll1");
		}
		if (!pInt("eins")) {
			System.out.println("toll2");
		}

		if (pDate("1990-01-02")) {
			System.out.println("toller1");
		}
		if (!pDate("eins")) {
			System.out.println("toller2");
		}

		if (pTele("4355625656565656")) {
			System.out.println("muha1");
		}
		if (!pTele("e56546456456456s")) {
			System.out.println("muha2");
		}

		if (pStr("1234 , . - _ sophia sophia")) {
			System.out.println("ganz toll1");
		}
		if (!pStr("insERt * sophia sophia")) {
			System.out.println("ganz toll2");
		}
		if (!pStr("sophia;")) {
			System.out.println("ganz toll3");
		}

		if (perso("lftm0<<<<>>>>>5mmg6900424117030988") != null) {
			System.out.println("TOLL");
		} else {
			System.out.println(":(");
		}
		if (perso("9704631642920117614010280") != null) {
			System.out.println("TOLL");
		} else {
			System.out.println(":(");
		}

	}

	public static boolean pDate(String s) {
		try {
			Date.valueOf(s);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	public static String perso(String s) {
		ArrayList<Integer> ein = new ArrayList<Integer>();
		ArrayList<Integer> aus = new ArrayList<Integer>();
		String rturn = "";
		s = s.toUpperCase();

		for (int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			if (Character.isDigit(c)) {
				rturn += c;
				ein.add(Integer.parseInt(c.toString()));
			} else if ((c >= 'A') && (c <= 'Z')) {
				rturn += c;
				ein.add(c - 55);
			}

		}
		if (ein.size() != 25)
			return null;
		for (int i = 0; i < 10; i++) {
			if (i == 9) {
				Integer sum = 0;
				for (int j = 0; j < 9; j++) {
					sum += aus.get(j);
				}
				sum %= 10;
				if (ein.get(i) != sum)
					return null;
				aus.add(sum);
			} else {
				switch (i % 3) {
				case 0:
					aus.add((ein.get(i) * 7) % 10);
					break;
				case 1:
					aus.add((ein.get(i) * 3) % 10);
					break;
				case 2:
					aus.add((ein.get(i) * 1) % 10);
					break;
				}
			}
		}
		for (int i = 10; i < 17; i++) {
			if (i == 16) {
				Integer sum = 0;
				for (int j = 10; j < 16; j++) {
					sum += aus.get(j);
				}
				sum %= 10;
				if (ein.get(i) != sum)
					return null;
				aus.add(sum);
			} else {
				switch (i % 3) {
				case 0:
					aus.add((ein.get(i) * 1) % 10);
					break;
				case 1:
					aus.add((ein.get(i) * 7) % 10);
					break;
				case 2:
					aus.add((ein.get(i) * 3) % 10);
					break;
				}
			}

		}
		for (int i = 17; i < 24; i++) {
			if (i == 23) {
				Integer sum = 0;
				for (int j = 17; j < 23; j++) {
					sum += aus.get(j);
				}
				sum %= 10;
				if (ein.get(i) != sum)
					return null;
				aus.add(sum);
			} else {
				switch (i % 3) {
				case 0:
					aus.add((ein.get(i) * 3) % 10);
					break;
				case 1:
					aus.add((ein.get(i) * 1) % 10);
					break;
				case 2:
					aus.add((ein.get(i) * 7) % 10);
					break;
				}
			}

		}
		Integer endpz = 0;
		for (int i = 0; i < 24; i++) {
			switch (i % 3) {
			case 0:
				endpz += (ein.get(i) * 7) % 10;
				break;
			case 1:
				endpz += (ein.get(i) * 3) % 10;
				break;
			case 2:
				endpz += (ein.get(i) * 1) % 10;
				break;
			}
		}
		if ((endpz % 10) != ein.get(24))
			return null;

		return rturn;
	}

	public static boolean pInt(String s) {
		try {
			Integer.valueOf(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;

	}

	public static boolean pStr(String s) {
		ArrayList<String> zeichen = new ArrayList<String>();
		zeichen.add(";");
		zeichen.add("'");
		zeichen.add("\"");
		zeichen.add("SELECT ");
		zeichen.add("DROP ");
		zeichen.add("DELETE ");
		zeichen.add("INSERT ");

		for (String i : zeichen) {
			if (s.toLowerCase().contains(i.toLowerCase()))
				return false;
		}
		return true;
	}

	public static boolean pTele(String s) {
		try {
			new BigDecimal(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}

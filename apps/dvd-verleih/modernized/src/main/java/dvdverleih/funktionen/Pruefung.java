package dvdverleih.funktionen;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Pruefung {

	public static boolean pDate(String s) {
		try {
			java.time.LocalDate.parse(s);
		} catch (java.time.format.DateTimeParseException e) {
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
				if (!ein.get(i).equals(sum))
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
				if (!ein.get(i).equals(sum))
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
				if (!ein.get(i).equals(sum))
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

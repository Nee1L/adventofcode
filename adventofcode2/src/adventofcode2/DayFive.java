package adventofcode2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DayFive {

	public static int partTwo(HashMap<String, ArrayList<String>> values, String[] input) {
		int page = 0;
		boolean updated = false;
		for (int i = 0; i < input.length - 1; i++) {
			if (values.get(input[i]) == null) {
				values.put(input[i], new ArrayList<>());
			}
			if (values.get(input[i + 1]) == null) {
				values.put(input[i + 1], new ArrayList<>());
			}
			for (int j = i + 1; j < input.length; j++) {
				if (!values.get(input[i]).contains(input[j])) {
					String buff = input[i];
					input[i] = input[j];
					input[j] = buff;
					i = 0;
					updated = true;
				}
			}
//			if (values.get(input[i]) < values.get(input[i + 1])) {
//				return 0;
//			}
			if ((i == input.length / 2) && updated) {
				page = Integer.parseInt(input[i]);
			}
		}
		System.out.println(page);
		return page;
	}

	public static int countPages(HashMap<String, ArrayList<String>> values, String[] input) {
		int page = 0;
		for (int i = 0; i < input.length - 1; i++) {
			if (values.get(input[i]) == null) {
				values.put(input[i], new ArrayList<>());
			}
			if (values.get(input[i + 1]) == null) {
				values.put(input[i + 1], new ArrayList<>());
			}
			for (int j = i + 1; j < input.length; j++) {
				if (!values.get(input[i]).contains(input[j])) {
					return 0;
				}
			}
//			if (values.get(input[i]) < values.get(input[i + 1])) {
//				return 0;
//			}
			if (i == input.length / 2) {
				page = Integer.parseInt(input[i]);
			}
		}
		System.out.println(page);
		return page;
	}

	public static void readFile() {
		int res = 0;
		HashMap<String, ArrayList<String>> values = new HashMap<>();
		try {
			File file = new File("D:\\workspaces\\adventofcode2\\adventofcode2\\src\\adventofcode2\\dayfive.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				System.out.println(data);
				if (data.contains("|")) {
					String[] bebra = data.split("\\|");
					if (values.get(bebra[0]) == null) {
						values.put(bebra[0], new ArrayList<>());
					}
					values.get(bebra[0]).add(bebra[1]);
				} else {
					String[] order = data.split(",");
					res += partTwo(values, order);

				}
			}
			System.out.println(res);
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}

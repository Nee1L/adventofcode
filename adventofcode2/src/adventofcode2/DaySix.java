package adventofcode2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DaySix {
	private static void printArray(List<List<String>> arr, int y, int x, String guard) {
		for (int i = 0; i < arr.size(); i++) {
			for (int j = 0; j < arr.get(i).size(); j++) {
//				if (y == i && x == j) {
//					System.out.print(guard);
//					continue;
//				}
				System.out.print(arr.get(i).get(j) + "");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	private static int isEndlessLoop(List<List<String>> arr, int x, int y, String guard, HashMap<String, Integer> bebra,
			int startX, int startY) {
		String lol = "";
		String guardCopy = new String(guard);
		List<List<String>> buff = new ArrayList<>();
		for (int i = 0; i < arr.size(); i++) {
			List<String> asd = new ArrayList();
			for (int j = 0; j < arr.get(i).size(); j++) {
				asd.add(new String(arr.get(i).get(j)));
			}
			buff.add(asd);
		}
		boolean grenze = false;
		switch (guardCopy) {
		case "^":
			if (y - 1 <= -1) {
				grenze = true;
				break;
			}
			if (buff.get(y - 1).get(x).equals("^")) {
				return 0;
			}
			if (buff.get(y - 1).get(x).equals("#")) {
				return 0;
			}
			buff.get(y - 1).set(x, "#");
			lol = y - 1 + " " + x;
			if (bebra.get(lol) != null) {
				return 0;
			}
			guardCopy = ">";
			break;
		case ">":
			if (x + 1 >= buff.get(y).size()) {
				grenze = true;
				break;
			}
			if (buff.get(y).get(x + 1).equals("^")) {
				return 0;
			}
			if (buff.get(y).get(x + 1).equals("#")) {
				return 0;
			}
			buff.get(y).set(x + 1, "#");
			lol = y + " " + (x + 1);
			if (bebra.get(lol) != null) {
				return 0;
			}
			guardCopy = "v";
			break;
		case "v":
			if (y + 1 >= buff.size()) {
				grenze = true;
				break;
			}
			if (buff.get(y + 1).get(x).equals("^")) {
				return 0;
			}
			if (buff.get(y + 1).get(x).equals("#")) {
				return 0;
			}
			buff.get(y + 1).set(x, "#");
			lol = (y + 1) + " " + x;
			if (bebra.get(lol) != null) {
				return 0;
			}
			guardCopy = "<";
			break;
		case "<":
			if (x - 1 <= -1) {
				grenze = true;
				break;
			}
			if (buff.get(y).get(x - 1).equals("^")) {
				return 0;
			}
			if (buff.get(y).get(x - 1).equals("#")) {
				return 0;
			}
			buff.get(y).set(x - 1, "#");
			lol = y + " " + (x - 1);
			if (bebra.get(lol) != null) {
				return 0;
			}
			guardCopy = "^";
			break;
		}
		if (grenze) {
			return 0;
		}
		if (partOne(buff, startX, startY, guard) == 1) {
			bebra.put(lol, 1);
			// System.out.println(lol);
			return 1;
		}
		// bebra.put(x + " " + y, 0);
		return 0;
	}

	private static int isLoopPossible(List<List<String>> arr, int x, int y, String guard) {
		int i = y;
		int j = x;
		boolean bebra = true;
		while (bebra) {
			switch (guard) {
			case "^":
				j++;
				if (j >= arr.get(i).size()) {
					bebra = false;
					break;
				}
				if (arr.get(i).get(j).contains("E")) {
					System.out.println(y + " " + x);
					return 1;
				}
				break;
			case ">":
				i++;
				if (i >= arr.size()) {
					bebra = false;
					break;
				}
				if (arr.get(i).get(j).contains("S")) {
					System.out.println(y + " " + x);
					return 1;
				}
				break;
			case "v":
				j--;
				if (j <= -1) {
					bebra = false;
					break;
				}
				if (arr.get(i).get(j).contains("W")) {
					System.out.println(y + " " + x);
					return 1;
				}
				break;
			case "<":
				i--;
				if (i <= -1) {
					bebra = false;
					break;
				}
				if (arr.get(i).get(j).contains("N")) {
					System.out.println(y + " " + x);
					return 1;
				}
			}
		}
		return 0;
	}

	private static void partTwo(List<List<String>> arr, int x, int y) {
		HashMap<String, Integer> bebra = new HashMap<>();
		int i = y;
		int j = x;
		int res = 0;
		// arr.get(i).set(j, "X");
		int counter = 1;
		String guard = "^";
		boolean end = false;
		while (!end) {
			res += isEndlessLoop(arr, j, i, guard, bebra, x, y);
			switch (guard) {
			case "^":

				if ((arr.get(i).get(j).equals("-") || arr.get(i).get(j).equals("+"))
						&& !arr.get(i).get(j).equals("^")) {
					counter++;
					arr.get(i).set(j, arr.get(i).get(j) + "E");
				} else {
					if (!arr.get(i).get(j).equals("^")) {
						if (arr.get(i).get(j).equals(".")) {
							arr.get(i).set(j, "N");
						} else {
							arr.get(i).set(j, arr.get(i).get(j) + "N");
						}
					}
				}
				if (i <= 0) {
					end = true;
					break;
				}
				if (arr.get(i - 1).get(j).equals("#")) {
					guard = ">";
					if (!arr.get(i).get(j).equals("^")) {
						arr.get(i).set(j, arr.get(i).get(j) + "E");
					}
					// printArray(arr, i, j, guard);
				} else {

					i--;

				}
				break;
			case ">":

				if ((arr.get(i).get(j).equals("|") || arr.get(i).get(j).equals("+"))
						&& !arr.get(i).get(j).equals("^")) {
					counter++;
					arr.get(i).set(j, arr.get(i).get(j) + "S");
				} else {
					if (!arr.get(i).get(j).equals("^")) {
						if (arr.get(i).get(j).equals(".")) {
							arr.get(i).set(j, "E");
						} else {
							arr.get(i).set(j, arr.get(i).get(j) + "E");
						}
					}
				}
				if (j >= arr.get(i).size() - 1) {
					end = true;
					break;
				}
				if (arr.get(i).get(j + 1).equals("#")) {
					guard = "v";
					if (!arr.get(i).get(j).equals("^")) {
						arr.get(i).set(j, arr.get(i).get(j) + "S");
					}
					// printArray(arr, i, j, guard);
				} else {

					j++;

				}
				break;
			case "v":

				if ((arr.get(i).get(j).equals("-") || arr.get(i).get(j).equals("+"))
						&& !arr.get(i).get(j).equals("^")) {
					counter++;
					arr.get(i).set(j, arr.get(i).get(j) + "W");
				} else {
					if (!arr.get(i).get(j).equals("^")) {
						if (arr.get(i).get(j).equals(".")) {
							arr.get(i).set(j, "S");
						} else {
							arr.get(i).set(j, arr.get(i).get(j) + "S");
						}
					}
				}
				if (i >= arr.size() - 1) {
					end = true;
					break;
				}
				if (arr.get(i + 1).get(j).equals("#")) {
					guard = "<";
					if (!arr.get(i).get(j).equals("^")) {
						arr.get(i).set(j, arr.get(i).get(j) + "W");
					}
					// printArray(arr, i, j, guard);
				} else {

					i++;

				}
				break;
			case "<":

				if ((arr.get(i).get(j).equals("|") || arr.get(i).get(j).equals("+"))
						&& !arr.get(i).get(j).equals("^")) {
					counter++;
					arr.get(i).set(j, arr.get(i).get(j) + "N");
				} else {
					if (!arr.get(i).get(j).equals("^")) {
						if (arr.get(i).get(j).equals(".")) {
							arr.get(i).set(j, "W");
						} else {
							arr.get(i).set(j, arr.get(i).get(j) + "W");
						}
					}
				}
				if (j <= 0) {
					end = true;
					break;
				}
				if (arr.get(i).get(j - 1).equals("#")) {
					guard = "^";
					if (!arr.get(i).get(j).equals("^")) {
						arr.get(i).set(j, arr.get(i).get(j) + "N");
					}
					// printArray(arr, i, j, guard);
				} else {

					j--;
				}
				break;
			}
		}
		System.out.println(res);
	}

	private static int partOne(List<List<String>> arr, int x, int y, String guard) {
		int i = y;
		int j = x;
		guard = "^";
		// arr.get(i).set(j, "X");
		int counter = 1;
		int iterations = 0;
		boolean end = false;
		while (!end) {
			iterations++;
			if (iterations > 100000) {
				return 1;
			}
			switch (guard) {
			case "^":
				if (i <= 0) {
					end = true;
					break;
				}
				if (!arr.get(i).get(j).equals("X")) {
					counter++;
					arr.get(i).set(j, "X");
				}
				if (arr.get(i - 1).get(j).equals("#")) {
					guard = ">";
					// arr.get(i).set(j, ">");
					// printArray(arr, i, j, guard);
				} else {
					i--;
				}
				break;
			case ">":
				if (j >= arr.get(i).size() - 1) {
					end = true;
					break;
				}
				if (!arr.get(i).get(j).equals("X")) {
					counter++;
					arr.get(i).set(j, "X");
				}
				if (arr.get(i).get(j + 1).equals("#")) {
					guard = "v";
					// arr.get(i).set(j, "v");
					// printArray(arr, i, j, guard);
				} else {
					j++;
				}
				break;
			case "v":
				if (i >= arr.size() - 1) {
					end = true;
					break;
				}
				if (!arr.get(i).get(j).equals("X")) {
					counter++;
					arr.get(i).set(j, "X");
				}
				if (arr.get(i + 1).get(j).equals("#")) {
					guard = "<";
					// arr.get(i).set(j, "<");
					// printArray(arr, i, j, guard);
				} else {
					i++;
				}
				break;
			case "<":
				if (j <= 0) {
					end = true;
					break;
				}
				if (!arr.get(i).get(j).equals("X")) {
					counter++;
					arr.get(i).set(j, "X");
				}

				if (arr.get(i).get(j - 1).equals("#")) {
					guard = "^";
					// arr.get(i).set(j, "^");
					// printArray(arr, i, j, guard);
				} else {
					j--;
				}
				break;
			}
		}
//		System.out.println(counter);
//		System.out.println(iterations);
		return 0;
	}

	public static void readFile() {
		List<List<String>> arr = new ArrayList<>();
		int count = 0;
		int x = 0;
		int y = 0;
		try {
			File file = new File("D:\\workspaces\\adventofcode2\\adventofcode2\\src\\adventofcode2\\daysix.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				System.out.println(data);
				if (data.contains("^")) {
					y = count;
					x = data.indexOf("^");
				}
				count++;
				arr.add(Arrays.asList(data.split("")));
			}
			System.out.println("\n");
			partTwo(arr, x, y);
//			partOne(arr, x, y, "^");
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}

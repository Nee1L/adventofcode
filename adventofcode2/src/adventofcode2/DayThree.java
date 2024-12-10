package adventofcode2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree {
	public static void readFile() {
		Pattern pattern = Pattern.compile("(mul\\(\\d+,\\d+\\))");
		int res = 0;
		int indexStart = 0;
		int indexEnd = 0;
		boolean kek = true;
		try {
			File file = new File("D:\\workspaces\\adventofcode2\\adventofcode2\\src\\adventofcode2\\daythree.txt");
			Scanner scanner = new Scanner(file);
			StringBuilder sb = new StringBuilder();

			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			String data = sb.toString();
			Scanner lol = new Scanner(data);
			System.out.println(data);
			// indexStart = data.indexOf("do\\(\\)");
//				if (kek) {
//					indexStart = 0;
//				} else {
//					if (data.indexOf("do()") != -1) {
//						indexStart = data.indexOf("do()");
//						kek = data.lastIndexOf("do()") > data.lastIndexOf("don't()");
//					} else {
//						continue;
//					}
//				}
			if (data.indexOf("don't()", indexStart) != -1) {
				indexEnd = data.indexOf("don't()", indexStart);
			} else {
				indexEnd = data.length();
			}
			int asd = data.lastIndexOf("do()");
			int fad = data.lastIndexOf("don't()");
			kek = asd > fad;
			String buff = new String(data);
			String loop = new String(data);
			System.out.println(indexStart + " " + indexEnd);
			while (loop.contains("do()") || loop.contains("don't()")) {
				buff = data.substring(indexStart, indexEnd);
				loop = data.substring(indexStart, data.length());
				System.out.println(buff);
				Matcher matcher = pattern.matcher(buff);
				while (matcher.find()) {
					String match = matcher.group(1);
					Pattern patternNumbers = Pattern.compile("(\\d+,\\d+)");
					Matcher matcherNumbers = patternNumbers.matcher(match);
					while (matcherNumbers.find()) {
						String numbers = matcherNumbers.group(1);
						String[] arr = numbers.split(",");
						res += Integer.parseInt(arr[0]) * Integer.parseInt(arr[1]);
					}
					System.out.println(matcher.group(1));
				}
				if (data.indexOf("do()", indexEnd) != -1) {
					indexStart = data.indexOf("do()", indexEnd);
				} else {
					break;
				}
				if (data.indexOf("don't()", indexStart) != -1) {
					indexEnd = data.indexOf("don't()", indexStart);
				} else {
					indexEnd = data.length();
				}
			}

//				Matcher matcher = pattern.matcher(data);
//				while (matcher.find()) {
//					String match = matcher.group(1);
//					Pattern patternNumbers = Pattern.compile("(\\d+,\\d+)");
//					Matcher matcherNumbers = patternNumbers.matcher(match);
//					while (matcherNumbers.find()) {
//						String numbers = matcherNumbers.group(1);
//						String[] arr = numbers.split(",");
//						res += Integer.parseInt(arr[0]) * Integer.parseInt(arr[1]);
//					}
//					System.out.println(matcher.group(1));
//				}

			System.out.println(res);
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
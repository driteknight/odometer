import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Odometer {

	public static boolean contains0(long reading) {
		while (reading > 0) {
			if (reading % 10 == 0) {
				return true;
			}
			reading /= 10;
		}
		return false;
	}

	public static boolean isAscendingDigits(long reading) {
		String readingStr = String.valueOf(reading);
		for (int i = 0; i < readingStr.length() - 1; i++) {
			if (readingStr.charAt(i) >= readingStr.charAt(i + 1)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isCorrectReading(long reading) {
		return !contains0(reading) && isAscendingDigits(reading);
	}

	public static int numOfDigits(long reading) {
		return String.valueOf(reading).length();
	}

	public static long getSmallestReading(int size) {

		int smallestReading = 0;
		for (int i = 1; i <= size; i++) {
			smallestReading = smallestReading * 10 + i;
		}
		return smallestReading;
	}

	public static long getLargestReading(int size) {
		int largestReading = 0;
		for (int i = 9 - size + 1; i <= 9; i++) {
			largestReading = largestReading * 10 + i;
		}
		return largestReading;
	}

	public static List<Long> createReading(int size) {
		List<Long> readingList = new ArrayList<Long>();
		long smallestReading = getSmallestReading(size);
		long largestReading = getLargestReading(size);

		for (long i = smallestReading; i <= largestReading; i++) {
			if (isCorrectReading(i)) {
				readingList.add(i);
			}
		}
		return readingList;
	}

	public static int getIndex(long reading, List<Long> readingList) {
		for (int i = 0; i < readingList.size(); i++) {
			if (readingList.get(i) == reading) {
				return i;
			}
		}
		return -1;
	}

	public static long nextReading(long reading, List<Long> readingList) {
		int index = getIndex(reading, readingList);
		if (index == readingList.size() - 1) {
			return readingList.get(0);
		}
		return readingList.get(index + 1);
	}

	public static long prevReading(long reading, List<Long> readingList) {
		int index = getIndex(reading, readingList);
		if (index == 0) {
			return readingList.get(readingList.size() - 1);
		}
		return readingList.get(index - 1);
	}

	public static long distance(long reading1, long reading2, List<Long> readingList) {
		int index1 = getIndex(reading1, readingList);
		int index2 = getIndex(reading2, readingList);

		if (index2 - index1 > 0) {
			return index2 - index1;
		} else {
			return readingList.size() - (index1 - index2);
		}
	}

	public static String checkOutput(long input, long output) {
		return input == output ? "Passed" : "Failed";
	}
	
	public static String checkOutput(boolean input, boolean output) {
		return input == output ? "Passed" : "Failed";
	}

	public static void main(String args[]) throws IOException {
		File file = new File("");

		BufferedReader br = new BufferedReader(new FileReader("testcase.txt"));
		String line = null;
		int testCaseNumber = 0;
		while ((line = br.readLine())!= null) {
			testCaseNumber++;
			String[] testCase = line.split(" ");
			switch(testCase[0]) {
				case "nextReading" : {
					long input = Long.parseLong(testCase[1]);
					long expectedOutput = Long.parseLong(testCase[2]);
					long output = nextReading(input, createReading(numOfDigits(input)));
					System.out.println("Test Case : " + testCaseNumber);
					System.out.println("Output : " + output);
					System.out.println(checkOutput(expectedOutput, output));
					System.out.println();
					break;
				}
				
				case "prevReading" : {
					long input = Long.parseLong(testCase[1]);
					long expectedOutput = Long.parseLong(testCase[2]);
					long output = prevReading(input, createReading(numOfDigits(input)));
					System.out.println("Test Case : " + testCaseNumber);
					System.out.println("Output : " + output);
					System.out.println(checkOutput(expectedOutput, output));
					System.out.println();
					break;
				}
				
				case "isCorrectReading" : {
					long input = Long.parseLong(testCase[1]);
					boolean expectedOutput = Boolean.valueOf(testCase[2]);
					boolean output = isCorrectReading(input);
					System.out.println("Test Case : " + testCaseNumber);
					System.out.println("Output : " + output);
					System.out.println(checkOutput(expectedOutput, output));
					System.out.println();
					break;
				}
				
				case "smallestReading" : {
					int input = Integer.parseInt(testCase[1]);
					long expectedOutput = Integer.parseInt(testCase[2]);
					long output = getSmallestReading(input);
					System.out.println("Test Case : " + testCaseNumber);
					System.out.println("Output : " + output);
					System.out.println(checkOutput(expectedOutput, output));
					System.out.println();
					break;
				}
				
				case "largestReading" : {
					int input = Integer.parseInt(testCase[1]);
					long expectedOutput = Integer.parseInt(testCase[2]);
					long output = getLargestReading(input);
					System.out.println("Test Case : " + testCaseNumber);
					System.out.println("Output : " + output);
					System.out.println(checkOutput(expectedOutput, output));
					System.out.println();
					break;
				}
				
				case "distance" : {
					long input1 = Long.parseLong(testCase[1]);
					long input2 = Long.parseLong(testCase[2]);
					long expectedOutput = Long.parseLong(testCase[3]);
					long output = distance(input1, input2, createReading(numOfDigits(input1)));
					System.out.println("Test Case : " + testCaseNumber);
					System.out.println("Output : " + output);
					System.out.println(checkOutput(expectedOutput, output));
					System.out.println();
					break;
				}
					
			}
		}

	}

}

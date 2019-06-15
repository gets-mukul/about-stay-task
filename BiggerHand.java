

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BiggerHand {

	public static Set<Character> suite = new HashSet<Character>(Arrays.asList('S', 'H', 'C', 'D'));

	public static void main(String[] args) throws Exception {

		String handA = "5D6H";
		String handB = "10C6D";

		Integer output = determineWinner(handA, handB);
		
		System.out.println(output);
		
//		testCase();

	}

	public static int determineWinner(String handA, String handB) throws Exception {
		if (!validHand(handA)) {
			throw new Exception("Not a valid hand");
		}
		if (!validHand(handB)) {
			throw new Exception("Not a valid hand");
		}
		handA = handA.toUpperCase();
		handB = handB.toUpperCase();
		int valueHandA = getValue(handA);
		int valueHandB = getValue(handB);
		if (valueHandA > valueHandB) {
			return 1;
		} else if (valueHandA < valueHandB) {
			return 2;
		} else {
			return 0;
		}
	}

	private static boolean validHand(String hand) {
		if (hand == null)
			return false;
		hand = hand.toUpperCase();
		if (hand.length() < 4 || hand.length() > 6)
			return false;
		if (!suite.contains(hand.charAt(hand.length() - 1)))
			return false;
		if (hand.replaceAll("[JKQASCDH]", "").replaceAll("[0-9]", "").length() > 0)
			return false;
		return true;
	}

	public static int getValue(String hand) throws Exception {

		hand = hand.substring(0, hand.length() - 1);
		int card1Value = -1;
		int card2Value = -1;
		String splitter = null;
		if (hand.contains("S")) {
			splitter = "S";
		} else if (hand.contains("C")) {
			splitter = "C";
		} else if (hand.contains("D")) {
			splitter = "D";
		} else if (hand.contains("H")) {
			splitter = "H";
		}

		if (splitter != null) {
			String arr[] = hand.split(splitter);
			if (arr.length == 2) {
				String leftCard = arr[0];
				String rightCard = arr[1];
				if ((leftCard.length() == 1) || (leftCard.length() == 2 && leftCard.equals("10"))) {
					leftCard = leftCard.replaceAll("J", "11").replaceAll("Q", "12").replaceAll("K", "13").replace("A",
							"14");
					try {
						card1Value = Integer.parseInt(leftCard);
					} catch (Exception e) {
						throw new Exception("Not a valid hand");
					}
				} else {
					throw new Exception("Not a valid hand.");
				}
				if ((rightCard.length() == 1) || (rightCard.length() == 2 && rightCard.equals("10"))) {
					rightCard = rightCard.replaceAll("J", "11").replaceAll("Q", "12").replaceAll("K", "13").replace("A",
							"14");
					try {
						card2Value = Integer.parseInt(rightCard);
					} catch (Exception e) {
						throw new Exception("Not a valid hand");
					}
				} else {
					throw new Exception("Not a valid hand.");
				}
			} else {
				throw new Exception("Not a valid hand");
			}
		} else {
			throw new Exception("Not a valid hand");
		}
		if (card1Value != -1 && card2Value != -1 && (card1Value >= 2 && card1Value <= 14)
				&& (card2Value >= 2 && card2Value <= 14)) {
			return card1Value + card2Value;
		} else {
			throw new Exception("Not a valid hand");
		}
	}

	public static void testCase() throws Exception {
		String handA, handB, CUSTOM_EXCEP = "Not a valid hand.";
		int expexted_ans, actual_ans;

		// test case for 1 is winner
		handA = "10SJH";
		handB = "4C7D";
		expexted_ans = 1;

		actual_ans = determineWinner(handA, handB);

		if (expexted_ans == actual_ans) {
			System.out.println("correct output");
		} else {
			System.out.println("incorrect output");
		}

		// test case for 2 is winner
		handA = "2H3D";
		handB = "9S10C";
		expexted_ans = 2;

		actual_ans = determineWinner(handA, handB);
		if (expexted_ans == actual_ans) {
			System.out.println("correct output");
		} else {
			System.out.println("incorrect output");
		}

		// test case for tie
		handA = "3H6D";
		handB = "5C4S";
		expexted_ans = 0;

		actual_ans = determineWinner(handA, handB);
		if (expexted_ans == actual_ans) {
			System.out.println("correct output");
		} else {
			System.out.println("incorrect output");
		}

		// test case for Invalid card
		handA = "13H6D";
		handB = "5C4S";
		try {
			actual_ans = determineWinner(handA, handB);
		} catch (Exception e) {
			if (e.getMessage().equalsIgnoreCase(CUSTOM_EXCEP)) {
				System.out.println("correct output");
			} else {
				System.out.println("incorrect output");
			}
		}

	}
}

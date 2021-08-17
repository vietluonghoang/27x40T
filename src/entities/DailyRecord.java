package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import utils.Utils;

public class DailyRecord {
	public final String KEY_STRING = "ký tự";
	public final String PRIZE_SPECIAL = "đặc biệt";
	public final String PRIZE_1 = "giải nhất";
	public final String PRIZE_2 = "giải nhì";
	public final String PRIZE_3 = "giải ba";
	public final String PRIZE_4 = "giải tư";
	public final String PRIZE_5 = "giải năm";
	public final String PRIZE_6 = "giải sáu";
	public final String PRIZE_7 = "giải bảy";
	private long timestamp;
	private String keyString = "";
	private HashMap<String, ArrayList<String>> prizeTable;
	private String[] prizeList = { PRIZE_SPECIAL, PRIZE_1, PRIZE_2, PRIZE_3, PRIZE_4, PRIZE_5, PRIZE_6, PRIZE_7 };

	public DailyRecord() {
		this.prizeTable = new HashMap<String, ArrayList<String>>();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String dateInDDMMYYYFormat, String separator) {
		this.timestamp = new Utils().getNumberOfDaySinceEpochTime(dateInDDMMYYYFormat, separator);
	}

	private void setKeyString(String keyString) {
		this.keyString = new Utils().removeInvisibleCharacters(keyString.trim()).toLowerCase();
	}

	public String getKeyString() {
		return keyString;
	}

	public void setPrize(String prize, String result) {
		switch (prize) {
		case KEY_STRING: {
			setKeyString(result);
			break;
		}
		case PRIZE_SPECIAL: {
			if (prizeTable.get(PRIZE_SPECIAL) == null) {
				ArrayList<String> specialPrize = new ArrayList<String>();
				specialPrize.add(result);
				prizeTable.put(PRIZE_SPECIAL, specialPrize);
			} else {
				prizeTable.get(PRIZE_SPECIAL).add(result);
			}
			break;
		}
		case PRIZE_1: {
			if (prizeTable.get(PRIZE_1) == null) {
				ArrayList<String> prize1 = new ArrayList<String>();
				prize1.add(result);
				prizeTable.put(PRIZE_1, prize1);
			} else {
				prizeTable.get(PRIZE_1).add(result);
			}
			break;
		}
		case PRIZE_2: {
			if (prizeTable.get(PRIZE_2) == null) {
				ArrayList<String> prize2 = new ArrayList<String>();
				prize2.add(result);
				prizeTable.put(PRIZE_2, prize2);
			} else {
				prizeTable.get(PRIZE_2).add(result);
			}
			break;
		}
		case PRIZE_3: {
			if (prizeTable.get(PRIZE_3) == null) {
				ArrayList<String> prize3 = new ArrayList<String>();
				prize3.add(result);
				prizeTable.put(PRIZE_3, prize3);
			} else {
				prizeTable.get(PRIZE_3).add(result);
			}
			break;
		}
		case PRIZE_4: {
			if (prizeTable.get(PRIZE_4) == null) {
				ArrayList<String> prize4 = new ArrayList<String>();
				prize4.add(result);
				prizeTable.put(PRIZE_4, prize4);
			} else {
				prizeTable.get(PRIZE_4).add(result);
			}
			break;
		}
		case PRIZE_5: {
			if (prizeTable.get(PRIZE_5) == null) {
				ArrayList<String> prize5 = new ArrayList<String>();
				prize5.add(result);
				prizeTable.put(PRIZE_5, prize5);
			} else {
				prizeTable.get(PRIZE_5).add(result);
			}
			break;
		}
		case PRIZE_6: {
			if (prizeTable.get(PRIZE_6) == null) {
				ArrayList<String> prize6 = new ArrayList<String>();
				prize6.add(result);
				prizeTable.put(PRIZE_6, prize6);
			} else {
				prizeTable.get(PRIZE_6).add(result);
			}
			break;
		}
		case PRIZE_7: {
			if (prizeTable.get(PRIZE_7) == null) {
				ArrayList<String> prize7 = new ArrayList<String>();
				prize7.add(result);
				prizeTable.put(PRIZE_7, prize7);
			} else {
				prizeTable.get(PRIZE_7).add(result);
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + prize);
		}
	}

	public String getPrize(String prize, int index) {
		String emptyResult = "--";
		switch (prize) {
		case PRIZE_SPECIAL: {
			if (prizeTable.get(PRIZE_SPECIAL) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_SPECIAL).get(index);
			}
		}
		case PRIZE_1: {
			if (prizeTable.get(PRIZE_1) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_1).get(index);
			}
		}
		case PRIZE_2: {
			if (prizeTable.get(PRIZE_2) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_2).get(index);
			}
		}
		case PRIZE_3: {
			if (prizeTable.get(PRIZE_3) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_3).get(index);
			}
		}
		case PRIZE_4: {
			if (prizeTable.get(PRIZE_4) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_4).get(index);
			}
		}
		case PRIZE_5: {
			if (prizeTable.get(PRIZE_5) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_5).get(index);
			}
		}
		case PRIZE_6: {
			if (prizeTable.get(PRIZE_6) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_6).get(index);
			}
		}
		case PRIZE_7: {
			if (prizeTable.get(PRIZE_7) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_7).get(index);
			}
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + prize);
		}
	}

	public ArrayList<String> getPrize(String prize) {
		ArrayList<String> emptyResult = new ArrayList<String>();
		switch (prize) {
		case PRIZE_SPECIAL: {
			if (prizeTable.get(PRIZE_SPECIAL) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_SPECIAL);
			}
		}
		case PRIZE_1: {
			if (prizeTable.get(PRIZE_1) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_1);
			}
		}
		case PRIZE_2: {
			if (prizeTable.get(PRIZE_2) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_2);
			}
		}
		case PRIZE_3: {
			if (prizeTable.get(PRIZE_3) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_3);
			}
		}
		case PRIZE_4: {
			if (prizeTable.get(PRIZE_4) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_4);
			}
		}
		case PRIZE_5: {
			if (prizeTable.get(PRIZE_5) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_5);
			}
		}
		case PRIZE_6: {
			if (prizeTable.get(PRIZE_6) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_6);
			}
		}
		case PRIZE_7: {
			if (prizeTable.get(PRIZE_7) == null) {
				return emptyResult;
			} else {
				return prizeTable.get(PRIZE_7);
			}
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + prize);
		}
	}

	public String[] getPrizeList() {
		return prizeList;
	}
}

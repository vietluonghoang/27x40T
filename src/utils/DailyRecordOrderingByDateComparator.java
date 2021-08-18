package utils;

import java.util.Comparator;

import entities.DailyRecord;

public class DailyRecordOrderingByDateComparator implements Comparator<DailyRecord> {
	private boolean isAsending = true;

	public DailyRecordOrderingByDateComparator(boolean isAsending) {
		super();
		this.isAsending = isAsending;
	}

	@Override
	public int compare(DailyRecord arg0, DailyRecord arg1) {
		// TODO Auto-generated method stub
		if (isAsending) {
			return arg0.getTimestamp() < arg1.getTimestamp() ? -1 : arg0.getTimestamp() == arg1.getTimestamp() ? 0 : 1;
		} else {
			return arg0.getTimestamp() > arg1.getTimestamp() ? -1 : arg0.getTimestamp() == arg1.getTimestamp() ? 0 : 1;
		}

	}

}

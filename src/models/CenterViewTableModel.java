package models;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import entities.DailyRecord;
import utils.Utils;

public class CenterViewTableModel implements TableModel {
	private ArrayList<DailyRecord> records;
	private Utils utils = new Utils();

	public CenterViewTableModel(ArrayList<DailyRecord> records) {
		super();
		this.records = records;
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		return String.class;
	}

//	@Override
//	public int getColumnCount() {
//		// TODO Auto-generated method stub
//		return records.size();
//	}

	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return records.get(arg0).getDate();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 27;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		int index = 0;
		DailyRecord rcd = records.get(arg1);
		for (String przName : rcd.getPrizeList()) {
			for (String prize : rcd.getPrize(przName)) {
				if (index == arg0) {
					return utils.getLast2Digits(prize);
				} else {
					index++;
				}
			}
		}
		System.out.println("-- TableModel getValueAt[" + arg0 + "][" + arg1 + "]: null");
		return null;
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		System.out.println("-- addTableModelListener");
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		int size = this.records.size();
		System.out.println("-- column count: " + size);
		return size;
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		System.out.println("-- removeTableModelListener");
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		System.out.println("-- setValueAt[" + arg1 + "][" + arg2 + "]: " + arg0);
	}

}

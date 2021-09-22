package entities.customized;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorizedCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -7409195881368413962L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		this.setValue(table.getValueAt(row, column));
		if (isSelected) {
			if (isMatchedPreviousCell(table, row, column, value)) {
				if (isMatchedPreviousCell(table, row, column - 1, value)) {
					this.setBackground(Color.RED);
				} else {
					this.setBackground(Color.MAGENTA);
				}
			} else {
				this.setForeground(Color.WHITE);
			}
		} else {
			if (isMatchedPreviousCell(table, row, column, value)) {
				if (isMatchedPreviousCell(table, row, column - 1, value)) {
					this.setBackground(Color.RED);
				} else {
					this.setBackground(Color.MAGENTA);
				}
			} else {
				this.setForeground(Color.BLACK);
				this.setBackground(table.getBackground());
			}
		}

		return this;
	}

	private boolean isMatchedPreviousCell(JTable table, int row, int column, Object value) {
		if (column > 0) {
			String prevCellValue = (String) table.getValueAt(row, column - 1);
			if (!prevCellValue.isEmpty() && ((String) value).equals(prevCellValue)) {
				return true;
			}
		}
		return false;
	}
}
package org.prom5.analysis.performance.advanceddottedchartanalysis.model;

import javax.swing.table.AbstractTableModel;

/**
 * Data structure for tables containing one column with metrics
 * (e.g. throughput time)
 *
 * @author Minseok Song
 * @version 1.0
 */
public class OverallMetricTableModel extends AbstractTableModel {

	    private static final long serialVersionUID = -2720292789575028501L;
	    
		private String[] columnNames = {"items", "values"};
		private Object[][] data = { {"time(first)", ""}, {"time(end)", ""}, {"avg spread", ""}, {"min spread", ""}, {"max spread", ""}};

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		public Class<?> getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}

		/*
		 * Set value at field[row, col] in the table
		 * data can change.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

		public void setHeadings(String one, String two) {
			columnNames[0] = one;
			columnNames[1] = two;
		}

}

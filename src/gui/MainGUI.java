package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controllers.DriverCenter;
import controllers.MessageCenter;
import controllers.ResultCrawler;
import entities.DailyRecord;
import entities.GeneralSettings;
import entities.customized.ColorizedCellRenderer;
import entities.customized.ImageViewport;
import models.CenterViewTableModel;
import utils.DailyRecordOrderingByDateComparator;
import utils.Utils;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class MainGUI {

	private JFrame frame;
	private JButton btnStart;
	private ArrayList<DailyRecord> records;
	private JPanel panelLeft;
	private JPanel panelRight;
	private JPanel panelBottom;
	private JPanel panelCenter;
	private JPanel panelSettings;
	private JPanel panelDateFromSettings;
	private JPanel panelDateEndSettings;
	private JPanel panelSortSettings;
	private JPanel panelAlwaysOnTopSettings;
	private JCheckBox cbAlwaysOnTop;
	private JPanel panelMessage;
	private JLabel lblErrorMessage;
	private JPanel panelDayFromTitle;
	private JPanel panelDayFromValue;
	private JLabel lblDayFromTitle;
	private JPanel panelDayEndTitle;
	private JPanel panelDayEndValue;
	private JLabel lblDayEndTitle;
	private JPanel panelCenterViewTable;
	private JScrollPane scrollPaneCenterView;
	private JTable tblCenterViewTable;
	private CenterViewTableModel centerTableModel;
	private JPanel panelSortSettingsTitle;
	private JPanel panelSortSettingsValue;
	private JLabel lblSortSettingsTitle;
	private JCheckBox chckbxSortSettingsValue;
	private JPanel panelLogMessageViewTop;
	private JPanel panelLogMessageViewLeft;
	private JPanel panelLogMessageViewBottom;
	private JPanel panelLogMessageViewRight;
	private JPanel panelLogMessageView;
	private JPanel panelLogMessageViewController;
	private JButton btnClearLogs;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panelLogMessageScrollView;
	private JScrollPane scrollPaneLogMessageView;
	private JTextArea taLogMessageView;
	private JCheckBox chckbxAutoScrollLogs;
	private Utils utils = new Utils();
	private JPanel panelStartDay;
	private JPanel panelStartMonth;
	private JPanel panelStartYear;
	private JPanel panelEndDay;
	private JPanel panelEndMonth;
	private JPanel panelEndYear;
	private JComboBox<String> cbbStartDay;
	private JComboBox<String> cbbStartMonth;
	private JComboBox<String> cbbStartYear;
	private JComboBox<String> cbbEndDay;
	private JComboBox<String> cbbEndMonth;
	private JComboBox<String> cbbEndYear;
	private JPanel panelDateRange;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("K33 - 27x40");
		frame.setBounds(100, 100, 1000, 550);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					if (JOptionPane.showConfirmDialog(frame, "Bạn có chắc là muốn đóng ứng dụng không?", "Đóng?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, utils.getImageIconFromResourceFile(
									GeneralSettings.defaultPopupIcon128)) == JOptionPane.YES_OPTION) {
						DriverCenter.terminateAllDrivers();
						System.exit(0);
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		initTopPanel();
		initLeftPanel();
		initRightPanel();
		initBottomPanel();
		initCenterPanel();
	}

	private void initTopPanel() {
		JPanel panelTop = new JPanel();
		frame.getContentPane().add(panelTop, BorderLayout.NORTH);

		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				startAction();
			}
		});
		panelTop.setLayout(new BorderLayout(0, 0));
		panelTop.add(btnStart, BorderLayout.EAST);
	}

	private void initCenterPanel() {
		panelCenter = new JPanel();
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(1, 0, 0, 0));

		panelCenterViewTable = new JPanel();
		panelCenter.add(panelCenterViewTable);
		panelCenterViewTable.setLayout(new GridLayout(1, 0, 0, 0));

		// initialize table view
		String[] colNames = { "", "", "" };
		Object[][] tableData = { { "", "", "" } };
		tblCenterViewTable = new JTable(tableData, colNames);
		tblCenterViewTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// initialize table row headers
		ListModel<String> listModel = new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] rowHeaderTitles = { "KK", "K1", "K2", "K3", "K4", "K5", "K6", "K7", "K8", "K9", "K10", "K11",
					"K12", "K13", "K14", "K15", "K16", "K17", "K18", "K19", "K20", "K21", "K22", "K23", "K24", "K25",
					"K26" };

			@Override
			public String getElementAt(int arg0) {
				// TODO Auto-generated method stub
				return rowHeaderTitles[arg0];
			}

			@Override
			public int getSize() {
				// TODO Auto-generated method stub
				return rowHeaderTitles.length;
			}
		};
		JList<String> rowHeaders = new JList<>(listModel);
		rowHeaders.setFixedCellWidth(35);
		int height = tblCenterViewTable.getRowHeight();
		int margin = tblCenterViewTable.getRowMargin();
		System.out.println("- height+margin: " + height + "+" + margin);
		rowHeaders.setFixedCellHeight(height);
//		rowHeaders.setCellRenderer(new RowHeeaderRenderer(tblCenterViewTable));
		tblCenterViewTable.setDefaultRenderer(Object.class, new ColorizedCellRenderer());

		scrollPaneCenterView = new JScrollPane(tblCenterViewTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCenterView.setRowHeaderView(rowHeaders);
		tblCenterViewTable.setFillsViewportHeight(true);
		panelCenterViewTable.add(scrollPaneCenterView);
	}

	private void initLeftPanel() {
		panelLeft = new JPanel();
		frame.getContentPane().add(panelLeft, BorderLayout.WEST);
		panelLeft.setPreferredSize(new Dimension(300, 350));
		panelLeft.setLayout(new GridLayout(1, 1, 0, 0));

		// init Settings Panel
		panelSettings = new JPanel();
		panelLeft.add(panelSettings);
		panelSettings.setBorder(
				new TitledBorder(null, "C\u00E0i \u0111\u1EB7t", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panelSettings = new GridBagLayout();
		gbl_panelSettings.columnWidths = new int[] { 300, 0 };
		gbl_panelSettings.rowHeights = new int[] { 100, 30, 30, 0, 0, 0 };
		gbl_panelSettings.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelSettings.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0 };
		panelSettings.setLayout(gbl_panelSettings);

		// init Date Range panel
		panelDateRange = new JPanel();
		panelDateRange.setBorder(
				new TitledBorder(null, "Kho\u1EA3ng qu\u00E9t", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelDateRange = new GridBagConstraints();
		gbc_panelDateRange.anchor = GridBagConstraints.NORTH;
		gbc_panelDateRange.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelDateRange.weightx = 1.0;
		gbc_panelDateRange.insets = new Insets(0, 0, 5, 0);
		gbc_panelDateRange.gridx = 0;
		gbc_panelDateRange.gridy = 0;
		panelSettings.add(panelDateRange, gbc_panelDateRange);
		panelDateRange.setLayout(new GridLayout(3, 1, 0, 0));

		// init Message panel
		panelMessage = new JPanel();
		panelDateRange.add(panelMessage);
		panelMessage.setLayout(new GridLayout(0, 1, 0, 0));
		lblErrorMessage = new JLabel("");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 11));
		panelMessage.add(lblErrorMessage);

		// init Date from panel
		panelDateFromSettings = new JPanel();
		panelDateRange.add(panelDateFromSettings);
		panelDateFromSettings.setLayout(new BorderLayout(0, 0));

		panelDayFromTitle = new JPanel();
		panelDateFromSettings.add(panelDayFromTitle, BorderLayout.WEST);
		panelDayFromTitle.setLayout(new GridLayout(0, 1, 0, 0));

		lblDayFromTitle = new JLabel("Từ:   ");
		panelDayFromTitle.add(lblDayFromTitle);

		panelDayFromValue = new JPanel();
		panelDateFromSettings.add(panelDayFromValue);
		panelDayFromValue.setLayout(new GridLayout(0, 3, 0, 0));

		panelStartDay = new JPanel();
		panelDayFromValue.add(panelStartDay);
		panelStartDay.setLayout(new GridLayout(0, 1, 0, 0));

		cbbStartDay = new JComboBox<String>();
		cbbStartDay.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
		cbbStartDay.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				scanningPeriodCheck();
			}
		});
		panelStartDay.add(cbbStartDay);

		panelStartMonth = new JPanel();
		panelDayFromValue.add(panelStartMonth);
		panelStartMonth.setLayout(new GridLayout(0, 1, 0, 0));

		cbbStartMonth = new JComboBox<String>();
		cbbStartMonth.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cbbStartMonth.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				scanningPeriodCheck();
			}
		});
		panelStartMonth.add(cbbStartMonth);

		panelStartYear = new JPanel();
		panelDayFromValue.add(panelStartYear);
		panelStartYear.setLayout(new GridLayout(0, 1, 0, 0));

		cbbStartYear = new JComboBox<String>();
		cbbStartYear.setModel(new DefaultComboBoxModel<String>(new String[] { "-" }));
		cbbStartYear.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				scanningPeriodCheck();
			}
		});
		panelStartYear.add(cbbStartYear);

		panelDateEndSettings = new JPanel();
		panelDateRange.add(panelDateEndSettings);
		panelDateEndSettings.setLayout(new BorderLayout(0, 0));

		panelDayEndTitle = new JPanel();
		panelDateEndSettings.add(panelDayEndTitle, BorderLayout.WEST);
		panelDayEndTitle.setLayout(new GridLayout(1, 0, 0, 0));

		lblDayEndTitle = new JLabel("Đến: ");
		panelDayEndTitle.add(lblDayEndTitle);

		panelDayEndValue = new JPanel();
		panelDateEndSettings.add(panelDayEndValue);
		panelDayEndValue.setLayout(new GridLayout(0, 3, 0, 0));

		panelEndDay = new JPanel();
		panelDayEndValue.add(panelEndDay);
		panelEndDay.setLayout(new GridLayout(0, 1, 0, 0));

		cbbEndDay = new JComboBox<String>();
		cbbEndDay.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
		cbbEndDay.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				scanningPeriodCheck();
			}
		});
		panelEndDay.add(cbbEndDay);

		panelEndMonth = new JPanel();
		panelDayEndValue.add(panelEndMonth);
		panelEndMonth.setLayout(new GridLayout(0, 1, 0, 0));

		cbbEndMonth = new JComboBox<String>();
		cbbEndMonth.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cbbEndMonth.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				scanningPeriodCheck();
			}
		});
		panelEndMonth.add(cbbEndMonth);

		panelEndYear = new JPanel();
		panelDayEndValue.add(panelEndYear);
		panelEndYear.setLayout(new GridLayout(0, 1, 0, 0));

		cbbEndYear = new JComboBox<String>();
		cbbEndYear.setModel(new DefaultComboBoxModel<String>(new String[] { "-" }));
		cbbEndYear.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				scanningPeriodCheck();
			}
		});
		panelEndYear.add(cbbEndYear);

		panelSortSettings = new JPanel();
		GridBagConstraints gbc_panelSortSettings = new GridBagConstraints();
		gbc_panelSortSettings.weightx = 1.0;
		gbc_panelSortSettings.anchor = GridBagConstraints.NORTH;
		gbc_panelSortSettings.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelSortSettings.insets = new Insets(0, 0, 5, 0);
		gbc_panelSortSettings.gridx = 0;
		gbc_panelSortSettings.gridy = 1;
		panelSettings.add(panelSortSettings, gbc_panelSortSettings);
		panelSortSettings.setLayout(new BorderLayout(0, 0));

		panelSortSettingsTitle = new JPanel();
		panelSortSettings.add(panelSortSettingsTitle, BorderLayout.WEST);
		panelSortSettingsTitle.setLayout(new GridLayout(0, 1, 0, 0));

		lblSortSettingsTitle = new JLabel("Sắp xếp: ");
		panelSortSettingsTitle.add(lblSortSettingsTitle);

		panelSortSettingsValue = new JPanel();
		panelSortSettings.add(panelSortSettingsValue, BorderLayout.CENTER);
		panelSortSettingsValue.setLayout(new GridLayout(0, 1, 0, 0));

		chckbxSortSettingsValue = new JCheckBox("Tăng dần/Giảm dần");
		chckbxSortSettingsValue.setSelected(GeneralSettings.isDailyRecordSortingByAsending);
		chckbxSortSettingsValue.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				GeneralSettings.isDailyRecordSortingByAsending = chckbxSortSettingsValue.isSelected();
				if (records != null) {
					updateTableView();
				}
			}
		});
		panelSortSettingsValue.add(chckbxSortSettingsValue);

		panelAlwaysOnTopSettings = new JPanel();
		GridBagConstraints gbc_panelAlwaysOnTopSettings = new GridBagConstraints();
		gbc_panelAlwaysOnTopSettings.insets = new Insets(0, 0, 5, 0);
		gbc_panelAlwaysOnTopSettings.weightx = 1.0;
		gbc_panelAlwaysOnTopSettings.anchor = GridBagConstraints.NORTH;
		gbc_panelAlwaysOnTopSettings.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelAlwaysOnTopSettings.gridx = 0;
		gbc_panelAlwaysOnTopSettings.gridy = 2;
		panelSettings.add(panelAlwaysOnTopSettings, gbc_panelAlwaysOnTopSettings);
		JCheckBox cbAlwaysOnTop_1 = new JCheckBox("Giữ cửa sổ luôn ở trên ");
		cbAlwaysOnTop_1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				setAlwaysOnTop(cbAlwaysOnTop_1.isSelected());
			}
		});
		panelAlwaysOnTopSettings.setLayout(new GridLayout(0, 1, 0, 0));
		panelAlwaysOnTopSettings.add(cbAlwaysOnTop_1);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		panelSettings.add(panel, gbc_panel);

		updateDateRangeValue();
	}

	private void initRightPanel() {
		panelRight = new JPanel();
		frame.getContentPane().add(panelRight, BorderLayout.EAST);
		panelRight.setLayout(new BorderLayout(0, 0));
		panelRight.setPreferredSize(new Dimension(300, 300));

		panelLogMessageViewTop = new JPanel();
		panelRight.add(panelLogMessageViewTop, BorderLayout.NORTH);
		panelLogMessageViewTop.setLayout(new GridLayout(1, 1, 0, 0));

		panel_8 = new JPanel();
		panelLogMessageViewTop.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 1, 0, 0));

		panelLogMessageViewLeft = new JPanel();
		panelRight.add(panelLogMessageViewLeft, BorderLayout.WEST);
		panelLogMessageViewLeft.setLayout(new GridLayout(0, 1, 0, 0));

		panel_7 = new JPanel();
		panelLogMessageViewLeft.add(panel_7);

		panelLogMessageViewBottom = new JPanel();
		panelRight.add(panelLogMessageViewBottom, BorderLayout.SOUTH);
		panelLogMessageViewBottom.setLayout(new GridLayout(0, 1, 0, 0));

		panel_9 = new JPanel();
		panelLogMessageViewBottom.add(panel_9);

		panelLogMessageViewRight = new JPanel();
		panelRight.add(panelLogMessageViewRight, BorderLayout.EAST);
		panelLogMessageViewRight.setLayout(new GridLayout(0, 1, 0, 0));

		panel_6 = new JPanel();
		panelLogMessageViewRight.add(panel_6);

		panelLogMessageView = new JPanel();
		panelLogMessageView
				.setBorder(new TitledBorder(null, "Logs", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRight.add(panelLogMessageView, BorderLayout.CENTER);
		panelLogMessageView.setLayout(new BorderLayout(0, 0));

		panelLogMessageScrollView = new JPanel();
		panelLogMessageScrollView.setBackground(Color.BLACK);
		panelLogMessageView.add(panelLogMessageScrollView, BorderLayout.CENTER);
		panelLogMessageScrollView.setLayout(new GridLayout(0, 1, 0, 0));

		panelLogMessageViewController = new JPanel();
		panelLogMessageView.add(panelLogMessageViewController, BorderLayout.NORTH);
		panelLogMessageViewController.setLayout(new GridLayout(0, 2, 0, 0));
		btnClearLogs = new JButton();
		btnClearLogs.setText("Xoá logs");
		btnClearLogs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MessageCenter.clearSideLog();
			}
		});
		panelLogMessageViewController.add(btnClearLogs);

		chckbxAutoScrollLogs = new JCheckBox("Cuộn tự động");
		chckbxAutoScrollLogs.setSelected(MessageCenter.isAutoscrollEnabled());
		chckbxAutoScrollLogs.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				MessageCenter.toggleAutoscroll();
			}
		});
		panelLogMessageViewController.add(chckbxAutoScrollLogs);

		taLogMessageView = new JTextArea();
		taLogMessageView.setWrapStyleWord(true);
		taLogMessageView.setLineWrap(true);
		taLogMessageView.setEditable(false);
		taLogMessageView.setOpaque(false);
		taLogMessageView.setBackground(new Color(0, 0, 0, 0));
		taLogMessageView.setForeground(new Color(255, 255, 255, 255));
		MessageCenter.setMessageSideTextArea(taLogMessageView);
		scrollPaneLogMessageView = new JScrollPane();
		scrollPaneLogMessageView.setViewport(new ImageViewport());
		scrollPaneLogMessageView.setViewportView(taLogMessageView);
		scrollPaneLogMessageView.setBackground(new Color(0, 0, 0, 255));
		scrollPaneLogMessageView.setOpaque(false);
		scrollPaneLogMessageView.getViewport().setOpaque(false);
		scrollPaneLogMessageView.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelLogMessageScrollView.add(scrollPaneLogMessageView);

	}

	private void initBottomPanel() {
		panelBottom = new JPanel();
		frame.getContentPane().add(panelBottom, BorderLayout.SOUTH);
	}

	private void startAction() {
		if (!GeneralSettings.isStartedRunning) {
			startScanning();
		} else {
			stopScanning();
		}
	}

	private void startScanning() {
		btnStart.setEnabled(false);

		MessageCenter.appendMessageToSideLog("-- Đang khởi động hệ thống quét... ");
		records = new ArrayList<>();
		ResultCrawler crawler = new ResultCrawler(records, this);
		Thread t1 = new Thread(crawler);
		t1.setName("crawler");
		t1.start();
		GeneralSettings.isStartedRunning = true;

		btnStart.setText("Stop");
		btnStart.setEnabled(true);
	}

	private void stopScanning() {
		DriverCenter.terminateAllDrivers();
		GeneralSettings.isStartedRunning = false;
		btnStart.setText("Start");
	}

	public void updateTableView() {
		MessageCenter.appendMessageToSideLog("-- Đã quét xong. Đang cập nhật bảng kết quả... ");
		Collections.sort(records,
				new DailyRecordOrderingByDateComparator(GeneralSettings.isDailyRecordSortingByAsending));
		centerTableModel = new CenterViewTableModel(records);
		tblCenterViewTable.setModel(centerTableModel);
		setTableColumnWidth();
		MessageCenter.appendMessageToSideLog("-- Đã cập nhật xong. Đang đóng hệ thống quét... ");
		stopScanning();
		MessageCenter.appendMessageToSideLog("-- Đã đóng hệ thống quét.");
	}

	private void setTableColumnWidth() {
		TableColumnModel tcm = tblCenterViewTable.getColumnModel();
		int columnCount = tcm.getColumnCount();
		System.out.println("-- setting column width: " + columnCount);
		for (int i = 0; i < columnCount; i++) {
			TableColumn col = tcm.getColumn(i);
			col.setMinWidth(25);
			col.setMaxWidth(60);
			col.setPreferredWidth(50);
		}
	}

	private void setAlwaysOnTop(boolean isOnTop) {
		frame.setAlwaysOnTop(isOnTop);
	}

	private void updateDateRangeValue() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime lastWeek = now.minusDays(GeneralSettings.defaultDaysOfResultCount);
		int currentYear = now.getYear();
		String[] years = { (currentYear - 1) + "", currentYear + "" };
		ComboBoxModel<String> yearModel = new DefaultComboBoxModel<>(years);
		cbbEndYear.setModel(yearModel);
		cbbStartYear.setModel(yearModel);
		System.out.println("-- today: " + now.getDayOfMonth());

		cbbEndDay.setSelectedIndex(now.getDayOfMonth() - 1); // minus 1 because combobox index starts from 0
		cbbEndMonth.setSelectedIndex(now.getMonth().getValue() - 1);
		cbbEndYear.setSelectedItem(currentYear + "");
		cbbStartDay.setSelectedIndex(lastWeek.getDayOfMonth() - 1);
		cbbStartMonth.setSelectedIndex(lastWeek.getMonth().getValue() - 1);
		cbbStartYear.setSelectedItem(lastWeek.getYear() + "");
	}

	private void scanningPeriodCheck() {
		lblErrorMessage.setText("");
		try {
			int startDay = Integer.parseInt((String) cbbStartDay.getSelectedItem());
			int startMonth = Integer.parseInt((String) cbbStartMonth.getSelectedItem());
			int startYear = Integer.parseInt((String) cbbStartYear.getSelectedItem());
			int endDay = Integer.parseInt((String) cbbEndDay.getSelectedItem());
			int endMonth = Integer.parseInt((String) cbbEndMonth.getSelectedItem());
			int endYear = Integer.parseInt((String) cbbEndYear.getSelectedItem());

			LocalDateTime startDate = generateDateTime(startDay, startMonth, startYear);
			LocalDateTime endDate = generateDateTime(endDay, endMonth, endYear);
			LocalDateTime now = LocalDateTime.now();

			if ((now.isEqual(endDate) || now.isAfter(endDate)) && (now.isEqual(startDate) || now.isAfter(startDate))
					&& (startDate.isEqual(endDate) || endDate.isAfter(startDate))) {
				if (endDate.compareTo(startDate) > 300) {
					throw new Exception("Date diff is more than 300");
				} else {
					GeneralSettings.defaultDaysOfResultCount = endDate.compareTo(startDate);
					GeneralSettings.defaultEndDateOffset = now.compareTo(endDate);
				}
			} else {
				throw new Exception("Invalid datetime");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			lblErrorMessage.setText("Nhập ngày tháng chưa đúng!");
		}

	}

	private LocalDateTime generateDateTime(int day, int month, int year) {
		LocalDateTime date = LocalDateTime.of(year, month, day, 0, 0);
		return date;
	}
}

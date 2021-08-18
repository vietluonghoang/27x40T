package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controllers.DriverCenter;
import controllers.MessageCenter;
import controllers.ResultCrawler;
import entities.DailyRecord;
import entities.GeneralSettings;
import models.CenterViewTableModel;
import utils.DailyRecordOrderingByDateComparator;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;

public class MainGUI {

	private JFrame frame;
	private JButton btnStart;
	private ArrayList<DailyRecord> records;
	private JPanel panelLeft;
	private JPanel panelRight;
	private JPanel panelBottom;
	private JPanel panelCenter;
	private JPanel panelSettings;
	private JPanel panelDayCountSettings;
	private JPanel panelSortSettings;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panelDayCountTitle;
	private JPanel panelDayCountValue;
	private JLabel lblDayCountTitle;
	private JSpinner spinnerDayCount;
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
		frame.setBounds(100, 100, 1000, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

		String[] colNames = { "", "" };
		Object[][] tableData = { { "", "" } };
		tblCenterViewTable = new JTable(tableData, colNames);
		tblCenterViewTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneCenterView = new JScrollPane(tblCenterViewTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblCenterViewTable.setFillsViewportHeight(true);
		panelCenterViewTable.add(scrollPaneCenterView);
	}

	private void initLeftPanel() {
		panelLeft = new JPanel();
		frame.getContentPane().add(panelLeft, BorderLayout.WEST);
		panelLeft.setLayout(new GridLayout(1, 0, 0, 0));
		panelLeft.setPreferredSize(new Dimension(230, 600));

		panelSettings = new JPanel();
		panelSettings.setBorder(
				new TitledBorder(null, "C\u00E0i \u0111\u1EB7t", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLeft.add(panelSettings);
		panelSettings.setLayout(new GridLayout(4, 0, 0, 0));

		panelDayCountSettings = new JPanel();
		panelSettings.add(panelDayCountSettings);
		panelDayCountSettings.setLayout(new BorderLayout(0, 0));

		panelDayCountTitle = new JPanel();
		panelDayCountSettings.add(panelDayCountTitle, BorderLayout.WEST);
		panelDayCountTitle.setLayout(new GridLayout(1, 0, 0, 0));

		lblDayCountTitle = new JLabel("Số ngày quét: ");
		panelDayCountTitle.add(lblDayCountTitle);

		panelDayCountValue = new JPanel();
		panelDayCountSettings.add(panelDayCountValue);
		panelDayCountValue.setLayout(new GridLayout(1, 0, 0, 0));

		SpinnerModel spinnerDayCountModel = new SpinnerNumberModel(GeneralSettings.maxResultCount, 1, 300, 5);
		spinnerDayCount = new JSpinner(spinnerDayCountModel);
		spinnerDayCount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				GeneralSettings.maxResultCount = (int) ((JSpinner) arg0.getSource()).getValue();
			}
		});
		panelDayCountValue.add(spinnerDayCount);

		panelSortSettings = new JPanel();
		panelSettings.add(panelSortSettings);
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

		panel_2 = new JPanel();
		panelSettings.add(panel_2);

		panel_3 = new JPanel();
		panelSettings.add(panel_3);
	}

	private void initRightPanel() {
		panelRight = new JPanel();
		frame.getContentPane().add(panelRight, BorderLayout.EAST);
		panelRight.setLayout(new BorderLayout(0, 0));
		panelRight.setPreferredSize(new Dimension(300, 600));

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
		panelLogMessageView.setBorder(new TitledBorder(null, "Logs", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRight.add(panelLogMessageView, BorderLayout.CENTER);
		panelLogMessageView.setLayout(new BorderLayout(0, 0));

		panelLogMessageScrollView = new JPanel();
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
		MessageCenter.setMessageSideTextArea(taLogMessageView);
		scrollPaneLogMessageView = new JScrollPane(taLogMessageView);
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
}

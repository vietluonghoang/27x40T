package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.DriverCenter;
import controllers.ResultCrawler;
import drivers.RunningDriver;
import entities.DailyRecord;
import entities.GeneralSettings;

import java.awt.BorderLayout;
import javax.swing.JButton;

public class MainGUI {

	private JFrame frame;
	private JButton btnStart;
	private ArrayList<DailyRecord> records;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				startAction();
			}
		});
		panel.add(btnStart);
	}

	private void startAction() {
		if (!GeneralSettings.isStartedRunning) {
			btnStart.setEnabled(false);
			records = new ArrayList<>();
			ResultCrawler crawler = new ResultCrawler(records);
			Thread t1 = new Thread(crawler);
			t1.setName("crawler");
			t1.start();
			GeneralSettings.isStartedRunning = true;
			btnStart.setText("Stop");
			btnStart.setEnabled(true);
		} else {
			DriverCenter.terminateAllDrivers();
			GeneralSettings.isStartedRunning = false;
			btnStart.setText("Start");
		}

	}

}

package controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import drivers.RunningDriver;
import entities.DailyRecord;
import entities.GeneralSettings;

public class ResultCrawler implements Runnable {
	private WebDriver driver;
	private ArrayList<DailyRecord> records;

	public ResultCrawler() {
		try {
			driver = new RunningDriver().chromeDriver();
			DriverCenter.appendDriver(driver);
			records = new ArrayList<>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultCrawler(ArrayList<DailyRecord> records) {
		try {
			driver = new RunningDriver().chromeDriver();
			DriverCenter.appendDriver(driver);
			this.records = records;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (driver != null) {
			driver.get(GeneralSettings.resultPageUrl);
			updateResultListSelection();
			getResultDetails();
		}
	}

	private void updateResultListSelection() {
		String xpathToResultSource = "//form[@id='so-ket-qua']/div/select[@id='code']";
		String xpathToReaultDate = "//form[@id='so-ket-qua']/div/input[@id='date']";
		String xpathToResultDayCount = "//form[@id='so-ket-qua']/div/div/input[@id='count']";
		String xpathToResultSubmitButton = "//form[@id='so-ket-qua']/div/button[@type='submit' and @class='btn btn-primary']";

		Select source = new Select(driver.findElement(By.xpath(xpathToResultSource)));
		source.selectByValue("mb");
		WebElement date = driver.findElement(By.xpath(xpathToReaultDate));
		LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
		String currentDate = now.getDayOfMonth() + "-" + now.getMonthValue() + "-" + now.getYear();
		date.clear();
		date.sendKeys(currentDate);
		WebElement count = driver.findElement(By.xpath(xpathToResultDayCount));
		count.clear();
		count.sendKeys(GeneralSettings.maxResultCount + "");
		count.click(); // to dismiss the date picker
		driver.findElement(By.xpath(xpathToResultSubmitButton)).click();
	}

	private void getResultDetails() {
		String xpathToResultDetails = "//div[contains(@class,'panel panel-default')]/div[contains(@class,'panel-body')]/div[contains(@class,'kqbackground vien tb-phoi')]//table[contains(@id,'result_tab')]";
		String xpathToResultDate = "./thead/tr/td/div/span[@id='result_date']";
		String xpathToResultPrize = "./tbody/tr/td/div/div[contains(@id,'rs')]";
		String xapthToResultPrizeTitle = "./../../../td[1]";
		for (WebElement detailsTable : driver.findElements(By.xpath(xpathToResultDetails))) {
			System.out.println("---- init DailyRecord");
			DailyRecord record = new DailyRecord();
			for (WebElement resultDate : detailsTable.findElements(By.xpath(xpathToResultDate))) {
				System.out.println("----");
				String date = resultDate.getText();
				System.out.println("Result Date: " + date);
				record.setTimestamp(date.split("ngày")[1], "-");
			}
			for (WebElement prize : detailsTable.findElements(By.xpath(xpathToResultPrize))) {
				String prizeName = "";
				String prizeDetails = "";
				for (WebElement prizeTitle : prize.findElements(By.xpath(xapthToResultPrizeTitle))) {
					System.out.println("----");
					prizeName = prizeTitle.getText().toLowerCase();
					System.out.println("Prize title: " + prizeName);
				}
				System.out.println("----");
				prizeDetails = prize.getText().toLowerCase();
				System.out.println("Prize: " + prizeDetails);
				record.setPrize(prizeName, prizeDetails);
			}

			records.add(record);
		}

		for (DailyRecord dailyRecord : records) {
			System.out.println("++++++++");
			System.out.println("Date: " + dailyRecord.getTimestamp());
			System.out.println("Key: " + dailyRecord.getKeyString());

			for (String przName : dailyRecord.getPrizeList()) {
				System.out.println("- " + przName);
				for (String prz : dailyRecord.getPrize(przName)) {
					System.out.println(prz);
				}
			}
		}
	}

}
package com.rafaelbezerra.cwi.currency.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rafaelbezerra.cwi.currency.util.CurrencyUtil;

public class CSVHelper {

	public static List<String[]> readCSVByQuotationDate(String quotationDate) {
		List<String[]> csvCurrencies = new ArrayList<String[]>();
		String csvFilePath = new StringBuilder(System.getProperty("user.dir")).append(File.separatorChar)
				.append(CurrencyUtil.CSV_BASE_PATH).append(File.separatorChar).append(quotationDate).toString();

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(csvFilePath));
			String csvCurrencie = null;
			while ((csvCurrencie = bufferedReader.readLine()) != null)
				csvCurrencies.add(csvCurrencie.split(CurrencyUtil.CSV_COLUMN_DELIMITER));
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
		return csvCurrencies;
	}

}

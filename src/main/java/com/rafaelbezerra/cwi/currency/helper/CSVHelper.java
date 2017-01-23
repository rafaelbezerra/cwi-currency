package com.rafaelbezerra.cwi.currency.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rafaelbezerra.cwi.currency.config.Config;

/**
 * CSV Helper, common utilities for CSV files
 * 
 * @author rafaelbezerra
 */
public class CSVHelper {

	private static final Logger LOGGER = Logger.getLogger(CurrencyHelper.getClassName(CSVHelper.class));

	/**
	 * Reads a whole CSV file and returns your data
	 * 
	 * @param csvFilePath
	 *            the CSV file path
	 * @return the CSV file data
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	public static List<String[]> readCSV(String csvFilePath) throws ParseException, IOException {
		List<String[]> csvCurrencies = new ArrayList<String[]>();

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(csvFilePath));
			String csvCurrencie = null;
			while ((csvCurrencie = bufferedReader.readLine()) != null)
				csvCurrencies.add(csvCurrencie.split(Config.getCsvColumnDelimiter()));
		} finally {
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
					LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
				}
		}
		return csvCurrencies;
	}

}

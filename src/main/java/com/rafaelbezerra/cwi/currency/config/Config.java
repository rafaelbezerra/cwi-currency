package com.rafaelbezerra.cwi.currency.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rafaelbezerra.cwi.currency.helper.CurrencyHelper;

public class Config {

	private static final Logger LOGGER = Logger.getLogger(CurrencyHelper.getClassName(Config.class));

	private static final String CONFIG_FILE_PATH = "config.properties";
	private static Properties properties = new Properties();
	private static Date lastLoad = null;

	private static int calculationScale;
	private static RoundingMode calculationRoundingMode;
	private static String csvBasePath;
	private static String csvColumnDelimiter;
	private static String csvUrlToDownloadPrefix;
	private static String csvFilePathDateFormat;
	private static String quotationDateInsertDateFormat;
	private static int loggerSize;
	private static int loggerRotationCount;
	private static String loggerLogFileName;

	private static void initConfiguration() {
		InputStream input = null;

		try {
			input = new FileInputStream(CONFIG_FILE_PATH);

			// load a properties file from class path, inside static method
			properties.load(input);

			setProperties(properties);

			lastLoad = new Date();
		} catch (IOException ex) {
			ex.printStackTrace();
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException ex) {
					ex.printStackTrace();
					LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
				}
		}

	}

	private static void setProperties(Properties properties) {
		calculationScale = Integer.parseInt(properties.getProperty("calculation_scale"));
		calculationRoundingMode = RoundingMode.valueOf(properties.getProperty("calculation_rounding_mode"));
		csvBasePath = properties.getProperty("csv_base_path");
		csvColumnDelimiter = properties.getProperty("csv_column_delimiter");
		csvUrlToDownloadPrefix = properties.getProperty("csv_url_to_download_prefix");
		csvFilePathDateFormat = properties.getProperty("csv_file_path_date_format");
		quotationDateInsertDateFormat = properties.getProperty("quotation_date_insert_date_format");
		loggerSize = Integer.parseInt(properties.getProperty("logger_size"));
		loggerRotationCount = Integer.parseInt(properties.getProperty("logger_rotation_count"));
		loggerLogFileName = properties.getProperty("logger_log_file_name");
	}

	private static void verifysIfItsLoaded() {
		if (lastLoad == null || CurrencyHelper.appendDate(lastLoad, 2, Calendar.HOUR).after(new Date()))
			initConfiguration();
	}

	public static int getCalculationScale() {
		verifysIfItsLoaded();
		return calculationScale;
	}

	public static RoundingMode getCalculationRoundingMode() {
		verifysIfItsLoaded();
		return calculationRoundingMode;
	}

	public static String getCsvBasePath() {
		verifysIfItsLoaded();
		return csvBasePath;
	}

	public static String getCsvColumnDelimiter() {
		verifysIfItsLoaded();
		return csvColumnDelimiter;
	}

	public static String getCsvUrlToDownloadPrefix() {
		verifysIfItsLoaded();
		return csvUrlToDownloadPrefix;
	}

	public static String getCsvFilePathDateFormat() {
		verifysIfItsLoaded();
		return csvFilePathDateFormat;
	}

	public static String getQuotationDateInsertDateFormat() {
		verifysIfItsLoaded();
		return quotationDateInsertDateFormat;
	}

	public static int getLoggerSize() {
		verifysIfItsLoaded();
		return loggerSize;
	}

	public static int getLoggerRotationCount() {
		verifysIfItsLoaded();
		return loggerRotationCount;
	}

	public static String getLoggerLogFileName() {
		verifysIfItsLoaded();
		return loggerLogFileName;
	}

}

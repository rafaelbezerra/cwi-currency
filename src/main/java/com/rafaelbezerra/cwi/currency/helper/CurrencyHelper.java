package com.rafaelbezerra.cwi.currency.helper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rafaelbezerra.cwi.currency.exception.QuotationDownloadException;

/**
 * Currency Helper, generic utilities for the Currency module
 * 
 * @author rafaelbezerra
 */
public class CurrencyHelper {

	private static final Logger LOGGER = Logger.getLogger(CurrencyHelper.getClassName(CurrencyHelper.class));

	private static final SimpleDateFormat sdf = new SimpleDateFormat();

	// FIXME verificar as exceptions
	/**
	 * Downloads a file
	 * 
	 * @param urlFrom
	 *            the url to be downloaded
	 * @param filePathTo
	 *            the file path to save the file
	 */
	public static void downloadFile(String urlFrom, String filePathTo) {
		BufferedInputStream bufferedInputStream = null;
		FileOutputStream fileOutputStream = null;

		try {
			bufferedInputStream = new BufferedInputStream(new URL(urlFrom).openStream());
			fileOutputStream = new FileOutputStream(filePathTo);

			final byte data[] = new byte[1024];
			int count;
			while ((count = bufferedInputStream.read(data, 0, 1024)) != -1)
				fileOutputStream.write(data, 0, count);
		} catch (MalformedURLException ex) {
			QuotationDownloadException exception = new QuotationDownloadException(
					new StringBuilder("Cannot download from ").append(urlFrom).append(" to ").append(filePathTo)
							.toString(),
					ex);
			exception.printStackTrace();
			LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
			throw exception;
		} catch (UnknownHostException ex) {
			QuotationDownloadException exception = new QuotationDownloadException(
					new StringBuilder("Cannot download from ").append(urlFrom).append(" to ").append(filePathTo)
							.append(". Verify your internet connection").toString(),
					ex);
			exception.printStackTrace();
			LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
			throw exception;
		} catch (IOException ex) {
			QuotationDownloadException exception = new QuotationDownloadException(
					new StringBuilder("Cannot download from ").append(urlFrom).append(" to ").append(filePathTo)
							.toString(),
					ex);
			exception.printStackTrace();
			LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
			throw exception;
		} finally {
			if (bufferedInputStream != null)
				try {
					bufferedInputStream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
					LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
				}
			if (fileOutputStream != null)
				try {
					fileOutputStream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
					LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
				}
		}
	}

	/**
	 * Verifies if file path exists, is valid
	 * 
	 * @param filePath
	 *            the file path to verify
	 * @return true if exists, false in otherwise
	 */
	public static boolean filePathExists(String filePath) {
		File f = new File(filePath);
		if (f.exists() && !f.isDirectory())
			return true;
		return false;
	}

	/**
	 * Gets the correct class name
	 * 
	 * @param classObj
	 *            class to be get the name
	 * @return the class name
	 */
	public static String getClassName(Object classObj) {
		Class<?> enclosingClass = classObj.getClass().getEnclosingClass();
		if (enclosingClass != null)
			return enclosingClass.getName();
		else
			return classObj.getClass().getName();
	}

	/**
	 * Formats a string to date
	 * 
	 * @param dateStr
	 *            the date string to be formatatted
	 * @param format
	 *            the format of date string
	 * @return the {@link java.util.Date}
	 */
	public static Date getDateFromString(String dateStr, String format) {
		sdf.applyPattern(format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException ex) {
			ex.printStackTrace();
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return null;
	}

	/**
	 * Formats a date to a specific string date format
	 * 
	 * @param date
	 *            the date
	 * @param format
	 *            the format
	 * @return the date string formatatted
	 */
	public static String getStringFromDate(Date date, String format) {
		sdf.applyPattern(format);
		return sdf.format(date);
	}

	/**
	 * Appends some time to a date, based on the date type and value
	 * 
	 * @param date
	 *            to be appended
	 * @param value
	 *            to be append to date
	 * @param type
	 *            the Calendar type to append. Ex.: Calendar.HOUR
	 * @return the date appended
	 */
	public static Date appendDate(Date date, int value, int type) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, value);
		return calendar.getTime();
	}

	/**
	 * Replaces commas for dots
	 * 
	 * @param value
	 *            the value to be replaced
	 * @return the value replaced
	 */
	public static String replaceCommasForDots(String value) {
		return value.replace(",", ".");
	}
}

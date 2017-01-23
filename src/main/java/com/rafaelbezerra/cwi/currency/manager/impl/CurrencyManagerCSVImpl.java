package com.rafaelbezerra.cwi.currency.manager.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rafaelbezerra.cwi.currency.config.Config;
import com.rafaelbezerra.cwi.currency.dto.CurrencyDTO;
import com.rafaelbezerra.cwi.currency.enums.CurrencyIdentifierEnum;
import com.rafaelbezerra.cwi.currency.exception.CurrencyInvalidException;
import com.rafaelbezerra.cwi.currency.exception.QuotationDateUnavailableException;
import com.rafaelbezerra.cwi.currency.exception.QuotationFileDoesnExistsException;
import com.rafaelbezerra.cwi.currency.helper.CSVHelper;
import com.rafaelbezerra.cwi.currency.helper.CurrencyHelper;
import com.rafaelbezerra.cwi.currency.manager.CurrencyManager;
import com.rafaelbezerra.cwi.currency.mapper.impl.CurrencyMapperImpl;

/**
 * The Currency Manager CSV implementation of {@link CurrencyManager}
 * 
 * @author rafaelbezerra
 */
public class CurrencyManagerCSVImpl implements CurrencyManager {

	private static final Logger LOGGER = Logger.getLogger(CurrencyHelper.getClassName(CurrencyManagerCSVImpl.class));

	private CurrencyMapperImpl currencyMapper = new CurrencyMapperImpl();

	private static final Map<Date, List<CurrencyDTO>> loadedCurrencies = new HashMap<Date, List<CurrencyDTO>>();

	/**
	 * Finds the currency data based on the currency identifier and the
	 * quotation date in the CSV's files
	 * 
	 * @exception QuotationFileDoesnExistsException
	 *                if the quotation date list is not loaded and if did not
	 *                possible to download the quotation date file the exception
	 *                will thrown
	 * @exception QuotationDateUnavailableException
	 *                if the date is after the current instant the exception
	 *                will thrown
	 * 
	 * 
	 * @param currency
	 *            the currency identifier
	 * @param quotation
	 *            the quotation date
	 */
	public CurrencyDTO findByCurrencyQuotation(String currency, String quotation) {
		Date quotationDate = CurrencyHelper.getDateFromString(quotation, Config.getQuotationDateInsertDateFormat());

		// validates if the quotation date is in a business day, if not, returns
		// the date to the near previous business date
		quotationDate = validateBusinessDay(quotationDate);

		// validates if the dates is in the future
		validateIsInTheFuture(quotationDate);

		// loads if necessary the quotation's list
		loadQuotationDate(quotationDate);

		CurrencyIdentifierEnum currencyEnum = CurrencyIdentifierEnum.getByIdentifier(currency);

		// returns the currency data based on currency and quotation date
		return searchInList(currencyEnum, quotationDate);
	}

	/**
	 * Searches for the currency data based on the currency and the quotation
	 * date
	 * 
	 * @param currencyEnum
	 *            the currency
	 * @param quotationDate
	 *            the quotation date
	 * @return the currency data
	 * 
	 * @exception CurrencyInvalidException
	 *                if the currency was not found, the exception will be
	 *                thrown
	 */
	private CurrencyDTO searchInList(CurrencyIdentifierEnum currencyEnum, Date quotationDate) {
		List<CurrencyDTO> currencyDTOList = loadedCurrencies.get(quotationDate);
		for (CurrencyDTO currencyDTO : currencyDTOList)
			if (currencyDTO.getCurrency().equals(currencyEnum))
				return currencyDTO;

		CurrencyInvalidException ex = new CurrencyInvalidException("The currency was not found, currency is invalid");
		LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		throw ex;
	}

	/**
	 * Loads the currencis list by quotation date, verifying if the currencies
	 * file is already downloaded, if not, tries to download the file and loads
	 * the currencies map.
	 * 
	 * @exception QuotationFileDoesnExistsException
	 *                if the quotation date list is not loaded and if did not
	 *                possible to download the quotation date file the exception
	 *                will thrown
	 * 
	 * @param quotationDate
	 *            quotation date to load the currencies
	 */
	private void loadQuotationDate(Date quotationDate) {
		try {
			if (!loadedCurrencies.containsKey(quotationDate)) {
				String quotationCSVFilePath = getQuotationCSVFilePath(quotationDate);
				if (!CurrencyHelper.filePathExists(quotationCSVFilePath)) {
					downloadQuotationCSVFile(quotationDate);
					if (!CurrencyHelper.filePathExists(quotationCSVFilePath)) {
						QuotationFileDoesnExistsException ex = new QuotationFileDoesnExistsException(
								"It was not possible to download the quotation CSV file");
						LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
						throw ex;
					}
				}

				List<String[]> currenciesList = CSVHelper.readCSV(quotationCSVFilePath);
				if (!currenciesList.isEmpty())
					loadedCurrencies.put(quotationDate, currencyMapper.mapList(currenciesList));
			}
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		} catch (ParseException ex) {
			ex.printStackTrace();
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		} catch (IOException ex) {
			ex.printStackTrace();
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	// FIXME tratar exception possibilidades de não haver url
	/**
	 * Downloads quotation CSV File
	 * 
	 * @param quotationDate
	 *            the quotation date file to be downloaded
	 * 
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ParseException
	 */
	private void downloadQuotationCSVFile(Date quotationDate)
			throws MalformedURLException, IOException, ParseException {
		CurrencyHelper.downloadFile(getQuotationCSVUrlToDownload(quotationDate),
				getQuotationCSVFilePath(quotationDate));
	}

	/**
	 * Gets the quotation CSV file path by the quotation date
	 * 
	 * @param quotationDate
	 *            the quotation date
	 * @return the quotation CSV filepath
	 * 
	 * @throws ParseException
	 */
	private String getQuotationCSVFilePath(Date quotationDate) throws ParseException {
		return new StringBuilder(System.getProperty("user.dir")).append(File.separatorChar)
				.append(Config.getCsvBasePath()).append(File.separatorChar)
				.append(getQuotationDateStrFormatted(quotationDate)).append(".csv").toString();
	}

	/**
	 * Gets the quotation CSV URL to download by the quotation date
	 * 
	 * @param quotationDate
	 *            the quotation date
	 * @return the quotation CVS file URL to download
	 * 
	 * @throws ParseException
	 */
	private String getQuotationCSVUrlToDownload(Date quotationDate) throws ParseException {
		return new StringBuilder(Config.getCsvUrlToDownloadPrefix()).append("/")
				.append(getQuotationDateStrFormatted(quotationDate)).append(".csv").toString();
	}

	/**
	 * Gets the quotation date formatatted to 'yyyyMMDD' string
	 * 
	 * @param quotationDate
	 *            the quotation date
	 * @return the quotation date string formatatted
	 * 
	 * @throws ParseException
	 */
	private String getQuotationDateStrFormatted(Date quotationDate) throws ParseException {
		return CurrencyHelper.getStringFromDate(quotationDate, Config.getCsvFilePathDateFormat());
	}

	/**
	 * Verifies if the date is in a business day, if false, returns the date to
	 * the previously business date
	 * 
	 * @param date
	 *            to validate
	 * @return the date in a business date
	 */
	private Date validateBusinessDay(Date date) {
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(date);

		switch (dateCalendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SATURDAY:
			dateCalendar.add(Calendar.DAY_OF_MONTH, -1);
			return dateCalendar.getTime();
		case Calendar.SUNDAY:
			dateCalendar.add(Calendar.DAY_OF_MONTH, -2);
			return dateCalendar.getTime();
		default:
			return date;
		}
	}

	/**
	 * Verifies if date is in the future, if true, the
	 * QuotationDateUnavailableException is thrown
	 * 
	 * @exception QuotationDateUnavailableException
	 *                if the date is after the current instant the exception
	 *                will thrown
	 * 
	 * @param date
	 *            date to validate
	 */
	private void validateIsInTheFuture(Date date) {
		if (date.after(new Date())) {
			QuotationDateUnavailableException ex = new QuotationDateUnavailableException(
					"The quotation date is not valid, is in the future");
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			throw ex;
		}
	}
}
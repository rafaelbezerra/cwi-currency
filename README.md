# cwi-currency
Currency converter for CWI Test.

## What is it?
The project is a single maven module, jar packaging, who provides currency convert based on central Brazil bank data, to do its calculations.  

## Basic
To use the converter, you need to call the CurrencyService.currencyQuotation(). 

> You can see an example here [Usage.java](https://github.com/rafaelbezerra/cwi-currency/blob/master/src/main/java/com/rafaelbezerra/cwi/currency/Usage.java)

## Feature requisites
You should create the following function:
> public BigDecimal currencyQuotation(String from, String to, Number value, String quotation);

### Where
* from: String with the currency name (example "USD") you want to convert;
* to: String with the currency name (example "EUR") you want to see the result;
* value: The value that should be converted. The currency of this value will be expressed in the “from” parameter;
* quotation: A date as String in the format “dd/MM/yyyy”;

###Restrictions
* You shall not work with non-native classes / libraries;
* If the from or to parameters are not valid, an exception must be thrown;
* If the value is smaller than zero, an exception must be thrown;
* For non-working days (Saturday and Sunday, ignoring holidays) takes the quotation from the immediately preceding business day. If the quotation of the previous day is not available, an exception must be thrown;
* If the quotation date is not available, an exception must be thrown;
* The data source used will be the Brazilian central bank CSV file available at:
  * http://www4.bcb.gov.br/pec/taxas/batch/cotacaomoedas.asp?id=txtodas
* The return value should be rounded to two decimal places.
* You must convert the currency through rate "Taxa Compra".
 
### Example
> currencyQuotation("USD", "EUR", 100.00, "20/11/2014") = 79.69

> Data source:
> * 20/11/2014 – 220 – A – USD - 2,54400000
> * 20/11/2014 - 978 - B - EUR - 3,19170000

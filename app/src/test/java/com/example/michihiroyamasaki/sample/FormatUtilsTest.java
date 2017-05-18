package com.example.michihiroyamasaki.sample;

import com.example.michihiroyamasaki.sample.utils.FormatUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class FormatUtilsTest {
    private FormatUtils formatUtils = FormatUtils.getInstance();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void dateFormatReturnsYyyyMmDdWithSlash() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        Date date = calendar.getTime();

        String formattedDate = formatUtils.dateFormat(date);

        assertTrue(formattedDate.equals("2015/04/15"));
    }

    @Test
    public void whenStringIsYyyyMmDdStyleThenParseDateReturnsDate() throws ParseException{
        String dateAsString = "2015/04/15";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();

        Date parsedDate = formatUtils.parseDate(dateAsString);

        assertThat(parsedDate).isEqualTo(date);
    }

    @Test
    public void whenStringIsNotYyyyMmDdStyleThenThrowsParseException() throws ParseException {
        thrown.expect(ParseException.class);

        formatUtils.parseDate("2015-03-13");
    }

    @Test
    public void currencyFormatReturnsSplittedYen() throws Exception {
        BigInteger currency = BigInteger.valueOf(2015);

        String formattedCurrency = formatUtils.currencyFormat(currency);

        assertTrue(formattedCurrency.equals("2,015円"));
    }

    @Test
    public void countFormatReturnsSplittedUnit(){
        int count = 3000;

        String formattedCount = formatUtils.countFormat(count);

        assertTrue(formattedCount.equals("3,000個"));
    }

    @Test
    public void numberFormatReturnsSplitted() throws Exception {
        int num = 3000;

        String formattedNumber = formatUtils.numberFormat(num);

        assertTrue(formattedNumber.equals("3,000"));
    }

}
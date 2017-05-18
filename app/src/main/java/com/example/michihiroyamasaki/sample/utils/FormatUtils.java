package com.example.michihiroyamasaki.sample.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatUtils {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);

    private FormatUtils(){}
    private static FormatUtils instance = new FormatUtils();

    public static FormatUtils getInstance(){
        return instance;
    }

    public String dateFormat(Date date){
        return dateFormat.format(date);
    }

    public Date parseDate(String dateAsString) throws ParseException {
        return dateFormat.parse(dateAsString);
    }

    public String currencyFormat(Number currency){
        return String.format(Locale.JAPAN, "%,3d円", currency.longValue());
    }

    public String countFormat(Number count){
        return String.format(Locale.JAPAN, "%,3d個", count.longValue());
    }

    public String numberFormat(Number number){
        return String.format(Locale.JAPAN, "%,3d", number.longValue());
    }

}

package fr.beber.generatormdp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Cette classe permet de faire des opérations sur les dates.
 *
 * @author Bertrand
 * @version 1.0
 */
public class DateFormat {

    public DateFormat() {

    }

    /**
     * Permet d'afficher la date en fonction du pattern.
     * @param myCalendar La date à afficher.
     * @param pattern Le format à afficher.
     * @return La date.
     */
    public static String getCalendarFormat(final Calendar myCalendar, final String pattern){
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(myCalendar.getTime());
    }

}

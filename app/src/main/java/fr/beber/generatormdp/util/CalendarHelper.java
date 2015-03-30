package fr.beber.generatormdp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Cette classe permet de faire des opérations sur les dates.
 *
 * @author Bertrand
 * @version 1.0
 */
public class CalendarHelper {

    public CalendarHelper() {

    }

    /**
     * Permet d'afficher la date en fonction du pattern.
     *
     * @param myCalendar La date à afficher.
     * @param pattern    Le format à afficher.
     * @return La date.
     */
    public static String getCalendarFormat(final Calendar myCalendar, final String pattern) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(myCalendar.getTime());
    }

    /**
     * Permet d'afficher la date en fonction du pattern en FR ("dd-MM-yyyy").
     *
     * @param myCalendar La date à afficher.
     * @return La date.
     */
    public static String getCalendarFormat(final Calendar myCalendar) {
        return getCalendarFormat(myCalendar, "dd-MM-yyyy");
    }

    /**
     * Permet de savoir si la date a expiré.
     *
     * @param calendar Date à comparer.
     * @param context  Le contexte courant.
     * @return <code>TRUE</code> si la date a expiré.
     */
    public static Boolean compareCalendarExpiration(final Context context, final Calendar calendar) {

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final Boolean isActiveDateExpiration = sharedPreferences.getBoolean("prefKeyChaneMDPDefault", Boolean.TRUE);
        final int addDay = Integer.valueOf(sharedPreferences.getString("prefKeyDateLimit", "0"));

        final Calendar today = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, addDay);

        return calendar.before(today) && Boolean.TRUE.equals(isActiveDateExpiration);
    }

}

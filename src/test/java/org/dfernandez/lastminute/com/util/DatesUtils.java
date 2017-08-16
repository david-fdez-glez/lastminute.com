package org.dfernandez.lastminute.com.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DatesUtils {

    /**
     * Function return a new Date, numDays in the future from now
     * @param numDays days to add
     * @return
     */
    public static Date getDatePlusDays(int numDays) {
        LocalDate localDate = LocalDate.now().plusDays(numDays);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}

package org.dfernandez.lastminute.com.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TestUtils {

    /**
     * Function return a new Date, numDays in the future from now
     * @param numDays days to add
     * @return
     */
    public static Date getDatePlusDays(int numDays) {
        LocalDate localDate = LocalDate.now().plusDays(numDays);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Get BigDecimal with scale and round
     * @param value
     * @return
     */
    public static BigDecimal getBigDecimalScale(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN);
    }
}

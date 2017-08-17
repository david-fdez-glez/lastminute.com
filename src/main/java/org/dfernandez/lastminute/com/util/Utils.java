package org.dfernandez.lastminute.com.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    /**
     * Get BigDecimal with scale and round
     * @param bigDecimal
     * @return
     */
    public static BigDecimal setBigDecimalScale(BigDecimal bigDecimal) {
        return bigDecimal.setScale(2, RoundingMode.HALF_EVEN);
    }
}

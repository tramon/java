package lab12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/** ChronoUnit: units of time (DAYS, HOURS, ...) used to add, truncate and measure. */
public class ChronoUnitExamples {

    public static void run() {
        System.out.println("=== ChronoUnit ===");

        LocalDate a = LocalDate.of(2026, 1, 15);
        LocalDate b = LocalDate.of(2026, 11, 21);

        // between(): whole units only (the fractional part is dropped)
        System.out.println("days   between = " + ChronoUnit.DAYS.between(a, b));
        System.out.println("weeks  between = " + ChronoUnit.WEEKS.between(a, b));
        System.out.println("months between = " + ChronoUnit.MONTHS.between(a, b));
        System.out.println("years  between = " + ChronoUnit.YEARS.between(a, b));
        System.out.println("negative if reversed = " + ChronoUnit.DAYS.between(b, a));

        LocalDateTime t1 = LocalDateTime.of(2026, 11, 21, 8, 0);
        LocalDateTime t2 = LocalDateTime.of(2026, 11, 23, 20, 45);
        System.out.println("hours   between = " + ChronoUnit.HOURS.between(t1, t2));
        System.out.println("minutes between = " + ChronoUnit.MINUTES.between(t1, t2));
        System.out.println("days    between = " + ChronoUnit.DAYS.between(t1, t2) + " (only 2 full days)");

        // Adding with a unit: plus(amount, unit)
        System.out.println("a.plus(3, MONTHS)  = " + a.plus(3, ChronoUnit.MONTHS));
        System.out.println("a.plus(3, MONTHS)  = " + a.plusMonths(3)); //same as previous line
        System.out.println("a.plus(2, DECADES) = " + a.plus(2, ChronoUnit.DECADES));
        System.out.println("t1.plus(36, HOURS) = " + t1.plus(36, ChronoUnit.HOURS));
        System.out.println("t1.plus(36, HOURS) = " + t1.plusHours(36)); //same as previous line

        // Truncating
        LocalTime now = LocalTime.of(14, 37, 52, 123_000_000);
        System.out.println("now             = " + now);
        System.out.println("truncate SECONDS = " + now.truncatedTo(ChronoUnit.SECONDS));
        System.out.println("truncate MINUTES = " + now.truncatedTo(ChronoUnit.MINUTES));
        System.out.println("truncate HOURS   = " + now.truncatedTo(ChronoUnit.HOURS));
        System.out.println("truncate DAYS    = " + t2.truncatedTo(ChronoUnit.DAYS));

        // The unit itself knows its Duration
        System.out.println("HOURS.getDuration() = " + ChronoUnit.HOURS.getDuration());
        System.out.println("DAYS.getDuration()  = " + ChronoUnit.DAYS.getDuration());

        // All available units
        System.out.print("units: ");
        for (ChronoUnit unit : ChronoUnit.values()) {
            System.out.print(unit + " ");
        }
        System.out.println();
        System.out.println();
    }
}

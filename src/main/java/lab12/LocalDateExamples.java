package lab12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/** LocalDate: a date without time or time zone (e.g. 2026-11-21). */
public class LocalDateExamples {

    public static void run() {
        System.out.println("=== LocalDate ===");

        // Creating
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1995, Month.MARCH, 15);
        LocalDate parsed = LocalDate.parse("2026-12-31");
        System.out.println("today    = " + today);
        System.out.println("birthday = " + birthday);
        System.out.println("parsed   = " + parsed);

        // Reading fields
        System.out.println("year/month/day = " + today.getYear() + "/" + today.getMonthValue() + "/" + today.getDayOfMonth());
        System.out.println("day of week    = " + today.getDayOfWeek());
        System.out.println("day of year    = " + today.getDayOfYear());
        System.out.println("leap year?     = " + today.isLeapYear());

        // Immutable: plus/minus return NEW objects
        System.out.println("+10 days   = " + today.plusDays(10));
        System.out.println("-2 months  = " + today.minusMonths(2));
        System.out.println("+1 year    = " + today.plusYears(1));
        System.out.println("withDayOfMonth(1) = " + today.withDayOfMonth(1));

        // Adjusters
        System.out.println("next Monday     = " + today.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
        System.out.println("last day of mon = " + today.with(TemporalAdjusters.lastDayOfMonth()));

        // Comparing
        System.out.println("birthday.isBefore(today) = " + birthday.isBefore(today));
        System.out.println("parsed.isAfter(today)    = " + parsed.isAfter(today));

        // Difference between dates
        Period period = Period.between(birthday, today);
        System.out.println("age = " + period.getYears() + "y " + period.getMonths() + "m " + period.getDays() + "d");
        System.out.println("days since birthday = " + ChronoUnit.DAYS.between(birthday, today));
        System.out.println();
    }
}

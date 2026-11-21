package lab12;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/** LocalDateTime: date + time, but still no time zone. */
public class LocalDateTimeExamples {

    public static void run() {
        System.out.println("=== LocalDateTime ===");

        // Creating
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime meeting = LocalDateTime.of(2026, Month.DECEMBER, 24, 18, 30);
        LocalDateTime combined = LocalDateTime.of(LocalDate.of(2027, 1, 1), LocalTime.of(0, 0));
        LocalDateTime parsed = LocalDateTime.parse("2026-11-21T14:05:30");
        System.out.println("now      = " + now);
        System.out.println("meeting  = " + meeting);
        System.out.println("combined = " + combined);
        System.out.println("parsed   = " + parsed);

        // Splitting into parts
        LocalDate datePart = meeting.toLocalDate();
        LocalTime timePart = meeting.toLocalTime();
        System.out.println("date part = " + datePart + ", time part = " + timePart);

        // Arithmetic
        System.out.println("meeting + 2 weeks, 90 min = " + meeting.plusWeeks(2).plusMinutes(90));
        System.out.println("meeting.withHour(9)       = " + meeting.withHour(9));
        System.out.println("atStartOfDay              = " + meeting.toLocalDate().atStartOfDay());

        // Comparing & differences
        System.out.println("meeting.isAfter(now) = " + meeting.isAfter(now));
        System.out.println("hours until new year = " + ChronoUnit.HOURS.between(now, combined));
        Duration d = Duration.between(now, combined);
        System.out.println("duration until new year = " + d.toDays() + " days " + d.toHoursPart() + " h");
        System.out.println();
    }

}

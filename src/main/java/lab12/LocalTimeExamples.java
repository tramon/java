package lab12;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/** LocalTime: a time of day without date or time zone (e.g. 14:30:15). */
public class LocalTimeExamples {

    public static void run() {
        System.out.println("=== LocalTime ===");

        // Creating
        LocalTime now = LocalTime.now();
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 30, 45);
        LocalTime parsed = LocalTime.parse("23:15");
        System.out.println("now    = " + now);
        System.out.println("start  = " + start);
        System.out.println("end    = " + end);
        System.out.println("parsed = " + parsed);
        System.out.println("MIDNIGHT = " + LocalTime.MIDNIGHT + ", NOON = " + LocalTime.NOON);

        // Reading fields
        System.out.println("hour/minute/second = " + end.getHour() + "/" + end.getMinute() + "/" + end.getSecond());

        // Immutable arithmetic (wraps around midnight)
        System.out.println("start + 3h 20m  = " + start.plusHours(3).plusMinutes(20));
        System.out.println("parsed + 1h     = " + parsed.plusHours(1));
        System.out.println("start - 10h     = " + start.minusHours(10));
        System.out.println("truncate to min = " + end.truncatedTo(ChronoUnit.MINUTES));

        // Comparing
        System.out.println("start.isBefore(end) = " + start.isBefore(end));

        // Difference
        Duration work = Duration.between(start, end);
        System.out.println("work duration = " + work);
        System.out.println("  in minutes  = " + work.toMinutes());
        System.out.println("  hours part  = " + work.toHours() + "h " + work.toMinutesPart() + "m");
        System.out.println();
    }
}

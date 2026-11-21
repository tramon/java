package lab12;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

/** Duration: an amount of time measured in seconds/nanos (hours, minutes, seconds). */
public class DurationExamples {

    public static void run() {
        System.out.println("=== Duration ===");

        // Creating
        Duration d1 = Duration.ofHours(2);
        Duration d2 = Duration.ofMinutes(90);
        Duration d3 = Duration.ofSeconds(3725);
        Duration d4 = Duration.ofMillis(1500);
        Duration parsed = Duration.parse("PT1H30M15S"); // ISO-8601 format
        System.out.println("ofHours(2)      = " + d1);
        System.out.println("ofMinutes(90)   = " + d2);
        System.out.println("ofSeconds(3725) = " + d3);
        System.out.println("ofMillis(1500)  = " + d4);
        System.out.println("parse           = " + parsed);

        // Between two moments
        LocalTime start = LocalTime.of(9, 15);
        LocalTime end = LocalTime.of(17, 45);
        Duration between = Duration.between(start, end);
        System.out.println("between 09:15 and 17:45 = " + between);

        LocalDateTime from = LocalDateTime.of(2026, 11, 20, 22, 0);
        LocalDateTime to = LocalDateTime.of(2026, 11, 22, 6, 30);
        System.out.println("between datetimes       = " + Duration.between(from, to));

        // Converting (Java 8 compatible way to get the "parts")
        long totalSeconds = d3.getSeconds();
        System.out.println("d3 total: " + d3.toHours() + " h, " + d3.toMinutes() + " min, " + totalSeconds + " s");
        System.out.println("d3 as h:m:s = " + (totalSeconds / 3600) + ":" + (totalSeconds % 3600 / 60) + ":" + (totalSeconds % 60));
        System.out.println("d4 millis   = " + d4.toMillis());

        // Arithmetic (immutable)
        System.out.println("d1 + d2          = " + d1.plus(d2));
        System.out.println("d1 - 30 minutes  = " + d1.minusMinutes(30));
        System.out.println("d2 * 2           = " + d2.multipliedBy(2));
        System.out.println("d2 / 3           = " + d2.dividedBy(3));
        System.out.println("negated          = " + d1.negated());

        // Comparing & checks
        System.out.println("d1.compareTo(d2) = " + d1.compareTo(d2));
        System.out.println("d1 > d2 ?        = " + (d1.compareTo(d2) > 0));
        System.out.println("isNegative?      = " + Duration.between(end, start).isNegative());
        System.out.println("isZero?          = " + Duration.ZERO.isZero());

        // Apply a Duration to a date-time
        System.out.println("start + d1 = " + start.plus(d1));
        System.out.println();
    }
}

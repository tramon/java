package lab12;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/** ZonedDateTime: date + time + time zone (handles offsets and daylight saving). */
public class ZonedDateTimeExamples {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");

    public static void run() {
        System.out.println("=== ZonedDateTime ===");

        // Creating
        ZonedDateTime here = ZonedDateTime.now();
        ZonedDateTime kyiv = ZonedDateTime.of(2026, 11, 21, 14, 0, 0, 0, ZoneId.of("Europe/Kyiv"));
        System.out.println("system zone now = " + here + "  (zone " + ZoneId.systemDefault() + ")");
        System.out.println("kyiv            = " + kyiv);

        // Same instant in different zones
        ZonedDateTime newYork = kyiv.withZoneSameInstant(ZoneId.of("America/New_York"));
        ZonedDateTime tokyo = kyiv.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime utc = kyiv.withZoneSameInstant(ZoneOffset.UTC);
        System.out.println("new york = " + newYork.format(FMT));
        System.out.println("tokyo    = " + tokyo.format(FMT));
        System.out.println("utc      = " + utc.format(FMT));

        // Same local time, different zone (a DIFFERENT instant)
        ZonedDateTime sameLocal = kyiv.withZoneSameLocal(ZoneId.of("America/New_York"));
        System.out.println("same local time in NY = " + sameLocal.format(FMT));
        System.out.println("equal instants? " + kyiv.toInstant().equals(sameLocal.toInstant()));

        // Offset and parts
        System.out.println("offset = " + kyiv.getOffset() + ", zone = " + kyiv.getZone());
        LocalDateTime local = kyiv.toLocalDateTime();
        System.out.println("local date-time = " + local);

        // Daylight saving: Europe/Kyiv clocks go forward 2027-03-28 at 03:00 -> 04:00
        ZonedDateTime beforeDst = ZonedDateTime.of(2027, 3, 28, 2, 30, 0, 0, ZoneId.of("Europe/Kyiv"));
        ZonedDateTime afterDst = beforeDst.plusHours(1);
        System.out.println("before DST = " + beforeDst.format(FMT) + " (" + beforeDst.getOffset() + ")");
        System.out.println("+1 hour    = " + afterDst.format(FMT) + " (" + afterDst.getOffset() + ")");

        // Day-based vs time-based arithmetic across DST
        ZonedDateTime evening = ZonedDateTime.of(2027, 3, 27, 12, 0, 0, 0, ZoneId.of("Europe/Kyiv"));
        System.out.println("plusDays(1)    = " + evening.plusDays(1).format(FMT));
        System.out.println("plusHours(24)  = " + evening.plusHours(24).format(FMT));

        // Duration between zoned values accounts for the zones
        ZonedDateTime flightDeparts = ZonedDateTime.of(2026, 12, 1, 10, 0, 0, 0, ZoneId.of("Europe/Kyiv"));
        ZonedDateTime flightArrives = ZonedDateTime.of(2026, 12, 1, 13, 0, 0, 0, ZoneId.of("America/New_York"));
        Duration flight = Duration.between(flightDeparts, flightArrives);
        System.out.println("flight duration = " + flight.toHours() + "h " + flight.toMinutesPart() + "m");

        // Instant = machine timestamp (UTC)
        Instant instant = Instant.now();
        System.out.println("instant = " + instant + " -> in Tokyo " + instant.atZone(ZoneId.of("Asia/Tokyo")).format(FMT));
        System.out.println();
    }

}

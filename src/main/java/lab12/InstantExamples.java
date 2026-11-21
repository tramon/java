package lab12;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/** Instant: a point on the UTC timeline (machine time), counted from 1970-01-01T00:00:00Z. */
public class InstantExamples {

    public static void run() {
        System.out.println("=== Instant ===");

        // Creating
        Instant now = Instant.now();
        Instant epoch = Instant.EPOCH;
        Instant fromSeconds = Instant.ofEpochSecond(1_800_000_000L);
        Instant fromMillis = Instant.ofEpochMilli(1_700_000_000_000L);
        Instant parsed = Instant.parse("2026-11-21T12:00:00Z");
        System.out.println("now         = " + now);
        System.out.println("epoch       = " + epoch);
        System.out.println("fromSeconds = " + fromSeconds);
        System.out.println("fromMillis  = " + fromMillis);
        System.out.println("parsed      = " + parsed);

        // Epoch numbers
        System.out.println("parsed epoch seconds = " + parsed.getEpochSecond());
        System.out.println("parsed epoch millis  = " + parsed.toEpochMilli());

        // Arithmetic (immutable)
        System.out.println("parsed + 90 minutes = " + parsed.plus(90, ChronoUnit.MINUTES));
        System.out.println("parsed - 1 day      = " + parsed.minus(1, ChronoUnit.DAYS));
        System.out.println("parsed + Duration   = " + parsed.plus(Duration.ofHours(5)));
        System.out.println("truncated to hours  = " + now.truncatedTo(ChronoUnit.HOURS));

        // Comparing & measuring
        System.out.println("epoch.isBefore(now) = " + epoch.isBefore(now));
        System.out.println("Duration.between(parsed, parsed+1h) = " + Duration.between(parsed, parsed.plusSeconds(3600)));
        System.out.println("years since epoch   = " + ChronoUnit.DAYS.between(epoch, now) / 365);

        // Instant <-> human time (needs a time zone)
        ZonedDateTime kyiv = parsed.atZone(ZoneId.of("Europe/Kyiv"));
        ZonedDateTime tokyo = parsed.atZone(ZoneId.of("Asia/Tokyo"));
        System.out.println("in Kyiv  = " + kyiv);
        System.out.println("in Tokyo = " + tokyo);
        LocalDateTime utcLocal = LocalDateTime.ofInstant(parsed, ZoneOffset.UTC);
        System.out.println("LocalDateTime (UTC) = " + utcLocal);
        Instant back = kyiv.toInstant();
        System.out.println("back to Instant     = " + back + ", same? " + back.equals(parsed));

        // Typical use: measuring how long code takes
        Instant begin = Instant.now();
        long sum = 0;
        for (int i = 0; i < 5_000_000; i++) {
            sum += i;
        }
        Duration elapsed = Duration.between(begin, Instant.now());
        System.out.println("loop result = " + sum + ", took " + elapsed.toMillis() + " ms (" + elapsed.toNanos() + " ns)");
        System.out.println();
    }
}

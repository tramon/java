package lab12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;

/** DateTimeFormatter: converting date/time objects to text and back. */
public class FormatterExamples {

    public static void run() {
        System.out.println("=== DateTimeFormatter ===");

        LocalDateTime dt = LocalDateTime.of(2026, 11, 21, 14, 5, 30);

        // 1. Predefined ISO formatters
        System.out.println("ISO_LOCAL_DATE      = " + dt.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("ISO_LOCAL_DATE_TIME = " + dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        // 2. Custom patterns
        DateTimeFormatter eu = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        DateTimeFormatter us = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a", Locale.US);
        DateTimeFormatter longFmt = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH);
        System.out.println("dd.MM.yyyy HH:mm:ss   = " + dt.format(eu));
        System.out.println("MM/dd/yyyy hh:mm a    = " + dt.format(us));
        System.out.println("EEEE, d MMMM yyyy     = " + dt.format(longFmt));

        // 3. Localized styles
        System.out.println("MEDIUM (US) = " + dt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.US)));
        System.out.println("LONG date (FR) = " + dt.toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRANCE)));

        // 4. Parsing text with a pattern
        LocalDate date = LocalDate.parse("25-12-2026", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDateTime parsed = LocalDateTime.parse("2026/01/05 08:15", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        System.out.println("parsed date     = " + date);
        System.out.println("parsed datetime = " + parsed);

        // 5. Invalid input -> DateTimeParseException
        try {
            LocalDate.parse("not-a-date", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Parse error: " + e.getMessage());
        }

        // 6. Zoned formatting (zone/offset pattern letters)
        ZonedDateTime zdt = ZonedDateTime.of(dt, ZoneId.of("Europe/Kyiv"));
        System.out.println("with zone = " + zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z (xxx)")));
        System.out.println("ISO_ZONED = " + zdt.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        System.out.println();
    }
}

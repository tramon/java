package lab11;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

/** Шпаргалка: java.io vs java.nio, Path/Paths, try-with-resources. */
public class IoCheatSheet {

    // 1. Байтові потоки: InputStream / OutputStream
    static void byteStreams(String file) throws IOException {
        try (OutputStream out = new FileOutputStream(file)) {
            out.write("bytes".getBytes(StandardCharsets.UTF_8));
        }
        try (InputStream in = new FileInputStream(file)) {
            int b;
            while ((b = in.read()) != -1) System.out.print((char) b);
            System.out.println();
        }
    }

    // 2. Символьні потоки: Reader / Writer (+ Buffered)
    static void readerWriter(String file) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(file))) {
            w.write("line 1");
            w.newLine();
            w.write("line 2");
        }
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = r.readLine()) != null) System.out.println(line);
        }
    }

    // 3. InputStreamReader - міст байти -> символи (з кодуванням)
    static void streamReader(String file) throws IOException {
        try (Reader r = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            int c;
            while ((c = r.read()) != -1) System.out.print((char) c);
            System.out.println();
        }
    }

    // 4. Scanner (читання) + PrintWriter (запис)
    static void scannerPrintWriter(String file) throws IOException {
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println("10 20");
            pw.printf("%s%n", "text");
        }
        try (Scanner sc = new Scanner(new File(file))) {
            int a = sc.nextInt(), b = sc.nextInt();
            sc.nextLine();
            System.out.println(a + b + " / " + sc.nextLine());
        }
        // Scanner з консолі: new Scanner(System.in)
    }

    // 5. NIO: Path + Files (найкоротший спосіб)
    static void nioFiles(Path path) throws IOException {
        Files.write(path, "nio 1\nnio 2".getBytes(StandardCharsets.UTF_8));        // записати
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);    // прочитати
        System.out.println(lines);
        Files.write(path, "appended\n".getBytes(), StandardOpenOption.APPEND);      // дописати
    }

    // 6. NIO: буферизовані reader/writer через Files
    static void nioBuffered(Path path) throws IOException {
        try (BufferedWriter w = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            w.write("buffered nio");
        }
        try (BufferedReader r = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            System.out.println(r.readLine());
        }
    }

    // 7. Paths / Path
    static void paths() {
        Path p = Paths.get("target", "lab11", "data.txt");   // Java 11+: Path.of(...)
        System.out.println(p.getFileName() + " | " + p.getParent() + " | " + p.toAbsolutePath());
        System.out.println(p.resolveSibling("other.txt"));    // сусідній файл
        System.out.println(Files.exists(p));
    }

    // 8. try-with-resources з кількома ресурсами (закриваються у зворотному порядку)
    static void copy(Path from, Path to) throws IOException {
        try (InputStream in = Files.newInputStream(from);
             OutputStream out = Files.newOutputStream(to)) {
            byte[] buf = new byte[1024];
            int n;
            while ((n = in.read(buf)) > 0) out.write(buf, 0, n);
        }
        // або одним рядком: Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
    }

    /** Єдиний метод, що запускає все. */
    public static void runAll() throws IOException {
        Path dir = Paths.get("target", "lab11");
        Files.createDirectories(dir);
        String f = dir.resolve("io.txt").toString();
        Path p = dir.resolve("nio.txt");

        System.out.println("--- byte streams ---");     byteStreams(f);
        System.out.println("--- reader/writer ---");    readerWriter(f);
        System.out.println("--- InputStreamReader ---"); streamReader(f);
        System.out.println("--- scanner/printwriter ---"); scannerPrintWriter(f);
        System.out.println("--- nio Files ---");        nioFiles(p);
        System.out.println("--- nio buffered ---");     nioBuffered(p);
        System.out.println("--- paths ---");            paths();
        System.out.println("--- copy ---");             copy(p, dir.resolve("copy.txt"));
        System.out.println("copied: " + Files.exists(dir.resolve("copy.txt")));
    }

    public static void main(String[] args) throws IOException {
        runAll();
    }
}

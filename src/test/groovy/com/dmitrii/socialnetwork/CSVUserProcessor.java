package com.dmitrii.socialnetwork;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

public class CSVUserProcessor {
    private final Set<String> usedUsernames = new HashSet<>();
    private static final List<String> HOBBIES = Arrays.asList(
        "Увлекается рукоделием и общением с внуками",
        "Увлекается волейболом",
        "Любит готовить и читать книги",
        "Увлекается садоводством и рыбалкой",
        "Любит путешествовать и фотографировать",
        "Увлекается танцами и музыкой",
        "Любит играть в теннис и шахматы",
        "Увлекается живописью и скульптурой",
        "Любит театр и классическую музыку",
        "Увлекается йогой и медитацией",
        "Любит кулинарию и винное дело",
        "Увлекается историей и археологией"
    );
    private final Random random = new Random();

    public void processCSVFile(String inputFile, String outputFile)
        throws IOException, CsvValidationException {
        List<String[]> processedData = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(inputFile, StandardCharsets.UTF_8))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length < 3) continue;

                String fullName  = nextLine[0].trim();
                String birthDate = nextLine[1].trim();
                String city      = nextLine[2].trim();

                String baseUsername = generateUsername(fullName, birthDate);
                String uniqueUsername = makeUnique(baseUsername);

                String hobby = getRandomHobby();
                processedData.add(new String[]{
                    uniqueUsername, fullName, birthDate, city, hobby
                });
            }
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(outputFile, StandardCharsets.UTF_8))) {
            writer.writeNext(new String[]{"username","fullname","birthdate","city","hobby"});
            processedData.forEach(writer::writeNext);
        }
    }

    /** Генерация базового username, без проверки уникальности */
    private String generateUsername(String fullName, String birthDate) {
        String[] parts = fullName.split("\\s+");
        String lastName  = parts[0];
        String firstName = parts.length > 1 ? parts[1] : "";
        String ln = transliterate(lastName).toLowerCase();
        String fn = transliterate(firstName).toLowerCase();
        ln = ln.length()>4 ? ln.substring(0,4) : ln;
        fn = fn.length()>4 ? fn.substring(0,4) : fn;
        String year = birthDate.substring(0,4);
        return fn + ln + "-" + year;
    }

    /** Добавляет числовой суффикс, если имя уже занято */
    private String makeUnique(String base) {
        if (!usedUsernames.contains(base)) {
            usedUsernames.add(base);
            return base;
        }
        int counter = 1;
        String candidate;
        do {
            candidate = base + "-" + counter;
            counter++;
        } while (usedUsernames.contains(candidate));
        usedUsernames.add(candidate);
        return candidate;
    }

    /** Транслитерация русских в латиницу */
    private String transliterate(String input) {
        Map<Character,String> map = Map.ofEntries(
            Map.entry('а',"a"), Map.entry('б',"b"), Map.entry('в',"v"),
            Map.entry('г',"g"), Map.entry('д',"d"), Map.entry('е',"e"),
            Map.entry('ё',"yo"),Map.entry('ж',"zh"),Map.entry('з',"z"),
            Map.entry('и',"i"), Map.entry('й',"y"), Map.entry('к',"k"),
            Map.entry('л',"l"), Map.entry('м',"m"), Map.entry('н',"n"),
            Map.entry('о',"o"), Map.entry('п',"p"), Map.entry('р',"r"),
            Map.entry('с',"s"), Map.entry('т',"t"), Map.entry('у',"u"),
            Map.entry('ф',"f"), Map.entry('х',"h"), Map.entry('ц',"c"),
            Map.entry('ч',"ch"),Map.entry('ш',"sh"),Map.entry('щ',"sch"),
            Map.entry('ы',"y"), Map.entry('э',"e"), Map.entry('ю',"yu"),
            Map.entry('я',"ya"), Map.entry('ъ',""), Map.entry('ь',"")
        );
        StringBuilder sb = new StringBuilder();
        input.toLowerCase().chars().forEach(c -> {
            char ch = (char)c;
            sb.append(map.getOrDefault(ch, Character.isLetter(ch) ? String.valueOf(ch) : ""));
        });
        return sb.toString();
    }

    /** Выбор случайного хобби */
    private String getRandomHobby() {
        return HOBBIES.get(random.nextInt(HOBBIES.size()));
    }

    @Test
    @Ignore
    public void generateData() throws IOException, CsvValidationException {
        new CSVUserProcessor().processCSVFile("./src/test/resources/people.v2.csv", "./db_import/people.csv");
        System.out.println("Генерация завершена.");
    }
}

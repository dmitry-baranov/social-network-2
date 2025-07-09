//package com.dmitrii.socialnetwork;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.HashSet;
//import java.util.Random;
//import java.util.Set;
//
//public class GenerateCSV {
//  private static final String[] FIRST_NAMES = {
//      // Female (sources [1][2][11])
//      "Olivia", "Emma", "Charlotte", "Amelia", "Mia", "Sophia", "Isabella",
//      "Evelyn", "Ava", "Sofia", "Camila", "Harper", "Luna", "Eleanor", "Violet",
//      "Aurora", "Elizabeth", "Eliana", "Hazel", "Chloe", "Ellie", "Nora", "Gianna",
//      "Lily", "Emily", "Aria", "Scarlett", "Penelope", "Zoe", "Ella", "Avery",
//      "Abigail", "Mila", "Lucy", "Isla", "Ivy", "Layla", "Grace", "Willow", "Riley",
//      "Emilia", "Naomi", "Elena", "Madison", "Valentina", "Victoria", "Stella",
//      "Delilah", "Maya", "Hannah", "Leah", "Lillian", "Genesis", "Josephine", "Sadie",
//
//      // Male (sources [3][4][19])
//      "Liam", "Noah", "Oliver", "James", "Elijah", "Mateo", "Theodore", "Henry",
//      "Lucas", "William", "Benjamin", "Levi", "Sebastian", "Jack", "Ezra", "Michael",
//      "Daniel", "Leo", "Owen", "Samuel", "Hudson", "Alexander", "Asher", "Luca",
//      "Ethan", "John", "David", "Jackson", "Joseph", "Mason", "Luke", "Matthew",
//      "Julian", "Dylan", "Elias", "Jacob", "Maverick", "Gabriel", "Logan", "Carter",
//      "Grayson", "Wyatt", "Jayden", "Lincoln", "Joshua", "Christopher", "Andrew",
//      "Ryan", "Nathan", "Isaiah", "Charles"
//  };
//
//  private static final String[] LAST_NAMES = {
//      // Sources [5][6][12][16]
//      "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller",
//      "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson",
//      "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez",
//      "Thompson", "White", "Harris", "Sanchez", "Clark", "Lewis", "Robinson", "Walker",
//      "Young", "Allen", "King", "Wright", "Scott", "Green", "Baker", "Adams", "Nelson",
//      "Hill", "Ramirez", "Campbell", "Mitchell", "Roberts", "Carter", "Phillips",
//      "Evans", "Turner", "Torres", "Parker", "Collins", "Edwards", "Stewart", "Flores",
//      "Morris", "Nguyen", "Murphy", "Rivera", "Cook", "Rogers", "Morgan", "Peterson",
//      "Cooper", "Reed", "Bailey", "Bell", "Gomez", "Kelly", "Howard", "Ward", "Cox"
//  };
//
//  private static final String[] CITIES = {
//      // Sources [7][13][17]
//      "New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia",
//      "San Antonio", "San Diego", "Dallas", "San Jose", "Austin", "Jacksonville",
//      "Fort Worth", "Columbus", "Charlotte", "San Francisco", "Indianapolis", "Seattle",
//      "Denver", "Washington", "Boston", "El Paso", "Nashville", "Detroit", "Portland",
//      "Las Vegas", "Baltimore", "Memphis", "Louisville", "Milwaukee", "Albuquerque",
//      "Tucson", "Fresno", "Sacramento", "Kansas City", "Long Beach", "Mesa", "Atlanta",
//      "Colorado Springs", "Virginia Beach", "Raleigh", "Omaha", "Miami", "Oakland",
//      "Minneapolis", "Tulsa", "Wichita", "New Orleans", "Arlington", "Tampa", "Honolulu",
//      "Aurora", "Anaheim", "Santa Ana", "Riverside", "Corpus Christi", "Lexington",
//      "Pittsburgh", "Anchorage", "Stockton", "Cincinnati", "St. Paul", "Toledo"
//  };
//  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//  public static void main(String[] args) {
//    int numberOfRecords = 1000000;
//    String fileName = "users1.csv";
//    generateCSV(numberOfRecords, fileName);
//  }
//
//  private static void generateCSV(int numberOfRecords, String fileName) {
//    Random random = new Random();
//    Set<String> usernames = new HashSet<>();
//
//    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//      for (int i = 0; i < numberOfRecords; i++) {
//        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
//        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
//        String username = generateUniqueUsername(usernames, firstName, lastName);
//        LocalDate dob = generateRandomDOB(random);
//        String city = CITIES[random.nextInt(CITIES.length)];
//
//        String line = String.format("%s,%s,%s,%s,%s", username, firstName, lastName, dob.format(DATE_FORMATTER), city);
//        writer.write(line);
//        writer.newLine();
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  private static String generateUniqueUsername(Set<String> usernames, String firstName, String lastName) {
//    String baseUsername = firstName.toLowerCase() + "-" + lastName.toLowerCase();
//    String username = baseUsername;
//    int suffix = 1;
//    while (usernames.contains(username)) {
//      username = baseUsername + suffix;
//      suffix++;
//    }
//    usernames.add(username);
//    return username;
//  }
//
//  private static LocalDate generateRandomDOB(Random random) {
//    int startYear = 1900;
//    int endYear = 2020;
//    int dayOfYear = random.nextInt(365) + 1;
//    int year = startYear + random.nextInt(endYear - startYear + 1);
//    return LocalDate.ofYearDay(year, dayOfYear);
//  }
//}

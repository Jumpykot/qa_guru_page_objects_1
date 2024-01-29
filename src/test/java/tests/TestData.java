package tests;

import com.github.javafaker.Faker;

import java.util.Locale;

import static Utils.RandomUtils.*;

public class TestData {

    static Faker faker = new Faker(new Locale("eng"));

    public static String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = getRandomEmail(),
            userAddress = getRandomAddress(),
            userPhoneNumber = getRandomPhone(),
            userGender = getRandomGender(),
            usersDayOfBirth = String.valueOf(getRandomDay()),
            usersMonthOfBirth = getRandomMonth(),
            usersYearOfBirth = String.valueOf(getRandomYear()),
            usersHobbie = getRandomHobbie(),
            usersPicture = "apple-touch-icon.png",
            usersSubject = getRandomSubject(),
            usersState = getRandomState(),
            usersCity = getRandomCity(usersState);

    public static String getRandomEmail() {
        return faker.internet().emailAddress();

    }

    public static String getRandomAddress() {
        return faker.address().streetAddress();

    }

    public static String getRandomPhone() {
        return String.format("%s%s", getRandomInt(1, 9), getRandomInt(100000000, 999999999));

    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};

        int index = getRandomInt(0, genders.length - 1);

        return genders[index];

    }

    public static int getRandomDay() {
        return getRandomInt(1, 28);

    }

    public static String getRandomMonth() {
        return faker.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December");

    }

    public static int getRandomYear() {
        return getRandomInt(1900, 2010);

    }

    public static String getRandomHobbie() {
        return faker.options().option("Sports", "Reading", "Music");

    }

    public static String getRandomSubject() {
        return faker.options().option("English", "Arts", "History", "Chemistry", "Math", "Hindi");

    }

    public static String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");

    }

    public static String getRandomCity(String usersState) {
        if (usersState.equals("NCR")) {
            return faker.options().option("Delhi", "Gurgaon", "Noida");
        }

        if (usersState.equals("Uttar Pradesh")) {
            return faker.options().option("Agra", "Lucknow", "Merrut");
        }

        if (usersState.equals("Haryana")) {
            return faker.options().option("Karnal", "Panipat");
        }

        if (usersState.equals("Rajasthan")) {
            return faker.options().option("Jaipur", "Jaiselmer");
        }

        return null;
    }

}

import java.util.Scanner;

interface Speaker {
    void speak(String message);

    void introduce();

    double convertCurrency(double amount, String targetCurrency);
}

class EnglishSpeaker implements Speaker {
    public void speak(String message) {
        System.out.println("English: " + message);
    }

    public void introduce() {
        System.out.println("I am an English speaker.");
    }



    public double convertCurrency(double amount, String targetCurrency) {
        double conversionRate = 1.2; // Placeholder conversion rate
        return amount * conversionRate;
    }
}

class FrenchSpeaker implements Speaker {
    public void speak(String message) {
        System.out.println("French: " + message);
    }

    public void introduce() {
        System.out.println("Je suis un locuteur français.");
    }


    public double convertCurrency(double amount, String targetCurrency) {
        double conversionRate = 0.8; // Placeholder conversion rate
        return amount * conversionRate;
    }
}

class LanguageAdapter implements Speaker {
    private FrenchSpeaker frenchSpeaker;

    LanguageAdapter(FrenchSpeaker frenchSpeaker) {
        this.frenchSpeaker = frenchSpeaker;
    }

    public void speak(String message) {
        String translatedMessage = "Translation: " + message;
        frenchSpeaker.speak(translatedMessage);
    }

    public void introduce() {
        frenchSpeaker.introduce();
    }


    public double convertCurrency(double amount, String targetCurrency) {
        speak("I want to convert " + amount + " USD to " + targetCurrency);
        double convertedAmount = frenchSpeaker.convertCurrency(amount, targetCurrency);
        speak("Converted amount: " + convertedAmount + " " + targetCurrency);
        return convertedAmount;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Speaker englishSpeaker = new EnglishSpeaker();
        Speaker frenchSpeaker = new FrenchSpeaker();
        Speaker adapter = new LanguageAdapter((FrenchSpeaker) frenchSpeaker);

        System.out.print("Enter the amount in USD: ");
        double amountInUSD = scanner.nextDouble();

        englishSpeaker.introduce();
        frenchSpeaker.introduce();

        adapter.convertCurrency(amountInUSD, "EUR");

        scanner.close();
    }
}

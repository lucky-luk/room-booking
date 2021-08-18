package roomBooking.api.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DataFormatValidator {

    public boolean validateEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validateRating(int rate) {
        return rate > 0 && rate <= 6;
    }

    public boolean validateNipNumber(String nipNumber) {
        String regex = "\\d{10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nipNumber);
        return matcher.matches();
    }

    public boolean validateRegonNumber(String regonNumber) {
        String regex = "\\d{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(regonNumber);
        return matcher.matches();
    }

    public boolean validatePostalCode(String postalCode) {
        String regex = "\\d{2}[-]\\d{3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }
}

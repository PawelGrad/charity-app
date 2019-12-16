package pl.coderslab.charity.Utils;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.Model.UserEntity.UserEntity;
import pl.coderslab.charity.Model.UserEntity.UserServiceImp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {

    public static Boolean checkPwd(String str) {
            Boolean match = false;
            Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,})");
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                match = true;
            }
            return match;
    }

}

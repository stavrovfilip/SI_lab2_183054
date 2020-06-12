import java.util.List;

class User {
    String username;
    String password;
    String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

public class SILab2 {

    public static boolean function(User user, List<String> allUsers) {
        String specialCharacters="!#$%&'()*+,-./:;<=>?@[]^_`{|}";
        if (user==null) {
            throw new RuntimeException("The user is not instantiated");
        }
        if (user.getUsername()==null || user.getPassword()==null)
            throw new RuntimeException("The user is missing some mandatory information");
        String password = user.getPassword();
        String passwordLower = password.toLowerCase();
        if (passwordLower.contains(user.getUsername().toLowerCase())) {
            return false;
        }
        else if (passwordLower.length()<8)
            return false;
        else {
            boolean digit = false, upper = false, special = false;

            for (int i=0;i<password.length();i++) {
                if (Character.isDigit(password.charAt(i)))
                    digit = true;
                if (Character.isUpperCase(password.charAt(i)))
                    upper = true;
                if (specialCharacters.contains(String.valueOf(password.charAt(i))))
                    special = true;
            }
            if (!digit || !upper || !special)
                return false;
        }
        return true;
    }
}

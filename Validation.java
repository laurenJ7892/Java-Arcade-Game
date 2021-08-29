public class Validation
{
    public static boolean isBlank(String inputString)
    {
        boolean stringBlank = inputString.isBlank() ? true : false;
        return stringBlank;
    }

    public static boolean isNumber(String inputNumberCheck)
    {
        boolean isNumber = inputNumberCheck.matches(".*\\d.*") ? true : false;
        return isNumber;
    }

    public static boolean isSpecialCharacter(String inputSpecialCheck)
    {
        boolean isSpecial = inputSpecialCheck.matches("[~@#$%^&*:;<>.,/}{+]+") ? true : false;
        return isSpecial;
    }

        public static boolean levelCheck(String inputLevel)
    {
        boolean levelCk;
        switch (inputLevel.trim().toLowerCase())
        {
            case "easy":
                levelCk = true;
                break;
            case "medium":
                levelCk = true;
                break;
            case "hard":
                levelCk = true;
                break;
            default:
                levelCk = false;
                break;
        }
        return levelCk;
    }

    public static boolean stringLengthCheck(String inputName)
    {
       boolean stringLength = (inputName.trim().length() < 7 || inputName.trim().length() > 12) ? false : true;
       return stringLength;
    }

    public static boolean vehicleOptionCheck(int vehicleSize, int userOption)
    {
        boolean userOptionCheck = (userOption > vehicleSize) ? true : false;
        return userOptionCheck;
    }
}
/**
 * @author Fouvez Dorian.
 */
package api.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jakarta.ws.rs.core.Response.Status;

public class PatternChecker {

  /**
   * Check if the String to check is matching the regex. Throw BusinessException if doesn't match.
   * 
   * @param regex the regex to match.
   * @param toCheck the string to check.
   * @param errorMessage the error message id doesn't match.
   */
  public static void checkPattern(String regex, String toCheck, String errorMessage) {
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(toCheck);
    if (!matcher.find()) {
      throw new BusinessException(errorMessage, Status.BAD_REQUEST);
    }
  }

  public static void checkEmailPattern(String email) {
    checkPattern("^[\\w\\.\\/\\\\$é~#èà&=+*-]+@[\\w\\.]+\\.[a-zA-Z]{2,4}$", email,
        "The gived email is not matching a the pattern of an email.");
  }

  public static void checkFacebookPattern(String facebook) {
    checkPattern("^https:\\/\\/www\\.facebook\\.com\\/[\\w\\.\\/\\\\$é~#èà&=+*-]+$", facebook,
        "The facebook link is not matching a the pattern of facebook link.");
  }

  public static void checkInstagramPattern(String instagram) {
    checkPattern("^https:\\/\\/www\\.instagram\\.com\\/[\\w\\.\\/\\\\$é~#èà&=+*-]+\\/$", instagram,
        "The instagram link is not matching a the pattern of instagram link.");
  }

  public static void checkInstagramPseudoPattern(String instagramPseudo) {
    checkPattern("^@[\\w\\.\\/\\\\$é~#èà&=+*-]+$", instagramPseudo,
        "The instagram pseudo is not matching a the pattern of instagram pseudo.");
  }

  public static void checkTwitterPattern(String twitter) {
    checkPattern("^https:\\/\\/www\\.twitter\\.com\\/[\\w\\.\\/\\\\$é~#èà&=+*-]+$", twitter,
        "The twitter link is not matching a the pattern of twitter link.");
  }

  public static void checkYoutubePattern(String youtube) {
    checkPattern("^https:\\/\\/www\\.youtube\\.com\\/channel\\/[\\w\\.\\/\\\\$é~#èà&=+*-]+$",
        youtube, "The youtube link is not matching a the pattern of youtube link.");
  }

}

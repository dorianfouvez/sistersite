package api.utils;

public class Escaper {

  public static String deleteTagsHtml(String toEscape) {
    return toEscape.replace("<", " < ").replace(">", " > ");
  }

}

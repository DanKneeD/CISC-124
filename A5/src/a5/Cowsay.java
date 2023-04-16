package a5;

import java.util.List;

public class Cowsay {

  public static void printWrappedMessage(
    List<String> lines,
    TextJustifier j,
    int width
  ) {
    for (String line : lines) {
      System.out.println(j.justify(line, width));
    }
  }

  public static void printCow() {
    String cow =
      "       \\\n" +
      "        \\   ^__^\n" +
      "         \\  (oo)\\_______\n" +
      "            (__)\\\\       )\\\\/\\\\\n" +
      "                ||----w |\n" +
      "                ||     ||\n";
    System.out.println(cow);
  }

  public static void main(String[] args) {
    String msg = "Of course you have a purpose -- to find a purpose.";

    // you can change the wrapper and its width on the next line
    AbstractStringWrapper w = new SpacesWrapper(msg, 15);
    w.wrap();

    // you can change the justifier on the next line
    TextJustifier j = new CenterJustifier();

    // you can change the column width on the next line
    int width = 20;
    printWrappedMessage(w.getLines(), j, width);
    printCow();
  }
}

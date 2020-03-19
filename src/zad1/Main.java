/**
 *
 *  @author Adarczyn Piotr S19092
 *
 */

package zad1;


import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class Main {
  public static void main(String[] args) {
    Service s = new Service("Poland");
    String weatherJson = s.getWeather("Warsaw");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();
    // ...



    // część uruchamiająca GUI
  }
}

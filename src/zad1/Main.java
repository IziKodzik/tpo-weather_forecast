/**
 *
 *  @author Adarczyn Piotr S19092
 *
 */

package zad1;


import javax.swing.*;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    Service s = new Service("Poland");
    String weatherJson = s.getWeather("Warsaw");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();
    // ...

    System.out.println(weatherJson);
    System.out.println(rate1);
    System.out.println(rate2);
    SwingUtilities.invokeLater(

              () -> {
                try {

                  ClientInterface.createGUI(s);

                }catch (IOException e){e.printStackTrace();}
    }
    );


    // część uruchamiająca GUI
  }
}

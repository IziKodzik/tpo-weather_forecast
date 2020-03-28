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
    Service s = new Service("Italy");
    String weatherJson = s.getWeather("Rome");
    Double rate1 = s.getRateFor("THB");
    Double rate2 = s.getNBPRate();
    // ...

    System.out.println(weatherJson + "<- json");
    System.out.println(rate1 + " CURR");
    System.out.println(rate2 + " NBP");
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

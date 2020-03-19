package zad1;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public
	class ClientInterface {

	public static void createGUI(Service service) throws IOException {

		JFrame window = new JFrame("ŁEDERFORKAST");
		window.getContentPane().setLayout(new GridLayout(3,1));

		JLabel jLabelWea = new JLabel(service.getWeather(service.city));
		JLabel jLabelr1 = new JLabel(String.valueOf(service.getRateFor(service.currencyToCompare)));
		JLabel jLabelr2 = new JLabel(String.valueOf(service.getNBPRate()));

		window.getContentPane().add(jLabelWea);
		window.getContentPane().add(jLabelr1);
		window.getContentPane().add(jLabelr2);


		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setSize(640,480);
		window.setVisible(true);
		createWiki(service);


	}

	private static void createWiki(Service service) {

		System.out.println(service);

		JFXPanel wikiPanel = new JFXPanel();
		JFrame window = new JFrame("WIKIA");
		Platform.runLater(()->{

			WebView webView = new WebView();
			WebEngine engine = webView.getEngine();
			engine.load("https://"+service.isoCode+".wikipedia.org/wiki/" + service.city);

			Pane root = new FlowPane();
			root.getChildren().addAll(webView);
			root.autosize();
			Scene scene = new Scene(root);


			wikiPanel.setScene(scene);
			window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			SwingUtilities.invokeLater(()->{
				window.add(wikiPanel);
				window.pack();
				window.setVisible(true);
				window.setResizable(false);
			});

		});
	}


}

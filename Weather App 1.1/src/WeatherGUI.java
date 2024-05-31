import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class WeatherGUI extends JFrame {
    private JSONObject weatherData;
    private JButton lightButton, darkButton, changeTheme;
    private JPanel panel;
    private JTextField searchTextField;
    private JLabel titleText;
    private JLabel temperatureText;
    private JLabel weatherConditionDesc;
    private JLabel humidityText;
    private JLabel humidityDefText;
    private JLabel forecastD1Text;
    private JLabel forecastD2Text;
    private JLabel forecastD3Text;



    public WeatherGUI(){
        // setup gui and add a title
        super("Weather App");

        // configure gui to end the program's process once it has been closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // set the size of gui in pixels
        setSize(550, 650);

        // load gui at the center of the screen
        setLocationRelativeTo(null);

        // make layout manager null to manually position components within the gui
        setLayout(null);

        // prevent any resize of gui
        setResizable(false);

        addGuiComponents();
    }

    private void setLightMode() {
        getContentPane().setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);
        // Adjust text colors
        humidityText.setForeground(Color.BLACK);
        humidityDefText.setForeground(Color.BLACK);
        humidityText.repaint();
        humidityDefText.repaint();
    }

    private void setDarkMode() {
        getContentPane().setBackground(new Color(1, 1, 1));
        panel.setBackground(new Color(30, 30, 30));
        // Set background color of all components to a dark color
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JComponent) {
                ((JComponent) component).setOpaque(true);
                ((JComponent) component).setBackground(new Color(30, 30, 30));
            }
        }
    }

    private void addGuiComponents(){
        // Light and dark mode switch buttons
        // changeTheme = new JButton("Change Theme");
        lightButton = new JButton("Light Mode");
        darkButton = new JButton("Dark Mode");

       // changeTheme.setBounds(15, 300, 100, 30);
        lightButton.setBounds(15, 300, 100, 30);
        darkButton.setBounds(15, 350, 100, 30);

        lightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLightMode();
                /*  if (panel.getBackground() == Color.DARK_GRAY){
                   titleText.setForeground(Color.WHITE);
                   temperatureText.setForeground(Color.WHITE);
                   weatherConditionDesc.setForeground(Color.WHITE);
                   humidityText.setForeground(Color.WHITE);
                   humidityDefText.setForeground(Color.WHITE);
                   forecastD1Text.setForeground(Color.WHITE);
                   forecastD2Text.setForeground(Color.WHITE);
                   forecastD3Text.setForeground(Color.WHITE);
                   repaint();
               }
               else if (panel.getBackground() == Color.WHITE){
                   titleText.setForeground(Color.BLACK);
                   temperatureText.setForeground(Color.BLACK);
                   weatherConditionDesc.setForeground(Color.BLACK);
                   humidityText.setForeground(Color.BLACK);
                   humidityDefText.setForeground(Color.BLACK);
                   forecastD1Text.setForeground(Color.BLACK);
                   forecastD2Text.setForeground(Color.BLACK);
                   forecastD3Text.setForeground(Color.BLACK);
                   repaint();
                } */

            }
        });

      darkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDarkMode();
            }
        });

       // add(changeTheme);
       add(lightButton);
       add(darkButton);

        // search field
        JTextField searchTextField = new JTextField();

        // set the location and size of text field
        searchTextField.setBounds(15, 15, 451, 45);

        // change font style and size
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(searchTextField);

        // weather image
        JLabel weatherConditionImage = new JLabel(loadImage("src/assets/cloudy.png"));
        weatherConditionImage.setBounds(35, 125, 450, 217);
        add(weatherConditionImage);

        // title text
        JLabel titleText = new JLabel("Weather All Around");
        titleText.setBounds(50, 80, 450, 54);
        titleText.setFont(new Font("Dialog", Font.BOLD, 40));
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleText);

        // temperature text
        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setBounds(50, 350, 450, 54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));

        // temperature text in fahrenheit
        JLabel temperatureFahText = new JLabel("<html>Fahrenheit: <br>(50 °F)</html>");
        temperatureFahText.setBounds(400, 350, 200, 54);
        temperatureFahText.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(temperatureFahText);

        // center the text
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        // weather condition description
        JLabel weatherConditionDesc = new JLabel("Cloudy");
        weatherConditionDesc.setBounds(50, 405, 450, 36);
        weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);

        // humidity image
        JLabel humidityImage = new JLabel(loadImage("src/assets/humidity.png"));
        humidityImage.setBounds(15, 525, 74, 66);
        add(humidityImage);

        // humidity text
        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
        humidityText.setBounds(90, 525, 85, 55);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(humidityText);

        // humidity definition
        JButton humidityButton = new JButton("Info");
        humidityButton.setToolTipText("The concentration of water in the air");
        humidityButton.setBounds(40, 550, 20, 20);
        humidityButton.setOpaque(false);
        humidityButton.setBackground(new Color(0,0,0,0));
        add(humidityButton);

        // precipitation image
        JLabel precipitationImage = new JLabel(loadImage("src/assets/precipitation.png"));
        precipitationImage.setBounds(15, 460, 74, 66);
        add(precipitationImage);

        // precipitation text
        JLabel precipitationText = new JLabel("<html><b>Precipitation</b> 100%</html>");
        precipitationText.setBounds(90, 460, 120, 55);
        precipitationText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(precipitationText);

        // precipitation definition
        JButton precipitationButton = new JButton("Info");
        precipitationButton.setToolTipText("The probability of rainfall");
        precipitationButton.setBounds(40, 480, 20, 20);
        precipitationButton.setOpaque(false);
        precipitationButton.setBackground(new Color(0,0,0,0));
        add(precipitationButton);

        // forecast image
        JLabel windspeedImage = new JLabel(loadImage("src/assets/windspeed.png"));
        windspeedImage.setBounds(200, 505, 74, 66);
        add(windspeedImage);

        // forecast definition
        JButton forecastButton = new JButton("Info");
        forecastButton.setToolTipText("The temperatures in the next 5 days");
        forecastButton.setBounds(200, 505, 74, 66);
        forecastButton.setOpaque(false);
        forecastButton.setBackground(new Color(0,0,0,0));
        add(forecastButton);

        // forecast day 1 text
        JLabel forecastD1Text = new JLabel("<html><b>5-day Forecast</b> Tom - </html>");
        forecastD1Text.setBounds(290, 480, 120, 90);
        forecastD1Text.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(forecastD1Text);

        // forecast day 2 text
        JLabel forecastD2Text = new JLabel("<html>2 days - </html>");
        forecastD2Text.setBounds(290, 510, 120, 90);
        forecastD2Text.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(forecastD2Text);

        // forecast day 3 text
        JLabel forecastD3Text = new JLabel("<html>3 days - </html>");
        forecastD3Text.setBounds(290, 530, 120, 90);
        forecastD3Text.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(forecastD3Text);

        // forecast day 4 text
        JLabel forecastD4Text = new JLabel("<html>4 days - </html>");
        forecastD4Text.setBounds(410, 490, 120, 90);
        forecastD4Text.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(forecastD4Text);

        // forecast day 5 text
        JLabel forecastD5Text = new JLabel("<html>5 days - </html>");
        forecastD5Text.setBounds(410, 510, 120, 90);
        forecastD5Text.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(forecastD5Text);

        // search button
        JButton searchButton = new JButton(loadImage("src/assets/search.png"));

        // change the cursor to a hand cursor when hovering over search button
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(475, 13, 47, 45);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get location from user
                String userInput = searchTextField.getText();

                // validate input - remove whitespace to ensure non-empty text
                if (userInput.replaceAll("\\s", "").length() <= 0) {
                    return;
                }

                // retrieve weather data
                weatherData = WeatherApp.getWeatherData(userInput);

                // update gui

                // update weather image
                String weatherCondition = (String) weatherData.get("weather_condition");

                // update weather image depending on the condition
                switch (weatherCondition) {
                    case "Clear":
                        weatherConditionImage.setIcon(loadImage("src/assets/clear.png"));
                        break;
                    case "Cloudy":
                        weatherConditionImage.setIcon(loadImage("src/assets/cloudy.png"));
                        break;
                    case "Rain":
                        weatherConditionImage.setIcon(loadImage("src/assets/rain.png"));
                        break;
                    case "Snow":
                        weatherConditionImage.setIcon(loadImage("src/assets/snow.png"));
                        break;
                }

                // update temperature text
                double temperature = (double) weatherData.get("temperature");
                temperatureText.setText(temperature + " °C");

                // update fahrenheit temperature text
                double temperatureFahrenheit = (double) weatherData.get("temperature");
                double tempF = (temperature * 1.8) + 32;
                DecimalFormat df = new DecimalFormat("#.#");
                String formatted = df.format(tempF);
                temperatureFahText.setText("<html>Fahrenheit: <br>(" + formatted + "°F)");

                // update weather condition text
                weatherConditionDesc.setText(weatherCondition);

                // update humidity text
                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Humidity</b> " + humidity + "%</html>");

                // update precipitation text
                long precipitation = (long) weatherData.get("precipitation");
                precipitationText.setText("<html><b>Precipitation</b> " + precipitation + "%</html>");

                // update forecast day 1-5 text
                double forecastD1 = (double) weatherData.get("forecastD1");
                forecastD1Text.setText("<html><b>5-day Forecast</b> Tom - " + forecastD1 + "°C" + "</html>");

                double forecastD2 = (double) weatherData.get("forecastD2");
                forecastD2Text.setText("2 days - " + forecastD2 + "°C");

                double forecastD3 = (double) weatherData.get("forecastD3");
                forecastD3Text.setText("3 days - " + forecastD3 + "°C");

                double forecastD4 = (double) weatherData.get("forecastD4");
                forecastD4Text.setText("4 days - " + forecastD4 + "°C");

                double forecastD5 = (double) weatherData.get("forecastD5");
                forecastD5Text.setText("5 days - " + forecastD5 + "°C");
            }
        });
        add(searchButton);
    }

     /* if (panel.getBackground() == Color.DARK_GRAY){
            titleText.setForeground(Color.WHITE);
            temperatureText.setForeground(Color.WHITE);
            weatherConditionDesc.setForeground(Color.WHITE);
            humidityText.setForeground(Color.WHITE);
            humidityDefText.setForeground(Color.WHITE);
            forecastD1Text.setForeground(Color.WHITE);
            forecastD2Text.setForeground(Color.WHITE);
            forecastD3Text.setForeground(Color.WHITE);
            repaint();
        }
        else if (panel.getBackground() == Color.WHITE){
            titleText.setForeground(Color.BLACK);
            temperatureText.setForeground(Color.BLACK);
            weatherConditionDesc.setForeground(Color.BLACK);
            humidityText.setForeground(Color.BLACK);
            humidityDefText.setForeground(Color.BLACK);
            forecastD1Text.setForeground(Color.BLACK);
            forecastD2Text.setForeground(Color.BLACK);
            forecastD3Text.setForeground(Color.BLACK);
            repaint();
        } */

    // create images in gui
    private ImageIcon loadImage(String resourcePath){
        try{
            // read image file from path given
            BufferedImage image = ImageIO.read(new File(resourcePath));

            // returns image icon to be rendered
            return new ImageIcon(image);
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Could not find resource");
        return null;
    }
}
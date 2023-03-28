package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LogoUI extends JPanel {

    JLabel picLabel;
    BufferedImage logo;
    Image scaledImage;
    private static final String IMAGE_STORE = "./data/logo.png";

    public LogoUI() {
        init();
    }

    private void init() {
        setLayout(new FlowLayout());
        setMinimumSize(new Dimension(MainMenuUI.WIDTH, MainMenuUI.HEIGHT));

        try {
            logo = ImageIO.read(new File(IMAGE_STORE));
        } catch (IOException e) {
            picLabel.setText("Could not load image");
        }

        scaledImage = logo.getScaledInstance(MainMenuUI.WIDTH, MainMenuUI.HEIGHT, Image.SCALE_SMOOTH);

        picLabel = new JLabel(new ImageIcon(scaledImage));
        add(picLabel, BorderLayout.CENTER);
    }


}

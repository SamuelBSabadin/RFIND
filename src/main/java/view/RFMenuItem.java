package view;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class RFMenuItem extends JMenuItem {
    private Font quicksand;
    private Color verydarkgray;
    public RFMenuItem(String text){
        super(text);
        try{
            InputStream fontePrincipal = RFMenu.class.getResourceAsStream("/Fonts/Quicksand/static/Quicksand-regular.ttf");
            quicksand = Font.createFont(Font.TRUETYPE_FONT, fontePrincipal);
            quicksand = quicksand.deriveFont(Font.PLAIN, 14);
        }catch(Exception e){
            System.out.println("Excessão na fonte: "+e.getMessage());
        }
        verydarkgray = new Color(32,35,42);

        setForeground(Color.WHITE);
        setBackground(verydarkgray);
        setFont(quicksand);
    }
}

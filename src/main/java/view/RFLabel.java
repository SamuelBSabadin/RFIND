package view;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.IOException;

public class RFLabel extends JLabel{
    private Font quicksand;
    public RFLabel(String texto)
    {
        super(texto);
        try{
            InputStream fontePrincipal = RFLabel.class.getResourceAsStream("/Fonts/Quicksand/static/Quicksand-Regular.ttf");
            quicksand = Font.createFont(Font.TRUETYPE_FONT,fontePrincipal);
            quicksand = quicksand.deriveFont(Font.PLAIN,24);
        }
        catch(Exception e){
            System.out.println("Excessão na fonte: "+e.getMessage());
        }
        setForeground(Color.WHITE);
        setFont(quicksand);
    }
}
package view;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class RFTable extends JTable {
    private Font quicksand;
    private Color verydarkgray;
    public RFTable(){
        verydarkgray = new Color(32,35,42);
        try{
            InputStream fontePrincipal = RFTable.class.getResourceAsStream("/Fonts/Quicksand/static/Quicksand-Regular.ttf");
            quicksand = Font.createFont(Font.TRUETYPE_FONT,fontePrincipal);
            quicksand = quicksand.deriveFont(Font.PLAIN,14);
        }
        catch(Exception e){
            System.out.println("Excessão na fonte: "+e.getMessage());
        }
        setFont(quicksand);
        setForeground(Color.WHITE);
        setBackground(verydarkgray);
        getTableHeader().setFont(quicksand);
        getTableHeader().setForeground(Color.WHITE);
        getTableHeader().setBackground(Color.DARK_GRAY);
    }
}

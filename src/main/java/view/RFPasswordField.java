package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.InputStream;

public class RFPasswordField extends JPasswordField{
    private Font quicksand;
    private int borderRadius;
    public RFPasswordField(){
        try{
            InputStream fontePrincipal = RFPasswordField.class.getResourceAsStream("/Fonts/Quicksand/static/Quicksand-Regular.ttf");
            quicksand = Font.createFont(Font.TRUETYPE_FONT,fontePrincipal);
            quicksand = quicksand.deriveFont(Font.PLAIN,15);
        }
        catch(Exception e){
            System.out.println("Excessão na fonte: "+e.getMessage());
        }
        this.borderRadius = 15;
        setForeground(Color.BLACK);
        setBackground(Color.LIGHT_GRAY);//ou Color.WHITE
        setFont(quicksand);
        setPreferredSize(new Dimension(360,25));
        setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g.create();
        // Ativa anti-aliasing para bordas suaves
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),borderRadius,borderRadius));
        g2d.dispose();
        super.paintComponent(g);
    }
    @Override
    protected void paintBorder(Graphics g){
        Graphics2D g2d = (Graphics2D)g.create();
        // Ativa anti-aliasing para bordas suaves
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.draw(new RoundRectangle2D.Double(0,0,getWidth()-1,getHeight()-1,borderRadius,borderRadius));
        g2d.dispose();
    }
}
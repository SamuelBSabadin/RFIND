package view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class RFScrollBar extends BasicScrollBarUI {
    private Color thumbColor;
    private Color trackColor;
    public RFScrollBar(){
        thumbColor = Color.LIGHT_GRAY;
        trackColor = Color.DARK_GRAY;
    }
    @Override
    protected void configureScrollBarColors(){
        thumbColor = this.thumbColor;
    }
    @Override
    protected JButton createIncreaseButton(int orientation){
        return createZeroButton();
    }

    @Override
    protected JButton createDecreaseButton(int orientation){
        return createZeroButton();
    }

    private JButton createZeroButton(){
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setFocusable(false);
        button.setBorder(null);
        button.setBackground(trackColor);
        return button;
    }
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(thumbColor);
        g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(trackColor);
        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }
}
package view;

import javax.swing.*;
import java.awt.*;

public class RFMessageDialog extends JDialog {
    private RFLabel messageLabel;
    private Color verygarkgray;

    public RFMessageDialog(Frame parent, String message, String title) {
        super(parent, title, true);
        setLayout(new BorderLayout());
        setSize(500, 250);
        setLocationRelativeTo(parent);
        messageLabel = new RFLabel(message);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(messageLabel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        RFButton okButton = new RFButton("OK");
        okButton.addActionListener(e -> dispose()); // Fecha o diálogo ao clicar
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);

        verygarkgray = new Color(32,35,42);
        getContentPane().setBackground(verygarkgray);
    }

    public static void showMessageDialog(Component parent, String message, String title) {
        Frame parentFrame = JOptionPane.getFrameForComponent(parent);
        RFMessageDialog dialog = new RFMessageDialog(parentFrame, message, title);
        dialog.setVisible(true);
    }
}

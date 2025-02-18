package src.presentation.components.errors;

import java.awt.Color;

import javax.swing.JTextField;

public class ErrorComponent extends JTextField {
    
    public ErrorComponent(String message) {
        super(message); 
        setEditable(false);
        setOpaque(false);
        setFocusable(false);
        setVisible(false);
        setBorder(null);
        
    }

    public ErrorComponent() {
        this("");
    }

    // Use this method to display an error message
    public void displayErrorMessage(String message) {
        setText(message);
        setVisible(true);
        setForeground(Color.RED); 
        repaint();
        revalidate();
    }

    public void displaySuccessMessage(String message) {
        setText(message);
        setVisible(true);
        setForeground(Color.GREEN);
        repaint();
        revalidate();
    }

    // Use this method to hide the error message component when there are no errors to show
    public void hideErrorMessage() {
        setVisible(false);
    }
}

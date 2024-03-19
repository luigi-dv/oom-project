package src.presentation.components.buttons;

import src.presentation.Router;
import src.presentation.components.ui.ScaledIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationIconButton extends JButton {
    private final String routeName;
    private final Router router;

    public NavigationIconButton(String iconPath, String routeName, Router router) {
        this.routeName = routeName;
        this.router = router;
        setIcon(new ScaledIcon(iconPath, 22, 22));
        addActionListener(new ButtonClickListener());
    }

    /**
     * The ButtonClickListener class is an action listener for the icon button.
     * It defines the action to be taken when the button is clicked based on the button type.
     */
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            router.switchTo(routeName);
        }
    }
}

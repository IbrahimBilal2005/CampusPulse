package views.signup;

import interface_adapter.choose_account_type.AccountTypeController;
import interface_adapter.choose_account_type.AccountTypeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChooseAccountTypeView extends JPanel implements ActionListener, PropertyChangeListener {

    private AccountTypeController accountTypeController;

    public ChooseAccountTypeView(AccountTypeViewModel accountTypeViewModel) {
        accountTypeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(AccountTypeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();

        JButton cancel = new JButton(AccountTypeViewModel.CANCEL_BUTTON_LABEL);
        JButton generalUser = new JButton(AccountTypeViewModel.GENERAL_USER_BUTTON_LABEL);
        JButton eventPoster = new JButton(AccountTypeViewModel.EVENT_POSTER_BUTTON_LABEL);

        buttons.add(cancel);
        buttons.add(generalUser);
        buttons.add(eventPoster);

        generalUser.addActionListener(
                evt -> accountTypeController.switchToGeneralUserSignupView()
        );

        eventPoster.addActionListener(
                evt -> accountTypeController.switchToGeneralUserSignupView()
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

        @Override
        public void actionPerformed(ActionEvent evt) {
            JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
        // not implemented
        }

    public String getViewName() {
        return "Choose Account Type";
    }

    public void setAccountTypeController(AccountTypeController accountTypeController) {
        this.accountTypeController = accountTypeController;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Choose Account Type Screen");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Add the view to the frame
            AccountTypeViewModel accountTypeViewModel1 = new AccountTypeViewModel();
            frame.add(new ChooseAccountTypeView(accountTypeViewModel1));
            frame.setVisible(true);
        });
    }
}

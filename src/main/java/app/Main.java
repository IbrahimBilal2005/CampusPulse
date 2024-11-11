package app;

import javax.swing.*;
import java.awt.*;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;

public class Main {
    public static void main(String[] args) {
        JPanel cardPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        UserFactory userFactory = new CommonUserFactory(); // You can inject a different implementation if needed
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

        AppBuilder appBuilder = new AppBuilder(
                cardPanel,
                cardLayout,
                userFactory,
                viewManagerModel,
                userDataAccessObject
        );

        JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addLoggedInView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addChangePasswordUseCase()
                .addLogoutUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}

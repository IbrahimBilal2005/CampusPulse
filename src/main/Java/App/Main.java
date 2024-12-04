package App;

import data_access.EventDAO;
import data_access.InMemoryUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.UserCreationStrategy;
import interface_adapter.ViewManagerModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserCreationStrategy userFactory = new UserCreationStrategy();
            ViewManagerModel viewManagerModel = new ViewManagerModel();
            InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
            EventDAO eventDAO = new EventDAO(); // Initialize your EventDAO as needed

            AppBuilder appBuilder = new AppBuilder(userFactory, viewManagerModel, userDataAccessObject, eventDAO);
            appBuilder
                    .addChooseAccountTypeView()
                    .addGeneralUserSignupView()
                    .addEventPosterSignupView()
                    .addSignupUseCase()
                    .addMyEventsScreen()
                    .addNewEventPostUseCase()
                    .addHomeScreen()
                    .addEventSearchingUseCase()
                    .addEventFilteringUseCase()
                    .addEventSortingUseCase()
                    .addMyEventsScreen()
                    .addDeleteEventUseCase();

            JFrame applicationFrame = appBuilder.build();
            applicationFrame.setVisible(true);
        });
    }
}
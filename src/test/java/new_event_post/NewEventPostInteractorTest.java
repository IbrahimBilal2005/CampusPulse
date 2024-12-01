package new_event_post;

import data_access.InMemoryEventDataAccess;
import entity.Event;
import interface_adapter.ViewManagerModel;
import interface_adapter.new_event_post.NewEventPostInState;
import interface_adapter.new_event_post.NewEventPostPresenter;
import interface_adapter.new_event_post.NewEventViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.new_event_post.NewEventPostInputData;
import use_case.new_event_post.NewEventPostInteractor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewEventPostInteractorTest {

    private NewEventPostInteractor interactor;
    private InMemoryEventDataAccess dataAccess;
    private NewEventPostPresenter presenter;
    private NewEventViewModel viewModel;
    private ViewManagerModel viewManager;

    @BeforeEach
    void setUp() {
        // Initialize components
        dataAccess = new InMemoryEventDataAccess();
        viewManager = new ViewManagerModel();
        viewModel = new NewEventViewModel();
        presenter = new NewEventPostPresenter(viewModel, viewManager);

        // Initialize interactor
        interactor = new NewEventPostInteractor(presenter, dataAccess);
    }

    @Test
    void testSuccessfulEventCreation() {
        // Arrange
        NewEventPostInputData inputData = new NewEventPostInputData(
                "Tech Talk",
                "A seminar on AI and ML",
                "Conference Hall",
                "10-10-2024 05:00",
                "10-10-2024 17:00",
                "Technology",
                "Education"
        );

        // Act
        interactor.execute(inputData);
        // Assert: Event is added to data store
        assertTrue(dataAccess.existsByName("Tech Talk"));
    }

    @Test
    void testInvalidStartTime() {
        // Arrange
        NewEventPostInputData inputData = new NewEventPostInputData(
                "Tech Talk",
                "A seminar on AI and ML",
                "Conference Hall",
                "invalid-date",
                "10-10-2024 17:00",
                "Technology",
                "Education"
        );

        // Act
        interactor.execute(inputData);

        // Assert: ViewModel is updated with error
        NewEventPostInState state = viewModel.getState();
        assertEquals("Start time Error", state.getStartError());
        assertNull(state.getEndError());

        // Assert: Event is not added
        assertFalse(dataAccess.existsByName("Tech Talk"));
    }

    @Test
    void testStartTimeAfterEndTime() {
        // Arrange
        NewEventPostInputData inputData = new NewEventPostInputData(
                "Tech Talk",
                "A seminar on AI and ML",
                "Conference Hall",
                "10-10-2024 18:00", // Start time
                "10-10-2024 15:00", // End time
                "Technology",
                "Education"
        );

        // Act
        interactor.execute(inputData);

        // Assert
        NewEventPostInState state = viewModel.getState();
        assertEquals("Start time Error", state.getStartError()); // Check for the error message
        assertFalse(dataAccess.existsByName("Tech Talk")); // Event should not exist
    }

    @Test
    void testInvalidEndTime() {
        // Arrange
        NewEventPostInputData inputData = new NewEventPostInputData(
                "Tech Talk",
                "A seminar on AI and ML",
                "Conference Hall",
                "10-10-2024 15:00",
                "invalid-date",
                "Technology",
                "Education"
        );

        // Act
        interactor.execute(inputData);

        // Assert: ViewModel is updated with error
        NewEventPostInState state = viewModel.getState();
        assertNull(state.getStartError());
        assertEquals("End time Error", state.getEndError());

        // Assert: Event is not added
        assertFalse(dataAccess.existsByName("Tech Talk"));
    }

    @Test
    void testEmptyEventName() {
        // Arrange
        NewEventPostInputData inputData = new NewEventPostInputData(
                "",
                "A seminar on AI and ML",
                "Conference Hall",
                "10-10-2024 15:00",
                "10-10-2024 17:00",
                "Technology",
                "Education"
        );

        // Act
        interactor.execute(inputData);

        // Assert
        NewEventPostInState state = viewModel.getState();
        assertEquals("Event name is empty or already exists.", state.getEventNameError()); // Check for the error message
        assertFalse(dataAccess.existsByName("")); // Ensure the event was not added
    }

    @Test
    void testDuplicateEventName() {
        // Arrange
        dataAccess.addEvent(new Event(
                "Tech Talk",
                "Existing description",
                "Existing location",
                null,
                null,
                List.of("Technology")
        ));

        NewEventPostInputData inputData = new NewEventPostInputData(
                "Tech Talk",
                "Another seminar",
                "Another Hall",
                "10-10-2024 15:00",
                "10-10-2024 17:00",
                "Technology",
                "Education"
        );

        // Act
        interactor.execute(inputData);

        // Assert: ViewModel is updated with error
        NewEventPostInState state = viewModel.getState();
        assertEquals("Event name is empty or already exists.", state.getEventNameError());

        // Assert: No new event added
        assertTrue(dataAccess.existsByName("Tech Talk"));
    }

    @Test
    void testEmptyLocation() {
        // Arrange
        NewEventPostInputData inputData = new NewEventPostInputData(
                "Tech Talk",
                "A seminar on AI and ML",
                "",
                "10-10-2024 15:00",
                "10-10-2024 17:00",
                "Technology",
                "Education"
        );

        // Act
        interactor.execute(inputData);

        // Assert: ViewModel is updated with error
        NewEventPostInState state = viewModel.getState();
        assertEquals("Location is required.", state.getLocationError());

        // Assert: Event is not added
        assertFalse(dataAccess.existsByName("Tech Talk"));
    }
}

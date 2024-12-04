package new_event_post;

import data_access.InMemoryEventDataAccess;
import data_access.InMemoryUserDataAccessObject;
import entity.Event;
import entity.EventPoster;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete_event.MyEventsViewModel;
import interface_adapter.new_event_post.NewEventPostInState;
import interface_adapter.new_event_post.NewEventPostPresenter;
import interface_adapter.new_event_post.NewEventViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.new_event_post.NewEventPostInputData;
import use_case.new_event_post.NewEventPostInteractor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewEventPostInteractorTest {

    private NewEventPostInteractor interactor;
    private InMemoryEventDataAccess dataAccess;
    private NewEventPostPresenter presenter;
    private NewEventViewModel viewModel;
    private ViewManagerModel viewManager;
    private MyEventsViewModel myEventsViewModel;

    @BeforeEach
    void setUp() {
        // Initialize components
        dataAccess = new InMemoryEventDataAccess();
        viewManager = new ViewManagerModel();
        viewModel = new NewEventViewModel();
        myEventsViewModel = new MyEventsViewModel();
        presenter = new NewEventPostPresenter(viewModel, viewManager, myEventsViewModel);

        // Initialize interactor
        interactor = new NewEventPostInteractor(presenter,dataAccess, dataAccess);
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
                "Education",
                "abcd"
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
                "Education",
                "abcd"
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
                "Education",
                "abcd"
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
                "Education",
                "abcd"
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
                "Education",
                "abcd"
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
        EventPoster predefinedPoster = new EventPoster(
                "abcd",
                "password123",
                "TechCorp",
                "www.soplink.com",
                new HashMap<>()
        );

        Event existingEvent = new Event(
                "Tech Talk",
                "Existing description",
                "Existing location",
                LocalDateTime.of(2024, 10, 10, 5, 0),
                LocalDateTime.of(2024, 10, 10, 6, 0),
                List.of("Technology")
        );

        predefinedPoster.getEvents().put("Tech Talk", existingEvent);
        dataAccess.addEvents(existingEvent);
        dataAccess.addtoMyevents(existingEvent, "abcd");

        NewEventPostInputData inputData = new NewEventPostInputData(
                "Tech Talk",                              // Duplicate Event Name
                "Another seminar",                        // Description
                "Another Hall",                           // Location
                "10-10-2024 15:00",                       // Start Time
                "10-10-2024 17:00",                       // End Time
                "Technology",                             // Tag 1
                "Education",                              // Tag 2
                "abcd"                                    // Username
        );

        // Act: Execute the interactor
        interactor.execute(inputData);

        // Assert: ViewModel is updated with error
        NewEventPostInState state = viewModel.getState();
        assertEquals("Event name is empty or already exists.", state.getEventNameError());
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
                "Education",
                "abcd"
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

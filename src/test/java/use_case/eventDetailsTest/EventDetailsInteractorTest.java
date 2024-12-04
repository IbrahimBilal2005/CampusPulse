package use_case.eventDetailsTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.event_details.EventDetailsInputData;
import use_case.event_details.EventDetailsInteractor;
import use_case.event_details.EventDetailsOutputBoundary;
import use_case.event_details.EventDetailsOutputData;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class EventDetailsInteractorTest {
    private EventDetailsInteractor interactor;
    private EventDetailsOutputBoundary mockPresenter;

    private boolean successCalled;

    @BeforeEach
    void setUp() {
        mockPresenter = new EventDetailsOutputBoundary() {

            @Override
            public void changeView(EventDetailsOutputData data) {
                if (data.isClick()) {
                    successCalled = true;
                }
            }
        };

        interactor = new EventDetailsInteractor(mockPresenter);
        successCalled = false;
    }

    @Test
    void testButtonclick() {
        EventDetailsInputData inputData = new EventDetailsInputData(true);
        interactor.execute(inputData);
        assertTrue(successCalled);
    }

}

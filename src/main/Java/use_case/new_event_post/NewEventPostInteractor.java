package use_case.new_event_post;

import entity.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class NewEventPostInteractor implements NewEventPostInputBoundary{
    private final NewEventPostOutputBoundary presenter;
    private final NewEventPostUserDataAccessInterface dataAccess;

    private static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("d-M-yyyy H:mm");

    public NewEventPostInteractor(NewEventPostOutputBoundary presenter,
                                  NewEventPostUserDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(NewEventPostInputData inputData) {
        // Parse and validate the start and end dates
        LocalDateTime start = parseDateTime(inputData.getStart());
        if (start == null) {
            presenter.presentFailure("Error: Invalid start date and time format. Please use DD-MM-YYYY HH:mm.");
            return;
        }

        LocalDateTime end = parseDateTime(inputData.getEnd());
        if (end == null) {
            presenter.presentFailure("Error: Invalid end date and time format. Please use DD-MM-YYYY HH:mm.");
            return;
        }

        if (start.isAfter(end)) {
            presenter.presentFailure("Error: Start time must be before end time.");
            return;
        }

        if (dataAccess.existsByName(inputData.getEventName())) {
            presenter.presentFailure("Error: Event with this name already exists.");
            return;
        }

        if (inputData.getEventName().isEmpty() || inputData.getLocation().isEmpty()) {
            presenter.presentFailure("Error: Event name and location are required.");
            return;
        }

        // Populate tags
        List<String> tags = new ArrayList<>();
        if (inputData.getTag1() != null && !inputData.getTag1().isEmpty()) {
            tags.add(inputData.getTag1());
        }
        if (inputData.getTag2() != null && !inputData.getTag2().isEmpty()) {
            tags.add(inputData.getTag2());
        }

        // Create the Event entity
        Event newEvent = new Event(
                inputData.getEventName(),
                inputData.getDescription(),
                inputData.getLocation(),
                start,
                end,
                tags
        );

        // Add the event to the data store
        dataAccess.addEvent(newEvent);
        presenter.presentSuccess(new NewEventPostOutputData(false, "Event Posted"));

    }

    /**
     * Parses a date-time string into LocalDateTime using the custom format.
     *
     * @param dateTimeStr the date-time string
     * @return LocalDateTime object if the format is valid; otherwise, null
     */
    private LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, CUSTOM_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }


}

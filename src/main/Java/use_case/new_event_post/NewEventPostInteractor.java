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

    private static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:MM");

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
            presenter.presentFailure("Start time Error");
            return;
        }

        LocalDateTime end = parseDateTime(inputData.getEnd());
        if (end == null) {
            presenter.presentFailure("End time Error");
            return;
        }

        if (start.isAfter(end)) {
            presenter.presentFailure("Start time Error");
            return;
        }

        if (dataAccess.existsByName(inputData.getEventName())) {
            presenter.presentFailure("Event name is empty or already exists.");
            return;
        }

        if (inputData.getEventName().isEmpty()) {
            presenter.presentFailure("Event name is empty or already exists.");
            return;
        }

        if (inputData.getLocation().isEmpty()) {
            presenter.presentFailure("Location is required.");
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
        presenter.presentSuccess(new NewEventPostOutputData(false));

    }

    @Override
    public void switchToHomeView(){
        presenter.switchToHomeView();
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

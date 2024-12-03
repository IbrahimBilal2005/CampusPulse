package use_case.sort;

import entity.Event;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The interactor for the sort use case.
 */
public class SortInteractor implements SortInputBoundary {
    private final SortOutputBoundary sortOutputBoundary;

    public SortInteractor(SortOutputBoundary sortOutputBoundary) {
        this.sortOutputBoundary = sortOutputBoundary;
    }

    @Override
    public void sort(SortInputData inputData) {
        String sortQuery = inputData.getSortQuery();
        List<Event> allEvents = inputData.getCurrentEvents();

        if (sortQuery.isEmpty()) {
            if (allEvents.isEmpty()) {
                sortOutputBoundary.prepareFailView("No events found");
            } else {
                sortOutputBoundary.prepareSuccessView(new SortOutputData(allEvents));
            }
            return;
        }

        List<Event> sortedFilteredEvents;

        switch (sortQuery) {
            case "Earliest":
                sortedFilteredEvents = allEvents.stream()
                        .sorted(Comparator.comparing(Event::getStart))
                        .collect(Collectors.toList());
                break;

            case "Latest":
                sortedFilteredEvents = allEvents.stream()
                        .sorted(Comparator.comparing(Event::getStart).reversed())
                        .collect(Collectors.toList());
                break;

            case "Longest":
                sortedFilteredEvents = allEvents.stream()
                        .sorted((event1, event2) -> {
                            Duration duration1 = Duration.between(event1.getStart(), event1.getEnd());
                            Duration duration2 = Duration.between(event2.getStart(), event2.getEnd());
                            return duration2.compareTo(duration1); // Reversed for longest first
                        })
                        .collect(Collectors.toList());
                break;


            case "Shortest":
                sortedFilteredEvents = allEvents.stream()
                        .sorted(Comparator.comparing(event -> Duration.between(event.getStart(), event.getEnd())))
                        .collect(Collectors.toList());
                break;

            default:
                sortOutputBoundary.prepareFailView("Invalid sort query");
                return;
        }

        sortOutputBoundary.prepareSuccessView(new SortOutputData(sortedFilteredEvents));
    }
}
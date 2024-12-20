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

    /**
     Constructs a SortInteractor with the specified output boundary.
     * @param sortOutputBoundary The output boundary used for preparing the success or fail view.
     */
    public SortInteractor(SortOutputBoundary sortOutputBoundary) {
        this.sortOutputBoundary = sortOutputBoundary;
    }

    /**
     * Use the specified input data to execute the sort use case.
     * @param inputData sort input data.
     */
    @Override
    public void sort(SortInputData inputData) {
        String sortQuery = inputData.getSortQuery();
        List<Event> currentEvents = inputData.getCurrentEvents();

        if (sortQuery.isEmpty()) {
            if (currentEvents.isEmpty()) {
                sortOutputBoundary.prepareFailView("No events found");
            } else {
                sortOutputBoundary.prepareSuccessView(new SortOutputData(currentEvents));
            }
            return;
        }

        List<Event> sortedFilteredEvents;

        switch (sortQuery) {
            case "Earliest":
                sortedFilteredEvents = currentEvents.stream()
                        .sorted(Comparator.comparing(Event::getStart))
                        .collect(Collectors.toList());
                break;

            case "Latest":
                sortedFilteredEvents = currentEvents.stream()
                        .sorted(Comparator.comparing(Event::getStart).reversed())
                        .collect(Collectors.toList());
                break;

            case "Longest":
                sortedFilteredEvents = currentEvents.stream()
                        .sorted((event1, event2) -> {
                            Duration duration1 = Duration.between(event1.getStart(), event1.getEnd());
                            Duration duration2 = Duration.between(event2.getStart(), event2.getEnd());
                            return duration2.compareTo(duration1); // Reversed for longest first
                        })
                        .collect(Collectors.toList());
                break;


            case "Shortest":
                sortedFilteredEvents = currentEvents.stream()
                        .sorted(Comparator.comparing(event -> Duration.between(event.getStart(), event.getEnd())))
                        .collect(Collectors.toList());
                break;

            default:
                sortedFilteredEvents = currentEvents;
        }

        sortOutputBoundary.prepareSuccessView(new SortOutputData(sortedFilteredEvents));
    }
}
package use_case.search;

import entity.Event;

import java.util.List;

public interface SearchOutputBoundary {
    /**
     * Presents the successful removal of a friend.
     *
     * @param user the data representing the outcome of the friend removal process,
     *             including the users involved and the status of the operation.
     */
    void setPassView(SearchOutputData user);

    /**
     * Presents the failure of the friend removal process with an appropriate error message.
     *
     * @param error the error message explaining why the friend removal process failed.
     */
    void setFailView(String error);

    SearchOutputData getOutputData();String getErrorMessage();


}

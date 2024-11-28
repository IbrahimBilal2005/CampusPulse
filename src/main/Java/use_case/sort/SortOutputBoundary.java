package use_case.sort;

public interface SortOutputBoundary {

    void setPassView(SortOutputData user);
    void setFailView(String error);
}
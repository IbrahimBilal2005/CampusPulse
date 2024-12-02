package use_case.filter;

public interface FilterOutputBoundary {
    void setPassView(FilterOutputData outputData);
    void setFailView(String error);
}
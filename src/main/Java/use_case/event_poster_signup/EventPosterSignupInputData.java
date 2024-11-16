package use_case.event_poster_signup;

public class EventPosterSignupInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;
    private final String organizationName;
    private final String sopLink;

    public EventPosterSignupInputData(String username,
                                      String password,
                                      String repeatPassword,
                                      String organizationName,
                                      String sopLink) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.organizationName = organizationName;
        this.sopLink = sopLink;
    }

    String getUsername() { return username; }
    String getPassword() { return password; }
    String getRepeatPassword() { return repeatPassword; }
    String getOrganizationName() { return organizationName; }
    String getSopLink() { return sopLink; }

}

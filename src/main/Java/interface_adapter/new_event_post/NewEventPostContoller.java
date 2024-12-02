package interface_adapter.new_event_post;

import use_case.new_event_post.NewEventPostInputBoundary;
import use_case.new_event_post.NewEventPostInputData;

public class NewEventPostContoller {
   private NewEventPostInputBoundary inputBoundary;

   public NewEventPostContoller(NewEventPostInputBoundary inputBoundary) {
       this.inputBoundary = inputBoundary;
   }

    public void execute(String eventName, String description,
                        String location, String start, String end,
                        String tag1, String tag2, String username) {
        NewEventPostInputData inputData = new NewEventPostInputData(
                 eventName, description, location, start, end, tag1, tag2, username
        );
        inputBoundary.execute(inputData);
    }

    public void switchToHomeView(){inputBoundary.switchToHomeView();}
}

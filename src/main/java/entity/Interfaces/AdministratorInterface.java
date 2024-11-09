package entity.Interfaces;

import entity.EventPoster;

public interface AdministratorInterface extends EventPosterInterface {
    void approveEventPoster(EventPoster eventPoster);
}

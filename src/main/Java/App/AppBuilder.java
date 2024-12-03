package App;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.EventDAO;
import data_access.InMemoryUserDataAccessObject;
import entity.AccountCreationStrategy;
import entity.UserCreationStrategy;
import interface_adapter.ViewManagerModel;
import interface_adapter.admin_approval.AdminApprovalController;
import interface_adapter.admin_approval.AdminApprovalPresenter;
import interface_adapter.admin_approval.AdminApprovalState;
import interface_adapter.admin_approval.AdminApprovalViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.delete_event.DeleteEventController;
import interface_adapter.delete_event.DeleteEventPresenter;
import interface_adapter.delete_event.MyEventsViewModel;
import interface_adapter.event_details.EventDetailsController;
import interface_adapter.event_details.EventDetailsPresenter;
import interface_adapter.filter.FilterController;
import interface_adapter.filter.FilterPresenter;
import interface_adapter.filter.FilterViewModel;
import interface_adapter.home.HomeScreenViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.new_event_post.NewEventPostContoller;
import interface_adapter.new_event_post.NewEventPostPresenter;
import interface_adapter.new_event_post.NewEventViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.choose_account_type.AccountTypeController;
import interface_adapter.signup.choose_account_type.AccountTypePresenter;
import interface_adapter.signup.choose_account_type.AccountTypeViewModel;
import interface_adapter.signup.event_poster_signup.EventPosterSignupController;
import interface_adapter.signup.event_poster_signup.EventPosterSignupPresenter;
import interface_adapter.signup.event_poster_signup.EventPosterSignupViewModel;
import interface_adapter.signup.general_user_signup.UserSignupController;
import interface_adapter.signup.general_user_signup.UserSignupPresenter;
import interface_adapter.signup.general_user_signup.UserSignupViewModel;
import interface_adapter.sort.SortController;
import interface_adapter.sort.SortPresenter;
import interface_adapter.sort.SortViewModel;
import use_case.admin_account_approval.AdminApprovalInputBoundary;
import use_case.admin_account_approval.AdminApprovalInteractor;
import use_case.admin_account_approval.AdminApprovalOutputBoundary;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.delete_event.DeleteEventInputBoundary;
import use_case.delete_event.DeleteEventInteractor;
import use_case.delete_event.DeleteEventOutputBoundary;
import use_case.event_details.EventDetailsInputBoundary;
import use_case.event_details.EventDetailsInteractor;
import use_case.event_details.EventDetailsOutputBoundary;
import use_case.filter.FilterInputBoundary;
import use_case.filter.FilterInteractor;
import use_case.filter.FilterOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.new_event_post.NewEventPostInputBoundary;
import use_case.new_event_post.NewEventPostInteractor;
import use_case.new_event_post.NewEventPostOutputBoundary;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import use_case.signup.general_user_signup.UserSignupInputBoundary;
import use_case.signup.general_user_signup.UserSignupInteractor;
import use_case.signup.general_user_signup.UserSignupOutputBoundary;
import use_case.signup.event_poster_signup.EventPosterSignupInteractor;
import use_case.signup.event_poster_signup.EventPosterSignupOutputBoundary;
import use_case.signup.event_poster_signup.EventPosterSignupInputBoundary;
import use_case.signup.choose_account_type.AccountTypeInteractor;
import use_case.signup.choose_account_type.AccountTypeOutputBoundary;
import use_case.signup.choose_account_type.AccountTypeInputBoundary;
import use_case.sort.SortInputBoundary;
import use_case.sort.SortInteractor;
import use_case.sort.SortOutputBoundary;
import views.ChangePasswordView;
import views.Home_screen;
import views.Login_screen;
import views.signup.BaseSignupView;
import views.signup.ChooseAccountTypeView;
import views.signup.EventPosterSignupView;
import views.signup.GeneralUserSignupView;
import views.ViewManager;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */

public class AppBuilder {

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final UserCreationStrategy userFactory;
    private final ViewManagerModel viewManagerModel;
    private final ViewManager viewManager;
    private final InMemoryUserDataAccessObject userDataAccessObject;
    private final EventDAO eventDAO;

    // Views and ViewModels
    private ChooseAccountTypeView chooseAccountTypeView;
    private GeneralUserSignupView generalUserSignupView;
    private EventPosterSignupView eventPosterSignupView;
    private BaseSignupView baseSignupView;
    private EventPosterSignupViewModel eventPosterSignupViewModel;
    private AccountTypeViewModel accountTypeViewModel;
    private UserSignupViewModel userSignupViewModel;
    private HomeScreenViewModel homeScreenViewModel;
    private ChangePasswordViewModel changePasswordViewModel;
    private NewEventViewModel newEventViewModel;
    private MyEventsViewModel myEventsViewModel;
    private SearchViewModel searchViewModel;
    private FilterViewModel filterViewModel;
    private SortViewModel sortViewModel;
    private AdminApprovalViewModel adminApprovalViewModel;

    private AdminApprovalState adminApprovalState;

    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private ChangePasswordView loggedInView;
    private Login_screen loginView;

    public AppBuilder(UserCreationStrategy userFactory, ViewManagerModel viewManagerModel,
                      InMemoryUserDataAccessObject userDataAccessObject, EventDAO eventDAO) {
        this.userFactory = userFactory;
        this.viewManagerModel = viewManagerModel;
        this.userDataAccessObject = userDataAccessObject;
        this.eventDAO = eventDAO;
        this.viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addChooseAccountTypeView() {
        accountTypeViewModel = new AccountTypeViewModel();
        chooseAccountTypeView = new ChooseAccountTypeView(accountTypeViewModel);
        cardPanel.add(chooseAccountTypeView, chooseAccountTypeView.getViewName());
        return this;
    }

    public AppBuilder addGeneralUserSignupView() {
        userSignupViewModel = new UserSignupViewModel();
        generalUserSignupView = new GeneralUserSignupView(userSignupViewModel);
        cardPanel.add(generalUserSignupView, generalUserSignupView.getViewName());
        return this;
    }

    public AppBuilder addEventPosterSignupView() {
        eventPosterSignupViewModel = new EventPosterSignupViewModel();
        eventPosterSignupView = new EventPosterSignupView(eventPosterSignupViewModel);
        cardPanel.add(eventPosterSignupView, eventPosterSignupView.getViewName());
        return this;
    }

    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new Login_screen(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    public AppBuilder addHomeScreen() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new ChangePasswordView(changePasswordViewModel);
        cardPanel.add(loggedInView);
        return this;
    }

    public AppBuilder addSignupUseCase() {
        // Add signup for the chosen account type
        final UserSignupOutputBoundary userSignupOutputBoundary = new UserSignupPresenter(viewManagerModel, userSignupViewModel, loginViewModel);

        final EventPosterSignupOutputBoundary eventPostersignupOutputBoundary = new EventPosterSignupPresenter(eventPosterSignupViewModel, viewManagerModel,
                loginViewModel);

        // For general user signup
        final UserSignupInputBoundary userSignupInteractor = new UserSignupInteractor(
                userDataAccessObject, userSignupOutputBoundary, userFactory);

        final UserSignupController userSignupController = new UserSignupController(userSignupInteractor);
        generalUserSignupView.setAccountTypeController(userSignupController);

        // For event poster signup
        final EventPosterSignupInputBoundary eventPosterSignupInteractor = new EventPosterSignupInteractor(
                userDataAccessObject, eventPostersignupOutputBoundary, userFactory);

        final EventPosterSignupController eventPosterSignupController = new EventPosterSignupController(eventPosterSignupInteractor);
        eventPosterSignupView.setAccountTypeController(eventPosterSignupController);

        return this;
    }

    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loginViewModel, viewManagerModel,
                homeScreenViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(changePasswordViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    public AppBuilder addEventPostingUseCase() {
        final NewEventPostOutputBoundary eventPostingOutputBoundary = new NewEventPostPresenter(newEventViewModel, viewManagerModel, myEventsViewModel);
        final NewEventPostInputBoundary eventPostingInteractor = new NewEventPostInteractor(eventPostingOutputBoundary, userDataAccessObject);

        final NewEventPostContoller eventPostingController = new NewEventPostContoller(eventPostingInteractor);
        eventPosterSignupView.setEventPostingController(eventPostingController);
        return this;
    }

    public AppBuilder addEventSearchingUseCase() {
        final SearchOutputBoundary eventSearchOutputBoundary = new SearchPresenter(searchViewModel, viewManagerModel);
        final SearchInputBoundary eventSearchInteractor = new SearchInteractor(eventDAO, eventSearchOutputBoundary);
        final SearchController eventSearchController = new SearchController(eventSearchInteractor);
        homeScreenViewModel.setEventSearchController(eventSearchController);
        return this;
    }

    public AppBuilder addEventFilteringUseCase() {
        final FilterOutputBoundary eventFilteringOutputBoundary = new FilterPresenter(filterViewModel,viewManagerModel);
        final FilterInputBoundary eventFilteringInteractor = new FilterInteractor(eventDAO, eventFilteringOutputBoundary);
        final FilterController eventFilteringController = new FilterController(eventFilteringInteractor);
        homeScreenViewModel.setEventFilteringController(eventFilteringController);
        return this;
    }

    public AppBuilder addEventSortingUseCase() {
        final SortOutputBoundary eventSortOutputBoundary = new SortPresenter(sortViewModel,viewManagerModel);
        final SortInputBoundary eventSortingInteractor = new SortInteractor(eventSortOutputBoundary);
        final SortController eventSortingController = new SortController(eventSortingInteractor);
        homeScreenViewModel.setEventFilteringController(eventSortingController);
        return this;
    }

    public AppBuilder addEventDetailsUseCase() {
        final EventDetailsOutputBoundary eventDetailsOutputBoundary = new EventDetailsPresenter(viewManagerModel, homeScreenViewModel);
        final EventDetailsInputBoundary eventDetailsInteractor = new EventDetailsInteractor(eventDetailsOutputBoundary);
        final EventDetailsController eventDetailsController = new EventDetailsController(eventDetailsInteractor);
        homeScreenViewModel.setEventDetailsController(eventDetailsController);
        return this;
    }

    public AppBuilder addDeleteEventUseCase() {
        final DeleteEventOutputBoundary deleteEventOutputBoundary = new DeleteEventPresenter(viewManagerModel, myEventsViewModel);
        final DeleteEventInputBoundary deleteEventInteractor = new DeleteEventInteractor(userDataAccessObject, deleteEventOutputBoundary);
        final DeleteEventController deleteEventController = new DeleteEventController(deleteEventInteractor);
        homeScreenViewModel.setDeleteEventController(deleteEventController);
        return this;
    }

    public AppBuilder addAdminAccountApprovalUseCase() {
        final AdminApprovalOutputBoundary adminAccountApprovalOutputBoundary = new AdminApprovalPresenter(adminApprovalState);
        final AdminApprovalInputBoundary adminAccountApprovalInteractor = new AdminApprovalInteractor(adminAccountApprovalOutputBoundary, userDataAccessObject);
        final AdminApprovalController adminAccountApprovalController = new AdminApprovalController(adminAccountApprovalInteractor);
        homeScreenViewModel.setAdminApprovalController(adminAccountApprovalController);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Signup Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);

        viewManagerModel.setState(chooseAccountTypeView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}


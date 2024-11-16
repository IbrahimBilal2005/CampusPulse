package use_case.signup.choose_account_type;

/**
 * The Input Data for the Pick Account Type Use Case.
 */
public class AccountTypeInputData {

    private String account_type;

    public AccountTypeInputData(String account_type) {
        this.account_type = account_type;
    }

    String getAccount_type() {
        return account_type;
    }
}

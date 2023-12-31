package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account UserRegister(Account account) {
        if (account.username == "")
            return null;
        if (account.password.length() < 4)
            return null;
        return accountDAO.UserRegister(account);
    }

    public Account UserLogin(Account account) {
        return accountDAO.UserLogin(account);
    }

}

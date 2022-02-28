package pt.upskill.projeto2.financemanager.accounts;

import pt.upskill.projeto2.financemanager.accounts.Account;
import pt.upskill.projeto2.financemanager.accounts.BanksConstants;

public class DraftAccount extends Account {
    public DraftAccount(long id, String name) {
        super(id, name);
    }

    public double getInterestRate() {
        return BanksConstants.normalInterestRate();
    }


}
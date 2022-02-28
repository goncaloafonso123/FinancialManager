package pt.upskill.projeto2.financemanager.accounts;

import pt.upskill.projeto2.financemanager.categories.Category;



public class SavingsAccount extends Account {
    public static Category savingsCategory = new Category("savings");


    public SavingsAccount(long id, String name) {
        super(id, name);
    }
    @Override
    public double getInterestRate() {
        return BanksConstants.savingsInterestRate();
    }
}




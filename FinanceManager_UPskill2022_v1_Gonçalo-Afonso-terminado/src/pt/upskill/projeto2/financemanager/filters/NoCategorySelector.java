package pt.upskill.projeto2.financemanager.filters;

import pt.upskill.projeto2.financemanager.accounts.StatementLine;
import pt.upskill.projeto2.financemanager.filters.Selector;

public class NoCategorySelector implements Selector<StatementLine> {

    @Override
    public boolean isSelected(StatementLine item) {
        if (item.getCategory() == null) {
            return true;
        } else {
            return false;
        }
    }
}



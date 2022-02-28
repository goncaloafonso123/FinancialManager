package pt.upskill.projeto2.financemanager.filters;

import pt.upskill.projeto2.financemanager.accounts.StatementLine;
import pt.upskill.projeto2.financemanager.date.Date;

public class BeforeDateSelector {
    private Date date;
    public BeforeDateSelector(Date date) {
        this.date=date;
    }

    public boolean isSelected(StatementLine statementLine) {
        if(statementLine.getDate().before(date)){
        return true;
        }else{
        return false;
        }
    }
}

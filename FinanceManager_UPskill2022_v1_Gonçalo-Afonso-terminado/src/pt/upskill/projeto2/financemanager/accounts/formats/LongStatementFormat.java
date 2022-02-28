package pt.upskill.projeto2.financemanager.accounts.formats;

import pt.upskill.projeto2.financemanager.accounts.StatementLine;
import pt.upskill.projeto2.financemanager.accounts.formats.StatementLineFormat;

public class LongStatementFormat implements StatementLineFormat {
    @Override
    public String format(StatementLine objectToFormat) {
        return objectToFormat.getDate()+" \t"+objectToFormat.getValueDate()+" \t"+objectToFormat.getDescription()+" \t"+objectToFormat.getDraft()+" \t"+objectToFormat.getCredit()+" \t"+objectToFormat.getAccountingBalance()+" \t"+objectToFormat.getAvailableBalance();
    }

    @Override
    public String fields() {
        return "Date \tValue Date \tDescription \tDraft \tCredit \tAccounting balance \tAvailable balance ";
    }
}

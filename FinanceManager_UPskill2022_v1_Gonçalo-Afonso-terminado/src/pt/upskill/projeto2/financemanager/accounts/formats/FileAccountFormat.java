package pt.upskill.projeto2.financemanager.accounts.formats;

import pt.upskill.projeto2.financemanager.accounts.Account;
import pt.upskill.projeto2.financemanager.accounts.StatementLine;
import pt.upskill.projeto2.financemanager.accounts.formats.Format;
import pt.upskill.projeto2.financemanager.date.Date;

public class FileAccountFormat implements Format<Account> {
    public String format(Account objectToFormat) {


       String firstLine = "Account Info - " + new Date();
       String SecondLine= "Account  ;" + objectToFormat.getId() + " ; "+objectToFormat.getCurrency()+"  ;"+objectToFormat.getName()+ " ;"+objectToFormat.getAccountType()+";";
       String ThirdLine= "Start Date ;"+objectToFormat.getStartDate();
       String ForthLine= "End Date ;"+objectToFormat.getEndDate();
       String FifthLine="Date ;Value Date ;Description ;Draft ;Credit ;Accounting balance ;Available balance";
       String StatementLine1="";

       for(StatementLine statementLine : objectToFormat.getStatementLines()){
           StatementLine1 = StatementLine1 +statementLine.getDate()+" ;"
           +statementLine.getValueDate()+" ;"+statementLine.getDescription()+" ;"
           +statementLine.getDraft()+" ;" +statementLine.getCredit()+" ;"
           +statementLine.getAccountingBalance()+" ;"+statementLine.getAvailableBalance()+System.lineSeparator();
       }


       return firstLine +System.lineSeparator()
               +SecondLine+System.lineSeparator()
               +ThirdLine+System.lineSeparator()
               +ForthLine+System.lineSeparator()
               +FifthLine+System.lineSeparator()
               +StatementLine1;

    }
}

package pt.upskill.projeto2.financemanager.accounts;

import pt.upskill.projeto2.financemanager.categories.Category;
import pt.upskill.projeto2.financemanager.date.Date;
import pt.upskill.projeto2.financemanager.filters.BeforeDateSelector;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public abstract class Account {

    private String name;
    private long id;
    private Date startDate;
    private Date endDate;
    private int interestRate;
    private double currentBalance;
    private double estimatedAverageBalance;
    private List<StatementLine> statementLines;
    private String currency;
    private String accountType;


    public Account(long id, String name) {
        this.name = name;
        this.id = id;
        this.currentBalance = currentBalance();
        this.currency = currency;
        this.accountType = accountType;
        this.estimatedAverageBalance = estimatedAverageBalance();
        statementLines = new ArrayList<StatementLine>();
    }

    public List<StatementLine> getStatementLines() {
        return statementLines;
    }


    public static Account newAccount(File file) {

        Account acc = null;

        try {
            Scanner fileReader = new Scanner(file);
            int lineCounter = 0;
            while (fileReader.hasNextLine()) {

                String line = fileReader.nextLine();

                if (line.equals("")) {
                    break;
                }

                if (lineCounter == 0) {
                    String[] firstLine = line.split("-");
                    Integer dia = Integer.parseInt(firstLine[1].trim());
                    Integer mes = Integer.parseInt(firstLine[2].trim());
                    Integer ano = Integer.parseInt(firstLine[3].trim());

                    // System.out.println(" " + firstLine[1] + " " + firstLine[2] + " " + firstLine[3]);
                }
                if (lineCounter == 1) {
                    String[] secondLine = line.split(";");

                    long id = Long.parseLong(secondLine[1].trim());
                    String currency = secondLine[2].trim();
                    String name = secondLine[3].trim();
                    String accountType = secondLine[4];
                    if (accountType.equals("DraftAccount")) {
                        acc = new DraftAccount(id, name);
                    } else {
                        acc = new SavingsAccount(id, name);
                    }
                    acc.setCurrency(secondLine[2].trim());
                    acc.setAccountType(secondLine[4]);


                }
                if (lineCounter == 2) {
                    String[] thirdLine = line.split(";");
                    String[] dataString = thirdLine[1].split("-");
                    Integer day = Integer.parseInt(dataString[0]);
                    Integer month = Integer.parseInt(dataString[1]);
                    Integer year = Integer.parseInt(dataString[2]);
                    Date startDate = new Date(day, month, year);
                    acc.setStartDate(startDate);
                    ;
                }

                if (lineCounter == 3) {
                    String[] fourthLine = line.split(";");
                    String[] dataString = fourthLine[1].split("-");
                    Integer day = Integer.parseInt(dataString[0]);
                    Integer month = Integer.parseInt(dataString[1]);
                    Integer year = Integer.parseInt(dataString[2]);
                    Date endDate = new Date(day, month, year);
                    acc.setEndDate(endDate);
                }

                if (lineCounter >= 5 && line.contains(";")) {
                    StatementLine s = StatementLine.newStatementLine(line);
                    acc.addStatementLine(s);
                    acc.setCurrentBalance(s.getAccountingBalance());
                    acc.setEstimatedAverageBalance(s.getAvailableBalance());
                }
                lineCounter++;

            }
            fileReader.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Não foi encontrado ficheiro , abrindo file generic");
            fileGeneric();
            e.printStackTrace();

        }

        return acc;
    }
    public static void fileGeneric(){
        try {
            Scanner fileReader = new Scanner(new File("account_info/1234567890986.csv"));
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                System.out.println(line);
            }
            fileReader.close();

        } catch (FileNotFoundException t) {
            System.out.println("não foi encontrado ficheiro");
            t.printStackTrace();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String additionalInfo() {

        return "";
    }

    public Date getStartDate() {
        if (statementLines.size() > 0) {
            return statementLines.get(0).getDate();
        } else
            return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        if (statementLines.size() == 0) {
            return endDate;
        } else {
            return statementLines.get(statementLines.size() - 1).getDate();
        }
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public abstract double getInterestRate();

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public void addStatementLine(StatementLine description) {
        statementLines.add(description);
        setCurrentBalance(description.getAccountingBalance());
        setEstimatedAverageBalance(description.getAvailableBalance());
        setStartDate(description.getValueDate());
        setEndDate(description.getDate());
    }

    public void removeStatementLinesBefore(Date date) {
    }

    public double totalDraftsForCategorySince(Category category, Date date) {
        double totalDrafts = 0.0;
        for (StatementLine s : statementLines) {
            if (category.getName().equals("savings")&& s.getDate().after(date))
                totalDrafts = totalDrafts + s.getDraft();
            if (s.getCategory() != null && s.getCategory().getName().equals(category.getName()) && s.getDate().after(date)) {
                totalDrafts = totalDrafts + s.getDraft();
            }
        }
        return totalDrafts;
    }

    public void autoCategorizeStatements(List<Category> categories) {
        for(StatementLine s : statementLines ){
            for (Category a : categories){
             if (a.getTags().contains(s.getDescription())){
                 s.setCategory(a);
             }
            }
        }

    }
    public double totalForMonth(int month, int year) {
        double total = 0.0;
        Date date = new Date(1, month, year);
        for (StatementLine s : statementLines) {
            Date date1 = new Date(1, s.getDate().getMonth(), s.getDate().getYear());
            if (date1.compareTo(date) == 0) {
                total = total + s.getDraft();
            }
        }
        return total;
    }


    public double currentBalance() {
        return currentBalance;
    }

    public double estimatedAverageBalance() {
        return estimatedAverageBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double getEstimatedAverageBalance() {
        return estimatedAverageBalance;
    }

    public void setCurrentBalance(double b) {
        this.currentBalance = b;
    }

    public void setEstimatedAverageBalance(double b) {
        this.estimatedAverageBalance = b;
    }

}









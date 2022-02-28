package pt.upskill.projeto2.financemanager.accounts;

import pt.upskill.projeto2.financemanager.categories.Category;
import pt.upskill.projeto2.financemanager.date.Date;

import java.io.FileNotFoundException;


public class StatementLine {
	private Date date;
	private Date valueDate;
	private String description;
	private double draft;
	private double credit;
	private double accountingBalance;
	private double availableBalance;
	private Category category;

	public StatementLine(Date date, Date valueDate, String description, double draft, double credit, double accountingBalance, double availableBalance, Category category) {
		this.date = date;
		this.valueDate = valueDate;
		this.description = description;
		this.draft = draft;
		this.credit = credit;
		this.accountingBalance = accountingBalance;
		this.availableBalance = availableBalance;
		this.category = category;
		if (date == null || description == null|| description.equals("")|| draft > 0 || credit < 0|| valueDate==null)
		throw new IllegalArgumentException("deu erro");

	}
		public Date getDate () {
			return date;
		}

		public Date getValueDate () {
			return valueDate;
		}

		public String getDescription () {
			return description;
		}

		public double getCredit () {

			return credit;
		}

		public double getDraft () {
			return draft;
		}

		public double getAccountingBalance () {
			return accountingBalance;
		}

		public double getAvailableBalance () {
			return availableBalance;
		}


		public Category getCategory () {
			return category;
		}

		public void setCategory (Category cat){
			this.category = cat;
		}


		public static StatementLine newStatementLine (String line){
			String[] statementsLine = line.split(";");
			String[] dataString = statementsLine[0].split("-");
			int day = Integer.parseInt(dataString[0].trim());
			int month = Integer.parseInt(dataString[1].trim());
			int year = Integer.parseInt(dataString[2].trim());
			Date date = new Date(day, month, year);
			String[] date2String = statementsLine[1].split("-");
			int day1 = Integer.parseInt(date2String[0].trim());
			int month1 = Integer.parseInt(date2String[1].trim());
			int year1 = Integer.parseInt(date2String[2].trim());
			Date valueDate = new Date(day1, month1, year1);
			String description = statementsLine[2].trim();
			double draft = Double.parseDouble(statementsLine[3].trim());
			double credit = Double.parseDouble(statementsLine[4]);
			double accountingBalance = Double.parseDouble(statementsLine[5].trim());
			double availableBalance = Double.parseDouble(statementsLine[6].trim());
			return new StatementLine(date, valueDate, description, draft, credit, accountingBalance, availableBalance, null);

		}
	}




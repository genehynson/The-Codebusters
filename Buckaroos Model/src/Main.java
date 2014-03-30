import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        MySQLAccess dao = new MySQLAccess();
        User aUser = new User("Test", "abc123", "test@gmail.com");
        Account anAcc = new Account(aUser.getUsername(), "Savings", "S1",
                4500.99, .43);
        // dao.addAccount(anAcc, aUser);
        // dao.addTransaction(aUser.getUsername(), anAcc, 271, "Withdrawal",
        // "USD", "Clothes", "2014/03/21", "10:04");
        // dao.addTransaction("admin", "Checking", 457.32, "Withdrawal", "USD",
        // "Food", "2014/03/28", "08:28");
        HashMap<String, Double> spendingMap = dao.getSpendingCategoryInfo(
                "admin", "Checking", "2014/03/01", "2014/03/31");
        System.out.println(spendingMap.entrySet());
        // System.out.println("2014/01/21".compareTo("2014/01/20"));
        // dao.removeTransaction("Test", "Checking", 271, "Clothes",
        // "2014/03/21",
        // "10:04");
        // System.out.println(dao.getAccount("Test", "Checking"));
        // dao.addAccount("Test", anAcc);
        // System.out.println(dao.getUser("Test"));
        // dao.updateUser("Test", "newPW", "test1@gmail.com");
        // System.out.println(dao.getUser("Test"));
        // ArrayList<Account> a = dao.getAllAccounts("Test");
        // for (Account acc : a) {
        // System.out.println(acc);
        // }
        // dao.addTransaction("admin", "Checking", 274, "Withdrawal", "USD",
        // "Clothes", "2014/03/24", "13:04");
        // dao.addTransaction("admin", "Checking", 805, "Deposit", "USD",
        // "Clothes", "2014/03/21", "08:04");
        // ArrayList<AccountTransaction> a = dao.getAllTransactions("admin",
        // "Checking");
        // for (AccountTransaction acc : a) {
        // System.out.println(acc);
        // }
    }
}

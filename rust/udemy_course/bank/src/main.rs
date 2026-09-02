use std::fmt::format;

#[derive(Debug)]
struct Account {
    balance: i32,
    id: u32,
    holder: String,
}

impl Account {
    fn new(id: u32, holder: String) -> Self {
        Account { 
            balance: 0,
            id, 
            holder,
        }
    }

    fn deposit(&mut self, amount: i32) -> i32 {
        self.balance += amount;
        self.balance
    }

    fn withdraw(&mut self, amount: i32) -> i32 {
        self.balance -= amount;
        self.balance
    }

    fn summary(&self) -> String {
        format!("{} has a balance of {}", self.holder, self.balance)
    }
}

#[derive(Debug)]
struct Bank {
    accounts: Vec<Account>,
}

impl Bank {
    fn new() -> Self {
        Bank {
            accounts: vec![]
        }
    }

    fn add_account(&mut self, account: Account) {
        self.accounts.push(account);
    }

    fn total_balance(&self) -> i32 {
        self.accounts.iter().map(|account| account.balance).sum()
    }

    fn summary(&self) -> Vec<String> {
        self.accounts.iter().map(|account| account.summary()).collect::<Vec<String>>()
    }
}
 
fn main() {
    let mut  bank = Bank::new();
    
    let mut  account = Account::new(1, String::from("me"));
    account.deposit(500);
    account.withdraw(250);

    let mut  account2 = Account::new(1, String::from("me"));
    account2.deposit(250);
    account2.withdraw(30);

    let mut  account3 = Account::new(1, String::from("me"));
    account3.deposit(100);
    account3.withdraw(250);

    bank.add_account(account);
    bank.add_account(account2);
    bank.add_account(account3);

    println!("{}", bank.total_balance());
    println!("{:#?}", bank.summary());
}

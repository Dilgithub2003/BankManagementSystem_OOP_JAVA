# SmartBank - Java OOP Console Banking System

SmartBank is a **console-based banking management system** developed using **Core Java**.  
This project demonstrates **Object-Oriented Programming concepts** including:

- Encapsulation
- Inheritance
- Abstraction
- Polymorphism
- Composition
- Method Overriding
- Upcasting / Downcasting

---

## 🚀 Features

| Feature | Description |
|--------|-------------|
| User Registration | Create and store customer profiles |
| Login System | Authenticate existing users |
| Account Creation | Create Saving or Checking accounts |
| Deposit Money | Add money to account balance |
| Withdraw Money | Withdraw with validation rules |
| Transfer Funds | Transfer between accounts |
| Transaction Records | Every transaction stored with timestamp |
| Logout | End user session safely |

---

## 🏦 Account Types

### 1. **Saving Account**
- Has **minimum balance**
- Has **withdrawal limit**
- Applies **interest** based on account balance

### 2. **Checking Account**
- Supports **overdraft limit**
- Allows withdrawing beyond balance (within overdraft range)

---

## 🧠 Core OOP Classes

| Class | Responsibility |
|-------|----------------|
| `User` | Base class holding common user data |
| `Customer` | Extends User, maintains list of accounts |
| `Account` *(Abstract)* | Base class for bank accounts |
| `SavingAccount` | Specific rules for savings account |
| `CheckingAccount` | Specific rules for checking account |
| `Transaction` *(Abstract)* | Base for all transactions |
| `DepositTransaction` | Handles deposits |
| `WithdrawTransaction` | Handles withdrawals |
| `TransferTransaction` | Handles fund transfers |
| `BankService` | Business logic layer |
| `BankApp` | Console UI (main method) |

---

## 🏗️ Project Structure


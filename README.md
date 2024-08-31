# Banking Application

## Overview

This is a basic banking application written in Java. It allows users to manage their accounts with features like depositing, withdrawing, taking credit, and managing contacts. The application also includes options for buying packages, paying suppliers, and printing receipts.

## Features

- **Sign Up**: Create a new account with a bonus balance of 500 units.
- **Deposit**: Add money to your account.
- **Withdraw**: Remove money from your account.
- **Check Balance**: View the current balance.
- **Take Credit**: Borrow money with interest.
- **Repay Credit**: Repay borrowed credit.
- **Contact Management**: Add, delete, and manage contacts, and send money to contacts.
- **Service Menu**: Buy internet packages, pay suppliers, and buy units.
- **Print Receipt**: Generate a receipt with transaction history and account details.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your machine.
- An IDE like IntelliJ IDEA for Java development.

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/banking-application.git
    ```

2. **Navigate to the project directory**:
    ```bash
    cd banking-application
    ```

3. **Compile and run the application**:
    ```bash
    javac Main.java Account.java
    java Main
    ```

## Code Structure

- **`Account.java`**: Contains the `Account` class with methods for managing the account and transactions.
- **`Main.java`**: Entry point of the application. Calls the `signUp` method and starts the menu.

## Usage

1. **Sign Up**: Follow the prompts to enter your name and phone number. Your account number and starting balance will be displayed.
2. **Menu**: Choose from the menu options to perform various actions such as depositing money, withdrawing money, or managing contacts.
3. **Service Menu**: Access additional services like buying internet packages or paying suppliers.
4. **Print Receipt**: Generate a receipt file with your transaction history and account details.

## Example

```plaintext
Menu:
1. Deposit
2. Withdraw
3. Check Balance
4. Take Credit
5. Repay Credit
6. Contact Menu
7. Service Menu
8. Print Receipt
0. Exit

Enter your choice: 1
Enter amount to deposit: 1000
Deposited 1000 successfully.

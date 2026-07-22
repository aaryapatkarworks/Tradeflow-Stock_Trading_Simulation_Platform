# TradeFlow

TradeFlow is a Java-based stock trading simulation application that models the core functionalities of a modern trading platform. It is being developed using object-oriented design principles with a focus on clean architecture, scalability, and maintainable code.

The project begins as a console application and will progressively evolve into a complete backend-driven trading system featuring portfolio management, transaction processing, authentication, database integration, and REST APIs.

---

## Features

### Implemented
- User Management
- Wallet Management

### Planned
- Stock Management
- Portfolio Management
- Buy & Sell Orders
- Transaction History
- Watchlist
- Trading Engine
- Market Simulation
- File Persistence
- Database Integration
- Authentication & Authorization
- REST API (Spring Boot)

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java | Core Programming Language |
| IntelliJ IDEA | Development Environment |
| Git | Version Control |
| GitHub | Repository Hosting |

---

## Project Structure

```text
TradeFlow/
│
├── src/
│   └── com/
│       └── aaryapatkarworks/
│           └── tradeflow/
│               ├── Main.java
│               │
│               ├── model/
│               │   ├── User.java
│               │   ├── Stock.java
│               │   ├── Portfolio.java
│               │   ├── Transaction.java
│               │   └── Wallet.java
│               │
│               ├── service/
│               │   ├── UserService.java
│               │   ├── TradingService.java
│               │   ├── PortfolioService.java
│               │   └── WalletService.java
│               │
│               ├── repository/
│               │   └── DataStore.java
│               │
│               ├── util/
│               │   ├── Constants.java
│               │   ├── InputValidator.java
│               │   └── ConsoleHelper.java
│               │
│               └── exception/
│                   ├── InsufficientBalanceException.java
│                   └── StockNotFoundException.java
│
├── docs/
│   ├── DESIGN.md
│   ├── REQUIREMENTS.md
│   └── UML/
│
├── screenshots/
│
├── .gitignore
├── LICENSE
└── README.md
```

> **Note:** The project structure above represents the intended architecture. Some directories and files will be introduced as development progresses.

---

## Getting Started

### Prerequisites

- Java JDK 21 or higher
- IntelliJ IDEA (Recommended)
- Git

### Clone the Repository

```bash
git clone https://github.com/aaryapatkarworks/TradeFlow.git
```

### Navigate to the Project

```bash
cd TradeFlow
```

### Run

Open the project in IntelliJ IDEA and run `Main.java`.

---

## Development Workflow

Every feature follows a structured development process:

```text
Design
   ↓
Implement
   ↓
Code Review
   ↓
Refactor
   ↓
Git Commit
```

---

## Project Status

The project is currently under active development.

The current focus is building the application's core domain model before implementing the trading engine and advanced features.

---

## Future Scope

- Console-based Trading Platform
- Spring Boot REST API
- MySQL Database Integration
- User Authentication
- Real-Time Market Simulation
- Interactive Dashboard
- Web-Based User Interface

---

## Contributing

This is currently a personal portfolio project. Suggestions, feedback, and improvements are always welcome.

---

## Author

**Aarya Patkar**


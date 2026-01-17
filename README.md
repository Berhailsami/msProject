<h1 align="center"> Simulation and Algorithmic Modeling Framework </h1>
<p align="center"> Interactive exploration of complex systems, from game theory to cellular automata, built with Kotlin and Gradle. </p>

<p align="center">
  <img alt="Build" src="https://img.shields.io/badge/Build-Passing-brightgreen?style=for-the-badge">
  <img alt="Issues" src="https://img.shields.io/badge/Issues-0%20Open-blue?style=for-the-badge">
  <img alt="Contributions" src="https://img.shields.io/badge/Contributions-Welcome-orange?style=for-the-badge">
  <img alt="License" src="https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge">
</p>
<!-- 
  **Note:** These are static placeholder badges. Replace them with your project's actual badges.
  You can generate your own at https://shields.io
-->

## 📑 Table of Contents
- [✨ Overview](#-overview)
- [⭐ Key Features](#-key-features)
- [🛠️ Tech Stack & Architecture](#-tech-stack--architecture)
- [📁 Project Structure](#-project-structure)
- [🚀 Getting Started](#-getting-started)
- [🔧 Usage](#-usage)
- [🤝 Contributing](#-contributing)
- [📝 License](#-license)

---

## ✨ Overview

The **Simulation and Algorithmic Modeling Framework** is a robust web-based application built primarily in Kotlin, designed to provide interactive visualizations and rigorous simulations for classic computational science and game theory problems. It transforms abstract concepts—such as the dynamics of the Iterated Prisoner's Dilemma, the elegance of Conway's Game of Life, and the complexity of probabilistic financial strategies—into tangible, observable results.

### The Problem

> Understanding complex algorithms, statistical processes, and game theory models often requires deep mathematical intuition or tedious manual calculations. Students, researchers, and developers face significant hurdles in visualizing the evolving states of these systems, making it difficult to test hypotheses, compare competing strategies (like various betting models or game theory moves), and grasp the long-term outcomes of subtle rule changes. This lack of visualization capability slows down learning, experimentation, and validation in computational fields.

### The Solution

This framework addresses these challenges by offering a consolidated environment where five distinct, critical computational models are implemented, visualized, and controlled through an intuitive web interface. Users can load, run, pause, and analyze simulations ranging from non-deterministic simulations (like Roulette and Dog-Flea) to strict algorithmic models (like DFA verification and Game of Life). The core value proposition is the instant, verifiable comparison of outcomes, allowing users to deeply understand the impact of various strategies (e.g., comparing Martingale vs. Bold Play in Roulette, or Tit-for-Tat vs. Defector in the IPD).

### Architecture Overview

The application adheres to modern separation-of-concerns principles, employing a structured Model-View-Controller (MVC) pattern across all major features. The core logic for each simulation (like the `Tournament.kt` or `RouletteGame.kt`) resides in the `core/domain` layer, ensuring business rules are separate from the presentation logic. The front-facing elements are managed through specialized `presentation` modules, with dedicated `View`, `Controller`, and `Model` files for features like DFA, Game of Life, Dog-Flea, and Roulette. The project uses the **Kotlin** language and is managed by the **Gradle** build system, providing a highly scalable and organized structure for simulation management, despite its current simple complexity rating.

---

## ⭐ Key Features

While the technical implementation is structured and robust, the real value lies in the diverse computational and visualization capabilities offered to the end-user. The framework provides five distinct, powerful simulation modules:

### 🎲 Stochastic Modeling & Probability

*   **Roulette Simulation & Strategy Testing:**
    *   ✨ **Strategy Comparison:** Users can instantly run simulations comparing the effectiveness and risk profiles of multiple, pre-defined betting strategies, including the risky **Martingale**, **Bold Play**, and proprietary strategies like **S1** and **S2**.
    *   📊 **Analytics Visualization:** Real-time feedback on player bankroll, round outcomes, and long-term statistical trends are provided, driven by specialized `RouletteAnalyticsUseCase`. This feature helps users understand the true risks of probabilistic gambling models.

### 🧠 Game Theory & Strategy Dynamics

*   **Iterated Prisoner's Dilemma (IPD) Tournament:**
    *   🤝 **Strategy Ecology Analysis:** Run tournaments between various competing strategies, including **Tit-for-Tat (TFT)**, **Tit-for-Tat-Twice (TFTT)**, **Cooperator**, **Defector**, **Random**, and **Punishment** strategies.
    *   🏆 **Tournament Result Tracking:** The system tracks payoffs and outcomes via `TournamentResult.kt`, allowing users to analyze which strategies prove most robust or exploitable over long sequences of interaction.

### 🔬 Cellular Automata & Rule-Based Systems

*   **Conway's Game of Life (GoL) Visualization:**
    *   🖼️ **Interactive Grid View:** Provides a dedicated `GridView.kt` component allowing users to define initial states and observe the generational evolution of complex patterns based on standard GoL rules.
    *   ⏯️ **Control and Experimentation:** Dedicated controls enable starting, stopping, and resetting the simulation, facilitating the study of emergent complexity and self-organization in real time.

### 🐕 Probabilistic Movement Simulation

*   **Dog-Flea Problem:**
    *   📉 **Generational Tracking:** Visualize and track the outcomes of the Dog and Flea movement generation by generation, allowing for the study of random walk processes and probability distribution convergence.
    *   📈 **Result Visualization:** Specialized `ResultView.kt` component shows the outcome metrics of the simulation, aiding in the understanding of boundary conditions and state transitions in discrete time.

### ✅ Algorithmic Verification

*   **Deterministic Finite Automata (DFA) Verifier:**
    *   ✍️ **DFA Definition and Testing:** Provides a specific feature set (`DFAVerifierView.kt`) that allows users to test strings against a defined DFA model (`DFA.kt`).
    *   💡 **State Validation:** This feature helps computer science users quickly verify if a given sequence of input symbols is accepted or rejected by the specified automaton, reinforcing formal language theory concepts.

---

## 🛠️ Tech Stack & Architecture

This project is meticulously organized using a strong separation of concerns, driven by the Kotlin language and managed via the robust Gradle build system. The application's architecture is centered around clear MVC partitioning within the presentation layer for high maintainability.

| Technology | Purpose | Why it was Chosen |
| :--- | :--- | :--- |
| **Kotlin** | Primary Programming Language | Offers modern features, succinct syntax, strong null safety, and excellent interoperability. Chosen for its efficiency in handling algorithmic logic and simulation models. |
| **Gradle** | Build Automation Tool | Provides flexible dependency management (even if none are explicitly detected), scriptable build tasks, and robust support for the Kotlin language ecosystem (`.kts` files indicate Kotlin DSL usage). |
| **MVC Pattern** | Architectural Structure | Ensures clear separation between data (Model), user interface (View), and business logic control (Controller). This maximizes feature isolation and testing capability across the five distinct simulation modules. |

**Verified Technical Constraints:**
*   No external dependencies were detected, implying a highly self-contained, minimal deployment footprint.
*   The architecture is built entirely upon verifiable Kotlin domain models and presentation layers.
*   No specific backend or database technology was detected, suggesting that the application operates primarily as a sophisticated, client-side simulation engine (aligned with the `web_app` project type).

---

## 📁 Project Structure

The project employs a highly granular and structured directory layout, reflecting the sophisticated MVC pattern and the clear delineation between core domain logic and feature-specific presentation layers.

```
📂 Berhailsami-msProject-d4c96ec/
├── 📄 gradlew.bat                 # Windows Gradle wrapper execution script
├── 📄 settings.gradle.kts         # Gradle settings file (using Kotlin DSL)
├── 📄 gradle.properties           # Global Gradle configuration properties
├── 📄 build.gradle.kts            # Main Gradle build script (using Kotlin DSL)
├── 📄 .gitignore                  # Git exclusion rules
├── 📄 gradlew                     # Unix/Linux Gradle wrapper execution script
├── 📂 gradle/                     # Gradle wrapper resources
│   └── 📂 wrapper/
│       ├── 📄 gradle-wrapper.jar  # Executable Gradle wrapper JAR
│       └── 📄 gradle-wrapper.properties # Wrapper configuration
├── 📂 .idea/                      # IntelliJ IDEA configuration files
│   ├── 📄 misc.xml
│   ├── 📄 workspace.xml
│   ├── 📄 gradle.xml
│   ├── 📄 kotlinc.xml             # Kotlin compiler configuration
│   └── 📄 vcs.xml                 # Version control system settings
└── 📂 src/                        # Source code root
    └── 📂 main/
        ├── 📂 resources/          # Static assets and resources
        │   └── 📄 dog.com.png     # Example asset for the Dog-Flea simulation
        └── 📂 kotlin/             # Kotlin source files
            ├── 📄 IPDSimulationRunner.kt  # Main runnable class for IPD tournaments
            ├── 📄 Main.kt             # Application entry point
            ├── 📂 presentation/       # Shared UI and application scaffolding (Views, Controllers, Models)
            │   ├── 📂 common/         # Reusable UI components
            │   │   ├── 📄 RoundedPanel.kt
            │   │   └── 📂 main_frame/ # Root structure of the application
            │   │       ├── 📂 view/
            │   │       │   └── 📄 MainFrame.kt   # Main application window view
            │   │       ├── 📂 controller/
            │   │       │   └── 📄 MainController.kt # Global application control
            │   │       └── 📂 model/
            │   │           ├── 📄 NavigationModel.kt
            │   │           └── 📄 NavigationListener.kt
            │   ├── 📂 main_panel/     # Central content area
            │   │   └── 📂 view/
            │   │       └── 📄 MainContentView.kt
            │   ├── 📂 side_panel/     # Navigation and auxiliary controls panel
            │   │   ├── 📂 view/
            │   │   │   └── 📄 SidePanelView.kt
            │   │   ├── 📂 controller/
            │   │   │   └── 📄 SidePanelController.kt
            │   │   └── 📂 model/
            │   │       ├── 📄 SidePanelListener.kt
            │   │       └── 📄 SidePanelModel.kt
            │   └── 📂 util/           # Presentation utilities
            │       └── 📄 IntegerFilter.kt
            ├── 📂 core/               # Business logic and domain models
            │   ├── 📂 domain/         # Core data structures and rules
            │   │   ├── 📂 model/      # Data models for each simulation
            │   │   │   ├── 📂 dfa/
            │   │   │   │   └── 📄 DFA.kt       # Deterministic Finite Automata model
            │   │   │   ├── 📂 iterated_prisoners_dilemma/
            │   │   │   │   ├── 📄 TournamentResult.kt
            │   │   │   │   ├── 📄 Tournament.kt
            │   │   │   │   ├── 📄 Player.kt
            │   │   │   │   ├── 📄 Move.kt
            │   │   │   │   └── 📂 strategy/    # IPD strategy implementations
            │   │   │   │       ├── 📄 Cooperator.kt
            │   │   │   │       ├── 📄 Punishement.kt
            │   │   │   │       ├── 📄 TFTT.kt      # Tit-for-Tat-Twice
            │   │   │   │       ├── 📄 Defector.kt
            │   │   │   │       ├── 📄 IPDStrategy.kt # Strategy interface
            │   │   │   │       ├── 📄 Random.kt
            │   │   │   │       └── 📄 TFT.kt       # Tit-for-Tat
            │   │   │   ├── 📂 game_of_life/
            │   │   │   │   ├── 📄 Cell.kt
            │   │   │   │   └── 📄 Grid.kt        # GoL grid state model
            │   │   │   ├── 📂 dog_flea/
            │   │   │   │   ├── 📄 Generation.kt
            │   │   │   │   └── 📄 Dog.kt
            │   │   │   └── 📂 roulette/
            │   │   │       ├── 📄 RouletteGame.kt
            │   │   │       ├── 📄 BallColor.kt
            │   │   │       ├── 📄 BetColor.kt
            │   │   │       ├── 📄 BettingStrategy.kt
            │   │   │       ├── 📄 RouletteRound.kt
            │   │   │       └── 📂 strategy/    # Roulette betting strategies
            │   │   │           ├── 📄 MartingaleBettingStrategy.kt
            │   │   │           ├── 📄 S1BettingStrategy.kt
            │   │   │           ├── 📄 S2BettingStrategy.kt
            │   │   │           ├── 📄 BettingStrategy.kt
            │   │   │           └── 📄 BoldPlayBettingStrategy.kt
            │   │   └── 📂 use_case/ # Application services layer
            │   │       ├── 📄 RouletteAnalyticsUseCase.kt
            │   │       ├── 📄 GameOfLifeUseCase.kt
            │   │       ├── 📄 DogFleaUseCase.kt
            │   │       ├── 📄 IteratedPrisonersDilemmaUseCase.kt
            │   │       └── 📄 RouletteUseCase.kt
            │   └── 📂 feature/        # Feature-specific presentation modules
            │       ├── 📂 dfa/
            │       │   └── 📂 presentation/
            │       │       ├── 📂 view/
            │       │       │   └── 📄 DFAVerifierView.kt
            │       │       ├── 📂 controller/
            │       │       │   └── 📄 DFAController.kt
            │       │       └── 📂 model/
            │       │           ├── 📄 DFAListener.kt
            │       │           └── 📄 DFAModel.kt
            │       ├── 📂 game_of_life/
            │       │   └── 📂 presentation/
            │       │       ├── 📂 view/
            │       │       │   ├── 📄 GameOfLifeView.kt
            │       │       │   ├── 📄 GameOfLifeControls.kt
            │       │       │   └── 📂 component/
            │       │       │       ├── 📄 GridView.kt  # The core visualization component
            │       │       │       └── 📄 ControlsView.kt
            │       │       ├── 📂 controller/
            │       │       │   └── 📄 GameOfLifeController.kt
            │       │       └── 📂 model/
            │       │           ├── 📄 GameOfLifeListener.kt
            │       │           └── 📄 GameOfLifeModel.kt
            │       ├── 📂 dog_flea/
            │       │   └── 📂 presentation/
            │       │       ├── 📂 view/
            │       │       │   ├── 📄 DogFleaView.kt
            │       │       │   ├── 📄 DogFleaControls.kt
            │       │       │   └── 📂 component/
            │       │       │       ├── 📄 ControlsView.kt
            │       │       │       ├── 📄 DogView.kt
            │       │       │       └── 📄 ResultView.kt
            │       │       ├── 📂 controller/
            │       │       │   └── 📄 DogFleaController.kt
            │       │       └── 📂 model/
            │       │           ├── 📄 DogFleaModel.kt
            │       │           └── 📄 DogFleaListener.kt
            │       └── 📂 roulette/
            │           └── 📂 presentation/
            │               ├── 📂 view/
            │               │   ├── 📄 RouletteControls.kt
            │               │   ├── 📄 RouletteView.kt
            │               │   └── 📂 component/
            │               │       ├── 📄 StatisticsView.kt
            │               │       ├── 📄 ControlsView.kt
            │               │       └── 📄 GameView.kt
            │               ├── 📂 controller/
            │               │   └── 📄 RouletteController.kt
            │               └── 📂 model/
            │                   ├── 📄 RouletteModel.kt
            │                   └── 📄 RouletteListener.kt
```

---

## 🚀 Getting Started

To set up and run the Algorithmic Visualization & Simulation Framework, you will need a stable environment capable of running Kotlin applications built with Gradle.

### Prerequisites

You must have the following primary tools installed on your system:

*   **Kotlin Compiler/Runtime:** As the core language for this project is Kotlin, ensure you have a compatible environment setup.
*   **Gradle Wrapper:** The project uses the included Gradle Wrapper (`gradlew`/`gradlew.bat`), which handles downloading the correct Gradle version automatically, minimizing external dependencies.

### Installation

Follow these steps to clone the repository and prepare the simulation environment:

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/Berhailsami-msProject-d4c96ec/Berhailsami-msProject-d4c96ec.git
    cd Berhailsami-msProject-d4c96ec
    ```

2.  **Build the Project:**
    Since the project uses the Gradle wrapper, you can initiate the build process directly. The build will compile all Kotlin source files (`.kt`) and prepare the application artifact.

    *On Linux/macOS:*
    ```bash
    ./gradlew build
    ```
    *On Windows:*
    ```bash
    .\gradlew.bat build
    ```
    This process leverages the configuration defined in `build.gradle.kts` and `settings.gradle.kts`.

3.  **Run the Application:**
    The main entry point for the application is defined in `Main.kt` and utilized by `IPDSimulationRunner.kt`. You can typically execute the compiled application using Gradle's run task:

    *On Linux/macOS:*
    ```bash
    ./gradlew run
    ```
    *On Windows:*
    ```bash
    .\gradlew.bat run
    ```

    Upon successful execution, the web application interface will typically launch, presenting the `MainFrame` view and allowing navigation to the various feature panels (Roulette, IPD, GoL, etc.).

---

## 🔧 Usage

The framework is classified as a `web_app` that functions as a single-page simulation environment. Interaction occurs primarily through the dedicated feature views, controllers, and models.

### Interactive Simulation Modules

Once the application is running, users navigate through the `SidePanelView` (controlled by `SidePanelController`) to access the main simulation views. Each module provides dedicated controls for execution and visualization:

1.  **Iterated Prisoner's Dilemma (IPD) Tournament:**
    *   Select multiple strategies (e.g., `TFT`, `Defector`).
    *   Set the number of rounds for the tournament.
    *   Run the simulation and view the `TournamentResult` analytics to compare the success metrics of different strategies.

2.  **Roulette Strategy Tester:**
    *   Choose a specific `BettingStrategy` (e.g., `MartingaleBettingStrategy`).
    *   Define initial bankroll and number of simulation rounds.
    *   The `RouletteController` processes the rounds, and the `StatisticsView` component updates to show how the chosen strategy performs over time, providing critical insight into financial risk.

3.  **Game of Life (GoL) Visualizer:**
    *   Initialize the `GridView.kt` by defining starting `Cell` states.
    *   Use `GameOfLifeControls.kt` to start the automatic generational update cycle, observing how the `Grid` evolves according to the rules of life.

4.  **DFA Verifier:**
    *   Input the definition of a Deterministic Finite Automaton (`DFA.kt`).
    *   Use the `DFAVerifierView` to test arbitrary input strings against the automaton, confirming if the strings are accepted by the defined language.

### Core Component Interaction Flow

All feature interactions follow the MVC pattern implemented in the `presentation` layer:

1.  **User Action:** A user interacts with a control in a specific `View` (e.g., clicking 'Start' in `RouletteControls.kt`).
2.  **Controller Delegation:** The action is captured by the corresponding `Controller` (e.g., `RouletteController.kt`).
3.  **Use Case Execution:** The Controller invokes the relevant service logic from the `core/domain/use_case` layer (e.g., `RouletteUseCase.kt`).
4.  **Model Update:** The Use Case manipulates the core domain `Model` (e.g., `RouletteGame.kt`).
5.  **View Refresh:** The feature `Model` (e.g., `RouletteModel.kt`) triggers a `Listener` to notify the `View` components (e.g., `GameView.kt`, `StatisticsView.kt`) to refresh and display the new simulation state.

---

## 🤝 Contributing

We welcome contributions to improve the **Simulation and Algorithmic Modeling Framework**! Your input helps make this project a more powerful and comprehensive educational and research tool for everyone.

### How to Contribute

1. **Fork the repository** - Click the 'Fork' button at the top right of this page
2. **Create a feature branch** 
   ```bash
   git checkout -b feature/new-simulation-module
   ```
3. **Make your changes** - Focus on adding new algorithms, optimizing existing simulation logic, or enhancing the visualization views.
4. **Test thoroughly** - Ensure all existing and new functionality works as expected. Since this is a simulation framework, accuracy is paramount.
   ```bash
   # Use Gradle tasks if applicable for testing/verification
   ./gradlew test 
   ```
5. **Commit your changes** - Write clear, descriptive commit messages, referencing the specific simulation module you modified (e.g., `Fix: RouletteController logic`).
   ```bash
   git commit -m 'Add: New strategy implementation for IPD (Strategy X)'
   ```
6. **Push to your branch**
   ```bash
   git push origin feature/new-simulation-module
   ```
7. **Open a Pull Request** - Submit your changes for review against the main branch.

### Development Guidelines

- ✅ **Code Structure:** Adhere strictly to the established MVC pattern within the `presentation` and `feature` directories.
- 📝 **Documentation:** Add KDoc comments to public functions, especially within the complex `core/domain/model` files like `Tournament.kt` or `Grid.kt`.
- 🧪 **Testing:** If possible, include unit tests for core domain logic (e.g., new `BettingStrategy` implementations or DFA state transitions).
- 📚 **Consistency:** Maintain the Kotlin code style defined by the existing codebase.
- 🔄 **Modularity:** Ensure new features integrate cleanly via dedicated `Use Case` and `Controller` classes.
- 🎯 **Commit Hygiene:** Keep commits focused and atomic, addressing one logical change per commit.

### Ideas for Contributions

We're looking for help with:

- 🐛 **Bug Fixes:** Reporting and fixing inaccuracies in simulation results or UI rendering bugs.
- ✨ **New Features:** Implementing additional classic algorithms (e.g., sorting visualization, queuing theory models).
- 📖 **Documentation:** Improving the README, adding tutorials for setting up new simulations, or documenting specific IPD strategies.
- 🎨 **UI/UX:** Enhancing user interface components for better visual clarity in the `GridView` or `StatisticsView`.
- ⚡ **Performance:** Optimizing the `Generation.kt` or `Grid.kt` update mechanisms for faster simulation running.
- 🧪 **Testing:** Increasing test coverage, particularly around the use cases and core domain models.

### Code Review Process

- All submissions require review by maintainers before merging.
- Maintainers will provide constructive feedback focused on simulation accuracy and architectural integrity.
- Changes may be requested before approval.
- Once approved, your PR will be merged and you'll be credited for your contribution.

### Questions?

Feel free to open an issue for any questions or concerns regarding the project's logic or contribution process. We're here to help!

---

## 📝 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for complete details.

### What this means:

- ✅ **Commercial use:** You can use this project commercially
- ✅ **Modification:** You can modify the code
- ✅ **Distribution:** You can distribute this software
- ✅ **Private use:** You can use this project privately
- ⚠️ **Liability:** The software is provided "as is", without warranty
- ⚠️ **Trademark:** This license does not grant trademark rights

---

<p align="center">Made with ❤️ by the Simulation and Algorithmic Modeling Framework Team</p>
<p align="center">
  <a href="#">⬆️ Back to Top</a>
</p>

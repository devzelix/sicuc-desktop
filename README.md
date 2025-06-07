# 🖥️ Carabobo Cultors Information System Desktop

## 📝 Description

Carabobo Cultors Information System Desktop is a Java desktop application designed to manage and display information about cultors in Carabobo state, Venezuela. It enables querying data related to municipalities, parishes, art categories, disciplines, and cultor profiles.

## ✨ Features

- 🔍 Manage and search cultors with advanced filters.
- 📊 Visualize statistical data using pie charts.
- 🌐 Initial data loading from web services.
- ⚙️ API URL configured via `.env` file.
- 🖥️ Modern and user-friendly GUI built with FlatLaf.
- ⚠️ Connection error handling with user notifications.

## 🛠️ Requirements

- ☕ Java 17 or higher.
- 📦 Dependencies:
  - [JFreeChart](https://www.jfree.org/jfreechart/)
  - [FlatLaf](https://www.formdev.com/flatlaf/)
  - [Gson](https://github.com/google/gson)
  - [dotenv-java](https://github.com/cdimascio/dotenv-java) _(for loading the .env file)_

## 🚀 Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/devzelix/sicuc-desktop.git
   cd sicuc
   ```

2. Create a `.env` file in the root directory with the following content:

   ```bash
   API_URL=https://example.com/api
   ```

3. Build the project using Maven or your preferred tool:

   ```bash
   mvn clean install
   ```

4. Run the application:

   ```bash
   java -jar target/backend-1.0.0.jar
   ```

## ⚙️ API Configuration

The API endpoint used by the application is loaded from a .env file. Make sure to include the correct URL before running the application:

    ```env
    API_URL=https://example.com/api
    ```

The application uses dotenv-java to load environment variables automatically at runtime.

## 🎯 Usage

- Upon startup, the application loads initial data. If a connection error occurs, a notification will be shown and reconnection attempts will be made.

- After logging in with valid credentials, access the main dashboard to search cultors, filter by categories or disciplines, and view graphical statistics.

## 📂 Project Structure

- com.culturacarabobo.cultorsmanager: Main package containing the Main class and UI.

- utils: Utilities for data handling, adapters, and constants.

- models: Model classes representing entities.

- services: Services to fetch data from the API.

- ui: Custom graphical user interface components.

## 🤝 Contributions

Contributions are welcome! Please open an issue or a pull request to suggest improvements or report bugs.

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

# ComputerDatabase
This project includes regression scripts for the Computer Database using a BDD framework with Cucumber and Selenium.

# Installation

You need to have following softwares installed on your computer:

- Install JDK 1.8 and set path.
- Install Maven and set path.
- Install Intellij.
- Intellij Plugins: Gherkin and Cucumber for Java plugins.
- Chromedriver - Download (path is set in @Before in test steps).

```java
System.setProperty("webdriver.chrome.driver", "D:\\Projects\\cucumber\\chromedriver.exe");
```

# Structure

**src/test/java/Pages**
Contains all the page object classes

**src/test/java/Runner**
Contains the Cucumber test runner class

**src/test/java/Steps**
Contains the Cucumber step defination class

**src/test/resources/Features**
Contains the Cucumber features files

# Running Tests
Run the TestRunner class to run the tests.

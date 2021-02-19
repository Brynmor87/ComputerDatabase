Feature: Computer database regression tests

##Added this to remove computer names used during the below tests
#  @BeforeTest
#  Scenario:Remove Computers
#    Given User is on the computer database
#    When Remove computers before test

  @BeforeTest
  Scenario:Successfully Add a Computer with All Values Input
    Given User is on the computer database
    When User completes the Add a new computer journey
      | field             | value           |
      | Computer Name     | TestComputer #1 |
      | Introduced date   | 1990-05-26      |
      | Discontinued date | 2015-07-19      |
      | Company           | IBM             |
    Then the computer is added to the database

  @BeforeTest
  Scenario:Successfully Add a Computer with only mandatory fields entered
    Given User is on the computer database
    When User completes the Add a new computer journey
      | field             | value           |
      | Computer Name     | TestComputer #2 |
      | Introduced date   | Blank           |
      | Discontinued date | Blank           |
      | Company           | Default          |
    Then the computer is added to the database

  @BeforeTest
  Scenario: Validation for Computer name on Add a new computer journey
    Given User is on the computer database
    When User doesn't enter a Computer name
    Then validation on Computer name blocks the save

  @BeforeTest
  Scenario Outline: Validation for Introduced date on Add a new computer journey
    Given User is on the computer database
    When User doesn't enter correct date format in Introduced date "<INT_DATE>"
    Then validation on Introduced date blocks the save

    Examples:
      | INT_DATE    |
      | ABCDEFGH    |
      | 12345678    |
      | 20/5/1990   |
      | 5/20/1990   |
      | 20/5/90     |
      | 5/20/90     |
      | 20/05/90    |
      | 05/20/90    |
      | 20/05/1990  |
      | 05/20/1990  |
      | 90/05/20    |
      | 20-May-1990 |
      | 1900-20-05  |
      | 1990/05/20  |
      | 20-05-1990  |
      | 1990-May-20 |

  @BeforeTest
  Scenario Outline: Validation for Discontinued date on Add a new computer journey
    Given User is on the computer database
    When User doesn't enter correct date format in Discontinued date "<DIS_DATE>"
    Then validation on Discontinued date blocks the save

    Examples:
      | DIS_DATE    |
      | ABCDEFGH    |
      | 12345678    |
      | 20/5/1990   |
      | 5/20/1990   |
      | 20/5/90     |
      | 5/20/90     |
      | 20/05/90    |
      | 05/20/90    |
      | 20/05/1990  |
      | 05/20/1990  |
      | 90/05/20    |
      | 20-May-1990 |
      | 1900-20-05  |
      | 1990/05/20  |
      | 20-05-1990  |
      | 1990-May-20 |

  @BeforeTest
  Scenario:Delete an existing computer
    Given User is on the computer database
    When User completes the Add a new computer journey
      | field             | value           |
      | Computer Name     | TestComputer #4 |
      | Introduced date   | Blank           |
      | Discontinued date | Blank           |
      | Company           | Default         |
    Then the computer is added to the database
    When the computer is deleted
    Then the computer is not in the database

  @BeforeTest
  Scenario:Successfully Edit a Computer with All Values Input
    Given User is on the computer database
    When User completes the Add a new computer journey
      | field             | value           |
      | Computer Name     | TestComputer #5 |
      | Introduced date   | 1995-07-07      |
      | Discontinued date | 2020-07-20      |
      | Company           | Apple Inc.      |
    Then the computer is added to the database
    When User edits the values in the fields
      | field             | value           |
      | Computer Name     | TestComputer #5a|
      | Introduced date   | 1987-02-22      |
      | Discontinued date | 2019-10-01      |
      | Company           | Nintendo        |
    Then the computer is updated in the database

  @BeforeTest
  Scenario:Successfully Edit a Computer with values removed
    Given User is on the computer database
    When User completes the Add a new computer journey
      | field             | value           |
      | Computer Name     | TestComputer #6 |
      | Introduced date   | 2001-09-30      |
      | Discontinued date | 2021-01-25      |
      | Company           | Nokia           |
    Then the computer is added to the database
    When User edits the values in the fields
      | field             | value           |
      | Computer Name     | TestComputer #6a|
      | Introduced date   | Blank           |
      | Discontinued date | Blank           |
      | Company           | Default         |
    Then the computer is updated in the database

  @BeforeTest
  Scenario: Validation for Computer name on Edit a computer journey
    Given User is on the computer database
    When User completes the Add a new computer journey
      | field             | value           |
      | Computer Name     | TestComputer #7 |
      | Introduced date   | 2001-09-30      |
      | Discontinued date | 2021-01-25      |
      | Company           | Nokia           |
    Then the computer is added to the database
    When User removes Computer name validation blocks the save
    Then the computer retains the original values in the database

  @BeforeTest
  Scenario Outline: Validation for Introduced date on Edit a computer journey
    Given User is on the computer database
    When User completes the Add a new computer journey
      | field             | value           |
      | Computer Name     | TestComputer #8 |
      | Introduced date   | 2001-09-30      |
      | Discontinued date | 2021-01-25      |
      | Company           | Nokia           |
    Then the computer is added to the database
    When User updates to incorrect date format in Introduced date validation blocks the save "<INT_DATE>"
    Then the computer retains the original values in the database

    Examples:
      | INT_DATE    |
      | ABCDEFGH    |
      | 12345678    |
      | 20/5/1990   |
      | 5/20/1990   |
      | 20/5/90     |
      | 5/20/90     |
      | 20/05/90    |
      | 05/20/90    |
      | 20/05/1990  |
      | 05/20/1990  |
      | 90/05/20    |
      | 20-May-1990 |
      | 1900-20-05  |
      | 1990/05/20  |
      | 20-05-1990  |
      | 1990-May-20 |

  @BeforeTest
  Scenario Outline: Validation for Discontinued date on Edit a computer journey
    Given User is on the computer database
    When User completes the Add a new computer journey
      | field             | value           |
      | Computer Name     | TestComputer #9 |
      | Introduced date   | 2001-09-30      |
      | Discontinued date | 2021-01-25      |
      | Company           | Nokia           |
    Then the computer is added to the database
    When User updates to incorrect date format in Discontinued date validation blocks the save "<DIS_DATE>"
    Then the computer retains the original values in the database

    Examples:
      | DIS_DATE    |
      | ABCDEFGH    |
      | 12345678    |
      | 20/5/1990   |
      | 5/20/1990   |
      | 20/5/90     |
      | 5/20/90     |
      | 20/05/90    |
      | 05/20/90    |
      | 20/05/1990  |
      | 05/20/1990  |
      | 90/05/20    |
      | 20-May-1990 |
      | 1900-20-05  |
      | 1990/05/20  |
      | 20-05-1990  |
      | 1990-May-20 |
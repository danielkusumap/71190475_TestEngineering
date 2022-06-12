Feature: feature to test login functionality
  Scenario Outline: Check login with valid credentials
    Given browser opened
    And user in register page for login
    And user click login
    And user in login page
    When user insert <email> and <password>
    And login button clicked
    Then <testid>: email <email>, kata sandi <password>
    Examples:
      |testid|email|password|
      |"EQV1"  |"dnl1@71190475.com"|"abcdefgh"|
      |"EQV2"  |"dnl2@71190475.com"|"12345678"|
      |"EQV3"  |"dnl3@71190475.com"|"abcd1234"|
      |"EQV4"  |"dnl4@71190475.com"|"ab22"    |
      |"EQV5"  |"dnl5@71190475.com"|"abcd123#@"|
      |"EQV6"  |"dnl6@71190475.com"|"abcdefghijklmnopqrst"|
      |"EQV7"  |"dnl7@71190475.com"|""                  |
      |"EQV8"  |""                 |"adapassword"         |
      |"EQV9"  |""                 |""                    |
      |"BVA1"  |"dnl10@71190475.com"|"abcd123"            |
      |"BVA2"  |"dnl11@71190475.com"|"zyxw4321"           |
      |"BVA3"  |"dnl12@71190475.com"|"daniel123"          |
      |"BVA4"  |"dnl13@71190475.com"|"danielkusuma"       |
      |"BVA5"  |"dnl14@71190475.com"|"danielkusumaa"      |
      |"BVA6"  |"dnl15@71190475.com"|"danielkusumaaa"     |
Feature: feature test register
  Scenario Outline: check register username and password
    Given buka browser
    And user in register page
    When insert <firstname> <email> and <password>
    And ada tombol reset dan create
    And create account
    Then <testid>: cek email <email>, kata sandi <password>
    Examples:
      |testid|firstname|email|password|
      |"EQV1"  |"dnl1"     |"dnl1@71190475.com"|"abcdefgh"|
      |"EQV2"  |"dnl2"     |"dnl2@71190475.com"|"12345678"|
      |"EQV3"  |"dnl3"     |"dnl3@71190475.com"|"abcd1234"|
      |"EQV4"  |"dnl4"     |"dnl4@71190475.com"|"ab22"    |
      |"EQV5"  |"dnl5"     |"dnl5@71190475.com"|"abcd123#@"|
      |"EQV6"  |"dnl6"     |"dnl6@71190475.com"|"abcdefghijklmnopqrst"|
      |"EQV7"  |"dnl7"     |"dnl7@71190475.com"|""                  |
      |"EQV8"  |"dnl8"     |""                 |"adapassword"         |
      |"EQV9"  |"dnl9"     |""                 |""                    |
      |"BVA1"  |"dnl10"    |"dnl10@71190475.com"|"abcd123"            |
      |"BVA2"  |"dnl11"    |"dnl11@71190475.com"|"zyxw4321"           |
      |"BVA3"  |"dnl12"    |"dnl12@71190475.com"|"daniel123"          |
      |"BVA4"  |"dnl13"    |"dnl13@71190475.com"|"danielkusuma"       |
      |"BVA5"  |"dnl14"    |"dnl14@71190475.com"|"danielkusumaa"      |
      |"BVA6"  |"dnl15"    |"dnl15@71190475.com"|"danielkusumaaa"     |

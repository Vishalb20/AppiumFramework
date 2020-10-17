# AppiumFramework
appium framework for running test on android app

# Summary
A basic Appium framework for an Android app using Appium, Java, Maven, the PageObject pattern, PageFactory, TestNG

# App Under Test
We are using the Storms_0_2_5_778_Prod.apk app included on Appium's GitHub repository.

# Pre-requisites
An emulator device should be created using android studio name: emulator-5554
ANDROID Environment variable are set correctly
Specify the sdk directory location in config.properties file - ~/AppiumFramework/src/main/resources/configfile/config.properties

# Directory Structure:
src 
-> main
   -> java
      --> helper 
      --> pageObject
        -->HomePage
        -->LogInPage
        -->SignUpPage
      --> practise.AppiumFramework
      --> resources
      --> utils
   -> resources
      --> configfile
      --> reports
      --> screenShots
      --> testSuite
-> test
   -> java
      --> practise.AppiumFramework
        -->HomePageTest
        -->LoginPageTest 
        -->SignUpPageTest.java
->Storms_0_2_5_778_Prod.apk	
pom.xml
testng.xml

# Note: 
Appium will be started and stopped programatically as long as pre-requisites are followed

# Run
Open the project as maven and build and run testng.xml



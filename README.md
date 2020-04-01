# FinalProject

### The FinalProject is used for testing functionalities

Target application URL is: https://petstore.octoperf.com/
Target browser: Chrome
Browser version: Version 80.0.3987.149 (Official Build) (64-bit)

Run test suite: *testng.xml* Total tests run: 11

Used libraries:

- TestNG automation testing framework
- Selenium portable framework for testing web applications
- Apache POI library for manipulating various file formats

JavaDoc is located in *doc* folder.

The following website functionalities are tested:

- click link to enter the store
- functionality of the links(checking if all links are working)
- pet store menu navigational links(left, quick and picture links)
- registration
- log in
- adding the items to cart
- checking the item price with the total price
- deleting cookies


## This project contains 3 packages:
- pages
- tests
- utils

All packages are in the *src* folder.

## Package **pages** contains 7 classes:
- BasicPage
- CartPage
- HomePage
- PetStoreMenuPage
- RegistrationPage
- SignInPage
- StoreItemPage


### Package **tests** contains 5 classes:
- CartTest
- EnterTheStoreTest
- PetStoreMenuPage
- RegistrationTest
- SignInTest

## Package **utils** contains 1 class:
- ExcelUtils

## Folder **data** contains 1 file:
- pet-store-data - document used for testing registration, login, and cart

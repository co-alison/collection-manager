# Collection Manager Application

## CPSC 210 W2 Personal Project

This application will help you keep track of your various collections, whether it be Funko Pop!, Hot Wheels, spoons, 
pins, or sneakers. If you're a beginner collector with one common item, or an avid collector with a hundred rare items, 
the collection manager is **perfect**. 

Users will be able to: 
- add/delete items from their collection
- view all items, 
- log current market prices, release dates, purchase dates, original prices, rarity, and any other details related to the 
item.
- categorize items based on type

As someone who enjoys collecting and watching my collection grow over the years, I often find myself forgetting 
important details. If I want to sell some of my items in 10 years, I would like to know how much I profited, or if I 
waited too long to sell. In addition, I'm a member of a family of collectors. With everyone having different interests,
I want to be able to view and store information about different collections from the same place. I'm hoping to extend
this passion to fellow collectors, or anyone who wants to start!

## User Stories
- As a user, I want to be able to create a new collection, and add or remove an item to/from my collection.
- As a user, I want to be able to view a list of collections or items.
- As a user, I want to be able to select and update information of an item.
- As a user, I want to be able to perform various functions on items such as:
  - Calculating the current average market price and price trend of an item.
  - Calculating the total value of a collection.
- As a user, when I select quit from the main menu, I want to be reminded to save my collections to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my collections from file.

## Phase 4: Task 2

Tue Mar 29 11:14:56 PDT 2022
Added new collection Funko Pop!


Tue Mar 29 11:14:56 PDT 2022
Added new collection Hot Wheels


Tue Mar 29 11:14:56 PDT 2022
Added new collection Sneakers


Tue Mar 29 11:14:58 PDT 2022
Added new item Alien to Funko Pop!


Tue Mar 29 11:14:58 PDT 2022
Added new item Geoffrey as Robin to Funko Pop!


Tue Mar 29 11:14:58 PDT 2022
Added new item Madame Leota to Funko Pop!


Tue Mar 29 11:14:58 PDT 2022
Added new item Purple Pulse Dunk Low to Sneakers


Tue Mar 29 11:15:08 PDT 2022
Added new collection Funko Pop!


Tue Mar 29 11:15:08 PDT 2022
Added new collection Hot Wheels


Tue Mar 29 11:15:08 PDT 2022
Added new collection Sneakers


Tue Mar 29 11:15:08 PDT 2022
Added new item Alien to Funko Pop!


Tue Mar 29 11:15:08 PDT 2022
Added new item Geoffrey as Robin to Funko Pop!


Tue Mar 29 11:15:08 PDT 2022
Added new item Madame Leota to Funko Pop!


Tue Mar 29 11:15:08 PDT 2022
Added new item Purple Pulse Dunk Low to Sneakers


Tue Mar 29 11:15:22 PDT 2022
Deleted collection Hot Wheels


Tue Mar 29 11:15:24 PDT 2022
Deleted collection Sneakers


Tue Mar 29 11:15:26 PDT 2022
Deleted item Geoffrey as Robin from Funko Pop!


Tue Mar 29 11:15:28 PDT 2022
Deleted item Madame Leota from Funko Pop!

## Phase 4: Task 3
- I would use a Set to store collections and items to improve the performance of the application and to ensure that there are no duplicate items/collections
- I would limit the amount of inner classes in CollectionApp, as there is a lot of duplication within each ListenerClass.
  - The design could be improved by creating an abstract ActionListenerClass and a ListSelectionListenerClass that each listener class could extend. This would reduce the duplication between listeners for different buttons and lists.
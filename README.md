# Avanade Tech 1 Android

## Introduction

There is a simple app waiting for you in Android Studio, which shows a list of books from a local database. The most important libraries and components used are:
- Kotlin + Coroutines
- ViewModel + LiveData (MVVM architecture)
- Room
- Dagger

Unfortunately, the app is unfinished and currently broken. You will be asked to fix all the problems and add a couple of new features.

## Rules

- Please make a separate commit for each step. It will make is easier for us to analyze your solution.
- You can use Google, StackOverflow, or anything else you need to solve the problems.
- Try to complete the tasks in order they are given. It's more important to complete less tasks fully than more tasks halfway.
- Do not worry if you don't complete all the tasks - do as much as you can and if you run out of time, commit what you've managed so far.

## Tasks

1. Make the app compile and run.
2. Make the app work properly (display a list of books on the main screen).
3. When a book is clicked, open a new Activity with details of that book (show all data from the database, including Author details). Design of that screen is up to you - don't worry about styling too much, but pay attention to a proper layout structure.
4. On the details screen, when author's name is clicked, show a dialog with all of that author's books.
5. On the main screen, allow sorting the books by title, author's name and year published.
6. Change the main screen to arrange the books in a grid. Add more information (author's name and year published) to each item and give them some simple styling.
package org.example;

import org.example.controller.MainMenuController;
import org.example.repo.AlbumRepo;
import org.example.repo.ArtistRepo;
import org.example.repo.factory.AlbumRepoFactory;
import org.example.repo.factory.ArtistRepoFactory;
import org.example.view.ConsoleIO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConsoleIO io = new ConsoleIO();
        AlbumRepo albums = AlbumRepoFactory.instance();
        ArtistRepo artists = ArtistRepoFactory.instance();

        MainMenuController mainMenu = new MainMenuController(io, albums, artists);
        mainMenu.run();



    }
}

/*
This Java code defines the main entry point for an application. Here's a plain English explanation of what it's doing:

Purpose:
The program serves as a music database application. It initializes the necessary components (like input/output handling and data repositories) and then hands control over to the MainMenuController, which manages the user interface and functionality.

Steps Taken:

Create Input/Output Handler:
A ConsoleIO object is created. This object handles reading input from and writing messages to the console (e.g., showing menu options or getting user input).

Initialize Data Repositories:
Two repositories (AlbumRepo and ArtistRepo) are created using factory classes (AlbumRepoFactory and ArtistRepoFactory). These repositories manage the data for albums and artists, respectively, likely providing methods to fetch, add, or update data.

Set Up the Main Menu Controller:
A MainMenuController object is created. It is responsible for displaying the main menu and processing user choices. The MainMenuController is given the ConsoleIO object and the two repositories (AlbumRepo and ArtistRepo) so it can interact with the user and the data.

Run the Application:
The run() method of the MainMenuController is called. This starts the main menu loop, where the user is prompted with options to interact with the music database (e.g., viewing albums, searching by artist, etc.).

Execution Flow:

The application starts in the main method.
It sets up the input/output system and the data repositories.
It passes these components to the MainMenuController.
The program then runs the main menu, allowing the user to interact with the application.
Example Usage:

When the user runs the program, they might see a menu with options like "View All Albums" or "View Albums by Artist."
They select an option, and the MainMenuController interacts with the repositories to fetch and display the requested information.
In Summary:
This code sets up the core components of a music database program and delegates control to a menu system (MainMenuController) that lets the user interact with albums and artists via a console-based interface.
 */
# booktrackerlite

**booktrackerlite** is a personal project for myself, an avid reader.  It's intended use is as a way for me to track my current reading list, and write basic notes for novels that I _have_ read, or am currently reading.

The usage for this application is intended to be lightweight and straightforward, it includes only necessary functionality for the above purposes.

**NOTE:** README is not complete, disregard the directions listed below.

## How to run application
The booktrackerlite web app is designed as a personal project, for my own use, and to demonstrate my proficiency with the spring framework in java.  As I have no current nor future plans to host this web app asides from on my local machine, there are necessary steps you need to take to get it running.

### Database connection
- Download MySQL, and host a db server - note your login credentials, these will be needed later
  - Note: User interface such as MySQL workbench will make this step much easier
- Login and run the provided SQL script located in `scripts`

### Running the application
- Clone this repo
- After opening the project folder with your IDE of choice, move to the `application.properties` file
- In this file, **edit** the credentials to match your database login information, additionally, you will need to add your own datasource url (this might vary depending on the port db is being hosted from)
- **Optional** once the above steps are complete, you might want to try and run the included integration/unit tests to ensure functionality
- Run the BooktrackerliteApplication, and access your app at `localhost:8080/books`
  
  ![Screenshot 2023-11-15 at 6 34 35 PM](https://github.com/hfish063/booktrackerlite/blob/main/screenshots/Screenshot%202023-11-20%20at%207.19.49%E2%80%AFPM.png?raw=true)

  ![Screenshot 2023-11-15 at 6 34 02 PM](https://github.com/hfish063/booktrackerlite/blob/main/screenshots/Screenshot%202023-11-20%20at%207.20.01%E2%80%AFPM.png?raw=true)

- Use the provided SQL script to create the required table in your database

## User guide

### Overview of catalog
![Screenshot 2023-11-20 at 7 01 20 PM](https://github.com/hfish063/booktrackerlite/blob/main/screenshots/Screenshot%202023-11-20%20at%207.17.47%E2%80%AFPM.png?raw=true)
- Here users can view their entire catalog of books, and navigate through different functionality by clicking buttons

### Add notes
![Screenshot 2023-11-20 at 7 02 01 PM](https://github.com/hfish063/booktrackerlite/blob/main/screenshots/Screenshot%202023-11-20%20at%207.17.59%E2%80%AFPM.png?raw=true)
- Add/update notes for specific title
- Includes any String of words, will be saved in the db alongside other book data

### Update a book
![Screenshot 2023-11-20 at 7 02 28 PM](https://github.com/hfish063/booktrackerlite/blob/main/screenshots/Screenshot%202023-11-20%20at%207.18.09%E2%80%AFPM.png?raw=true)
- Change title/author, increment/decrement current page number

### Get description
![Screenshot 2023-11-20 at 7 03 05 PM](https://github.com/hfish063/booktrackerlite/blob/main/screenshots/Screenshot%202023-11-21%20at%2012.47.09%E2%80%AFAM.png?raw=true)
- Book descriptions obtained through the *OpenLibrary* Api, unavailable titles will generate empty descriptions


## Additional info...

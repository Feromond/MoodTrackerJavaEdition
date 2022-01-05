MoodTracker Final Submission 	- Jacob Mish (30067029)

The development enviornment for this project was Intellij for my Java coding, and Scenebuilder for creating my GUI. The project source code files are all stored together without any package, in a src file. 

---------------------------------------------------------------------
SWITCH BETWEEN GUI & CMD APP

- To run the graphical user interface version of this mood tracker, execute the MoodTrackerFinalGUI.java file. This is the main file to run when desiring the GUI option

- To run the command line version of this mood tracker, execute the MoodTrackerFinalCMD.java file. This is the main file to run when wanting to use the command line / text based interface.

(NOTE: The ability to review previous entries is only avaliable when running the GUI version of the application. Otherwise, entries will continue to be saved in the MoodLog.txt file. The user may access the file manually to see all stored results at any time, but previewing old entries during runtime of the application is a feature limited to the GUI version.)
---------------------------------------------------------------------
PRIMARY FEATURES AND FUNCTIONALITY

This application detects whether it is running for the first time (aka. the MoodLog.txt either does not exist or is empty) and will preform slightly differently based on this condition. For the first time use, the application will gather the users name, then will collect their mood input information (score from 1-10, overall mood, social mood, introspective mood) and record it into a MoodLog.txt file which is generated just outside of the src file. The GUI application also offers the additional feature to iterate through all past existing entries that have been entered by the user. If user has run the program before, it will no longer ask for a username but instead welcome the user with their name. If the user has not used the program for more than 2 days, the initial welcome will offer the user encouragement to continue to use this tracker. Both the CMD and GUI versions of this program record and store the data in the same manner, only varying when reading the file as the GUI takes advantage of more information for displaying previous entries. The CMD application is also able to access these same older entries as the code is maintained, although the presentation of this information in a text-based or command line setting is not preferable and thus has been restricted to the GUI. 
---------------------------------------------------------------------
MAJOR FEATURES

- Various different scenes, controls, and containers used in the GUI to expand the user experience. Using various V-Boxes, H-Boxes and all forms of other containers to neatly organize the various different labels, buttons, textboxes, choiceboxes, and textfields that are used within the project. Additionally, the use of muiltiple FXML files and individual controllers for each scene to build the organized and more user friendly experience when using the application. 

- The ability to present past user entries in an organized / neat format in the GUI. Very easy to use design with formatted and cleaned data that is presented. Using a HashMap to read through the MoodLog.txt file to extract the important desired content and prepare it for presentation.

- Full error checking to validate different user inputs in both the GUI and CMD versions. Organizes and formats user inputs to ensure they are correctly structured for recording and future access.  
---------------------------------------------------------------------
FILE FORMAT

Both the text-based and GUI application use the file formate .txt for gathering input and outputting the recorded data. 

The program files are all stored as a group together within the sources file (src). The MoodLog.txt file which is created to store the record of the users entry is generated just outside of the source file (src). This file can be accessed to review any stored records / entries from the user. To reset the program and eliminiate all entries, the MoodLog.txt file can be deleted or all text within the file can be removed. Doing so will reset the program which will then ask for the users name once again.
---------------------------------------------------------------------
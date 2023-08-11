# **File Integrity Monitoring System (FIM)**
File Integrity Monitoring (FIM) is an essential aspect of cybersecurity, ensuring the integrity and security of files within a system. FIM continuously monitors files and directories for any unauthorized changes, providing a vigilant defense against potential security breaches.

## Project Overview
The File Integrity Monitoring System (FIM) is a Java-based application designed to monitor changes in specified directories and generate reports on detected modifications. Here's how it works:
1. **Select Directory to Monitor**: Choose the target directory that you want to monitor for file changes.
2. **Snapshot Creation**: Create an initial snapshot of the selected directory. This snapshot captures the current state of files and their attributes (such as timestamps, sizes, and permissions).
3. **Monitor Changes**: The application continuously monitors the target directory for any modifications, additions, or deletions of files.
4. **Report Generation**: When you request a report, the application takes a new snapshot of the directory and compares it with the previous snapshot. Any changes are noted, and the details are entered into a report.txt file. Each change is timestamped for accurate tracking.
5. **Maintain History**: By keeping track of when snapshots and reports are created, you can maintain a history of changes over time.
6. **Hash-Based Verification**: Snapshots created and checked are made using the hashes of the files. The hashing algorithm used to form digest codes is SHA256, ensuring a secure and efficient comparison of file states.

## How to Use
The File Integrity Monitoring System (FIM) is a Java-based application designed to monitor changes in specified directories and generate reports on detected modifications. Here's how it works:
1. **Setup**: Clone this repository to your local machine.
2. **Compilation**:Compile the Java source files using a Java compiler.
3. **Run Application**: Run the compiled Java program.
4. **Select Directory**: Provide the path of the directory you want to monitor.
5. **Monitor Changes**: The application will now monitor the specified directory for any file changes.
6. **Generate Report**: When you want to check for changes, request a report. The application will compare the latest snapshot with the previous one and document any modifications in the report.txt file.

## Technologies Used
- Java Programming Language
- SHA256 hashing algorithm.

## ScreenShots
![App Screenshot](https://github.com/US2002/multiUserChatApp/blob/main/Assets/MultiUserChatAppPhoto.png)

## Future Enhancements
- Implement email notifications for real-time alerts on critical changes.
- Include a graphical user interface (GUI) for easier interaction.
- Support monitoring multiple directories simultaneously.


## Usage/Examples
- Launch the client application.
- Register a new account using a unique username and a secure password.
- Log in with your registered credentials.
- Start chatting with other clients who are online.

## Contributions
Contributions are welcome! If you find any issues, have suggestions, or want to add new features, feel free to open a pull request.

## Feedback and Support
We would love to hear your feedback and suggestions for improving the Recipe Generator App. If you encounter any issues or have any questions, please create a new issue in the issue tracker.

## License
This project is licensed under the MIT License


# CampusPulse

CampusPulse is a software application designed to help users manage and discover events on campus. This README provides all necessary information to install, use, and contribute to the project.

## Table of Contents
- [Installation](#installation)
- [Usage Guide](#usage-guide)
- [License](#license)
- [Feedback](#feedback)
- [Contributing](#contributing)
- [Common Issues](#common-issues)
- [Authors and Contributors](#authors-and-contributors)

## Installation

### Supported Platforms
CampusPulse is designed to run on the following operating systems:
- Windows 10 and above
- macOS Mojave and above
- Linux (Ubuntu 18.04 and above)

### Installation Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/campuspulse.git
   cd campuspulse
   ```
2. Ensure you have Java Development Kit (JDK) 11 or above installed. You can download it from [Oracle's official website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
3. Compile the project:
   ```bash
   javac -d bin src/main/java/**/*.java
   ```
4. Run the application:
   ```bash
   java -cp bin views.Home_screen
   ```

### Common Installation Issues
- **Java Not Installed**: Make sure that JDK is installed. You can download it from Oracle's official website.
- **Path Issues**: Ensure that your Java installation path is correctly set in your system's environment variables.

### Troubleshooting Steps
If you encounter issues during installation, refer to the Troubleshooting Guide or search for solutions in the GitHub discussions section.

## Usage Guide

Once the application is installed, you can start using CampusPulse to:
- Search for events.
- Filter events based on various criteria such as location or date range.
- Manage your user profile.

### How to Use
1. Launch the application.
2. Use the search bar on the homepage to find events by name or keyword.
3. Use the filter dropdown to narrow down results based on location or date range.

### Example Usage
```bash
# Search for events related to 'sports'
search("sports")

# Filter events by location 'North Campus'
filterByLocation("North Campus")
```

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Feedback
We welcome feedback from users! You can provide feedback through the following methods:
- Feedback Form (Link to the form)
- Discussion Board on GitHub (Link to GitHub Discussions)

### Feedback Guidelines
- Please ensure your feedback is constructive.
- Specify any issues encountered or suggestions for improvement.

## Contributing
We appreciate contributions to the CampusPulse project! Here's how you can contribute:

1. Fork the repository:
   ```bash
   git clone https://github.com/yourusername/campuspulse.git
   ```
2. Create a new branch for your feature:
   ```bash
   git checkout -b feature/YourFeature
   ```
3. Make your changes and commit them:
   ```bash
   git commit -m "Add some feature"
   ```
4. Push to the branch:
   ```bash
   git push origin feature/YourFeature
   ```
5. Create a Pull Request.

### Contribution Guidelines
- Ensure your code follows the project's coding standards.
- Clearly describe your changes in the pull request.

## Common Issues

### Installation Issues
- **Java Compatibility**: Ensure you are using the correct version of Java (JDK 11 or above).
- **File Not Found**: Make sure all necessary files are present in the repository.

### Application Issues
If the application crashes, check the console for error messages and report them in the issues section of the GitHub repository.

## Authors and Contributors
- **Waleed Akrabi** : Search, Filter, CreationStrategy, Map API
- **Ibrahim Bilal** : Signup, Delete Event, Sort Events, Logout, Mongo DAO's
- **Ahmed Ibrahim Waqas**: Change Password, post event
- **Ali Towaiji**: Admin Approval, Home Screen - Event View
- **Tanay Langhe**: Login, Event Details, Map API Integration 
- 
- [Contributor Name(s)](Link to their GitHub or profile)

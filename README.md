# CampusPulse

Project Specification for Group # 180

Team Name: CampusPulse / EventHub
Domain: UofT Events Collator

Software Specification: The program is designed to streamline event discovery for students and affiliates at UofT. It allows users to easily search for and find events hosted by UofT affiliates/clubs/students based on their interests. The program would provide users essential event details, including titles, descriptions, timings, and locations, enabling users to have one platform to explore any events that align with their academic and social goals. 

User Stories: 
[statements of interactions between the user and the system]
[see additional instructions on Quercus]
[aim for at least one user story per group member + 1 extra; in the table below, each group member must be assigned to one user story + mark one user story as being a team user story — this one should be the one that is most central to the basic functionality of your system. That is, the one you would probably want to implement first.]

- Bob is a first year student who can’t decide which clubs to join. He’s interested in soccer, coding, and music. Instead of manually searching for each club on various platforms, Bob signs up for CampusPulse and finds a list of all the clubs in one place. On CampusConnect, his feed is personalized to these interests, allowing him to easily see an event that he is most likely to join. [team story]

- Emily is marketing director for UofT’s Climate Activism club. There’s a meeting coming up, so she creates a post on CampusPulse with the club title, description, as well as all the relevant time and location information in order to inform any interested users. [ibrahim story]

- John has joined the Climate Activism club, and he saw Emily’s post about an upcoming meeting. Since John completely forgot the location for the meeting, he follows the directions to the location Emily posted. [Ahmad Ibrahim story]

- Mathew is in charge of approving any new Clubs at UofT. Jerry, a student at UofT, recently got approved for their new “Anime Club” and wants to make a post on CampusPulse marketing their first meeting. Mathew approves Jerry as a Event Hoster using the utorid and email Jerry provided while signing up for CampusPulse as an Event Hoster. Now, Jerry can post information about any upcoming Anime Club events. [Ali story]

- Albert is interested in reading and wants to find clubs that relate to his interest. When he joins CampusPulse, he is able to search for all the clubs related to reading, and see their upcoming events. [Waleed Story]

- Sofia is a third-year student passionate about environmental sustainability. She wants to stay informed about events related to her field of study. Upon logging into CampusPulse, she utilizes the event filtering feature to receive email notifications about specific sustainability-related events happening on campus, ensuring she never misses an opportunity to engage with like-minded individuals. [Tanay story]

- Offensive language prevention, either block posts with language or censor them

Note: Specific team member stories are tentative and can change based on specific skills and interest of group members.

Proposed Entities for the Domain:
[based on your specification, indicate a few potential entities for your domain — including their names and instance variables]  

Account: Creation of an account
Username 
Password
User (Extend Account): Account for someone who is looking for events
Gender (maybe limit what is shown if certain events are gender specific?)
Preferences (What category of events they are interested in)
EventPoster (Extends Account): Account for someone to post event
CreateEvents (have functions for making, deleting and editing events)
Administrator (Extends Account)
ApprovePosters (have functions for approving or changing approval status of event posters)
Events: Class used to format events to be posted
Name
Image (Optional)
Link (Link to website with more info)
Description
Req (If only certain people are allowed to participate or certain items need to be brought)
Location
Start and end time


3 log in screen - user and event poster admin
Event Poster inherits User

Event Poster:
 - Needs an event poster screen
 - Event Description Screen
 - Profile Screen

user:
 - Event Description Screen
 - Profile Screen
 - 
Home page

Proposed API for the project:
[links to one or more APIs your team plans to make use of; include brief notes about what services the API provides and whether you have successfully tried calling the API]

MAP: https://developers.google.com/maps/documentation/javascript
Allows AccountPosters to share locations to their upcoming events
Allows general users to have directions to event locations
Signup/Login: https://firebase.google.com/products/auth 
Account authorization and identification

Scheduled Meeting Times + Mode of Communication:
[when will your team meet each week — you MUST meet during the weekly tutorial timeslot and we strongly recommend scheduling one more regular meeting time]
Meeting time outside of lab: [indicate day and time here]
Hour before lab each week
Saturday at noon every weekend, duration varies depending on specific phase of project

Mode of Communication: [indicate mode of communication here]
Modes of Communication:
iMessage project group chat for quick updates
Microsoft Teams for longer meetings
In person meetups before lab


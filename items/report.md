# [G32] Report

The following is a report template to help your team successfully provide all the details necessary for your report in a structured and organised manner. Please give a straightforward and concise report that best demonstrates your project. Note that a good report will give a better impression of your project to the reviewers.

Note that you should have removed ALL TEMPLATE/INSTRUCTION textes in your submission (like the current sentence), otherwise it hampers the professionality in your documentation.

*Here are some tips to write a good report:*

* `Bullet points` are allowed and strongly encouraged for this report. Try to summarise and list the highlights of your project (rather than give long paragraphs).*

* *Try to create `diagrams` for parts that could greatly benefit from it.*

* *Try to make your report `well structured`, which is easier for the reviewers to capture the necessary information.*

*We give instructions enclosed in square brackets [...] and examples for each sections to demonstrate what are expected for your project report. Note that they only provide part of the skeleton and your description should be more content-rich. Quick references about markdown by [CommonMark](https://commonmark.org/help/)*

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Application Description](#application-description)
4. [Application UML](#application-uml)
5. [Application Design and Decisions](#application-design-and-decisions)
6. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
7. [Testing Summary](#testing-summary)
8. [Implemented Features](#implemented-features)
9. [Team Meetings](#team-meetings)
10. [Conflict Resolution Protocol](#conflict-resolution-protocol)

## Administrative
- Firebase Repository Link: <insert-link-to-firebase-repository>
   - Confirm: I have already added comp21006442@gmail.com as a Developer to the Firebase project prior to due date.
- Two user accounts for markers' access are usable on the app's APK (do not change the username and password unless there are exceptional circumstances. Note that they are not real e-mail addresses in use):
   - Username: comp2100@anu.edu.au	Password: comp2100
   - Username: comp6442@anu.edu.au	Password: comp6442

## Team Members and Roles
The key area(s) of responsibilities for each member

| UID        |        Name        |                               Role |
|:-----------|:------------------:|:-----------------------------------|
| u7558707   |     Zhaoyun Xu     |  Project Manager, Developer |
| u7618794   |     Yuke Huang     |  UI Designer, Developer |
| u7566045   |     Yijing Jia     |  UI Designer, Developer |
| u7551551   |    Junzhe Huang    |  Developer, Tester |


## Summary of Individual Contributions

Specific details of individual contribution of each member to the project.

Each team member is responsible for writing **their own subsection**.

A generic summary will not be acceptable and may result in a significant lose of marks.

*[Summarise the contributions made by each member to the project, e.g. code implementation, code design, UI design, report writing, etc.]*

*[Code Implementation. Which features did you implement? Which classes or methods was each member involved in? Provide an approximate proportion in pecentage of the contribution of each member to the whole code implementation, e.g. 30%.]*

*you should ALSO provide links to the specified classes and/or functions*
Note that the core criteria of contribution is based on `code contribution` (the technical developing of the App).

*Here is an example: (Note that you should remove the entire section (e.g. "others") if it is not applicable)*

1. **u7551551 Junzhe Huang** I contribute 25% of the code. Here are my contributions:
    - Features Implementation
    1. [Data Search] Hard
    2. [User Chat] Hard
    - Code Implementation
        1. bean
            1. `ChatModel.class` [All of class] https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/ChatModel.java
        2. searchCore
            1. `Token.class` [All of class] https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/searchCore/Token.java
            2. `TokenHelper.class` [All of class] https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/searchCore/TokenHelper.java
            3. `Tokenizer.class` [All of class] https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/searchCore/Tokenizer.java
        3. ui
            1. `MainMenu.class` [search()] https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/MainMenu.java
            2. `SearchResultActivity.class` [search()] https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/SearchResultActivity.java
            3. `ChatActivity.class` [All of class] https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/ChatActivity.java
        4. manager
            1. `SearchDataManager.class` [All of class] https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/manager/SearchDataManager.java

    - Code Design:
        1. Tokenizer pattern
        2. Singleton pattern
        3. Database table design
        4. chat design
    - UI Design:
        1. All of UI design of chat
    - Report Writing:
        1. Application Design and Decisions (partial - 25%)
        2. Summary of Individual Contributions
    
2. **u7618794, Yuke Huang**  I have 25% contribution, as follows: 
  - **Code Contribution in the final App**
    - Basic Features - DataFiles: [scamCase.json](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/assets/scamCase.json)
    - Custom Feature - FB-Persist: Extension: 
        - `ScamCaseDao.java` [add and get data from firestore interface](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/ScamCaseDao.java#L13-22)
        - `ScamCaseDaoImpl.java`[add and get data from firestore implementation](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/ScamCaseDaoImpl.java#L66-148)
    
    **UI**
    
    - Add post activity: 
      - [AddPostPage.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/AddPostPage.java)
      - [activity_add_post_page.xml](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_add_post_page.xml)
      - [items in each Spinner](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/values/strings.xml#L30-160)
    - Case details activity: 
       - [CaseDetail.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/CaseDetail.java)
       - [activity_case_detail.xml](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_case_detail.xml)
    - main menu activity - floating action button:
       - [initFltBtn()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/MainMenu.java#L210-256)
       - [Fab design](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_main_menu.xml#L116-126)
       - [ic_add_2.xml](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/drawable/ic_add_2.xml)
    - Submit post successfully page:
       - [SubmitSuccessPage.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/SubmitSuccessPage.java)
       - [activity_submit_success_page.xml](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_submit_success_page.xml)
    - implements Parcelable to User class:
        - [CREATOR](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/User.java#L30-46)
        - [describeContents() and writeToParcel()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/User.java#L72-82)
    - implements Parcelable to ScamCase class:
        - [CREATOR](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/ScamCase.java#L59-82)
        - [describeContents() and writeToParcel()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/ScamCase.java#L180-215)
    - implements Parcelable to ScamCaseWithUser class:
        - [CREATOR](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/ScamCaseWithUser.java#L33-49)
        - [describeContents() and writeToParcel()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/ScamCaseWithUser.java#L59-76)
    - ScamCaseCardAdapter 
       - [setOnClickListener()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/adapter/ScamCaseCardAdapter.java#L44-52)
       - [OnClickListener for the item view](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/adapter/ScamCaseCardAdapter.java#L72-81)
    
  - **Code and App Design**
    - I involved in the DAO design pattern.
    - I designed the [case detail page](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_case_detail.xml), [add post page](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_add_post_page.xml), [submit successfully page](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_submit_success_page.xml) and [floating action button](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_main_menu.xml#L116-126) in main menu.
    
  - **Others**: 
    
    - responsible for 8 meeting minutes writing.
    - design the [application icon figure](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/drawable/icon.png) and implement the adaptive icons: [ic_launcher_foreground.webp](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/mipmap-xxxhdpi/ic_launcher_foreground.webp), [ic_launcher_background.xml](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/values/ic_launcher_background.xml)
    - create the [UML class diagram](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/items/media/UML-6442.png)
    - write [Conflict Resolution Protocol](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/items/Conflict_Resolution_Protocol.pdf)
    - report: responsible for Application Description, Team management, and conflict Resolution Protocol.

3. **u7566045, Yijing Jia**  I have 25% contribution, as follows: <br>
  - **Code Contribution in the final App**
    
    - Feature Implementation
    
      1. [LoadShowData] medium
      2. [Data-Profile] easy
      4. [P2P-Block] medium
      5. [Data-Persist] (Extension Part)
    - Code Implementation
    
      1. UI
    
         1. Mainmenu
            1. [MainMenu.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/MainMenu.java)
            2. [activity_main_menu.xml](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_main_menu.xml)
            3. [mainmenu_cardlist](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/mainmenu_cardlist.xml)
    
         2. Profile
            1. [Profile.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/Profile.java)
            2. [activity_profile.xml](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_profile.xml)
    
         3. UpdateProfile
            1. [UpdateProfile.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/UpdateProfile.java)
            2. [activity_update_profile.xml](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_update_profile.xml)
            3. [profile_cardlist.xml](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/profile_cardlist.xml)
         4. ChatActivity
            1. Method [initBlock()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/ChatActivity.java#L221-266)
            2. Method [blockUser()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/ChatActivity.java#L268-291)
    
      2. dao
    
         1. [blockDao](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/BlockDao.java)
         2. [blockDaoImpl](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/BlockDaoImpl.java)
         3. ScamCaseDao
            1. [getAllScamCase()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/ScamCaseDao.java#L9-12)
            2. [getDocumentId()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/ScamCaseDao.java#L23-27)
         4. ScamCaseDaoImpl 
            1. [getAllScamCase()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/ScamCaseDaoImpl.java#L43-62)
            2. [getDocumentId()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/ScamCaseDaoImpl.java#L149-170)
         5. UserDao
            1. [getUserByEmail](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/UserDao.java#L10)
         6. UserDaoImpl
            1. [getUserByEmail](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/UserDaoImpl.java#L40-59)
    
      3. adapter
    
         1. [ScamCaseCardAdapter - 60%](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/adapter/ScamCaseCardAdapter.java)
         2. [ScamCaseCardProfileAdapter](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/adapter/ScamCaseCardProfileAdapter.java)
    
      4. manager
    
         1. [UserInfoManager](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/manager/UserInfoManager.java)
    
      5. bean
    
         1. [BlockModel](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/BlockModel.java)
    
      6. tool
         1. [AndroidUtil](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/AndroidUtil.java)
  - **Code and App Design** 
    
    - Design Pattern: dao and adapter design parttern.
    - UI design: Designed UI style and created all the [UI demo](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/items/UI_Demo.zip) using Axure.
  - **Others**:  

    - Report Writing
      1. Implementation of features
      2. Individual Contributions


4. **UID1, Name1**  I have 25% contribution, as follows: <br>
  - **Code Contribution in the final App**
    - Feature A1, A2, A3 - class Dummy: [Dummy.java](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java)
    - XYZ Design Pattern -  class AnotherClass: [functionOne()](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43), [function2()](the-URL)
    - ... (any other contribution in the code, including UI and data files) ... [Student class](../src/path/to/class/Student.java), ..., etc.*, [LanguageTranslator class](../src/path/to/class/LanguageTranslator.java): function1(), function2(), ... <br><br>

  - **Code and App Design** 
    - [What design patterns, data structures, did the involved member propose?]*
    - [UI Design. Specify what design did the involved member propose? What tools were used for the design?]* <br><br>

  - **Others**: (only if significant and significantly different from an "average contribution") 
    - [Report Writing?] [Slides preparation?]*
    - [You are welcome to provide anything that you consider as a contribution to the project or team.] e.g., APK, setups, firebase* <br><br>


## Application Description


Our AntiScam application is a user-driven platform designed to combat scams by allowing users to share their experiences, provide scam alerts, and engage in meaningful communication with the content creator. 
1. Users can [post details of scam incidents](application screenshot/add_post.png) including the type of scam, title, payment method, monetary losses incurred and so on.
2. The [search functionality](application screenshot/search_for_title.png) enables easy access to specific scam cases, helping users find relevant information quickly. 
3. The app features a [private messaging](application screenshot/chat.png) system that fosters direct communication between users, facilitating the exchange of insights and clarification on scam-related issues. 
4. We prioritize user privacy and have implemented a ['block'](application screenshot/chat.png) feature, granting users control over their communication preferences by allowing them to prevent specific users from sending direct messages. 
5. On the [user's profile](application screenshot/my_profile.png) page, users can view their browsing history, and the last post read will show on the top of history section.
6. User also have choice to [delete all the history records](application screenshot/delete_history_records.png).
7. We also offer personalized services, allowing users to click on their [avatar](application screenshot/change_avatar.png) to change it to other photo.
8. The add post button in the [main menu](application screenshot/main_menu.png) is draggable, user can drag it along the right edge.

Our app aims to create an informed community, protecting individuals from potential scams.
<br><br>
<img src="application screenshot/main_menu.png"  width="30%" height="30%">
<img src="application screenshot/add_post.png"  width="30%" height="30%">
<img src="application screenshot/search_for_title.png"  width="30%" height="30%">
<img src="application screenshot/chat.png"  width="30%" height="30%">
<img src="application screenshot/other_user_profile.png"  width="30%" height="30%">
<img src="application screenshot/my_profile.png"  width="30%" height="30%">
<img src="application screenshot/delete_history_records.png"  width="30%" height="30%">
<img src="application screenshot/change_avatar.png"  width="30%" height="30%">
<br><br>
### Application Use Cases and or Examples

**Target Users: Public**
- **Use Case 1: Searching for Scam Case**
   - **User:** John
   - **Scenario:** John receives a suspicious email advertising a product at an unbelievably low price, significantly lower than the market price. He is unsure whether this is a scam or a genuine bargain.
   - **Action:** John opens the AntiScam app and uses the search feature to look for product and service scams.
  - **Result:** John finds some posts that exactly match his scenario, which confirms that this is a scam, and he saves $150.
<br><br>
- **Use Case 2: Knowing the Latest Scam Tactics**
   - **User:** Sarah
   - **Scenario:** Sarah is curious about the latest scam tactics.
   - **Action:** She accesses the AntiScam app and search for scam cases by interested title or scam types. 
   - **Result:** Sarah find there are various of Scam Tactics, and knowing it in advance can help her to avoid being scammed.
<br><br>
  
**Target Users: Victims**
- **Use Case 3: Reporting a Scam Case and Alerting Others**
    - **User:** Amy
    - **Scenario:**  After identifying that she has encountered a social media scam, Amy notices that the script used in the chat is slightly different from the cases she has seen before.
    - **Action:** Amy clicks the plus button on the main menu page and fills in the required information, including title, lost amount, scam type, and more. She then describes the detailed process she experienced and shares the new scam script.
    - **Result:**  The AntiScam app records her scam case, and when she returns to the main page, she finds that her post is already showed at the top, and all other users can see the new post.
<br><br>
- **Use Case 4: Seek help and potential ways to get money back**
    - **User:** Vera
    - **Scenario:** Vera feels hopeless because she just lost a large amount of money by a scam.
    - **Action:** Vera opens the AntiScam app and add a post about her experience. In the description part, she includes the willing to get help. Then she submits it.
    - **Result:**  Other users see her post and message her privately. Some users provide really useful methods which help Vera to calm down. But Vera encounter one user is up to no good. She decides to block that user from message her.
<br><br>
- **Use Case 5: Seek Comfort and Social needs**
   - **User:** Emily
   - **Scenario:** Emily faced a romance scam recently, she cries every day and want to find someone to talk with.
   - **Action:** She opens AntiScam app and search for romance scams.
   - **Result:** She finds one user called Isabella also face romance scam recently,she messages Isabella and share her story, they become good friends.
<br><br>

**Target Users:authorities such as government agencies, police and so on**
- **Use Case 6: make announcement about recent scams**
    - **User:** government agencies, police and so on
    - **Scenario:** These authorities want to make official announcements about recent scams to inform and alert the public.
    - **Action:** They log in to the AntiScam application and create a new announcement. 
    - **Result:**  The announcement is published on the AntiScam platform, and users can access this important information to stay informed and protected against scams.
      <br><br>
  
**Staff to maintain the application**
- **Use Case 7: make announcement about recent scams**
    - **User:** Moderator
    - **Scenario:** A user post a new scam case in AntiScam app.
    - **Action:** They review recent posts to ensure this user adhere to community guidelines.
    - **Result:** Inappropriate content is removed, and other users can not see it.
  <br><br>
<hr> 

### Application UML

![UML ClassDiagram](media/UML-6442.png) <br><br>
<hr>



## Code Design and Decisions

This is an important section of your report and should include all technical decisions made. Well-written justifications will increase your marks for both the report as well as for the relevant parts (e.g., data structure). This includes, for example,

- Details about the parser (describe the formal grammar and language used)

- Decisions made (e.g., explain why you chose one or another data structure, why you used a specific data model, etc.)

- Details about the design patterns used (where in the code, justification of the choice, etc)

*Please give clear and concise descriptions for each subsections of this part. It would be better to list all the concrete items for each subsection and give no more than `5` concise, crucial reasons of your design.

<hr>

### Data Structures

*[What data structures did your team utilise? Where and why?]*

Here is a partial (short) example for the subsection `Data Structures`:*

*I used the following data structures in my project:*

1. *LinkedList*
   * *Objective: used for storing xxxx for xxx feature.*
   * *Code Locations: defined in [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and [class AnotherClass, lines l1-l2](url); processed using [dataStructureHandlerMethod](url) and ...
   * *Reasons:*
      * *It is more efficient than Arraylist for insertion with a time complexity O(1)*
      * *We don't need to access the item by index for xxx feature because...*
      * For the (part), the data ... (characteristics) ...

2. ...

3. ...

<hr>

### Design Patterns
*[What design patterns did your team utilise? Where and why?]*

1. *xxx Pattern*
   * *Objective: used for storing xxxx for xxx feature.*
   * *Code Locations: defined in [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and [class AnotherClass, lines l1-l2](url); processed using [dataStructureHandlerMethod](url) and ...
   * *Reasons:*
      * ...

<hr>

### Parser

### <u>Grammar(s)</u>
Designed with a tokenizer-based approach for parsing grammar, utilizing common string prefixes to reduce query time, minimize unnecessary string comparisons, resulting in higher query efficiency than a hash tree.

Production Rules:

elixir
Copy
%=2000    ::= Query amount
#=title   ::= Query title
@=xxx@gmail.com ::= Query username
%>2000   ::= Greater-than query
%>=2000  ::= Greater-than or equal to query
%<2000   ::= Less-than query
%<=2000  ::= Less-than or equal to query
%!=2000  ::= Not-equal-to query
%=2000&#=title ::= AND query
%=2000||#=title ::= OR query
### <u>Tokenizers and Parsers</u>

*Collects user search strings, incorporates them into tokenizer parsing, generating corresponding tokens, and storing corresponding values. Then, generates corresponding Firebase Filter syntax based on these tokens.

Advantages:

Text Preprocessing: The tokenizer can convert raw text into discrete language units such as words or sub-words. Tokenization allows for the transformation of text into a form that computers can understand and process.
Language Understanding: Tokenizers can help us better understand the meaning of the text. By dividing the text into language units, we can more easily identify the structure of sentences, the meaning of words, and their relationships.
Feature Extraction: In natural language processing tasks, tokenizers can serve as a part of feature extraction. By dividing the text into words or phrases, we can extract various statistical features, such as word frequency, part of speech, etc.
Multilanguage Support: Tokenizers can handle multiple languages, including English, Chinese, French, etc. For cross-language text processing tasks, tokenizers are indispensable tools. Different languages have different tokenization rules and characteristics.
Customized Rules: Tokenizers often provide functionality for customized rules, which can be adjusted according to specific needs.*

### Others

*[What other design decisions have you made which you feel are relevant? Feel free to separate these into their own subheadings.]*

<br>
<hr>

## Implemented Features

### Basic Features
1. [LogIn]. Log in refers to the process where users enter their username and password on a login page to verify their identity and grant them access privileges. User information is stored in Firebase. If the username and password entered on the login page are correct, the user logs in successfully and is redirected to the app's main page. Otherwise, a toast message pops up, indicating that the username or password is incorrect.

   Two pre-existing accounts have been added to Firebase: 

   Username: [comp6442@anu.edu.au](mailto:comp6442@anu.edu.au), Password: comp6442 

   Username: [comp2100@anu.edu.au](mailto:comp2100@anu.edu.au), Password: comp2100  (easy)<br>

   * Code: 

     Class [act/LoginActivity](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/LoginActivity.java) 

     Class [manager/FirebaseAuthManage](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/manager/FirebaseAuthManager.java)

2. [DataFiles].2,500 scam cases were created, including information such as post user, post date, scam type, title, description, etc. The data files were stored in JSON format and then batch-imported into Firebase as a collection. (easy)

   * Code

     Data File [scamCase.json](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/assets/scamCase.json), 

     Class LoginActivity

     ​	- Method [updateFirestore](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/LoginActivity.java#L164-224)

   * Link to the Firebase repo: [scam_cases](https://console.firebase.google.com/project/antiscam-5b447/firestore/data/~2Fscam_cases)

3. [LoadShowData]. When a user is logged in and redirected to the main menu, scam cases listed on the main menu will be loaded from Firebase every 10 seconds. Since each scam case contains both scam case information and user information, we load and combine them together, presenting them as a single scam case card. If a new post is added, upon returning to the main page, the data on the main page will also be refreshed. If a user clicks on a scam case card, they will be directed to the detailed information page for that scam case. Additionally, the user's avatar will also be loaded from Firebase. (Medium)

   - Code

     Class [act/MainMenu](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/MainMenu.java)

     Class [act/CaseDetail](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/CaseDetail.java)

     Class [act/History](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/History.java)

     Class [adapter/ScamCaseCardAdapter](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/adapter/ScamCaseCardAdapter.java)

     Class [dao/ScamCaseDao](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/ScamCaseDao.java)

     Class [dao/ScamCaseDaoImpl](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/ScamCaseDaoImpl.java)

     Class [dao/ScamCaseUserCombine](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/ScamCaseUserCombine.java)

     Class [dao/UserDao](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/UserDao.java)

     Class [dao/UserDaoImpl](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/UserDaoImpl.java)

     Class [manager/UserInfoManager](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/manager/UserInfoManager.java)

     Class [bean/ScamCase](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/ScamCase.java)
     
     Class [bean/User](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/User.java)
     
     Class [bean/ScamCaseWithUser](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/ScamCaseWithUser.java)
     

4. [Search] Users are able to search for scam case information on app. Search feature utilises the Tokenizer and Parser. Combined searches are supported. (medium)

   - Search token, combine symbol and compare symbol
   
     Token and search target
   
     - token: @, search target: user email
     - token: #, search target: title
     - token: %, amount
     - token: $, scam type
   
     The symbols '&' (for 'and') and '|' (for 'or') are used, as well as the comparison symbols '=', '<',  '>', '>=', '<=' and so on. 
   
   - Code
   
      Class act/MainMenu
   
      ​	- Method [search()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/MainMenu.java#L258-273)
   
      Class [act/SearchResultActivity](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/SearchResultActivity.java)
   
      Class [searchCore/Token](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/searchCore/Token.java)
   
      Class [searchCore/Parser](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/searchCore/Parser.java)
   
      Class [searchCore/Tokenizer](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/searchCore/Tokenizer.java)
   
      Class [manager/SearchDataManager](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/manager/SearchDataManager.java)
   


### Custom Features
Feature Category: Greater Data Usage, Handling and Sophistication <br>
1. [Data-Profile]. A user profile page has been created, which includes the user's profile image and username. Users can also modify their profile image and user name, by clicking image or username users will be redirected to update profile page.  (easy)
   
   * Code:
   
     Class [act/Profile](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/Profile.java) 
   
     Class [act/UpdateProfile](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/UpdateProfile.java)
   
     Class [adapter/ScamCaseCardProfileAdapter](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/adapter/ScamCaseCardProfileAdapter.java)
   
     Class [tool/CircleImageTransformer](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/CircleImageTransformer.java)
   
2. [Data-Deletion]. Users can delete posts they've created. When a user clicks 'Delete,' the corresponding data will be removed from the Firebase scam_case collection, at the same time, the data will also be deleted from AVLTree (We implemented LRU and a custom underlying data structure of LRU named "TreeHashMap"). After deletion, user posts list will be refreshed. (medium)

   - Code:

     Class act/Profile 

     ​	- Method [onDelBtnClick](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/Profile.java#L126-159)

     ​	- Method [deletePost](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/Profile.java#L292-312)
   
     Class tool/LRUCache
   
     ​	- Method [remove](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/LRUCache.java#L80-85)

Feature Category: Firebase Integration 
3. [FB-Auth] Use Firebase to implement User Authentication.  (easy)

   * Code: 

     Class act/LoginActivity

     ​	- Method [logIn](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/LoginActivity.java#L135-149) 

4. [FB-Persist] Use Firebase to persist all data used in app. User could add posts and the data will be stored in Firebase. Without restarting, when other users swipe down, the scam case list will be updated synchronously as the Firebase is updated. This means that users will be able to see the instant updates from another user. (hard)

   - Code:

     Class [act/AddPostPage](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/AddPostPage.java)

     Class [act/SubmitSuccessPage](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/SubmitSuccessPage.java)

     Class dao/ScamCaseDao

     ​	- Method [updateNextId](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/ScamCaseDao.java#L19-22)

     Class dao/ScamCaseDaoImpl

     ​	- Method [addScamCase](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/ScamCaseDaoImpl.java#L91-101)

     ​	- Method [getNextId](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/ScamCaseDaoImpl.java#L103-125)

     ​	- Method [updateNextId](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/ScamCaseDaoImpl.java#L127-137)

     Class act/Mainmenu

     ​	- Method [swipeRefresh](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/MainMenu.java#L154-164)

     ​	- Method [reloadMainmenuScamCase](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/MainMenu.java#L165-173)

Feature Category: Peer to Peer Messaging

5. [P2P-DM] Provide users with the ability to message each other directly in private. All messages will be instantly received by both parties in the private conversation. (hard)

   - Code:

     Class [act/ChatActivity](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/ChatActivity.java)

     Class [adapter/ChatAdapter](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/adapter/ChatAdapter.java)
     
     Class [bean/ChatModel](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/ChatModel.java)

6. [P2P-Block] Provide users with the ability to ‘block’ and prevent another user from direct messaging them. However, users can still send private messages to users they have blocked. Users who have been blocked will be unable to send private messages to the person who blocked them. (medium)

   - Code:

     Class act/ChatActivity

     ​	- Method [initBlock](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/ChatActivity.java#L232-277)

     ​	- Method [blockUser](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/ChatActivity.java#L279-301)

     Class [dao/BlockManager](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/BlockManager.java)

     Class [bean/BlockModel](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/BlockModel.java)

<hr>

### Surprised Features

- Suprised feature is not implemented

   <hr>

## Summary of Known Errors and Bugs

*[Where are the known errors and bugs? What consequences might they lead to?]*
*List all the known errors and bugs here. If we find bugs/errors that your team does not know of, it shows that your testing is not thorough.*

*Here is an example:*

1. *Bug 1:*
   - *A space bar (' ') in the sign in email will crash the application.*
   - ...

2. *Bug 2:*
3. ...

<br> <hr>


## Testing Summary

We've done testing through two ways: white box, black box testing. And we've found bugs and solved some of them by debugging or logging to the console.

- White box testing

  In white box testing, we utilized the emulator to execute all our methods and code. Given our familiarity with the expected outcomes of the code, we tailored our tests accordingly. For example, we inspected the UI layout to confirm it aligned with our intended design. At the same time, we tested the search functionality by setting various search parameters and ensuring the results matched our expectations. This approach helped us detect and rectify certain overlooked errors.

  We managed to successfully test some functionalities like "onCreate" or "onClick". By examining the specifics of the code within these methods, we could check if the appropriate actions were being triggered. For instance, if we clicked the button, we would certainly know if there was an error by looking at the interface and database to check if that's the right function we wanted to achieve.

- Black box testing:

  - We wrote some black box testing by Junit to ensure our tokenizer, parser functions are implemented correctly. The codes we tested are as follows:
    - [AVLTree.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/AVLTree.java)
    - [LRUCache.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/LRUCache.java)
    - [TreeHashMap.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/TreeHashMap.java)
    - Token.java
    - Tokenizer.java

  - The test we wrote is to test most of the functions we are using and track their outputs. For example, in the LRUCache class, we wrote several tests to meet our application requirements:

    ```java
    testBasicFunction()  // Tests the fundamental operations of the cache
    testEmptyCache()	// Ensures the cache behaves correctly when it's empty.
    testCacheSizeLimit()	// Checks that the cache does not exceed its predefined size limit.
    testAddWhenFull()	 // Validates that when the cache is full and a new item is added, the least recently used item is correctly added.	
    testGetAll() 	// Tests the ability to retrieve all items in the cache.
    testRemove()	 // Ensures that items can be manually removed from the cache
    testEvictionOrder()	    // Validates the order in which items are added
    testRecentUsage()		 // Checks that accessing an item updates its usage.
    testOverwriteExistingKey()	 // Ensures that if a key already exists in the cache 
    testNullValue()	 // Tests the cache's behavior when null values are added.
    ```

- Test Coverage

  We didn't write any tests for the interfaces, UI classes or database connections, and we have done a test coverage report. The testing classes and functions are in the image:

  - In the package tool:(一些类可能要移进来)
  - 

- Debugging and Logging

  In Android Studio, we set breakpoints in our code, which means when the app runs, it will 'pause' at these breakpoints. This allows us to inspect the current state of the app and examine the values of variables. For example, if we want to see the content of a list of scam cases at a particular point in our code, we can set a breakpoint and the debugger will show us the content of that list when it hits the breakpoint.

  Another way we understand what's happening in our app is by using log statements, like `Log.d()`. Think of these as little notes or messages we leave for ourselves. When a piece of code runs, it leaves these messages in the Logcat, a console in Android Studio. For example, after fetching data from the database, we might leave a message saying 'Data got from database'. Later, we can look at Logcat and see this message, helping us confirm that our data fetching code ran successfully. When dealing with firestore database, understanding the sequence in which data is fetched or saved is crucial and hard, as they may asynchronously load. We can use log statements right after database operations. By checking the sequence of log messages, we will understand the order of our database operations.

<br><hr>


## Team Management

### Meetings Records
- *[Team Meeting 1](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/items/Meeting%20materials/meeting1.md)*
- *[Team Meeting 2](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/items/Meeting%20materials/meeting%202.md)*
- *[Team Meeting 3](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/items/Meeting%20materials/meeting%203.md)*
- *[Team Meeting 4](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/items/Meeting%20materials/meeting%204.md)*
- *[Team Meeting 5](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/items/Meeting%20materials/meeting%205.md)*
- *[Team Meeting 6](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/items/Meeting%20materials/meeting%206.md)*
- *[Team Meeting 7](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/items/Meeting%20materials/meeting%207.md)*
- *[Team Meeting 8](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/items/Meeting%20materials/meeting%208.md)*


| No. |    Date    |                Topic                |
|:----|:----------:|:-----------------------------------:|
| 1   | 2023-09-06 |  review of assignment requirements  |
| 2   | 2023-09-07 |        discuss app scenarios        | 
| 3   | 2023-09-08 |       discuss basic features        |
| 4   | 2023-09-10 |         UI tasks allocation         | 
| 5   | 2023-09-14 |      custom features selection      |
| 6   | 2023-09-17 | implement basic and custom features |
| 7   | 2023-10-06 |        review tasks progress        |
| 8   | 2023-10-18 |  report + code review +checklist    |
In addition to these eight meetings, our team have regular stand-up meetings to review the progress of our tasks. Furthermore, whenever a team member encounters challenges or difficulties, we support and help each other. All the team members are good at teamwork, each meeting we can communicate effectively and every groupmates can finish their allocated tasks on time.

<hr>

### Conflict Resolution Protocol
- *[Conflict Resolution Protocol](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/items/Conflict_Resolution_Protocol.pdf)*

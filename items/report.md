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
        - [ScamCaseDao.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/ScamCaseDao.java#L13-22)
        - [ScamCaseDaoImpl.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/ScamCaseDaoImpl.java#L66-148)
    
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
    - other 
       - [setOnClickListener()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/adapter/ScamCaseCardAdapter.java#L44-52)
       - [OnClickListener for the item view](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/adapter/ScamCaseCardAdapter.java#L72-81)
    
  - **Code and App Design** 
    - I involved in the DAO and adapter design pattern.
    - I designed the [case detail page](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_case_detail.xml), [add post page](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_add_post_page.xml), [submit successfully page](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_submit_success_page.xml) and [floating action button](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_main_menu.xml#L116-126) in main menu.

  - **Others**: 
    - responsible for 8 meeting minutes writing.
    - design the [application icon figure](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/drawable/icon.png) and implement the adaptive icons: [ic_launcher_foreground.webp](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/mipmap-xxxhdpi/ic_launcher_foreground.webp), [ic_launcher_background.xml](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/res/values/ic_launcher_background.xml)
    - create the UML class diagram independently
    - report: responsible for Application Description, Team management, and conflict Resolution Protocol.

3. **UID1, Name1**  I have 25% contribution, as follows: <br>
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

*[What is your application, what does it do? Include photos or diagrams if necessary]*

*Here is a pet specific application example*

*PetBook is a social media application specifically targetting pet owners... it provides... certified practitioners, such as veterians are indicated by a label next to their profile...*

### Application Use Cases and or Examples

**Target Users: General Public**
- **Use Case 1: Searching for Scam Case**
   - **User:** John
   - **Scenario:** John receives a suspicious email advertising a product at an unbelievably low price, significantly lower than the market price. He is unsure whether this is a scam or a genuine bargain.
   - **Action:** John opens the AntiScam app and uses the search feature to look for product and service scams.
  - **Result:** John finds some posts that exactly match his scenario, which confirms that this is a scam, and he saves $150.

- **Use Case 2: Reporting a Scam Case**
   - **User:** Amy
   - **Scenario:**  After identifying that she has encountered an social media scam, Amy notices that the script used in the chat is slightly different from the cases she has seen before.
   - **Action:** Amy clicks the plus button on the main menu page and fills in the required information, including title, lost amount, scam type, and more. She then describes the detailed process she experienced and shares the new scam script. 
   - **Result:**  The AntiScam app records her scam case, and when she returns to the main page, she finds that her post is already showed at the top, and all other users can see the new post.

- **Use Case 3: Reporting a Scam Case**

   - **Scenario:** Emily is about to make an online purchase and wants to check if the seller is reliable.
   - **Action:** She enters the seller's information into the app's search bar.
  - **Result:** The app displays scam alerts related to the seller, helping Emily make an informed decision.

**Target Users: Anti-Scam Community**
Use Case 3: Discussing Scam Tactics

User: Sarah
Scenario: Sarah is curious about the latest scam tactics.
Action: She accesses the app's discussion forum.
Result: Sarah joins a conversation about scams, shares her insights, and learns from others in the community.
Use Case 4: Alerting Others About a New Scam

User: Michael
Scenario: Michael falls victim to a new scam and wants to warn others.
Action: He creates a scam alert post and adds a detailed description.
Result: The post is published, and other users can see it and avoid falling for the same scam.
Target Users: Moderators
Use Case 5: Reviewing and Moderating Posts

User: Moderator
Scenario: The moderator logs into the AntiScam app.
Action: They review recent posts and comments to ensure they adhere to community guidelines.
Result: Inappropriate content is removed, and users are notified of the action taken.
Use Case 6: Supporting Users

User: Moderator
Scenario: A user reports an issue with the app.
Action: The moderator responds to the user's request for assistance.
Result: The user's issue is resolved, ensuring a positive user experience.

<hr> 

### Application UML

![UML ClassDiagram](media/UML-6442.png) <br>
<hr>

## Code Design and Decisions

<hr>

### Data Structures

*We used the following data structures in our project:*

1. *LRU cache (A custom implemented cache mechanism)*
   * *Objective:*
      * To efficiently store and retrieve information about scam cases that users have read, we implemented a custom TreeHashMap in LRUCache. Each time a user reads a new scam case, its details are stored in this TreeHashMap. Additionally, we use a custom doubly-linked list to keep track of the order in which users have read the scam cases, allowing the historical interface to display these cases in the order of reading. This design effectively forms an LRUCache structure with a fixed capacity of 100 cases. Once this capacity is filled, the earliest read case will be pushed to make room for a new scam case upon its addition.
      * Every activity which is trying to use cache firstly use [getInstance()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/HistoryCache.java#L16), then use [getCache()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/HistoryCache.java#L24) and [setCache()](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/HistoryCache.java#L28) to get and update cache in the local files of phones or simulators.
   * *Code Locations: defined in [Class LRUCache](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/LRUCache.java)*
   * *Reasons:*
      * Frequently accessed data can be retrieved rapidly from memory, avoiding the need to reload from a database or another store, thus significantly reducing data access latency. Users can conveniently view the scam cases they've most recently read without the wait of extensive loading or retrieval processes.
2. *TreeHashMap (A custom implemented hash map)*
   * *Objective:*
     * TreeHashMap is created in `TreeHashMap.java`, then`LRUCache` use it in [line 10](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/LRUCache.java#L18). When users reads case information in application, we store and update cache, treehashmap and doubly-linked list.
   * *Code Locations: defined in [Class TreeHashMap](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/TreeHashMap.java)*
   * *Reasons:*
     * In the ideal scenario where no hash collisions are present, operations like querying, inserting, and deleting within our cache are remarkably efficient with a time complexity of ***O*(1)**. This design choice ensures an fast response for data retrieval, insertion, and removal from the cache.
     * By adding a custom TreeHashMap to our LRU Cache for key-value pair storage, we gain the advantage of both structured hierarchical storage and quick access.
3. *AVL Tree(A custom implemented data structure)*
   * *Objective:*
     * AVLTree is used as the underlying data structure for hash buckets in TreeHashMap, with every hash map has 10 avl trees in total.
   * *Code Locations: defined in [Class AVLTree](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/AVLTree.java)*
   * *Reasons:*
     * AVL trees offer a consistent time complexity of ***O(log(n))*** for operations like queries, insertions, and deletions. Even in cases where hash collisions occur in hash map, the retrieval, addition, and removal of data from hash buckets remain fast due to the AVL tree's self-balancing property.
     * Compared to linked lists and standard binary search trees, AVL trees ensure a more balanced structure, preventing the tree from becoming too skewed on extreme cases. 
     * AVL trees strike a balance between operation speed and complexity. AVL trees has better querying efficiency compared to red-black trees, they slightly lag behind in insertion and deletion. However, this trade-off is often worthwhile, especially when considering the added complexity of implementing red-black trees.
     *  B-trees have their own advantages in large datasets or databases because they store data in large nodes, AVL trees offer a more straightforward implementation for memory. As we only store 100 cases, while 10 cases for every avl tree in average,  AVL trees become the best choice for smaller datasets.
4. *Doubly-linked List*
   * *Objective:*
     * In LRU Cache, a doubly-linked list is implemented to track the order in which scams are read.
   * *Code Locations: defined in [Class DoublyLinkedList](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/LRUCache.java#L99)*
   * *Reasons:*
     * The inherent structure of a doubly-linked list enables efficient removal of nodes from any position and addition to the front ensuring that the cache operations remain rapid and the least recently used items are easily identified and removed when the cache is full.
5. *Json*
   * *Objective:*
     * We store cache as local files in json format.
   * *Code Locations: defined in Method [saveCacheToInternalStorage](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/CacheToFile.java#L22), [loadCacheFromInternalStorage](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/CacheToFile.java#L32)*
   * *Reasons:*
     * The inherent structure of a doubly-linked list enables efficient removal of nodes from any position and addition to the front ensuring that the cache operations remain rapid and the least recently used items are easily identified and removed when the cache is full.

<hr>
### Design Patterns

*We used the following design patterns in our project:*

1. *DAO*
   * *Objective: In our project, we implemented the DAO (Data Access Object) pattern in our data querying and storage classes.*
   * *Code Locations: defined in [BlockDao.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/BlockDao.java), [BlockDaoImpl.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/BlockDaoImpl.java), [ScamCaseDao.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/ScamCaseDaoImpl.java), [ScamCaseDaoImpl.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/ScamCaseDaoImpl.java)*, [UserDao.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/UserDao.java), [UserDaoImpl.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/UserDaoImpl.java)
   * *Reasons:*
      * The main reason for adopting this pattern is that it makes data access logic away from our business logic(like in Adapter or Activity). This separation allows us for more flexibility when it comes to modifying or extending our data sources, at the same time, ensuring that our business logic remains unchanged. Moreover, by using the DAO pattern, our code becomes more readable and maintainable, which makes debugging more easily.
2. *Singleton*
   * *Objective: We implemented the Singleton pattern in our data querying and storage classes to guarantee a single instance throughout the application. We've also implemented it on the LRUCache.*
   * *Code Locations: defined in [BlockDaoImpl.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/BlockDaoImpl.java), [ScamCaseDaoImpl.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/ScamCaseDaoImpl.java)*, [UserDaoImpl.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dao/UserDaoImpl.java), [HistoryCache.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/HistoryCache.java)
   * *Reasons:*
     * The Singleton pattern plays an important role in efficiently managing resources within an application. By ensuring that only one instance of a dao class exists, we can effectively control our application's memory consumption, thus reducing overhead and boosting the application's performance. Although Firebase utilizes a connection pool to solve issues related to resource contention and data inconsistency, our primary motivation was to economize memory usage and improve operational performance. This approach enhances object management, eliminates the unnecessary replication of objects or connections, and results in smoother, more responsive application behavior.
3. *Interator*
   * *Objective: We implemented the Iterator design pattern on our doubly-linked list to provide a unified way to traverse through its elements.*
   * *Code Locations: defined in [Class LRUCache.DoublyLinkedList.DLLIterator](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/LRUCache.java#L235-267)*
   * *Reasons:*
     * The Iterator pattern offers several advantages when applied to a doubly-linked list. Firstly, it abstracts the underlying data structure, allowing for a consistent way to access elements of the list. This enables potential future modifications to the list without altering other function. Secondly, the Iterator enhances code readability by offering a standard nethod to iterate, reducing the complexities of traversal. Lastly, it provides a safer way to read through the list by guarding against concurrent modifications, which might lead to unpredictable danger.

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
| No   | 待确认点/待办                                           |
| :--- | ------------------------------------------------------- |
| 1    | Class FirebaseAuthManage是否需要写进Log in的feature？？ |
| 2    | DataFile需要将comp21006442@gmail.com加为Developer       |
| 3    | Login中的updateFirestore是用来做什么的？？              |
| 4    | LoadShowData是否还需要接收notifications？               |
|      |                                                         |
|      |                                                         |
|      |                                                         |
|      |                                                         |
|      |                                                         |



## Implemented Features

*[What features have you implemented? where, how, and why?]* <br>
*List all features you have completed in their separate categories with their featureId. THe features must be one of the basic/custom features, or an approved feature from Voice Four Feature.*

### Basic Features
1. [LogIn]. Log in refers to the process where users enter their username and password on a login page to verify their identity and grant them access privileges. User information is stored in Firebase. If the username and password entered on the login page are correct, the user logs in successfully and is redirected to the app's main page. Otherwise, a toast message pops up, indicating that the username or password is incorrect.

   Two pre-existing accounts have been added to Firebase: 

   Username: [comp6442@anu.edu.au](mailto:comp6442@anu.edu.au), Password: comp6442 

   Username: [comp2100@anu.edu.au](mailto:comp2100@anu.edu.au), Password: comp2100  (easy)<br>

   * Code: 

     Class [LoginActivity](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/LoginActivity.java#L1-225) 

     **????** Class [FirebaseAuthManage](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/singleton/FirebaseAuthManager.java#L1-72)

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

     Class [adapter/ScamCaseCardAdapter](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/adapter/ScamCaseCardAdapter.java)

     Class [dataclass/ScamCaseDao](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/ScamCaseDao.java)

     Class [dataclass/ScamCaseUserCombine](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/ScamCaseUserCombine.java)

     Class [dataclass/UserDao](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/UserDao.java)

     Class [dataclass/UserDaoImpl](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/UserDaoImpl.java)

     Class [dataclass/UserInfoManager](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/UserInfoManager.java)

     Class [bean/ScamCase](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/ScamCase.java)

     Class [bean/ScamCaseWithUser](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/ScamCaseWithUser.java)

     Class [bean/User](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/User.java)

4. [Search] Users are able to search for scam case information on app. Search feature utilises the Tokenizer and Parser. Combined searches are supported. (medium)

   - Search token, combine symbol and compare symbol

     | Token | Search target |
     | :---- | ------------- |
     | @     | user email    |
     | #     | title         |
     | %     | amount        |
     | $     | scam type     |

     The symbols '&' (for 'and') and '|' (for 'or') are used, as well as the comparison symbols '=', '<',  '>' and so on. 

   - Code

     Class [act/SearchResultActivity](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/SearchResultActivity.java)

     Class [core/Token](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/core/Token.java)

     Class [core/TokenHelper](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/core/TokenHelper.java)

     Class [core/Tokenizer](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/core/Tokenizer.java)

     Class [repository/DataRepository](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/repository/DataRepository.java)

     <br>

### Custom Features
Feature Category: Greater Data Usage, Handling and Sophistication <br>
1. [Data-Profile]. A user profile page has been created, which includes the user's profile image and username. Users can also modify their profile picture and username on this page by clicking image or username.  (easy)
   
   * Code:
   
     Class [act/Profile](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/Profile.java) 
   
     Class [act/UpdateProfile](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/UpdateProfile.java)
   
     Class [adapter/ScamCaseCardProfileAdapter](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/adapter/ScamCaseCardProfileAdapter.java)
   
     Class [tool/CircleImageTransformer](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/CircleImageTransformer.java)
   
2. [Data-Deletion]. Users can delete posts they've created. When a user clicks 'Delete,' the corresponding data will be removed from the Firebase scam_case collection, at the same time, the data will also be deleted from AVLTree (We implemented LRU and a custom underlying data structure of LRU named "TreeHashMap"). After deletion, user posts list will be refreshed. (medium)

   - Code:

     Class act/Profile 

     ​	- Method [onDelBtnClick](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/Profile.java#L126-159)<br>

     ​	- Method [deletePost](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/Profile.java#L292-312)

Feature Category: Firebase Integration <br>
3. [FB-Auth] Use Firebase to implement User Authentication.  (easy)

   * Code: 

     Class act/LoginActivity

     ​	- Method [logIn](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/LoginActivity.java#L135-149) 

     ​	- Method [onStart](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/LoginActivity.java#L126-133)

4. [FB-Persist] Use Firebase to persist all data used in app. User could add posts and the data will be stored in Firebase. Without restarting, when other users swipe down, the scam case list will be updated synchronously as the Firebase is updated. This means that users will be able to see the instant updates from another user. (hard)

   - Code:

     Class [act/AddPostPage](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/AddPostPage.java)

     Class [act/SubmitSuccessPage](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/SubmitSuccessPage.java)

     Class [builder/ScamCaseBuilder](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/builder/ScamCaseBuilder.java)

     Class dataclass/ScamCaseDao

     ​	- Method [updateNextId](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/ScamCaseDao.java#L19-27)

     Class dataclass/ScamCaseDaoImpl

     ​	- Method [addScamCase](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/ScamCaseDaoImpl.java#L90-101)

     ​	- Method [getNextId](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/ScamCaseDaoImpl.java#L103-125)

     ​	- Method [updateNextId](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/ScamCaseDaoImpl.java#L127-137)

     Class [tool/CheckInput](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/CheckInput.java)

     Class [tool/GetString](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/GetString.java)

     Class [tool/NewDate](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/tool/NewDate.java)

     Class act/Mainmenu

     ​	- Method [swipeRefresh](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/MainMenu.java#L154-164)

     ​	- Method [reloadMainmenuScamCase](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/MainMenu.java#L165-173)

Feature Category: Peer to Peer Messaging

5. [P2P-DM] Provide users with the ability to message each other directly in private. All messages will be instantly received by both parties in the private conversation. (hard)

   - Code:

     Class [act/ChatActivity](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/ChatActivity.java)

     Class [adapter/ChatAdapter](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/adapter/ChatAdapter.java)

6. [P2P-Block] Provide users with the ability to ‘block’ and prevent another user from direct messaging them. However, users can still send private messages to users they have blocked. Users who have been blocked will be unable to send private messages to the person who blocked them. (medium)

   - Code:

     Class act/ChatActivity

     ​	- Method [initBlock](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/ChatActivity.java#L232-277)

     ​	- Method [blockUser](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/act/ChatActivity.java#L279-301)

     Class [dataclass/BlockManager](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/dataclass/BlockManager.java)

     Class [bean/BlockModel](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/main/java/com/example/antiscam/bean/BlockModel.java)

<hr>

### Surprised Features

- Suprised feature is not implemented

  <br> <hr>

## Summary of Known Errors and Bugs

*[Where are the known errors and bugs? What consequences might they lead to?]*
*List all the known errors and bugs here. If we find bugs/errors that your team does not know of, it shows that your testing is not thorough.*

*Here is an example:*

1. *Bug 1:*
   - *A space bar (' ') in the sign in email will crash the application.*
   - ...

2. *Bug 2:*

3. ...

   


## Testing Summary

We've done testing through two ways: white box, black box testing. And we've found bugs and solved some of them by debugging or logging to the console.

- White box testing

  In white box testing, we utilized the emulator to execute all our methods and code. Given our familiarity with the expected outcomes of the code, we tailored our tests accordingly. For example, we inspected the UI layout to confirm it aligned with our intended design. At the same time, we tested the search functionality by setting various search parameters and ensuring the results matched our expectations. This approach helped us detect and rectify certain overlooked errors.

  We managed to successfully test some functionalities like "onCreate" or "onClick". By examining the specifics of the code within these methods, we could check if the appropriate actions were being triggered. For instance, if we clicked the button, we would certainly know if there was an error by looking at the interface and database to check if that's the right function we wanted to achieve.

- Black box testing:

  - We wrote some black box testing by Junit to ensure our tokenizer, parser functions are implemented correctly. The codes we tested are as follows:
    - [AVLTreeTest.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/test/java/com/example/antiscam/AVLTreeTest.java)
    - [CodeTest.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/test/java/com/example/antiscam/CodeTest.java)
    - [LRUCacheTest.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/test/java/com/example/antiscam/LRUCacheTest.java)
    - [ListNodeTest.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/test/java/com/example/antiscam/ListNodeTest.java)
    - [NodeTest.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/test/java/com/example/antiscam/NodeTest.java)
    - [TokenizerTest.java](https://gitlab.cecs.anu.edu.au/u7558707/ga-23s2/-/blob/main/src/app/src/test/java/com/example/antiscam/TokenizerTest.java)
    
  - The test we wrote is to test most of the functions we are using and track their outputs. For example, in the `LRUCache` class, we wrote several tests to meet our application requirements:

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

  We didn't write any tests for the interfaces, UI classes or database connections, and we have done a test coverage report. The testing classes and functions are in the images:

  - ![test coverage1](photos/test_coverage1.png)

  - ![test_coverage2](photos/test_coverage2.png)

  - We can check the coverage for the classes and files we test:

    | class       | class coverage | method coverage | line coverage |
    | ----------- | -------------- | --------------- | ------------- |
    | AVLTree     | 100%           | 87%             | 93.3%         |
    | LRUCache    | 100%           | 55.90%          | 78.60%        |
    | ListNode    | 100%           | 87.50%          | 85.7%         |
    | Node        | 100%           | 81.80%          | 85%           |
    | TreeHashMap | 100%           | 77.80%          | 80.60%        |
    | Token       | 100%           | 63.60%          | 70.60%        |
    | Tokenizer   | 100%           | 100%            | 86.70%        |
    | Average     | 100%           | 79%             | 79%           |

  - The average coverage is above 70%. And the reason for the low coverage of `LRUCache` is that it contains many inside private class method, which we are not using and not able to test, but these codes are retained for the functionality of gson(which can serialize the complex class of `LRUCache` to Json format, which is easy to test and check).

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


| No. |     Date     |                       Type |
|:----|:------------:|---------------------------:|
| 1   |    |  |
| 2   |   |      |
| 3   |    |      |
| 4   |  |    |
| 5   |  |    |
| 6   |  |    |
| 7   |  |    |


<hr>

### Conflict Resolution Protocol
*[Write a well defined protocol your team can use to handle conflicts. That is, if your group has problems, what is the procedure for reaching consensus or solving a problem?
(If you choose to make this an external document, link to it here)]*

This shall include an agreed procedure for situations including (but not limited to):
- e.g., if a member fails to meet the initial plan and/or deadlines
- e.g., if your group has issues, how will your group reach consensus or solve the problem?
- e.g., if a member gets sick, what is the solution? Alternatively, what is your plan to mitigate the impact of unforeseen incidents for this 6-to-8-week project? 

# G32

## Team Meeting 5 - break week2  - 2023-09-14 14:30-16:00


**Lead/scribe:** Zhaoyun Xu/Yuke Huang

## Agenda Items
| Number   | Item                                       |
|:---------|:-------------------------------------------|
| 1 | UI Design Changes                          |
| 2 | Data Storage and Data Structure Discussion |
| 3 | Final Custom Features Selection            |
| 4 | Task Update                                |

## Meeting Minutes
Meeting Notes:

**1.UI Design Changes:**

- The meeting began with a discussion about changes to the UI design. It was decided to remove the "knowledge" and "knowledge details" pages from the app's design.

**2.Data Storage and Data Structure Discussion:**

- The team discussed how to store the app's data, and it was decided to use Firebase for data storage.
- To enhance data retrieval speed for frequently accessed cases, it was agreed to implement a local cache using a HashMap data structure. This local caching mechanism will store recently viewed cases for quicker access.
- Within the HashMap, it will employ AVL tree data structure for data retrieval.

**3.Final Custom Features Selection:**

- The team reviewed and finalized the list of custom features to be implemented in the app:

>- FB-Auth (Firebase Authentication) - Easy
>- FB-Persist (Firebase Data Persistence) - Medium
>- Data-Deletion - Medium
>- P2P-DM (Peer-to-Peer Direct Messaging) - Hard
>- P2P-Block (Peer-to-Peer Blocking) - Medium
>- Data-Profile - Easy

**4.Task Update:**

- A task update was discussed, and it was decided that Yuke Huang's task would be changed. She will now be responsible for creating 2500 data instances and storing them in JSON format.


## Action Items
| Task                                                | Assigned To  |   Due Date   |
|:----------------------------------------------------|:------------:|:------------:|
| creating 2500 data instances                        |  Yuke Huang  |  2023-09-17  |
| **(unchanged)** homepage + data display components. |  Yijing Jia  |  2023-09-17  |
| **(unchanged)** search logic + results page         | Junzhe Huang | 2023-09-17  |
| **(unchanged)** login page + knowledge items        |  Zhaoyun Xu  |  2023-09-17  |


## Next Metting
2023-09-17

## Scribe Rotation
| Name |
|:-----|
|Yuke Huang|


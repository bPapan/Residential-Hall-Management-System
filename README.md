# Residential-Hall-Management-System

It is a Java project for designing a smart residential hall management system for BUET students.

This software has 3 different modules for three different types of users: central administrator(s), administrator(s) of a hall and students. Using this software, the central administrator(s) can -

1.  Add a new residential hall
2.  Update the capacity/room count of an existing hall 
3.  Assign a hall to each student 
4.  Allow a student to change hall or resident status upon request 
5.  Set fees for the students 
6.  Assign administrators and staff members to the halls.

The administrator(s) of a hall can -

1.  Assign a room to each resident student of the respective hall 
2.  Change the room of a student upon request 
3.  Change payment status for a fee of a student
4.  Organize events in the hall and keep information about the sponsors of those events 
5.  Form teams for different inter-hall events.

A student can - 

1.  Request to change his room within a hall
2.  Request to change the assigned hall or his/her resident status
3.  Observe the pending and paid fees.

Moreover, in this software, each view or page has efficient search facility based on different parameters. 

Before running this software, create tables using Oracle SQL developer or Navicat according to the commands in _Blaze.sql_ file. Then run the main() method of Main class inside main package in 'gui' of 'src'.

## Prerequisites:

- JDK 1.8
- Oracle 12c or lower
- Update the username and password (and url, if user type is different) in Connection.java file in database inside src

## User Credentials:

- All the user credentials for logging in to the system can be found in 'login' table in the database
- When a new user entry (hall/student) is created, the password is set as a default value

## Admin features:

To log in to the system as admin, the exact username and password for the admin should be provided:
![Screenshot (204)](https://user-images.githubusercontent.com/37974385/112370372-7f012100-8d07-11eb-876b-ae956cdd5246.png)

Once logged in, the admin will be able to view the following page:
![Screenshot (763)](https://user-images.githubusercontent.com/37974385/188392972-cd36261b-206b-45d6-8b4a-1aaf26cc00f7.png)

The admin can search the halls by different criteria, as shown below:
![Screenshot (764)](https://user-images.githubusercontent.com/37974385/188393024-0483c952-4c3b-490f-a6dd-324d8f9fc9a8.png)

![Screenshot (765)](https://user-images.githubusercontent.com/37974385/188393101-6a8c9abb-c26c-4c57-a115-af9eea34a2e5.png)

![Screenshot (766)](https://user-images.githubusercontent.com/37974385/188393795-ce07c5f9-723e-4850-9829-2527b98a177b.png)

![Screenshot (767)](https://user-images.githubusercontent.com/37974385/188393846-e8fa07c9-a0f3-4ce2-be49-d3287bec9339.png)

![Screenshot (768)](https://user-images.githubusercontent.com/37974385/188393916-37c0fd4f-383b-445d-b954-67f03764b159.png)

If the admin clicks on the drawer icon on the top left corner of the page, he/she will be able to see the following options:
![Screenshot (769)](https://user-images.githubusercontent.com/37974385/188393957-67bfda42-07b8-40c7-b45e-fe05a1dbe580.png)

From there, he/she can add a new hall to the system.
![Screenshot (770)](https://user-images.githubusercontent.com/37974385/188393992-e1986999-98e3-4355-91fa-560083bd7313.png)

![Screenshot (771)](https://user-images.githubusercontent.com/37974385/188394042-cbe6090c-01ec-4b5b-b6dd-651bbbe4dda2.png)

![Screenshot (772)](https://user-images.githubusercontent.com/37974385/188394079-61293c36-0705-4f49-83af-4c580040097e.png)

The list of the students of the institution is represented in the system as below:
![Screenshot (773)](https://user-images.githubusercontent.com/37974385/188394118-fcc681e2-56f0-47c8-815a-a2df82c07854.png)

The admin can search the students by different criteria, as shown below:
![Screenshot (774)](https://user-images.githubusercontent.com/37974385/188394262-3ea600e6-eb52-49e7-b50a-14468d5572d6.png)

![Screenshot (776)](https://user-images.githubusercontent.com/37974385/188394374-c7fb446c-03a5-46da-9362-6db15143cd8b.png)

![Screenshot (777)](https://user-images.githubusercontent.com/37974385/188394428-c39b01d3-387c-4354-935b-0e1ce014a871.png)

![Screenshot (778)](https://user-images.githubusercontent.com/37974385/188394467-87c28f2a-4ec5-42fc-bf48-c6c8ed7d34d3.png)

The admin can add a new student to the system, by going to the Add Student option from the right navigation bar:
![Screenshot (841)](https://user-images.githubusercontent.com/37974385/188394714-77f5fd34-1687-4bbd-a671-03307a920274.png)

He/she can add necessary information of the student, as shown below:
![Screenshot (782)](https://user-images.githubusercontent.com/37974385/188394775-46d1ef53-8e21-46ac-bc4e-46d2a8fefe9f.png)

![Screenshot (783)](https://user-images.githubusercontent.com/37974385/188394829-8ac51bc8-94dd-4926-8b5f-9fd3eb512ac1.png)

![Screenshot (784)](https://user-images.githubusercontent.com/37974385/188394868-b3d653b1-5c07-43dd-892c-258a2305745d.png)

![Screenshot (785)](https://user-images.githubusercontent.com/37974385/188394914-13276cb6-d243-4c9d-86a5-623f814307de.png)

The admin can see the list of the teachers enrolled in the system, as shown below:
![Screenshot (787)](https://user-images.githubusercontent.com/37974385/188398058-5ee5937e-716f-484c-a034-1bdcd709438c.png)

To view the history of the responsibilities assigned to a teacher, the admin needs to select a row from the table view and click on the View History button.
![Screenshot (789)](https://user-images.githubusercontent.com/37974385/188398231-78f9ddb8-7df4-4667-9575-6600acc0f5d7.png)

![Screenshot (790)](https://user-images.githubusercontent.com/37974385/188398271-17591d2a-9b0f-44b7-a904-15e5aa51e9ea.png)

To assign a hall management related responsibility to a teacher, the admin needs to select a row from the table view and click on the Update button.
![Screenshot (791)](https://user-images.githubusercontent.com/37974385/188398389-b81de941-6b39-4eda-ae6c-605490087aad.png)

![Screenshot (792)](https://user-images.githubusercontent.com/37974385/188398421-2597c489-a4c5-43e8-831d-ec2951896352.png)

![Screenshot (793)](https://user-images.githubusercontent.com/37974385/188398801-f4570e83-c029-4614-aa04-3218cdca3039.png)

The admin can also add a new teacher to the system by going to the Add Teacher option from the right navigation pane.
![image](https://user-images.githubusercontent.com/37974385/188399334-2a14b7cb-c19e-47db-a9e4-2f8e904a7a5d.png)

![Screenshot (798)](https://user-images.githubusercontent.com/37974385/188399413-bac8a98a-133f-4953-a046-3571cc426ca2.png)

![Screenshot (799)](https://user-images.githubusercontent.com/37974385/188399510-a68fda85-0338-450a-8332-cb25cc95bb72.png)

The list of payments to be cleared by the students are represented in the Payment_Info page.
![Screenshot (794)](https://user-images.githubusercontent.com/37974385/188399587-43cba0be-e633-4c05-8118-dce06f03e900.png)

The admin can clear the entry for a payment of a student by selecting an entry and clicking on the Update button.
![Screenshot (795)](https://user-images.githubusercontent.com/37974385/188401257-ecc870e2-01e7-47fc-9273-a7cf9251aec6.png)

![Screenshot (796)](https://user-images.githubusercontent.com/37974385/188401325-324f6d1a-9db3-4055-a894-b9571b847fa5.png)

![Screenshot (797)](https://user-images.githubusercontent.com/37974385/188401425-b2f008c0-d6a5-4bf3-a0a7-e4d933d483e9.png)

The admin can apply a new fee on the students by going to the Set up Fees option from the right navigation pane:
![Screenshot (800)](https://user-images.githubusercontent.com/37974385/188401579-ba667962-25a7-43fe-b030-7cc8a42c0f6a.png)

![Screenshot (801)](https://user-images.githubusercontent.com/37974385/188401637-66d74f16-c7af-4486-8523-346b68f3df3a.png)

![Screenshot (802)](https://user-images.githubusercontent.com/37974385/188401674-e187d394-c1c8-4b41-84b1-53adbaf6939a.png)

## Student Features:

A Student can login to the system by entering his/her 7 digit ID and password:
![Screenshot (842)](https://user-images.githubusercontent.com/37974385/188494878-de7862c0-4091-4c41-a660-26c793da7aec.png)

![Screenshot (825)](https://user-images.githubusercontent.com/37974385/188494916-07ee9f6d-1366-41d7-bcec-9717a5717dbc.png)

He/she can apply for changing the room by selecting a room from the list and clicking on the Apply for Room button.
![Screenshot (843)](https://user-images.githubusercontent.com/37974385/188495034-25188bfa-fd17-40d2-823a-4ea73603e604.png)

![Screenshot (826)](https://user-images.githubusercontent.com/37974385/188495080-4c55c50a-3e60-495f-8495-702515b249af.png)

If the room is available, the request will be forwarded to the corresponding hall admin
![Screenshot (827)](https://user-images.githubusercontent.com/37974385/188495093-3c2f0114-8230-4cb3-b00c-86d15f0c609c.png)

If the student requests again for a room change while the previous request is still pending, the student will see an error message.
![Screenshot (845)](https://user-images.githubusercontent.com/37974385/188496211-14f3434b-6ffb-417a-a1ce-e534e32bac52.png)

![Screenshot (818)](https://user-images.githubusercontent.com/37974385/188495136-86c809cd-5d0f-4f17-b402-e69e6711e91a.png)

In the Payment_Info table, the student will find all the payments
![Screenshot (844)](https://user-images.githubusercontent.com/37974385/188495604-97772b2c-c117-444b-98dc-25b24ec2a292.png)

## Hall Admin features:

The admin of a hall can login to the system by providing the corresponding username and password.
![Screenshot (847)](https://user-images.githubusercontent.com/37974385/188869897-1ca18e84-785b-44fb-8a2d-d9082f783f3f.png)

The admin can see the notifications by clicking on the 'View' option on the top navigation pane and going to the 'Notifications' option.
![Screenshot (828)](https://user-images.githubusercontent.com/37974385/188869934-11551708-efdd-44b3-82b6-981ce14b0196.png)

There he/she can see the notification for room change (also other notifications).
![Screenshot (829)](https://user-images.githubusercontent.com/37974385/188869947-9975ac1d-e148-4266-9241-870132ed0eac.png)

He/she can decide on the request by selecting the row and clicking on the 'Mark Checked' button.
![Screenshot (830)](https://user-images.githubusercontent.com/37974385/188869972-2885eda1-1c17-4817-ae19-985be6dd5152.png)

![Screenshot (831)](https://user-images.githubusercontent.com/37974385/188869999-1c41ac5e-5792-40cf-b425-38889dd2b46c.png)

Also the admin can change the assigned room of a student by selecting the entry for the student and clicking on the 'Assign/Change Room' button.
![Screenshot (848)](https://user-images.githubusercontent.com/37974385/188870420-960819d4-1f0a-4234-a484-ba6c2ea85097.png)

![Screenshot (849)](https://user-images.githubusercontent.com/37974385/188870443-759a1c5a-5ef2-41ed-b5f1-27c1ee800d74.png)

If the hall admin enters a room no. that is invalid, he/she will get the following error:
![Screenshot (852)](https://user-images.githubusercontent.com/37974385/188870979-4611182c-210e-41a3-b9f7-a49143812fb3.png)

Otherwise, the entry for the student will be updated.
![Screenshot (850)](https://user-images.githubusercontent.com/37974385/188871025-b86b3904-1d02-4aec-9e32-b463556316e4.png)

![Screenshot (851)](https://user-images.githubusercontent.com/37974385/188871104-6d9b1a12-ab07-4e31-b401-1a2b4987c1d5.png)

The hall admin can also see the history of a student in the hall (which rooms he/she has resided in) by clicking on the 'View History' button.
![Screenshot (838)](https://user-images.githubusercontent.com/37974385/188875118-c6371264-4f08-4809-8fcc-bb6ab19cc60e.png)

In the 'View Staff' page, the admin can see the list of the staff in the hall. 
![Screenshot (853)](https://user-images.githubusercontent.com/37974385/188872388-c9407486-5c92-4bae-a1e3-e1d1a96f62c6.png)

He/she can update the duty of a staff by selecting his/her name from the staff table and clicking on the 'Update' button.
![Screenshot (854)](https://user-images.githubusercontent.com/37974385/188872425-622f7164-a6ff-4f69-8dfa-b76e99e664d3.png)

![Screenshot (855)](https://user-images.githubusercontent.com/37974385/188872458-6cea08eb-2ff7-4804-8534-c0f63afa7a08.png)

![Screenshot (856)](https://user-images.githubusercontent.com/37974385/188872482-24bd62a4-0769-4f7a-86b8-e65646b11bef.png)

![Screenshot (857)](https://user-images.githubusercontent.com/37974385/188872529-3e022fc2-abd1-4db5-8b8d-2fd1ad77e9d9.png)

Also, the hall admin can add a new staff member by selecting the 'Add Staff' option from the right navigation pane.
![Screenshot (859)](https://user-images.githubusercontent.com/37974385/188872636-2e1fc544-f1c4-4c53-9eb5-209d3b87021a.png)

![Screenshot (860)](https://user-images.githubusercontent.com/37974385/188872656-9308db53-60fb-48e8-8fa8-2826a1907d49.png)

![Screenshot (861)](https://user-images.githubusercontent.com/37974385/188872684-e0f4d4ca-1dd5-4bb9-9d51-41f2e27b4198.png)

The hall admin can see the list of the rooms in the 'View Rooms' page.
![Screenshot (858)](https://user-images.githubusercontent.com/37974385/188872549-2cb8b5e8-c9c3-4960-9744-89ae2b617196.png)

Also he/she can add a new room to the database by selecting the add room option from the right navigation pane. 

![Screenshot (862)](https://user-images.githubusercontent.com/37974385/188874989-a4ff79ac-639e-484e-9d81-d9935cfcb871.png)

![Screenshot (863)](https://user-images.githubusercontent.com/37974385/188875007-208ba269-3313-45de-b2f5-490d038f80cf.png)



## A few notes from the Developers:

- While developing this project, we did not have any knowledge of version control, so there might be some bugs as our code bases could not be merged properly
- We used basic JavaFX and Oracle SQL while developing, so we could not introduce some advanced features to our system
- There are some other features which we have not listed in the readme file. 

Any feedback on our system will be appreciated.


















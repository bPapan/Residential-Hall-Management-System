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





































INSERT INTO public.tasks(creatorlogin, creatorgroupid, taskname, taskdescription, taskpriority, taskstatus, taskexpirydate)
	VALUES
	('irina', 0, 'Task 1', 'Task 1 description', 2, '0', '2024-03-24 16:48:05.0'),
	('vlad', 0, 'Task 1', 'Task 1 description', 0, '0', '2024-03-24 16:48:05.0'),
	('nikita', 0, 'Task 1', 'Task 1 description', 1, '1', '2024-03-24 16:48:05.0')

---------------------------

INSERT INTO public.usergroups(
	groupid, groupname, grouppassword)
	VALUES (0, 'Group', '1234');

----------------------------------

INSERT INTO public.userandgroup(username, groupid)
	VALUES
	('irina', 0),
	('vlad', 0),
	('nikita', 0),
	('alex', 0);

--------------------------

INSERT INTO public.users(username, userlogin, userpassword)
	VALUES
	('Irina', 'irina', '1234'),
	('Valdislav', 'vlad', '1234'),
	('Nikita', 'nikita', '1234'),
	('Aleksandr', 'alex', '1234');
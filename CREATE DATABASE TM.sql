CREATE TABLE Users -- Таблица пользователей
(
	UserName CHARACTER VARYING(30) NOT NULL, -- Имя пользователя - не уникальное
	UserLogin CHARACTER VARYING(30) UNIQUE NOT NULL PRIMARY KEY, -- Логин пользователя - уникальный (ПК)
	UserPassword CHARACTER VARYING(30) NOT NULL -- Пароль пользователя
);

CREATE TABLE UserGroups -- Таблица групп
(
	GroupID SERIAL PRIMARY KEY, -- ID группы - уникальный (ПК)
	GroupName CHARACTER VARYING(30) NOT NULL, -- Имя группы - не уникальное
	GroupPassword CHARACTER VARYING(30) -- Пароль для входа в группу, группа может быть без пароля, в этом случае это поле равно NULL
);

/*
Таблица принадлжености пользователей к группам. Каждая строка содержит значения:
UserName - логин пользователя (ВК - ссылается на таблицу пользователей на UserLogin);
GroupID - ID группы, к которой пользователь принадлежит (ВК - ссылается на таблицу групп на GroupID);
*/
CREATE TABLE UserAndGroup
(
	UserName CHARACTER VARYING(30) REFERENCES users (UserLogin),
	GroupID INTEGER REFERENCES usergroups (GroupID),
	PRIMARY KEY (UserName, GroupID) -- Составной ПК - состоит из логина пользователя и ID группы
);

CREATE TABLE Tasks -- Таблица задач
(
	UserTaskID SERIAL PRIMARY KEY, -- ID задачи
	CreatorLogin CHARACTER VARYING(30) NOT NULL, -- Логин пользователя, создавшего задачу
	CreatorGroupID INTEGER NOT NULL, -- ID группы, в которой состоит пользователь-создатель задачи
	TaskName CHARACTER VARYING(30) NOT NULL,
	TaskDescription CHARACTER VARYING(30) NOT NULL,
	TaskPriority INTEGER NOT NULL DEFAULT 0 CHECK (TaskPriority IN (0, 1, 2)),
	TaskStatus BOOLEAN DEFAULT '0',
	TaskExpiryDate TIMESTAMP(6) WITH TIME ZONE DEFAULT (NOW() + interval '1 day'),
	/*
	Составной ВК - ссылается на ПК таблицы UserAndGroup, позволяя иметь доступ как к создателю задачи, так и к его группе.
	*/
	FOREIGN KEY (CreatorLogin, CreatorGroupID) REFERENCES UserAndGroup(UserName, GroupID) ON DELETE CASCADE
);

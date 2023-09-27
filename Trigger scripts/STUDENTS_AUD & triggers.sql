CREATE TABLE STUDENTS_AUD
(
    EVENT_ID      int auto_increment
        primary key,
    EVENT_DATE    datetime     not null,
    EVENT_TYPE    varchar(255) null,
    STUDENT_ID    INT,
    OLD_FIRSTNAME varchar(255) null,
    NEW_FIRSTNAME varchar(255) null,
    OLD_LASTNAME  varchar(255) null,
    NEW_LASTNAME  varchar(255) null,
    OLD_EMAIL     varchar(255) null,
    NEW_EMAIL     varchar(255) null
);

CREATE DEFINER = kasia@LOCALHOST TRIGGER STUDENTS_DELETE
    AFTER DELETE
    ON students
    FOR EACH ROW
BEGIN
    INSERT INTO STUDENTS_AUD (EVENT_DATE, EVENT_TYPE, STUDENT_ID, old_firstname, old_lastname, old_email)
        VALUE (curtime(), 'DELETE', old.id, OLD.firstname, OLD.lastname, old.email);
END;

CREATE DEFINER = kasia@LOCALHOST TRIGGER STUDENTS_CREATE
    AFTER INSERT
    ON students
    FOR EACH ROW
BEGIN
    INSERT INTO STUDENTS_AUD (EVENT_DATE, EVENT_TYPE, STUDENT_ID, new_firstname, new_lastname, new_email)
        VALUE (CURTIME(), 'INSERT', NEW.ID, NEW.firstname, NEW.lastname, NEW.email);
END;

CREATE DEFINER = kasia@LOCALHOST TRIGGER STUDENTS_UPDATE
    AFTER UPDATE
    ON students
    FOR EACH ROW
BEGIN
    INSERT INTO STUDENTS_AUD (EVENT_DATE, EVENT_TYPE, STUDENT_ID, new_firstname, new_lastname, new_email, old_firstname,
                              old_lastname, old_email)
        VALUE (CURTIME(), 'UPDATE', OLD.ID, NEW.firstname, NEW.lastname, NEW.email, OLD.firstname, OLD.lastname,
               old.email);
END;
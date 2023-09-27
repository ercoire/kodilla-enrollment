CREATE TABLE COURSE_NOTIFICATION_AUD
(
    EVENT_ID         int auto_increment
        primary key,
    EVENT_DATE       datetime     not null,
    EVENT_TYPE       varchar(255) null,
    NOTIFICATION_ID  INT,
    OLD_COURSE       varchar(255) null,
    NEW_COURSE       varchar(255) null,
    OLD_EMAIL        varchar(255) null,
    NEW_EMAIL        varchar(255) null,
    OLD_STUDENT_NAME varchar(255) null,
    NEW_STUDENT_NAME varchar(255) null
);

CREATE DEFINER = kasia@LOCALHOST TRIGGER NOTIFICATIONS_CREATE
    AFTER INSERT
    ON course_enrollment_notification
    FOR EACH ROW
BEGIN
    INSERT INTO COURSE_NOTIFICATION_AUD (EVENT_DATE, EVENT_TYPE, NOTIFICATION_ID, NEW_COURSE, new_EMAIL,
                                         new_STUDENT_NAME)
        VALUE (CURTIME(), 'INSERT', NEW.ID, NEW.course, NEW.email, NEW.student_name);
END;

CREATE DEFINER = kasia@LOCALHOST TRIGGER NOTIFICATIONS_DELETE
    AFTER DELETE
    ON course_enrollment_notification
    FOR EACH ROW
BEGIN
    INSERT INTO COURSE_NOTIFICATION_AUD (EVENT_DATE, EVENT_TYPE, NOTIFICATION_ID, OLD_COURSE, OLD_EMAIL,
                                         OLD_STUDENT_NAME)
        VALUE(CURTIME(), 'INSERT', OLD.ID, OLD.course, OLD.email, OLD.student_name);
END;
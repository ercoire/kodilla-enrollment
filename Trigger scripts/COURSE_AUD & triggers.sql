CREATE TABLE COURSES_AUD
(
    EVENT_ID            int auto_increment
        primary key,
    EVENT_DATE          datetime     not null,
    EVENT_TYPE          varchar(255) null,
    COURSE_ID           int,
    OLD_TITLE           VARCHAR(255),
    NEW_TITLE           VARCHAR(255),
    OLD_DAY             varchar(255) null,
    NEW_DAY             varchar(255) null,
    OLD_DESCRIPTION     varchar(255) null,
    NEW_DESCRIPTION     varchar(255) null,
    OLD_DURATION        int,
    NEW_DURATION        int,
    OLD_END_DATE        date,
    NEW_END_DATE        date,
    OLD_STARTING_DATE   date,
    NEW_STARTING_DATE   date,
    OLD_PRICE_PER_MONTH INT,
    NEW_PRICE_PER_MONTH INT,
    OLD_TIME            time(6),
    NEW_TIME            TIME(6)
);

CREATE DEFINER = admin@LOCALHOST TRIGGER COURSES_CREATE
    AFTER INSERT
    ON courses
    FOR EACH ROW
BEGIN
    INSERT INTO COURSES_AUD (EVENT_DATE, EVENT_TYPE, COURSE_ID, new_day, new_description, new_duration, new_end_date,
                             NEW_PRICE_PER_MONTH, new_starting_date, new_time, new_title)
        VALUE (CURTIME(), 'INSERT', NEW.ID, NEW.day, NEW.description, NEW.duration, NEW.end_date, NEW.price_per_month,
               NEW.starting_date, NEW.time, NEW_TITLE);
END;

CREATE DEFINER = admin@LOCALHOST TRIGGER COURSES_DELETE
    AFTER DELETE
    ON courses
    FOR EACH ROW
BEGIN
    INSERT INTO COURSES_AUD (EVENT_DATE, EVENT_TYPE, COURSE_ID, old_day, old_description, old_duration, old_end_date,
                             old_price_per_month, old_starting_date, old_time, old_title)
        VALUE (CURTIME(), 'DELETE', OLD.ID, OLD.day, OLD.description, OLD.duration, OLD.end_date, OLD.price_per_month,
               OLD.starting_date, OLD.time, OLD.title);
END;

CREATE DEFINER = admin@LOCALHOST TRIGGER COURSES_UPDATE
    AFTER UPDATE
    ON courses
    FOR EACH ROW
BEGIN
    INSERT INTO COURSES_AUD (EVENT_DATE, EVENT_TYPE, COURSE_ID, new_day, new_description, new_duration, new_end_date,
                             NEW_PRICE_PER_MONTH, new_starting_date, new_time, new_title, old_day, old_description,
                             old_duration, old_end_date, old_price_per_month, old_starting_date, old_time, old_title)
        VALUE (CURTIME(), 'UPDATE', OLD.ID, NEW.day, NEW.description, NEW.duration, NEW.end_date, NEW.price_per_month,
               NEW.starting_date, NEW.time, NEW_TITLE, OLD.day, OLD.description, OLD.duration, OLD.end_date,
               OLD.price_per_month, OLD.starting_date, OLD.time, OLD.title);
END;


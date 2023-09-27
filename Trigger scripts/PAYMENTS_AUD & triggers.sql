CREATE TABLE PAYMENTS_AUD
(
    EVENT_ID         int auto_increment
        primary key,
    EVENT_DATE       datetime     not null,
    EVENT_TYPE       varchar(255) null,
    ID               int,
    OLD_AMOUNT       varchar(255) null,
    NEW_AMOUNT       varchar(255) null,
    OLD_COURSE_ID    varchar(255) null,
    NEW_COURSE_ID    varchar(255) null,
    OLD_PAYMENT_DATE date         null,
    NEW_PAYMENT_DATE date         null,
    OLD_STUDENT_ID   varchar(255) null,
    NEW_STUDENT_ID   varchar(255) null

);

CREATE DEFINER = kasia@LOCALHOST TRIGGER PAYMENTS_CREATE
    AFTER INSERT
    ON payments
    FOR EACH ROW
BEGIN
    INSERT INTO PAYMENTS_AUD (EVENT_DATE, EVENT_TYPE, ID, NEW_AMOUNT, NEW_COURSE_ID, NEW_PAYMENT_DATE, NEW_STUDENT_ID)
        VALUE (CURRENT_TIME, 'INSERT', NEW.ID, NEW.amount, NEW.course_id, NEW.payment_date, NEW.student_id);
END;

CREATE DEFINER = kasia@LOCALHOST TRIGGER PAYMENTS_DELETE
    AFTER DELETE
    ON payments
    FOR EACH ROW
BEGIN
    INSERT INTO PAYMENTS_AUD (EVENT_DATE, EVENT_TYPE, ID, OLD_AMOUNT, OLD_COURSE_ID, OLD_PAYMENT_DATE, OLD_STUDENT_ID)
        VALUE (CURTIME(), 'DELETE', OLD.ID, OLD.amount, OLD.course_id, OLD.payment_date, OLD.student_id);
END;
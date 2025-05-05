-- beginning of the script
BEGIN;

-- insert some lebanese institutions into the table
INSERT INTO INSTITUTION
    (
     code,
     name,
     status
    )
VALUES (12345, 'American University of Beirut', 1),
       (23456, 'Lebanese American University', 1),
       (34567, 'Saint Joseph University', 1),
       (45678, 'University of Balamand', 0),
       (56789, 'Lebanese International University', 1);

-- insert a test username into the table
INSERT INTO APP_USERS
    (
     username,
     password)
VALUES ('amalm3anna', 'P@ssw0rd123');


-- end of the script
END;
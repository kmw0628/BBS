/* Create bbs user */
CREATE ROLE bbs WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  NOREPLICATION
  ENCRYPTED PASSWORD 'SCRAM-SHA-256$4096:/4NsM3CYb/yrPTBsDEamkQ==$xcX3yK/Y+VDMmkWSqTWh0BfrT5qjLYxnlbSTvuzVvhs=:iGXpEV/5UyOvH66yt8b/Z584H1JEi2d1PxI9I5jjC7I=';

/* Create Tablespace */
CREATE TABLESPACE bbs
  OWNER bbs
  LOCATION '/app/DB/psql-12.5/data/bbs';

ALTER TABLESPACE bbs
  OWNER TO bbs;
  
/* Create Database */
CREATE DATABASE bbs
    WITH
    OWNER = bbs

    TABLESPACE = bbs
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

/* Create Schema */
CREATE SCHEMA IF NOT EXISTS bbs
    AUTHORIZATION bbs;
    
/* create bbs.bbs */
    drop table bbs.bbs;
CREATE TABLE IF NOT EXISTS bbs.bbs
(
    "bbsid" int NOT NULL,
    "bbstitle" character varying COLLATE pg_catalog."default" NOT NULL,
    "userid" character varying COLLATE pg_catalog."default" NOT NULL,
    "bbsdate" timestamp with time zone NOT NULL,
    "bbscontent" character varying COLLATE pg_catalog."default" NOT NULL,
    "bbsavailable" integer NOT NULL DEFAULT 1,
    CONSTRAINT bbs_pkey PRIMARY KEY ("bbsid")
        USING INDEX TABLESPACE bbs
)

TABLESPACE bbs;

ALTER TABLE IF EXISTS bbs.bbs
    OWNER to bbs;

    
    CREATE TABLE IF NOT EXISTS bbs.users
(
    "UserID" character varying COLLATE pg_catalog."default" NOT NULL,
    "UserPASSWORD" character varying COLLATE pg_catalog."default" NOT NULL,
    "UserNAME" character varying COLLATE pg_catalog."default" NOT NULL,
    "UserGENDER" character varying COLLATE pg_catalog."default" NOT NULL,
    "UserEMAIL" character varying COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY ("UserID")
        USING INDEX TABLESPACE bbs
)

TABLESPACE bbs;

ALTER TABLE IF EXISTS bbs.users
    OWNER to bbs;
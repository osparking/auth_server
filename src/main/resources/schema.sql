CREATE CACHED TABLE "USER"(

    "ID" INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,

    "USERNAME" CHARACTER VARYING(40) NOT NULL,

    "PASSWORD" CHARACTER VARYING(250) DEFAULT NULL,

    "ENABLED" BOOLEAN DEFAULT FALSE,

    "ROLE" CHARACTER VARYING(45) DEFAULT 'ROLE_USER' NOT NULL

);
ALTER TABLE "USER" ADD CONSTRAINT "CONSTRAINT_2" PRIMARY KEY("ID");
CREATE UNIQUE INDEX "username_unique" ON "USER"("USERNAME" NULLS FIRST);
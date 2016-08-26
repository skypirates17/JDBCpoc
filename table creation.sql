-- Table: student_info

-- DROP TABLE student_info;

CREATE TABLE student_info
(
  student_id serial NOT NULL,
  student_name character varying(100),
  student_age character varying(5),
  student_address character varying(255),
  CONSTRAINT student_info_pkey PRIMARY KEY (student_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE student_info
  OWNER TO postgres;

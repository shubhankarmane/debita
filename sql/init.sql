create schema if not exists debita;
create user 'debita_user' identified by 'Pa$$w0rd';
grant all privileges on debita.* to 'debita_user';
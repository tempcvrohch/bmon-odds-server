#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username postgres --dbname postgres <<-EOSQL
        CREATE USER "${POSTGRES_USER}" WITH PASSWORD '${POSTGRES_PASS}';
        CREATE DATABASE "${POSTGRES_DB}";
        GRANT ALL PRIVILEGES ON DATABASE "${POSTGRES_DB}" TO "${POSTGRES_USER}";
				SET search_path TO ${POSTGRES_SCHEMA};
EOSQL

echo "'${POSTGRES_DB}' created successfully."
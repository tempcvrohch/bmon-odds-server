#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username postgres --dbname postgres <<-EOSQL
        CREATE USER "${BMON_POSTGRES_USER}" WITH PASSWORD '${BMON_POSTGRES_PASS}';
        CREATE DATABASE "${BMON_POSTGRES_DB}";
        GRANT ALL PRIVILEGES ON DATABASE "${BMON_POSTGRES_DB}" TO "${BMON_POSTGRES_USER}";
				SET search_path TO ${BMON_POSTGRES_SCHEMA};
EOSQL

echo "'${BMON_POSTGRES_DB}' created successfully."
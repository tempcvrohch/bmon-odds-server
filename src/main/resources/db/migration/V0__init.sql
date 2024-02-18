CREATE TYPE "bmon_schema"."bet_status" AS ENUM
('WIN', 'LOSS', 'PENDING', 'VOID');

CREATE TABLE "bmon_schema"."default_columns"
(
	created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
	updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE "bmon_schema"."markets"
(
	LIKE "bmon_schema"."default_columns",
	id BIGSERIAL PRIMARY KEY,	
	name TEXT
);

CREATE TABLE "bmon_schema"."sports"
(
	LIKE "bmon_schema"."default_columns",
	id BIGSERIAL PRIMARY KEY,	
	name TEXT
);

CREATE TABLE "bmon_schema"."players"
(
	LIKE "bmon_schema"."default_columns",
	id BIGSERIAL PRIMARY KEY,	
	firstname TEXT NOT NULL,
	lastname TEXT NOT NULL,
	slug TEXT NOT NULL,
	country_code TEXT NOT NULL
);

CREATE TABLE "bmon_schema"."leagues"
(
	LIKE "bmon_schema"."default_columns",
	id BIGSERIAL PRIMARY KEY,	
	name TEXT
);

CREATE TABLE "bmon_schema"."matches"
(
	LIKE "bmon_schema"."default_columns",
	id BIGSERIAL PRIMARY KEY,
	leagues_id INTEGER REFERENCES "bmon_schema"."leagues" (id) ON DELETE SET NULL,
	sports_id INTEGER REFERENCES  "bmon_schema"."sports" (id) ON DELETE SET NULL,
	name TEXT
);

CREATE TABLE "bmon_schema"."match_states"
(
	LIKE "bmon_schema"."default_columns",
	id BIGSERIAL PRIMARY KEY,
	matches_id INTEGER REFERENCES "bmon_schema"."matches" (id) ON DELETE CASCADE NOT NULL,
	point_score TEXT NOT NULL,
	serving_index numeric (1) NOT NULL,
	set_score text NOT NULL
);

CREATE TABLE "bmon_schema"."market_states"
(
	LIKE "bmon_schema"."default_columns",
	id BIGSERIAL PRIMARY KEY,
	markets_id INTEGER REFERENCES "bmon_schema"."markets" (id) ON DELETE SET NULL,
	players_id INTEGER REFERENCES "bmon_schema"."players" (id) ON DELETE SET NULL,
	match_states_id INTEGER REFERENCES "bmon_schema"."match_states" (id) ON DELETE CASCADE NOT NULL,
	odd NUMERIC(5, 2) DEFAULT 1,
	suspended BOOLEAN NOT NULL DEFAULT FALSE,
	stake_limit NUMERIC (10,2) NOT NULL DEFAULT 100
);

CREATE TABLE "bmon_schema"."users"
(
	LIKE "bmon_schema"."default_columns",
	id BIGSERIAL PRIMARY KEY,
	balance NUMERIC(10, 2) NOT NULL DEFAULT 0,
	username text NOT NULL UNIQUE,
	password text NOT NULL
);

CREATE TABLE "bmon_schema"."bets"
(
	LIKE "bmon_schema"."default_columns",
	id BIGSERIAL PRIMARY KEY,
	stake NUMERIC(10,2) NOT NULL,
	status "bmon_schema"."bet_status" NOT NULL DEFAULT 'PENDING',
	to_return NUMERIC(10,2) NOT NULL,
	market_states_id INTEGER REFERENCES "bmon_schema"."market_states" (id) ON DELETE SET NULL,
	users_id INTEGER REFERENCES "bmon_schema"."users" (id) ON DELETE CASCADE NOT NULL
);


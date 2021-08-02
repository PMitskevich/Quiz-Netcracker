create table answers (
    id uuid not null,
    answer_is_right boolean,
    title varchar(255),
    question_id uuid,
    primary key (id)
);
create table categories (
    id uuid not null,
    description varchar(255),
    name varchar(255),
    primary key (id)
);
create table game_room (
    id uuid not null,
    game_id uuid,
    primary key (id)
);
create table games (
    id uuid not null,
    description varchar(2048),
    photo varchar(255),
    name varchar(255),
    access varchar(255),
    views int8,
    rating int8,
    rating_count int8,
    player_id uuid,
    game_category_id uuid,
    primary key (id)
);
create table game_categories (
    id uuid not null,
    description varchar(255),
    name varchar(255),
    primary key (id)
);
create table levels (
    id uuid not null,
    description varchar(255),
    title varchar(255),
    primary key (id)
);
create table players (
    id uuid not null,
    email varchar(255),
    login varchar(255),
    photo varchar(255),
    user_id uuid,
    game_room_id uuid,
    primary key (id)
);
create table questions (
    id uuid not null,
    description varchar(2048),
    title varchar(255),
    category_id uuid,
    game_id uuid,
    level_id uuid,
    photo varchar(255),
    primary key (id)
);
create table statictics (
    id uuid not null,
    answer_id uuid,
    player_id uuid,
    question_id uuid,
    primary key (id)
);
create table game_access (
     id uuid not null,
     game_id uuid,
     player_id uuid,
     access boolean,
     activation_code varchar(255),
     primary key (id)
);
create table users (
    id uuid not null,
    activation_code varchar(255),
    active boolean,
    login varchar(255),
    email varchar(255),
    password varchar(255),
    role varchar(255),
    primary key (id)
);
alter table answers
    add constraint answers_question_fk foreign key (question_id) references questions;
alter table game_room
    add constraint game_room_game_fk foreign key (game_id) references games;
alter table games
    add constraint games_player_fk foreign key (player_id) references players;
alter table games
    add constraint games_game_category_fk foreign key (game_category_id) references game_categories;
alter table players
    add constraint players_user_fk foreign key (user_id) references users;
alter table players
    add constraint players_game_room_fk foreign key (game_room_id) references game_room;
alter table questions
    add constraint questions_category_fk foreign key (category_id) references categories;
alter table questions
    add constraint questions_game_fk foreign key (game_id) references games;
alter table questions
    add constraint questions_level_fk foreign key (level_id) references levels;
alter table statictics
    add constraint statictics_answer_fk foreign key (answer_id) references answers;
alter table statictics
    add constraint statictics_question_fk foreign key (question_id) references questions;
alter table statictics
    add constraint statictics_player_fk foreign key (player_id) references players;
alter table game_access
    add constraint game_access_game_fk foreign key (game_id) references games;
alter table game_access
    add constraint game_access_player_fk foreign key (player_id) references players;
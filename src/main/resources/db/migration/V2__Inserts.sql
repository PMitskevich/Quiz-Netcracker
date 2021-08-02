-- Заполняем уровни сложности
INSERT INTO levels (id, description, title) VALUES ('fdecef96-6ee3-4d25-803b-26042cb532a7', 'Простой', 'Простой');
INSERT INTO levels (id, description, title) VALUES ('0458cc5b-8871-4807-98e8-68451f3b1f0b', 'Средний', 'Средний');
INSERT INTO levels (id, description, title) VALUES ('d401d941-6883-45c5-9038-4bbd9f63bb3c', 'Сложный', 'Сложный');

-- Заполняем категории для игр
INSERT INTO game_categories(id, description, name) VALUES ('5ad1b56f-b92e-4a18-883b-495167fe5051', 'Программирование', 'Программирование');
INSERT INTO game_categories(id, description, name) VALUES ('0f4c92a1-61db-438a-86ca-549c820044b4', 'Общая эрудиция', 'Общая эрудиция');
INSERT INTO game_categories(id, description, name) VALUES ('2ca00af8-dc19-4b69-9c12-d8f046a946a2', 'Разное', 'Разное');


-- Инициализируем админа
INSERT INTO users (id, activation_code, active, login, email, password, role)
VALUES ('365924ba-fe34-412d-99b6-dec9554815d9', NULL, TRUE, 'Administrator', 'admin@mail.ru', '$2a$10$xlS02xL1DHt3gwWCgjTI2.UVNU58EjZkgVYnyS.Y.LFY/tRyMC/Ae', 'ADMIN');

-- Заполняем категории
INSERT INTO categories(id, description, name) VALUES ('f7b926e4-3a0c-43d5-913d-28e27a612a76', 'Программирование', 'Программирование');
INSERT INTO categories(id, description, name) VALUES ('5fb660e7-5425-46da-9b33-6a8fc4c716a0', 'Литература', 'Литература');
INSERT INTO categories(id, description, name) VALUES ('ebf7b015-6061-43d1-92e7-3fac5c5802b3', 'Общее', 'Общее');
INSERT INTO categories(id, description, name) VALUES ('634572e8-6926-463d-8712-c699b4821cdc', 'Разное', 'Разное');
INSERT INTO categories(id, description, name) VALUES ('ed2b4a73-a6ec-476a-b82e-16eac9249f5e', 'Мультфильмы', 'Мультфильмы');
INSERT INTO categories(id, description, name) VALUES ('ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', 'Спорт', 'Спорт');
INSERT INTO categories(id, description, name) VALUES ('90ed209c-7714-4c2b-b539-2c87799264e7', 'История', 'История');
INSERT INTO categories(id, description, name) VALUES ('c2a658e6-ea92-4df8-bc06-620089ffb187', 'География', 'География');
INSERT INTO categories(id, description, name) VALUES ('dad69283-f7c4-47aa-a754-69a2525b43cd', 'Биология', 'Биология');
INSERT INTO categories(id, description, name) VALUES ('feacb6b6-9534-4f1b-9131-2a20a6ec2455', 'IT-сфера', 'IT-сфера');

-- Разрабатываем игру про футбол (запустить может только админ)
INSERT INTO games(id, description, photo, name, access, views, rating, rating_count, player_id, game_category_id)
VALUES ('716fb029-148d-4515-8aca-7cb8a604ee24', 'Общие факты о футболе', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1611788079133-myach-futbolnyj-myach-adidas-finale-18-top-training-ball-cw4134.jpg', 'Викторина про футбол', 'PUBLIC', '185', NULL, NULL, NULL, '2ca00af8-dc19-4b69-9c12-d8f046a946a2');

INSERT INTO players(id, email, login, photo, user_id, game_room_id)
VALUES ('fe51e1e3-60e5-4c4b-800f-1ede7d7eb6dd', 'admin@mail.ru', 'Administrator', NULL, '365924ba-fe34-412d-99b6-dec9554815d9', NULL);

UPDATE games SET player_id = 'fe51e1e3-60e5-4c4b-800f-1ede7d7eb6dd' WHERE id = '716fb029-148d-4515-8aca-7cb8a604ee24';

INSERT INTO game_access(id, game_id, player_id, access, activation_code)
VALUES ('cba12d14-41ef-4a1c-9478-1bdf93fd1c0f', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fe51e1e3-60e5-4c4b-800f-1ede7d7eb6dd', TRUE, NULL);

-- 1 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('9885d35d-4b8f-4b19-8e10-10e15691c0d5', 'В какой стране и в каком городе проходил Финал Клубного чемпионата мира 2019?', 'В какой стране, и в каком городе проходил Финал Клубного чемпионата мира 2019?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES ('9885d35d-4b8f-4b19-8e10-10e15691c0d5', TRUE, 'Доха (Катар)', '9885d35d-4b8f-4b19-8e10-10e15691c0d5');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES ('f69d909d-d909-4212-ae46-dcf1f9148939', FALSE , 'Абу-Даби (ОАЭ)', '9885d35d-4b8f-4b19-8e10-10e15691c0d5');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES ('cc166654-4689-4947-9879-abc7d92122b9', FALSE, 'Лондон (Англия)', '9885d35d-4b8f-4b19-8e10-10e15691c0d5');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES ('ff4a4eed-b69f-4451-98eb-0cd49be383ed', FALSE, 'Иокогама (Япония)', '9885d35d-4b8f-4b19-8e10-10e15691c0d5');

-- 2 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('f481f984-ee1c-45f7-83e9-4aa28c2f5e9a', 'Кто стал победителем Клубного чемпионата мира 2019?', 'Кто стал победителем Клубного чемпионата мира 2019?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('1defa588-d865-41dd-9c63-a4bef4547727', FALSE, 'Фламенго (Бразилия)', 'f481f984-ee1c-45f7-83e9-4aa28c2f5e9a');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('15eef00b-8f52-4280-90d8-4559afa15dd5', FALSE, 'Манчестер Юнайтед (Англия)', 'f481f984-ee1c-45f7-83e9-4aa28c2f5e9a');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('ca7614c1-99a1-42c5-9315-300ef740664a', FALSE, 'Бавария (Германия)', 'f481f984-ee1c-45f7-83e9-4aa28c2f5e9a');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('65d6c565-246e-4a9b-8917-83a00a8a2a4e', TRUE, 'Ливерпуль (Англия)', 'f481f984-ee1c-45f7-83e9-4aa28c2f5e9a');

-- 3 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('b2022474-3a82-4949-a28e-49b72eeaac11', 'Кто признан лучшим вратарём вы 2019 году по версии ФИФА?', 'Кто признан лучшим вратарём вы 2019 году по версии ФИФА?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('3729ad4a-2aba-43c7-8cf6-20079191294e', FALSE, 'Ян Облак из «Атлетико»', 'b2022474-3a82-4949-a28e-49b72eeaac11');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('e7e2c7e6-f5f0-4847-a997-dde2a5934a88', TRUE, 'Алиссон из «Ливерпуля»', 'b2022474-3a82-4949-a28e-49b72eeaac11');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('22434073-6785-4a62-93f0-f9a07f8fca69', FALSE, 'Тибо Куртуа из «Реала»', 'b2022474-3a82-4949-a28e-49b72eeaac11');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('8035bdff-a8f0-4e54-bb0b-3e0e6382d95c', FALSE, 'Давид де Хеа из «Манчестер Юнайтед»', 'b2022474-3a82-4949-a28e-49b72eeaac11');

-- 4 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('06ad3516-43bf-4cdc-a0f7-9a6139e84e56', 'Кто является лучшим бомбардиром за всю историю сборной Португалии?', 'Кто является лучшим бомбардиром за всю историю сборной Португалии?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('7f61672d-1210-40c4-891e-2c812f7aa2d1', TRUE, 'Криштиану Роналду', '06ad3516-43bf-4cdc-a0f7-9a6139e84e56');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('01400df8-85cb-414c-9a2a-4b3d0088468a', FALSE, 'Маттейс де Лигт', '06ad3516-43bf-4cdc-a0f7-9a6139e84e56');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('be984e5c-2d41-45be-913b-5d43fb74067e', FALSE, 'Вирджил ван Дейк', '06ad3516-43bf-4cdc-a0f7-9a6139e84e56');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('eee9e447-1585-4066-86fb-0a3720a62c76', FALSE, 'Лионель Месси', '06ad3516-43bf-4cdc-a0f7-9a6139e84e56');

-- 5 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('ddab8485-db15-4197-8b2f-2b587cb99ce2', 'В какой стране появился и начал развиваться футбол?', 'В какой стране появился и начал развиваться футбол?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('ef818a72-6680-4d8f-a760-623b78617a19', FALSE, 'Бразилия', 'ddab8485-db15-4197-8b2f-2b587cb99ce2');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('77ce25e3-9013-4bec-aa80-d226e9de9b9f', FALSE, 'Испания', 'ddab8485-db15-4197-8b2f-2b587cb99ce2');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('065645cf-e876-474a-b084-ee3596dd1de8', TRUE, 'Англия', 'ddab8485-db15-4197-8b2f-2b587cb99ce2');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('93fc1932-0449-45a6-a1ba-e7366210de8b', FALSE, 'Португалия', 'ddab8485-db15-4197-8b2f-2b587cb99ce2');

-- 6 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('d7ffbf1c-f858-469a-863a-68973810c158', 'В какой стране впервые проходил Кубок мира ФИФА?', 'В какой стране впервые проходил Кубок мира ФИФА?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('a55e1d80-f799-4568-9a2d-6b8be5487b14', FALSE, 'Франция', 'd7ffbf1c-f858-469a-863a-68973810c158');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('4df49b2a-03f9-41f5-bc01-8acc5b955148', TRUE, 'Уругвай', 'd7ffbf1c-f858-469a-863a-68973810c158');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('62cdd3e5-96df-40e3-a3e5-1431f81fbf71', FALSE, 'Англия', 'd7ffbf1c-f858-469a-863a-68973810c158');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('15f3d195-9fd9-4c21-b27b-900b210f3127', FALSE, 'Испания', 'd7ffbf1c-f858-469a-863a-68973810c158');

-- 7 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('6513ed2e-7b13-474b-bab7-f4c28a008f4e', 'Как называется гол, который футболист забил в свои ворота?', 'Как называется гол, который футболист забил в свои ворота?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('61d5ce9e-928f-4706-8e29-e8c3df2dfe8c', TRUE, 'Автогол', '6513ed2e-7b13-474b-bab7-f4c28a008f4e');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('51011eaa-643c-42df-a948-40d1618287ca', FALSE, 'Штрафной', '6513ed2e-7b13-474b-bab7-f4c28a008f4e');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('924d4642-f355-4c97-ac1d-120b98e601b7', FALSE, '«Мертвый мяч»', '6513ed2e-7b13-474b-bab7-f4c28a008f4e');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('09bea017-b5da-4077-ac5c-9d714a0b9def', FALSE, 'Ложный маневр', '6513ed2e-7b13-474b-bab7-f4c28a008f4e');

-- 8 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('8a462607-7867-4f5f-8ffa-5cbd3f9ba185', 'Какое количество игроков одновременно находится на игровом поле с двух сторон?', 'Какое количество игроков одновременно находится на игровом поле с двух сторон?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(ID, ANSWER_IS_RIGHT, TITLE, QUESTION_ID)
VALUES('13170e63-6807-4499-bcdb-a48df44b25d1', FALSE, '11 игроков', '8a462607-7867-4f5f-8ffa-5cbd3f9ba185');

INSERT INTO answers(ID, ANSWER_IS_RIGHT, TITLE, QUESTION_ID)
VALUES('fa5c6f72-b2c6-41d0-aedd-7609dcfec279', FALSE, '30 игроков', '8a462607-7867-4f5f-8ffa-5cbd3f9ba185');

INSERT INTO answers(ID, ANSWER_IS_RIGHT, TITLE, QUESTION_ID)
VALUES('d96c793c-5c58-4cc4-8976-2789b28a385b', TRUE, '22 игрока', '8a462607-7867-4f5f-8ffa-5cbd3f9ba185');

INSERT INTO answers(ID, ANSWER_IS_RIGHT, TITLE, QUESTION_ID)
VALUES('548344e7-4913-45af-a3a8-8b37c79b573c', FALSE, '24 игрока', '8a462607-7867-4f5f-8ffa-5cbd3f9ba185');

-- 9 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('7d40166b-d70a-4a24-b2f0-3457825ffe05', 'Команда из какой страны чаще всего становилась победителем чемпионата мира и сколько раз?', 'Команда из какой страны чаще всего становилась победителем чемпионата мира и сколько раз?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('41a42bf3-538f-4ccf-bddd-16809949d365', FALSE, 'Англия (4 победы)', '7d40166b-d70a-4a24-b2f0-3457825ffe05');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('222fbc43-ef7d-45b7-a8d6-244713bf144a', FALSE, 'Германия (3 победы)', '7d40166b-d70a-4a24-b2f0-3457825ffe05');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('9ac60285-4ea5-49b1-a623-0d7becd666d8', FALSE, 'Испания (4 победы)', '7d40166b-d70a-4a24-b2f0-3457825ffe05');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('b9d9280b-6d14-4b24-aa89-78fb87eb0011', TRUE, 'Бразилия (5 побед)', '7d40166b-d70a-4a24-b2f0-3457825ffe05');

-- 10 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('e8afd1cd-1a7c-4c0a-a43e-7be06026dbc6', 'Назовите правильные размеры стандартных футбольных ворот?', 'Назовите правильные размеры стандартных футбольных ворот?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('14410ad4-3498-4646-b62a-7cb90c14e258', FALSE, '7,22 на 2,45 метра', 'e8afd1cd-1a7c-4c0a-a43e-7be06026dbc6');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('2d42f3ad-0bfe-435f-93f3-6e55520339d0', TRUE, '7,32 на 2,44 метра', 'e8afd1cd-1a7c-4c0a-a43e-7be06026dbc6');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('953a0ab4-a521-4c0b-825e-a6ba67994246', FALSE, '6,9 на 2,11 метра', 'e8afd1cd-1a7c-4c0a-a43e-7be06026dbc6');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('09e85e75-4d3a-4be3-b377-9340f5c2ed72', FALSE, '7,11 на 2,33 метра', 'e8afd1cd-1a7c-4c0a-a43e-7be06026dbc6');

-- 11 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('e0fb4813-d76c-4689-b3cf-5ba8cb3fded4', 'Как правильно расшифровывается аббревиатура УЕФА?', 'Как правильно расшифровывается аббревиатура УЕФА?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('f6b033ff-9e1f-43ef-8d85-fa7798cbd3b6', TRUE, 'Союз европейских футбольных ассоциаций', 'e0fb4813-d76c-4689-b3cf-5ba8cb3fded4');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('af62e791-7a32-440e-8cca-718298855458', FALSE, 'Союз евроинтегрированных футбольных ассоциаций', 'e0fb4813-d76c-4689-b3cf-5ba8cb3fded4');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('dbd61fd0-9683-4464-a11d-5b651ff0fee8', FALSE, 'Содружество европейских футбольных абсорбций', 'e0fb4813-d76c-4689-b3cf-5ba8cb3fded4');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('0e784cfa-328f-4814-85d7-3b671b0b35f6', FALSE, 'Собрание европейских футбольных ассоциаций', 'e0fb4813-d76c-4689-b3cf-5ba8cb3fded4');

-- 12 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('f459cd9a-69cc-4179-a25d-19f5f5f4d1f7', 'Что оформил игрок, забивший 3 мяча за один матч?', 'Что оформил игрок, забивший 3 мяча за один матч?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('e14e8f88-e079-4bbc-af7d-fe88df1f5346', FALSE, 'Автогол', 'f459cd9a-69cc-4179-a25d-19f5f5f4d1f7');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('10af100f-eb69-40c3-a708-9b51f2a0afc0', FALSE, 'Гандикап', 'f459cd9a-69cc-4179-a25d-19f5f5f4d1f7');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('7a64bc65-e65f-4015-9a9e-b5b18c6bdc7b', FALSE, 'Трипл-сек', 'f459cd9a-69cc-4179-a25d-19f5f5f4d1f7');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('b7e629f2-7b47-41c2-a62e-b390a03b1aa1', TRUE, 'Хет-трик', 'f459cd9a-69cc-4179-a25d-19f5f5f4d1f7');

-- 13 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('ce16ebbd-33f7-4037-93df-6d5193c09dc3', 'Сколько существует размеров футбольных мячей?', 'Сколько существует размеров футбольных мячей?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('a9014df4-b491-411f-aa8b-23b4c9ba8abc', FALSE, '2 размера', 'ce16ebbd-33f7-4037-93df-6d5193c09dc3');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('1b3baaf3-d870-4550-b095-365334f04771', TRUE, '5 размеров', 'ce16ebbd-33f7-4037-93df-6d5193c09dc3');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('6f341298-570f-47f2-8f0d-31ccf05172c9', FALSE, '3 размера', 'ce16ebbd-33f7-4037-93df-6d5193c09dc3');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('959373f7-337e-483b-9bf2-776081b3d67b', FALSE, '6 размеров', 'ce16ebbd-33f7-4037-93df-6d5193c09dc3');

-- 14 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('57828661-118f-456f-a1d2-6a6497c8e46f', 'Как называется самая почетная персональная награда для футболиста?', 'Как называется самая почетная персональная награда для футболиста?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('120deb1a-2f45-4f16-8415-6b932e4f345f', TRUE, 'Золотой мяч', '57828661-118f-456f-a1d2-6a6497c8e46f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('ef809494-42f9-4145-bfc7-0b8b05d81b17', FALSE, 'Статуэтка чемпиона', '57828661-118f-456f-a1d2-6a6497c8e46f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('8db3ba4d-c88d-4911-bf81-5e70a85d3672', FALSE, 'Кубок чемпиона', '57828661-118f-456f-a1d2-6a6497c8e46f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('d14ff442-8c2d-489f-b22c-69998589df2a', FALSE, 'Золотая бутса', '57828661-118f-456f-a1d2-6a6497c8e46f');

-- 15 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('02aa08a8-fa73-47a7-98bd-88da0d9f28fb', 'Футбольный клуб какой страны в 20 веке 5 раз подряд становился победителем Лиги Чемпионов? Как он называется?', 'Футбольный клуб какой страны в 20 веке 5 раз подряд становился победителем Лиги Чемпионов? Как он называется?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('8f3579ee-be0a-4a0a-8ead-2d137a56dea2', FALSE, 'Арсенал (Англия)', '02aa08a8-fa73-47a7-98bd-88da0d9f28fb');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('f884aaba-4b7d-45a1-babc-d6c799ef414d', TRUE, 'Реал Мадрид (Испания)', '02aa08a8-fa73-47a7-98bd-88da0d9f28fb');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('e7c9b067-4034-4a6f-a9b2-01ab29ce4383', FALSE, 'Ювентус (Италия)', '02aa08a8-fa73-47a7-98bd-88da0d9f28fb');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('f7801bd9-c798-4b76-99ed-79d4bcd8b9ae', FALSE, 'Манчестер Сити (Англия)', '02aa08a8-fa73-47a7-98bd-88da0d9f28fb');

-- 16 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('d8ee072d-de99-4a2a-af48-b5ce3efc7145', 'Что такое финт в футболе?', 'Что такое финт в футболе?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('a3f15c01-72f4-4baa-85e6-9b9dbd234b87', FALSE, 'Название тайма', 'd8ee072d-de99-4a2a-af48-b5ce3efc7145');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('5e4c110d-cc46-4e48-812f-b13ba2c572ea', FALSE, 'Определение местоположения мяча', 'd8ee072d-de99-4a2a-af48-b5ce3efc7145');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('46ceda35-c442-4f97-9efb-0819ffbd0476', TRUE, 'Технический элемент, который выполняют, чтобы обмануть соперника', 'd8ee072d-de99-4a2a-af48-b5ce3efc7145');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('34c5a7a9-feb1-4e22-8406-f766ab38fb69', FALSE, 'Второе наименование полузащитника', 'd8ee072d-de99-4a2a-af48-b5ce3efc7145');

-- 17 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('e6a189b6-966f-4c9c-b672-5f3f17c6ed0f', 'При каких условиях футболисты играют красным или желтым мячом?', 'При каких условиях футболисты играют красным или желтым мячом?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('c1b9b46f-ad3d-4959-9f8c-119d1b336187', TRUE, 'Во время неблагоприятных погодных условий – дождь, снег, туман', 'e6a189b6-966f-4c9c-b672-5f3f17c6ed0f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('3dea0e56-716f-4552-88ef-5266cb3b1f3b', FALSE, 'Если мяч вылетел за пределы поля', 'e6a189b6-966f-4c9c-b672-5f3f17c6ed0f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('412a240a-fde2-45ae-966a-cad417a05b58', FALSE, 'Когда начинается дополнительное время игры', 'e6a189b6-966f-4c9c-b672-5f3f17c6ed0f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('1dc1b6a2-3c58-46c8-8a4e-ecec012e62dc', FALSE, 'Во втором тайме', 'e6a189b6-966f-4c9c-b672-5f3f17c6ed0f');

-- 18 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('a3e5ada0-c8bf-42ea-b4b0-1f2a233245ba', 'Какое количество рефери необходимо для судейства одного футбольного матча?', 'Какое количество рефери необходимо для судейства одного футбольного матча?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('891a92b5-dc45-4ba1-a1cd-c500f91c4cc5', FALSE, 'Семеро', 'a3e5ada0-c8bf-42ea-b4b0-1f2a233245ba');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('b891616d-fe9d-4fbc-aeb7-4149e66e2ab7', FALSE, 'Пятеро', 'a3e5ada0-c8bf-42ea-b4b0-1f2a233245ba');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('39dbdf6c-c521-4b7c-b151-8c451ad5fd16', FALSE, 'Двое', 'a3e5ada0-c8bf-42ea-b4b0-1f2a233245ba');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('721a7559-d9c0-4a58-8978-2d6182f74f01', TRUE, 'Трое', 'a3e5ada0-c8bf-42ea-b4b0-1f2a233245ba');

-- 19 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('9459d58f-605c-47ae-a3ba-3c6342ec8adf', 'Кто является автором самого сильного удара в футболе, согласно данным на начало 2020 года?', 'Кто является атвором самого сильного удара в футболе, согласно данным на начало 2020 года?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('9e804ca9-8cb0-4d76-b8dd-55be6a74438e', TRUE, 'Халк', '9459d58f-605c-47ae-a3ba-3c6342ec8adf');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('1fcd1534-855a-425f-979e-de5facc3ca8c', FALSE, 'Дэвид Бекхэм', '9459d58f-605c-47ae-a3ba-3c6342ec8adf');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('22031abc-919c-4cfe-ba56-0876db73a25b', FALSE, 'Лукас Подольски', '9459d58f-605c-47ae-a3ba-3c6342ec8adf');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('bdb83b07-87da-413e-aa3b-e03665c4263d', FALSE, 'Роберто Карлос', '9459d58f-605c-47ae-a3ba-3c6342ec8adf');

-- 20 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('22fbb077-02e6-45f6-a6c9-e151990538d8', 'Сколько желтых карточек допускается получить игроку за одну игру?', 'Сколько желтых карточек допускается получить игроку за одну игру?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('b3c60b4b-816b-482d-8b7e-91f24cc565c6', FALSE, 'Ни одной', '22fbb077-02e6-45f6-a6c9-e151990538d8');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('8ed8e247-8cd3-47b2-997c-e8a2a81bb605', TRUE, 'Одну', '22fbb077-02e6-45f6-a6c9-e151990538d8');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('218bac09-0831-4fc5-99ed-c1b829046c60', FALSE, 'Две', '22fbb077-02e6-45f6-a6c9-e151990538d8');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('ab7e4d2a-1239-4876-b204-8e505fd6e226', FALSE, 'Три', '22fbb077-02e6-45f6-a6c9-e151990538d8');

-- 21 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('ce22cc72-3a62-49d2-a305-19d752063ac2', 'Какие цвета карточек существуют в футболе?', 'Какие цвета карточек существуют в футболе?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('ee8fe612-412c-44d6-a31d-180231c826bd', FALSE, 'Черная и красная', 'ce22cc72-3a62-49d2-a305-19d752063ac2');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('d02b081e-85c3-431d-abad-9f26eec9beb1', FALSE, 'Красная и желтая', 'ce22cc72-3a62-49d2-a305-19d752063ac2');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('7667639a-adcd-41e5-9de0-04db1fc69cd8', TRUE, 'Зеленая, красная и желтая', 'ce22cc72-3a62-49d2-a305-19d752063ac2');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('5c6c7c2b-dbd3-4998-98d0-26c368a15d34', FALSE, 'Синяя и красная', 'ce22cc72-3a62-49d2-a305-19d752063ac2');

-- 22 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('1cd64fe0-cfc7-4fa6-8442-0549cedbf2c8', 'Как называют игрока, который в соревновании забил больше всех голов?', 'Как называют игрока, который в соревновании забил больше всех голов?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('9c87db9f-e2db-440b-8b0e-7b5923a6829a', FALSE, 'Голкипер', '1cd64fe0-cfc7-4fa6-8442-0549cedbf2c8');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('1dca3c7a-81f5-4335-9b89-29435abc292c', FALSE, 'Вице-чемпион', '1cd64fe0-cfc7-4fa6-8442-0549cedbf2c8');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('dfbab96b-91c7-4481-b29f-c867b82f201b', FALSE, 'Лидер', '1cd64fe0-cfc7-4fa6-8442-0549cedbf2c8');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('4cf98e0b-cb7b-42b3-b4c7-29c8338d754e', TRUE, 'Лучший бомбардир', '1cd64fe0-cfc7-4fa6-8442-0549cedbf2c8');

-- 23 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('d27869f2-480c-4c36-81a5-1ebc50940038', 'Чем сигнализирует нарушение боковой судья основному арбитру?', 'Чем сигнализирует нарушение боковой судья основному арбитру?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('11a82c1d-c925-42a2-aa93-5287bf22e824', TRUE, 'Флагом', 'd27869f2-480c-4c36-81a5-1ebc50940038');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('898c51fe-1e95-490a-a327-8a5f5dc0f49d', FALSE, 'Рукой', 'd27869f2-480c-4c36-81a5-1ebc50940038');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('79f8e52d-16a8-47e6-9865-5daaefd95b36', FALSE, 'Свистком', 'd27869f2-480c-4c36-81a5-1ebc50940038');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('af25f760-db20-4b69-994a-99fe1604a1c1', FALSE, 'Карточкой', 'd27869f2-480c-4c36-81a5-1ebc50940038');

-- 24 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('d0522b21-a2d8-415c-b8ce-d0f56a526b6b', 'Как называется штрафной удар, который совершают по воротам на расстоянии 11 метров?', 'Как называется штрафной удар, который совершают по воротам на расстоянии 11 метров?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('7051389f-793c-4950-b7fc-df3ad144522e', FALSE, 'Аут', 'd0522b21-a2d8-415c-b8ce-d0f56a526b6b');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('23b24c0f-a330-4be6-8b77-6b50423c344b', TRUE, 'Пенальти', 'd0522b21-a2d8-415c-b8ce-d0f56a526b6b');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('6e7ae698-5877-4c0d-9d3d-b5025875e97d', FALSE, 'Гол', 'd0522b21-a2d8-415c-b8ce-d0f56a526b6b');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('1d8790db-e7f3-48cb-b45b-dcff9e49dead', FALSE, 'Буллит', 'd0522b21-a2d8-415c-b8ce-d0f56a526b6b');

-- 25 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('2f78d7e2-88e0-4dd8-8356-48ff525a6db5', 'Что в переводе с английского означает слово «футбол»?', 'Что в переводе с английского означает слово «футбол»?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '716fb029-148d-4515-8aca-7cb8a604ee24', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('fca496d3-fc36-4032-957a-036d75f1a0e0', FALSE, '«Мяч для ноги»', '2f78d7e2-88e0-4dd8-8356-48ff525a6db5');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('c4725918-8779-4bf4-b3e4-d16290226592', FALSE, '«Бей ногой»', '2f78d7e2-88e0-4dd8-8356-48ff525a6db5');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('2d88a38c-0cff-40b4-aa1b-f1d2555a55dd', FALSE, '«Ручной мяч»', '2f78d7e2-88e0-4dd8-8356-48ff525a6db5');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('e919b6a7-4c8f-415c-9042-ac6a7ffb1a51', TRUE, '«Мячом и ступней»', '2f78d7e2-88e0-4dd8-8356-48ff525a6db5');

-- Разрабатываем игру "Кто хочет стать миллионером?" (запускать может только админ)
insert into games(id, description, photo, name, access, views, rating, rating_count, player_id, game_category_id)
VALUES ('7e9fffd6-b1e6-4343-bc4d-2930620436b4', '15 интересных вопросов', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612267962433-dadd84efdf6e0ec53664fe89a32e7283.jpeg', 'Кто хочет стать миллионером?', 'PUBLIC', '46', NULL, NULL, 'fe51e1e3-60e5-4c4b-800f-1ede7d7eb6dd', '0f4c92a1-61db-438a-86ca-549c820044b4');

INSERT INTO game_access(id, game_id, player_id, access, activation_code)
VALUES ('8919f74d-5dbc-4624-a965-72b7c8ff9e9d', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', 'fe51e1e3-60e5-4c4b-800f-1ede7d7eb6dd', TRUE, NULL);

-- 1 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('093fca06-4129-46ae-a78a-39a306b5e156', 'В каком году произошла Куликовская битва?', 'В каком году произошла Куликовская битва?',
        '90ed209c-7714-4c2b-b539-2c87799264e7', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', 'fdecef96-6ee3-4d25-803b-26042cb532a7', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612268394004-457825ae807ab549d7eb4bb8ef070b5d.jpeg');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('bf981085-d11c-4faa-9f01-fcdee0006dd9', TRUE, '1380', '093fca06-4129-46ae-a78a-39a306b5e156');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('483a27d7-98fd-489a-9222-aaaacc123df2', FALSE, '1616', '093fca06-4129-46ae-a78a-39a306b5e156');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('1b4cdaab-d5fe-4571-b31e-5f3c25d0d2d1', FALSE, '1569', '093fca06-4129-46ae-a78a-39a306b5e156');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('2f325d89-3675-4d7e-b2bd-b02aa21f3207', FALSE, '1773', '093fca06-4129-46ae-a78a-39a306b5e156');

-- 2 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('e798c8a1-28ea-497a-8970-c29c3d4e482c', 'Что, образно говоря, держит за пазухой затаивший злобу человек?', 'Что, образно говоря, держит за пазухой затаивший злобу человек?',
        'ebf7b015-6061-43d1-92e7-3fac5c5802b3', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('e2586b6d-76b4-4c03-a989-b857f791311f', FALSE, 'Сюрприз', 'e798c8a1-28ea-497a-8970-c29c3d4e482c');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('aa002c47-d4c3-4c41-80ca-22535655c0e5', FALSE, 'Рогатку', 'e798c8a1-28ea-497a-8970-c29c3d4e482c');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('4dcba59e-d452-4ae4-ab2d-a70176485149', FALSE, 'Паспорт', 'e798c8a1-28ea-497a-8970-c29c3d4e482c');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('a4ecc2fc-0d19-4210-9bf3-4b4bf84f81bd', TRUE, 'Камень', 'e798c8a1-28ea-497a-8970-c29c3d4e482c');

-- 3 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('55e41994-5f41-4acd-8ec7-9964da4286f4', 'Где на земном шаре день равен ночи в течение всего года?', 'Где на земном шаре день равен ночи в течение всего года?',
        'ebf7b015-6061-43d1-92e7-3fac5c5802b3', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('c08f8aa3-61f2-486f-9e73-7e46112d04fa', TRUE, 'На экваторе', '55e41994-5f41-4acd-8ec7-9964da4286f4');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('d33704b5-9804-44b9-8e41-d04e762f832a', FALSE, 'На полюсах', '55e41994-5f41-4acd-8ec7-9964da4286f4');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('1ba3ce67-aa10-49a3-8407-94b8e770ca6f', FALSE, 'На полярном круге', '55e41994-5f41-4acd-8ec7-9964da4286f4');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('5e30a5ff-60d7-49bb-8180-d50622339189', FALSE, 'На нулевом меридиане', '55e41994-5f41-4acd-8ec7-9964da4286f4');

-- 4 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('04f2d789-7816-4fb5-b3ad-7de9ed953f74', 'В какой из этих стран один из официальных языков - французский?', 'В какой из этих стран один из официальных языков - французский?',
        'c2a658e6-ea92-4df8-bc06-620089ffb187', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', 'fdecef96-6ee3-4d25-803b-26042cb532a7', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612298418025-f6aff354da9556bc39b3c6db8b2b9b41.jpeg');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('89eede7b-b856-4668-af6b-bf76c505859d', FALSE, 'Эквадор', '04f2d789-7816-4fb5-b3ad-7de9ed953f74');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('48a0974f-b2aa-474f-96be-54b907f60f18', TRUE, 'Республика Гаити', '04f2d789-7816-4fb5-b3ad-7de9ed953f74');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('13a02527-7649-43eb-b9b2-9ecfd4c88ee3', FALSE, 'Кения', '04f2d789-7816-4fb5-b3ad-7de9ed953f74');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('13694ad5-b0c0-46ee-8f9d-d434ad66c79a', FALSE, 'Венесуэла', '04f2d789-7816-4fb5-b3ad-7de9ed953f74');

-- 5 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('2656b0ae-e603-4690-ad98-9f1aa29888c2', 'Как футболисты называют внешнюю сторону стопы?', 'Как футболисты называют внешнюю сторону стопы?',
        'ed677e4d-ce26-4e75-8a9b-1be83b5b39d7', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', 'fdecef96-6ee3-4d25-803b-26042cb532a7', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('b5735246-9b92-4af2-a522-8018d0f3a390', FALSE, 'Датчанка', '2656b0ae-e603-4690-ad98-9f1aa29888c2');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('3284f317-e8fe-4043-a08d-66f6ae2f8ce9', FALSE, 'Финка', '2656b0ae-e603-4690-ad98-9f1aa29888c2');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('4d6d1213-00e0-47e3-8d9d-8fcdfeabfb36', FALSE, 'Норвежка', '2656b0ae-e603-4690-ad98-9f1aa29888c2');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('e05a9224-8c7e-4db8-b6c8-deabdbcfa5b6', TRUE, 'Шведка', '2656b0ae-e603-4690-ad98-9f1aa29888c2');

-- 6 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('c32b98d4-c48f-4283-b6ec-105566e0ee92', 'У человека семь шейных позвонков. А сколько их у жирафа?', 'У человека семь шейных позвонков. А сколько их у жирафа?',
        'dad69283-f7c4-47aa-a754-69a2525b43cd', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', '0458cc5b-8871-4807-98e8-68451f3b1f0b', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612299575921-unnamed.jpg');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('1967af2d-faff-461e-8c6f-fc670336619e', FALSE, '10', 'c32b98d4-c48f-4283-b6ec-105566e0ee92');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('2972de6e-5fca-4944-bc65-5dc3bca4cba1', FALSE, '21', 'c32b98d4-c48f-4283-b6ec-105566e0ee92');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('d943494e-93dd-41e5-9bbb-8041066793d7', TRUE, '7', 'c32b98d4-c48f-4283-b6ec-105566e0ee92');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('5f5fa493-1338-405a-b7e5-d978e37cea7d', FALSE, '15', 'c32b98d4-c48f-4283-b6ec-105566e0ee92');

-- 7 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('8b2a5628-f2cd-4b40-9f0a-5fae1322d810', 'Что журнал «Тайм» однажды признал «Человеком года»?', 'Что журнал «Тайм» однажды признал «Человеком года»?',
        '634572e8-6926-463d-8712-c699b4821cdc', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', '0458cc5b-8871-4807-98e8-68451f3b1f0b', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('4fc2d344-cef4-443c-bd63-fae478f82cf7', TRUE, 'Компьютер', '8b2a5628-f2cd-4b40-9f0a-5fae1322d810');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('5343ece4-e0bd-4aff-9e18-b2c56b50eb1a', FALSE, 'Телефон', '8b2a5628-f2cd-4b40-9f0a-5fae1322d810');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('6fe0381e-6f44-40d0-a6a0-686faf5c8e68', FALSE, 'Телевизор', '8b2a5628-f2cd-4b40-9f0a-5fae1322d810');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('7572ab87-e627-4e3c-b07f-1dbee8039d28', FALSE, 'Космический корабль', '8b2a5628-f2cd-4b40-9f0a-5fae1322d810');

-- 8 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('82881e75-4a71-45d1-897e-1dedadb508e4', 'Кто автор антиутопического романа "О дивный новый мир"?', 'Кто автор антиутопического романа "О дивный новый мир"?',
        '5fb660e7-5425-46da-9b33-6a8fc4c716a0', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', '0458cc5b-8871-4807-98e8-68451f3b1f0b', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612300515578-68f6758f5a51b7c3145c8db23c71596e.jpeg');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('59cf63bb-8c71-4e10-8ca2-e6f82a989e18', FALSE, 'Рэй Брэдбери', '82881e75-4a71-45d1-897e-1dedadb508e4');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('89f4f01e-f23e-4d0b-9b15-83764d35a362', FALSE, 'Джордж Оруэлл', '82881e75-4a71-45d1-897e-1dedadb508e4');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('630b526f-53fa-4222-a076-3d24e879dec1', TRUE, 'Олдос Хаксли', '82881e75-4a71-45d1-897e-1dedadb508e4');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('27237539-dac6-4e0a-98ca-00cf09300f13', FALSE, 'Сомерсет Моэм', '82881e75-4a71-45d1-897e-1dedadb508e4');

-- 9 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('476b124a-11ca-4032-9af8-fd4c51ac9cd7', 'С каким философом Екатерина II вела дружескую переписку?', 'С каким философом Екатерина II вела дружескую переписку?',
        '90ed209c-7714-4c2b-b539-2c87799264e7', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', '0458cc5b-8871-4807-98e8-68451f3b1f0b', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('3e89163e-4b30-4cd0-843b-6a174e20ac94', FALSE, 'Паскаль', '476b124a-11ca-4032-9af8-fd4c51ac9cd7');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('66ec952b-8f5a-42b4-9e41-c799624698d3', TRUE, 'Вольтер', '476b124a-11ca-4032-9af8-fd4c51ac9cd7');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('3e608bb8-516b-482a-ad9d-bc1e05cc16c1', FALSE, 'Руссо', '476b124a-11ca-4032-9af8-fd4c51ac9cd7');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('0f3007b9-96f6-476a-a746-448e5df382ab', FALSE, 'Декарт', '476b124a-11ca-4032-9af8-fd4c51ac9cd7');

-- 10 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('1fe96a0e-6a04-4b2f-97cb-f4bb7de3fca0', 'В каком городе был проведён первый международный кинофестиваль?', 'В каком городе был проведён первый международный кинофестиваль?',
        'ebf7b015-6061-43d1-92e7-3fac5c5802b3', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', '0458cc5b-8871-4807-98e8-68451f3b1f0b', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('f8340abd-2daf-43d6-9383-d86939438202', FALSE, 'Берлин', '1fe96a0e-6a04-4b2f-97cb-f4bb7de3fca0');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('ad989bb6-9a2a-437b-bb91-d0824f83736f', FALSE, 'Париж', '1fe96a0e-6a04-4b2f-97cb-f4bb7de3fca0');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('8234a1e9-b2b3-40b4-9921-f58a133792ef', TRUE, 'Венеция', '1fe96a0e-6a04-4b2f-97cb-f4bb7de3fca0');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('909c44bb-6e9b-4215-85a5-385e8216629d', FALSE, 'Канн', '1fe96a0e-6a04-4b2f-97cb-f4bb7de3fca0');

-- 11 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('5ea1f6f4-3527-4d55-8e7a-a677bd65a483', 'Как называется самая глубокая точка поверхности Земли, находящаяся на дне Марианской впадины?', 'Как называется самая глубокая точка поверхности Земли, находящаяся на дне Марианской впадины?',
        'c2a658e6-ea92-4df8-bc06-620089ffb187', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', 'd401d941-6883-45c5-9038-4bbd9f63bb3c', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612364422440-185a7872c4fddfc2b8b0d3c780a21e49.jpeg');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('11a553b1-dceb-45dd-ab4b-3b5750b3d349', FALSE, 'Черное Логово', '5ea1f6f4-3527-4d55-8e7a-a677bd65a483');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('c5180d4f-e0ee-46fc-bf6b-59379fe304b5', FALSE, 'Филиппинская плита', '5ea1f6f4-3527-4d55-8e7a-a677bd65a483');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('850bd6bd-0988-477d-a90b-4fc305ddae18', TRUE, 'Бездна Челленджера', '5ea1f6f4-3527-4d55-8e7a-a677bd65a483');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('23421ceb-bd1a-42f5-9aad-81435036434a', FALSE, 'Кермадек', '5ea1f6f4-3527-4d55-8e7a-a677bd65a483');

-- 12 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('5c35c84f-2f38-45fb-94a9-bce05f33759f', 'В какой из этих столиц бывших союзных республик раньше появилось метро?', 'В какой из этих столиц бывших союзных республик раньше появилось метро?',
        '634572e8-6926-463d-8712-c699b4821cdc', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', 'd401d941-6883-45c5-9038-4bbd9f63bb3c', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('4d6fc15d-9bc2-4c49-b34e-d5fb9a9a9dc6', FALSE, 'Баку', '5c35c84f-2f38-45fb-94a9-bce05f33759f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('6096f797-7213-4d29-99df-f57909bc4250', FALSE, 'Минск', '5c35c84f-2f38-45fb-94a9-bce05f33759f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('33de15b4-2f49-4b8c-9b04-d06712a47bbc', TRUE, 'Тбилиси', '5c35c84f-2f38-45fb-94a9-bce05f33759f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('c2a4e832-1548-494a-be26-f8899fe0ceac', FALSE, 'Ереван', '5c35c84f-2f38-45fb-94a9-bce05f33759f');

-- 13 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('6cff4ce5-3cda-47de-9b46-4d4bdf3b7a0f', 'Какое имя не принимал ни один папа римский?', 'Какое имя не принимал ни один папа римский?',
        '634572e8-6926-463d-8712-c699b4821cdc', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', 'd401d941-6883-45c5-9038-4bbd9f63bb3c', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('15fa78e1-437f-462a-8da0-cc0de6f85341', FALSE, 'Валентин', '6cff4ce5-3cda-47de-9b46-4d4bdf3b7a0f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('d24d5191-7983-4fa7-bb70-b4a013a37b84', FALSE, 'Евгений', '6cff4ce5-3cda-47de-9b46-4d4bdf3b7a0f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('d525b847-b87b-4507-922a-5d0d3bef212f', FALSE, 'Виктор', '6cff4ce5-3cda-47de-9b46-4d4bdf3b7a0f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('a9ee2908-0301-42a5-a16d-848f29b176e3', TRUE, 'Георгий', '6cff4ce5-3cda-47de-9b46-4d4bdf3b7a0f');

-- 14 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('6b563085-eda8-42e3-a32a-073a6d45e2dc', 'Что в свободное время мастерил химик Д. И. Менделеев?', 'Что в свободное время мастерил химик Д. И. Менделеев?',
        '634572e8-6926-463d-8712-c699b4821cdc', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', 'd401d941-6883-45c5-9038-4bbd9f63bb3c', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612365672292-mendeleev.jpg');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('a57900e7-bf6b-4696-91e4-f29f34f98b17', TRUE, 'Чемоданы', '6b563085-eda8-42e3-a32a-073a6d45e2dc');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('2fb64bb8-43a2-4298-8fd5-032416004c66', FALSE, 'Игрушки', '6b563085-eda8-42e3-a32a-073a6d45e2dc');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('f56818a9-cc05-41f0-a6f8-3abb44c57f17', FALSE, 'Прялки', '6b563085-eda8-42e3-a32a-073a6d45e2dc');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('6be99a86-97b3-4cdb-9e4f-68b5453f21ec', FALSE, 'Табуретки', '6b563085-eda8-42e3-a32a-073a6d45e2dc');

-- 15 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('b69a5a6b-662d-480d-ac95-c64eed7dd5c7', 'Реки с каким названием нет на территории России?', 'Реки с каким названием нет на территории России?',
        'c2a658e6-ea92-4df8-bc06-620089ffb187', '7e9fffd6-b1e6-4343-bc4d-2930620436b4', 'd401d941-6883-45c5-9038-4bbd9f63bb3c', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('5778a226-5576-44c5-bb8b-a9fae2200e7d', FALSE, 'Шея', 'b69a5a6b-662d-480d-ac95-c64eed7dd5c7');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('f492c798-1e00-4f97-8d2f-d47377913d68', FALSE, 'Уста', 'b69a5a6b-662d-480d-ac95-c64eed7dd5c7');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('656a6332-32d9-40db-88e6-77dfe215f83f', TRUE, 'Спина', 'b69a5a6b-662d-480d-ac95-c64eed7dd5c7');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('8b6e1dbd-ceb9-40f2-87f3-ac55830de456', FALSE, 'Палец', 'b69a5a6b-662d-480d-ac95-c64eed7dd5c7');

-- Разрабатываем игру "Программирование" (запускать может только админ)
insert into games(id, description, photo, name, access, views, rating, rating_count, player_id, game_category_id)
VALUES ('2948956b-6351-40ff-aab8-0556631cb947', 'Неадекватное Java-интервью', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612368989641-projets.jpg', 'Программирование', 'PUBLIC', '32', NULL, NULL, 'fe51e1e3-60e5-4c4b-800f-1ede7d7eb6dd', '5ad1b56f-b92e-4a18-883b-495167fe5051');

INSERT INTO game_access(id, game_id, player_id, access, activation_code)
VALUES ('dd8a39ca-2ade-4dd3-8241-188e98ef4f97', '2948956b-6351-40ff-aab8-0556631cb947', 'fe51e1e3-60e5-4c4b-800f-1ede7d7eb6dd', TRUE, NULL);

-- 1 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('a2c5cd2b-e464-4c59-aa21-6ead09fdeec5', 'Какой метод вызовется?', 'Какой метод вызовется?',
        'f7b926e4-3a0c-43d5-913d-28e27a612a76', '2948956b-6351-40ff-aab8-0556631cb947', '0458cc5b-8871-4807-98e8-68451f3b1f0b', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612369669337-1.png');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('5da2d5d3-2676-4203-ab33-7d517953c597', FALSE, '#1', 'a2c5cd2b-e464-4c59-aa21-6ead09fdeec5');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('49368981-51f5-4bc1-ae10-8e1f6c30d5e0', TRUE, '#2', 'a2c5cd2b-e464-4c59-aa21-6ead09fdeec5');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('d6a6e5a0-d1e0-46f5-a89e-94b223299c8a', FALSE, '#3', 'a2c5cd2b-e464-4c59-aa21-6ead09fdeec5');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('d8bc56fa-cb8e-47ec-b879-cf5aa73ba1ee', FALSE, 'Compile error', 'a2c5cd2b-e464-4c59-aa21-6ead09fdeec5');

-- 2 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('dc164cd0-070f-4c49-b98b-b537fd7e7905', 'Какой будет Output?', 'Какой будет Output?',
        'f7b926e4-3a0c-43d5-913d-28e27a612a76', '2948956b-6351-40ff-aab8-0556631cb947', '0458cc5b-8871-4807-98e8-68451f3b1f0b', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612369979479-2.png');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('36f3ca48-ee99-4ea8-9307-1f5be60aab12', TRUE, 'Best conference:', 'dc164cd0-070f-4c49-b98b-b537fd7e7905');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('6f1cfc14-915d-4d61-8138-09bbecaf6cdc', FALSE, 'Best conference:http://www.javapoint.ru', 'dc164cd0-070f-4c49-b98b-b537fd7e7905');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('2e7ccbba-e266-410b-ab8f-1fee1322ad42', FALSE, 'Зависнет', 'dc164cd0-070f-4c49-b98b-b537fd7e7905');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('b864a604-45f9-4919-845c-825084dfec90', FALSE, 'Compile error', 'dc164cd0-070f-4c49-b98b-b537fd7e7905');

-- 3 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('e378a156-fdcb-43c6-acaa-927ff6cd883b', 'Какие сравнения напечатают true?', 'Какие сравнения напечатают true?',
        'f7b926e4-3a0c-43d5-913d-28e27a612a76', '2948956b-6351-40ff-aab8-0556631cb947', '0458cc5b-8871-4807-98e8-68451f3b1f0b', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612370534201-3.png');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES ('0edadfd5-27d8-4f49-b401-808bfaa48a8f', FALSE, 'Все', 'e378a156-fdcb-43c6-acaa-927ff6cd883b');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES ('98b05e79-7994-4845-806f-36d8ae5cf9d8', FALSE, 'Никакие', 'e378a156-fdcb-43c6-acaa-927ff6cd883b');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES ('508633c5-5d60-41a1-8c70-3f124f7ac8a8', TRUE, 'Только №1', 'e378a156-fdcb-43c6-acaa-927ff6cd883b');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES ('f0a07529-19e7-405a-b63a-10987e200e47', FALSE, 'Только №2', 'e378a156-fdcb-43c6-acaa-927ff6cd883b');

-- 4 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('e95b5419-3268-476c-aaad-ce2464a7132f', 'Результат вызова метода work()?', 'Результат вызова метода work()?',
        'f7b926e4-3a0c-43d5-913d-28e27a612a76', '2948956b-6351-40ff-aab8-0556631cb947', '0458cc5b-8871-4807-98e8-68451f3b1f0b', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612371155840-4.png');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('6e747785-a9cb-450d-bc7e-ed01fd8120b0', FALSE, 'StackOverflowError', 'e95b5419-3268-476c-aaad-ce2464a7132f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('f075e30a-fec7-49cc-8407-94640c2268aa', FALSE, 'NullPointerException', 'e95b5419-3268-476c-aaad-ce2464a7132f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('263239da-c187-4558-bdff-babd925b7d6c', TRUE, 'Зависнет', 'e95b5419-3268-476c-aaad-ce2464a7132f');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('99ab5f36-3517-49b1-9576-cf00a18fd1c1', FALSE, 'Успешное завершение метода', 'e95b5419-3268-476c-aaad-ce2464a7132f');

-- 5 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('0c4cabf3-6c1f-45af-9e1c-d2bb27d52f87', 'Какой будет результат?', 'Какой будет результат?',
        'f7b926e4-3a0c-43d5-913d-28e27a612a76', '2948956b-6351-40ff-aab8-0556631cb947', '0458cc5b-8871-4807-98e8-68451f3b1f0b', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612371607873-5.png');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('c7fd87f1-4868-4ba0-a1e8-621c443df8ee', FALSE, 'ArrayIndexOutOfBoundsException', '0c4cabf3-6c1f-45af-9e1c-d2bb27d52f87');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('ab466887-327c-4fad-8310-84f94a6c9328', FALSE, 'Завершится без ошибок', '0c4cabf3-6c1f-45af-9e1c-d2bb27d52f87');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('b9f38e09-fb97-4c43-83ef-069b24a153d1', FALSE, 'Compile error', '0c4cabf3-6c1f-45af-9e1c-d2bb27d52f87');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('f98e0c27-475e-4432-8b0f-fd3b346b50d6', TRUE, 'ArithmeticException', '0c4cabf3-6c1f-45af-9e1c-d2bb27d52f87');

-- 6 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('37e2bfe4-40f9-423f-bee8-d293e08f3509', 'Какой будет Output?', 'Какой будет Output?',
        'f7b926e4-3a0c-43d5-913d-28e27a612a76', '2948956b-6351-40ff-aab8-0556631cb947', '0458cc5b-8871-4807-98e8-68451f3b1f0b', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612372180651-6.png');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('7123e0d0-1f1a-4d32-afdf-1088a52fd1e2', FALSE, '1', '37e2bfe4-40f9-423f-bee8-d293e08f3509');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('4cd64ed8-916c-4bec-b956-64d4f14b11ea', TRUE, 'ClassCastException', '37e2bfe4-40f9-423f-bee8-d293e08f3509');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('9b79ce91-a31a-438a-b2ff-4a97951d3b6c', FALSE, '2', '37e2bfe4-40f9-423f-bee8-d293e08f3509');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('383d0752-a025-48da-bf91-21568386379e', FALSE, '3', '37e2bfe4-40f9-423f-bee8-d293e08f3509');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('f1f0fee2-d892-4430-b0bc-f42740d0628f', FALSE, 'Compile error', '37e2bfe4-40f9-423f-bee8-d293e08f3509');

-- 7 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('659c0b2d-e4c2-41b4-bce4-2b823fd1b320', 'Какой будет Output?', 'Какой будет Output?',
        'f7b926e4-3a0c-43d5-913d-28e27a612a76', '2948956b-6351-40ff-aab8-0556631cb947', '0458cc5b-8871-4807-98e8-68451f3b1f0b', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612372629225-7.png');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('2c6e1a15-9a8e-4ae8-b03a-aa364e49743d', TRUE, '4', '659c0b2d-e4c2-41b4-bce4-2b823fd1b320');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('6ede6498-7651-4538-a290-a628ae1cd947', FALSE, '3', '659c0b2d-e4c2-41b4-bce4-2b823fd1b320');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('1db7565c-9d4e-4bdd-9415-5c0b395dc0cc', FALSE, 'Compile error', '659c0b2d-e4c2-41b4-bce4-2b823fd1b320');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('ddcab825-27f2-4d67-b298-0a6bfa8635f9', FALSE, 'Runtime exception', '659c0b2d-e4c2-41b4-bce4-2b823fd1b320');

-- 8 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('949a7e66-8116-4770-af07-0a0a86f7051a', 'Какой будет Output?', 'Какой будет Output?',
        'f7b926e4-3a0c-43d5-913d-28e27a612a76', '2948956b-6351-40ff-aab8-0556631cb947', '0458cc5b-8871-4807-98e8-68451f3b1f0b', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612373148160-8.png');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('284e59cb-83ba-4c40-83f6-55eabdab4987', FALSE, 'NullPointerException', '949a7e66-8116-4770-af07-0a0a86f7051a');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('43761140-7e30-44bb-86ff-f30f1fdbed48', FALSE, 'Завершится без ошибок', '949a7e66-8116-4770-af07-0a0a86f7051a');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('0e2e307b-cda3-4162-9321-9eb8e2237518', TRUE, 'NoClassDefFoundError', '949a7e66-8116-4770-af07-0a0a86f7051a');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('78a4ceb8-c0c3-4de4-bc6f-6af8118a2bcf', FALSE, 'ExceptionInInitializerError', '949a7e66-8116-4770-af07-0a0a86f7051a');

-- 9 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('1f61a11b-562c-4605-86a3-cbc7ad175d87', 'Какой будет Output?', 'Какой будет Output?',
        'f7b926e4-3a0c-43d5-913d-28e27a612a76', '2948956b-6351-40ff-aab8-0556631cb947', '0458cc5b-8871-4807-98e8-68451f3b1f0b', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612373622048-9.png');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('1b52b0bb-9669-41c6-bc62-73ece37f4d11', FALSE, 'Thread end. и зависнет', '1f61a11b-562c-4605-86a3-cbc7ad175d87');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('d924bc18-cffb-4f83-9adc-549c8f4750b7', FALSE, 'Результат неизвестен', '1f61a11b-562c-4605-86a3-cbc7ad175d87');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('c081fd59-1980-47ec-a06e-ef182316cdac', TRUE, 'Thread end.Main end', '1f61a11b-562c-4605-86a3-cbc7ad175d87');

-- 10 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('fa62248f-2606-4306-87c3-1caad8dc7083', 'Какие сравнения напечатают true?', 'Какие сравнения напечатают true?',
        'f7b926e4-3a0c-43d5-913d-28e27a612a76', '2948956b-6351-40ff-aab8-0556631cb947', '0458cc5b-8871-4807-98e8-68451f3b1f0b', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1612374036277-10.png');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('ac71cc5e-36d1-45ae-a517-f2876b3e5278', FALSE, '1', 'fa62248f-2606-4306-87c3-1caad8dc7083');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('665459d8-ec15-49bc-8140-da256de15d5b', TRUE, '1 и 3', 'fa62248f-2606-4306-87c3-1caad8dc7083');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('490211b5-ce2d-40a8-9971-0a4d1f6998c3', FALSE, '1 и 2', 'fa62248f-2606-4306-87c3-1caad8dc7083');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('157c1ecd-cf68-48a1-9bde-818eeee71640', FALSE, 'Все', 'fa62248f-2606-4306-87c3-1caad8dc7083');

-- Разрабатываем игру "Netcracker" (запускать может только админ)
insert into games(id, description, photo, name, access, views, rating, rating_count, player_id, game_category_id)
VALUES ('58e183eb-86a3-4b29-acb7-76b593418253', 'Факты о Netcracker', 'https://quiz-netcracker-basket.s3.eu-north-1.amazonaws.com/1611576823716-socialmediacard_homepage.png', 'Netcracker', 'PRIVATE', '21', NULL, NULL, 'fe51e1e3-60e5-4c4b-800f-1ede7d7eb6dd', '5ad1b56f-b92e-4a18-883b-495167fe5051');

INSERT INTO game_access(id, game_id, player_id, access, activation_code)
VALUES ('3bfa6f65-ca19-4653-9515-be0e93b81dc5', '58e183eb-86a3-4b29-acb7-76b593418253', 'fe51e1e3-60e5-4c4b-800f-1ede7d7eb6dd', TRUE, NULL);

-- 1 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('333a8670-a17f-47b2-9490-d268d0f8b05a', 'Какой год основания компании Netcracker?', 'Какой год основания компании Netcracker?',
        'feacb6b6-9534-4f1b-9131-2a20a6ec2455', '58e183eb-86a3-4b29-acb7-76b593418253', '0458cc5b-8871-4807-98e8-68451f3b1f0b', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('d58dbc15-76ff-4c78-b91e-a1fe5b76cd5f', FALSE, '1990', '333a8670-a17f-47b2-9490-d268d0f8b05a');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('89cb9ab1-786d-4480-ba69-be59c06ad65c', FALSE, '1991', '333a8670-a17f-47b2-9490-d268d0f8b05a');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('d3ae033e-49ad-4c3e-9463-7f6c655d5403', FALSE, '1992', '333a8670-a17f-47b2-9490-d268d0f8b05a');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('fab5d948-bbc1-4346-9b8a-f49a2c4a8f04', TRUE, '1993', '333a8670-a17f-47b2-9490-d268d0f8b05a');

-- 2 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('abb29486-7807-4417-b3d9-1982fd274454', 'В каком городе расположена штаб-квартира Netcracker?', 'В каком городе расположена штаб-квартира Netcracker?',
        'feacb6b6-9534-4f1b-9131-2a20a6ec2455', '58e183eb-86a3-4b29-acb7-76b593418253', '0458cc5b-8871-4807-98e8-68451f3b1f0b', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('8cdee563-909f-4efd-a504-171168a11e2d', FALSE, 'Нью-Йорк', 'abb29486-7807-4417-b3d9-1982fd274454');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('f952c370-9cdb-457e-951a-e55b5f40c095', FALSE, 'Миннеаполис', 'abb29486-7807-4417-b3d9-1982fd274454');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('b3ee2d10-3f85-40c4-928e-440529eb3b78', TRUE, 'Уолтем', 'abb29486-7807-4417-b3d9-1982fd274454');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('a2240f55-6d67-49d8-b9a3-8259e753e0ef', FALSE, 'Спрингфилд', 'abb29486-7807-4417-b3d9-1982fd274454');

-- 3 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('b4aeb5ff-086e-4136-a1f1-7fe91d6faf4d', 'Основатель(и) Netcracker - ...', 'Основатель(и) Netcracker - ...',
        'feacb6b6-9534-4f1b-9131-2a20a6ec2455', '58e183eb-86a3-4b29-acb7-76b593418253', '0458cc5b-8871-4807-98e8-68451f3b1f0b', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('def8843a-4a04-4c30-8bb6-4ced01e1d7b7', TRUE, 'Майкл Файнберг и Бонни Ворд', 'b4aeb5ff-086e-4136-a1f1-7fe91d6faf4d');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('df7266f8-362e-4c9c-b560-92f0f83d27e7', FALSE, 'Гэвин Артурз', 'b4aeb5ff-086e-4136-a1f1-7fe91d6faf4d');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('bdd5c425-79d6-40e4-a0f1-7eae358627ee', FALSE, 'Самуил Джонсон и Адам Уилкер', 'b4aeb5ff-086e-4136-a1f1-7fe91d6faf4d');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('b6cf4360-4633-492f-9610-4d448fbbf21a', FALSE, 'Остин Эндерсон', 'b4aeb5ff-086e-4136-a1f1-7fe91d6faf4d');

-- 4 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('65e121a3-d60b-4c74-b388-4b572353005a', 'Кем в 2008 году была приобретена компания NetСracker?', 'Кем в 2008 году была приобретена компания NetСracker?',
        'feacb6b6-9534-4f1b-9131-2a20a6ec2455', '58e183eb-86a3-4b29-acb7-76b593418253', '0458cc5b-8871-4807-98e8-68451f3b1f0b', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('89e1cf1e-f2ac-4fad-9472-0f0e1c7186db', FALSE, 'Crack', '65e121a3-d60b-4c74-b388-4b572353005a');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('057abf2f-1401-4b28-8d50-daf6c5b1bf46', TRUE, 'NEC', '65e121a3-d60b-4c74-b388-4b572353005a');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('76a902dc-d0e2-43da-8f6f-f65ce9b97ade', FALSE, 'NC', '65e121a3-d60b-4c74-b388-4b572353005a');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('e72751d9-e024-4513-9caa-7ba72bc582d3', FALSE, 'Alto', '65e121a3-d60b-4c74-b388-4b572353005a');

-- 5 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('213e7b1e-c515-4fe4-802f-0d11afc92527', 'Сумма сделки по покупке NetСracker - ...', 'Сумма сделки по покупке NetСracker - ...',
        'feacb6b6-9534-4f1b-9131-2a20a6ec2455', '58e183eb-86a3-4b29-acb7-76b593418253', '0458cc5b-8871-4807-98e8-68451f3b1f0b', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('42ccc2ae-14d2-4cdb-81d7-a12bb362d45c', FALSE, '500 млн долларов', '213e7b1e-c515-4fe4-802f-0d11afc92527');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('3ecd8088-5355-4ae8-84a0-c79312be554e', FALSE, '150 млн долларов', '213e7b1e-c515-4fe4-802f-0d11afc92527');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('d9c5a5cf-8851-437f-adaa-03671f272027', TRUE, '300 млн долларов', '213e7b1e-c515-4fe4-802f-0d11afc92527');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('151a49ae-198c-4a15-8e5a-6a7b46857f44', FALSE, '950 млн долларов', '213e7b1e-c515-4fe4-802f-0d11afc92527');

-- 6 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('4eecb9d1-4d19-4a4f-85f5-f9f651b49e42', 'На чем НЕ специализируется NetСracker?', 'На чем НЕ специализируется NetСracker?',
        'feacb6b6-9534-4f1b-9131-2a20a6ec2455', '58e183eb-86a3-4b29-acb7-76b593418253', '0458cc5b-8871-4807-98e8-68451f3b1f0b', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('418fe216-e6ba-429d-990a-7b42bd7b9937', FALSE, 'OSS', '4eecb9d1-4d19-4a4f-85f5-f9f651b49e42');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('a7f04af0-656c-47d0-8c94-b82de61d6aa4', TRUE, 'NGOSS', '4eecb9d1-4d19-4a4f-85f5-f9f651b49e42');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('2cfad4c4-289f-48c7-96c4-22ea66d5376c', FALSE, 'BSS', '4eecb9d1-4d19-4a4f-85f5-f9f651b49e42');

-- 7 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('68516d74-5284-47a3-a997-791e2c7ed1d8', 'Какого сертификата зрелости была удостоена компания?', 'Какого сертификата зрелости была удостоена компания?',
        'feacb6b6-9534-4f1b-9131-2a20a6ec2455', '58e183eb-86a3-4b29-acb7-76b593418253', '0458cc5b-8871-4807-98e8-68451f3b1f0b', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('e9dc2857-5cd9-4dc2-98cb-fa14865e1b63', FALSE, 'Cobit', '68516d74-5284-47a3-a997-791e2c7ed1d8');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('dd1f5176-1808-47ef-9bed-a066c50d165e', TRUE, 'CMMI', '68516d74-5284-47a3-a997-791e2c7ed1d8');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('840b7b13-3774-4ed6-aadc-7e476f1e8bfd', FALSE, 'ITIL', '68516d74-5284-47a3-a997-791e2c7ed1d8');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('050f536b-05bc-47b1-bf8a-d6e079b3f4f4', FALSE, 'ITSM', '68516d74-5284-47a3-a997-791e2c7ed1d8');

-- 8 вопрос
INSERT INTO questions(id, description, title, category_id, game_id, level_id, photo)
VALUES ('13ed1653-ee01-425d-bc3b-750475f911ac', 'Кто НЕ является HR Netcracker?', 'Кто НЕ является HR Netcracker?',
        'feacb6b6-9534-4f1b-9131-2a20a6ec2455', '58e183eb-86a3-4b29-acb7-76b593418253', '0458cc5b-8871-4807-98e8-68451f3b1f0b', NULL);

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('e2115b34-5557-44dd-a5ac-97301d283de4', FALSE, 'Алина Шавлинская', '13ed1653-ee01-425d-bc3b-750475f911ac');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('fe50dc5f-d2a3-4af0-9312-0e5db4394b92', FALSE, 'Алеся Алюшкевич', '13ed1653-ee01-425d-bc3b-750475f911ac');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('72d10d36-8466-4aa6-8406-dc1d966ab3be', FALSE, 'Ольга Чернецкая', '13ed1653-ee01-425d-bc3b-750475f911ac');

INSERT INTO answers(id, answer_is_right, title, question_id)
VALUES('c40d2aa9-6b71-404c-9838-e556721d116c', TRUE, 'Надежда Ангарская', '13ed1653-ee01-425d-bc3b-750475f911ac');
# Программа по сокращению URL ссылок

## Вводная информация

Программа реализована на Java с использованием в качестве БД - MySQL.
Создана БД - urlshortenerdb, а в ней таблица - usertable.
Таблица содержит следующие колонки:
* id - генерируется автоматически,
* UUID - генерируется автоматически при регистрации пользователя,
* LONGURL - длинная ссылка,
* SHORTURL - короткая ссылка,
* COUNTER - счетчик переходов,
* CREATIONDATE - дата создания (автоматический timestamp),
* DAYSTOEXPIRE - количество дней "жизни" ссылки.

## Краткое описание функционала
Пользователю при регистрации создается уникальный UUID

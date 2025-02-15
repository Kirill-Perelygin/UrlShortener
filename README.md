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
Пользователю при регистрации создается уникальный UUID, который он использует для авторизации. 
После авторизации пользователю доступны следующие функции: 
* переход по ранее сокращенной ссылке, 
* генерация короткой ссылки, 
* отображение всех доступных пользователю ссылок, 
* удаление ранее сгенерированных ссылок, 
* возврат в главное меню. 

Количетсво переходов по ссылкам, а также время их существования могу быть введены пользователем вручную при создании ссылки или заданы по дефолту из конфигурационного файла (в случае отказа от установки кастомных значений пользователем)

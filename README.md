# testTest
# Задание
<b>Написать REST API бэкенд (предпочтительно) со следующим функционалом:</b>

	Сохранение в БД Новостей и их Типов.
    Новость должна иметь структуру:
        Имя;
        Краткое описание;
        Полное описание;
        Тип новости;
    Тип новостей должен иметь структуру:
        Имя типа;
        Цвет типа;

<b>Нужен функционал</b>

    CRUD (Create, Read, Update, Delete) типов новостей;
    CRUD новостей;
    Возможность получить список всех новостей (имя, краткое описание, тип новости – имя типа, цвет типа);
    Возможность получить список новостей определенного типа;
    Возможность получить список всех типов новостей.

<b>Для написания программы желательно использовать Spring boot framework. 
Для работы с БД желательно использовать spring-data-jpa.</b>
# Инструкция

<p>Получить список новостей : http://localhost:8080/news</p>
	
<p>Создать новость : http://localhost:8080/news/creation/{{newsTypeId}}</p>
	newsTypeId - id типа новости
<p>Удалить новость :http://localhost:8080/news/delete/{{newsId}}</p>
	newsId - id новости
<p>Обновить новость : http://localhost:8080/news/update/{{newsId}}/{{newsTypeId}}</p>
	newsTypeId - id типа новости, newsId - id новости
<p>Получить определённую новость по id : http://localhost:8080/news/{{newsId}}</p>
	newsId - id новости
<p>Получить список типов новостей : http://localhost:8080/news/type-found</p>
	
<p>Создать тип новости : http://localhost:8080/news/type-creation</p>
	
<p>Удалить тип новости : http://localhost:8080/news/type-delete/{{newsTypeId}}</p>
	newsTypeId - id типа новости
<p>Обновить тип новости : http://localhost:8080/news/type-update/{{newsTypeId}}</p>
	newsTypeId - id типа новости
<p>Получить определённый тип новости по id : http://localhost:8080/news/type-found/{{newsTypeId}}</p>
	newsTypeId - id типа новости

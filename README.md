# Дипломный проект курса Яндекс.Практикум "Автоматизатор тестирования на Java"
## Надежда Котова 31 когорта
Задание 3: UI
### Технологии проекта:

Java 11, JUnit4, Maven, Allure, Selenium

### Как запускать:

`mvn clean test`

`mvn allure:serve`

___________________________________________________________________________________
# Техническое задание

Протестировать веб-приложение [Stellar Burgers](https://stellarburgers.nomoreparties.site/)

Описать элементы, которые будут использоваться в тестах, с помощью Page Object.
Протестировать функциональность в Google Chrome, Подключить Allure-отчёт.

### Регистрация:
- Успешная регистрация
- Ошибка для некорректного пароль. Минимальный пароль - шесть символов

### Авторизация:
- по кнопке «Войти в аккаунт» на главной
- через кнопку «Личный кабинет»
- через кнопку в форме регистрации
- через кнопку в форме восстановления пароля

### Переход в Личный кабинет:
- Переход по клику на "Личный кабинет"

### Переход из личного кабинета в конструктор:
- переход по клику на "Конструктор"
- переход по клику на логотип Stellar Burgers

### Выход из аккаунта:
- выход по кнопке "Выйти" в Личном кабинете

### Раздел "Конструктор":
- переход к разделу "Булки"
- переход к разделу "Соусы"
- переход к разделу "Начинки"
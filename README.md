##### Описание
REST-сервис, выдающий автомобильные номера.
Контекст приложения: /number

REST-сервис должен реализовывать два GET-метода: random и next

Ответ в формате plain text.

Правильные примеры вызовов:

Запрос: GET http://localhost:8080/number/random
Ответ: C399BA 116 RUS

Запрос: GET http://localhost:8080/number/next
Ответ: C400BA 116 RUS

### Запуск приложения
1) mvn clean package
2) docker-compose up
 
Или просто запуск без докера через апликейшон.

### Стек
- Java 11
- Maven
- Docker
- Spring Boot 2.7
- Lombok
- Spring Boot Tests
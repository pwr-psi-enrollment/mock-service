# mock-service

## Struktura danych
* `/data/courses` - dane kursów wraz z grupami zajęciowymi (do zapisów), musi się zgadzać `registeredId` oraz `semesterId`
* `/data/semesters` - dane semestrów z kursami dla registeredId
* `/data/students` - szczegółowe dane studentów wraz z kierunkami
* `/data/users`- dane logowania użytkowników, **nazwa pliku == nazwa użytkownika**

ID które się do siebie odnoszą muszą się zgadzać, żeby nasze zapisy działały poprawnie!

# Mettings-Server 

Aplikacja wspomagająca organizowanie spotkań lub wydarzeń, umożliwiająca użytkownikom przeglądanie i tworzenie wydarzeń z zdefiniowaniem potrzebnych informacji (opis, typ, miejsce wydarzenia, opcjonalne zdjęcie) oraz komentowanie danego wydarzenia.

## Technologie i narzędzia

 - Java 11
 - Spring Boot  2.3.0
 - Spring Security
 - Lombok
 - zalando spring web problem
 - JWT


## Funkcjonalności

Aplikacja umożliwia tworzenie konta, dzięki któremu użytkownik ma możliwość podglądu dostępnych wydarzeń, dodawania nowych wydarzeń, ich aktualizowania i usuwania. Jako wydarzenie rozumiane są typy wydarzeń takie jak koncerty, prezentacje lub mniejsze spotkania skupiające przyjaciół, osób o wspólnych zainteresowaniach. Każde wydarzenie zawiera formularz do dodawania komentarzy oraz możliwość ich wyświetlenia. Użytkownik ma możliwość wyświetlenia wszystkich wydarzeń na mapie. Jest również udostępniona funkcjonalność wyszukiwania wydarzeń po ich typie. Z poziomu administratora jest dostępna funkcjonalność usuwania wydarzeń oraz użytkowników. Użytkownik również posiada możliwość usuwania i aktualizowania dodanych przez siebie wydarzeń.

## Porty

| Nazwa | Port|
| ---- | --- |
| MongoDB | 27017 |
| meetings-service | 8080 |
 
## Uruchomienie aplikacji

Aplikacja była uruchamiana i testowana za pomocą środowiska Intellij IDEA

Instalacja Java 11
Instalacja MongoDB, lub poprzez użycie np. Dockera

Wejście do katalogu z projektem
```bash
$ cd mettings
```

Uruchomienie aplikacji

```bash
$ ./gradlew clean build
```

Uruchomienie testów

```bash
$ ./gradlew test
```

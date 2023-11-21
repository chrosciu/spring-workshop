## Konteneryzacja aplikacji

### Za pomocą buildpack

Budujemy kontener:

`mvn spring-boot:build-image`

Sprawdzamy na liście czy kontener się zbudował:

`docker image ls | grep spring-workshop`

Uruchamiamy:

`docker run -it -p 8080:8080 spring-workshop:0.0.1-SNAPSHOT`

Można przekazać property poprzez zmienną środowiskową:

`docker run -it -p 8080:8080 -e GENERATOR_INITIAL=10 spring-workshop:0.0.1-SNAPSHOT`

## Zadania

### Zadanie 1

- Utwórz interfejs `PaymentRepository` reprezentujący repozytorium płatności (miejsce gdzie można zapisać stworzoną płatność)

```java
public interface PaymentRepository {
    Payment save(Payment payment);
}
```

- Dodaj implementację interfejsu `PaymentRepository`, bazując na wybranej, standardowej kolekcji np. `HashMap`
- Wstrzyknij stworzone repozytorium, jako kolejną zależność `FakePaymentService` i wykorzystaj ją do zapisywania płatności
- Spróbuj dodać testy jednostkowe

### Zadanie 2

- Utwórz adnotację `@Retry` którą można zakładać na metodach. Będzie ona służyła do oznaczania metod, których próba wykonania powinna być ponowiona w momencie doprowadzenia do wyrzucenia wyjątku. Adnotacja powinna zawierać atrybut `attempts` mówiący ile maksymalnie prób ponowienia ma nastąpić
- Stwórz aspekt `RetryMethodExecutor`, który będzie podpinał się do metod oznaczonych adnotacją `@Retry` i zrealizuje ponowienie wykonania tych metod w przypadku rzucenia wyjątku

### Zadanie 3

Nasz sklep internetowy został rozbudowany - oprócz części odpowiedzialnej za płatności (`PaymentService`) zostały dodane funkcjonalności zamówień (`OrderService`) oraz oferty sklepu (`ProductService`), a wszystko to zostało spięte w module sklepu (`ShopService`).

Niestety gdy po tych zmianach próbujemy uruchomić kod, to nic nie działa - gdyż Spring nie jest w stanie podnieść swojego kontekstu. Brakuje mu bowiem informacji o nowo dodanych częściach aplikacji wymienionych powyżej.

Twoim zadaniem jest dodanie takiej informacji (rozszerzenie istniejącej konfiguracji Springa) w jednej z trzech wersji:

- wyłącznie z użyciem kodu Javy poprzez klasy konfiguracyjne
- poprzez adnotacje Springowe
- z użyciem XML

tak aby udało się doprowadzić aplikację do działającej wersji.

### Zadanie 4

W związku z wprowadzeniem nowych regulacji prawnych pojawiła się konieczność rozbudowy modułu odpowiedzialnego za zamówienia (`OrderService`) o audyt wszystkich operacji związanych z tworzeniem i edycją zamówień.

Twoim zadaniem jest zaimplementowanie takiego mechanizmu bazującego na poniższych założeniach:

- za każdym razem gdy zostanie utworzone nowe zamówienie lub zmodyfikowane istniejące, to powinno zostać wygenerowane odpowiadające temu zdarzenie (event)
- zdarzenie powinno być odebrane poprzez odpowiednią klasę która na jego podstawie zaloguje na konsolę wiadomość składającą się z:
  - prefixu audytowego
  - rodzaju zdarzenia (utworzenie / zmodyfikowanie) zamówienia
  - reprezentacji tekstowej zamówienia
- prefix audytowy powinien być wczytywany jako configuration property (klucz można nazwać dowolnie)
- klasa odbierająca zdarzenia powinna być aktywna tylko jeżeli aktywny jest profil odpowiedzialny za audyt (profil również można nazwać dowolnie)

### Zadanie 5

Utwórz kontroler REST API `OrderController` tak aby możliwe było dodawanie nowych zamówień poprzez REST API. 
Szczegółowe wymagania:

- dodanie zamówienia powinno być realizowane poprzez uderzenie w endpoint `/api/orders` metodą `POST`
- aby dodać zamówienie trzeba jako request body następujący JSON (jego odpowiednikiem będzie nowa klasa `OrderDto`):

```json
{
    "productsIds" : [1, 2]
}
```

- pole `productsIds` reprezentuje listę `id` produktów jakie mają znaleźć się w zamówieniu (należy je następnie zamapować na produkty)
- w związku z powyższym koniecznie będzie roszerzenie interfejsu ProductRepository o metodę `findById`
- jeżeli dodanie zamówienia się powiedzie metoda powinna zwrócić jego `id` jako odpowiedź

### Zadanie 6

Zamiast klasy `OrderDto` utwórz dwie nowe klasy `OrderInputDto` (nie zawierającą pola `id`) oraz `OrderOutputDto` (zawierającą to pole) a następnie użyj ich w klasie `OrderController` tak aby metoda tworząca nowe zamówienie zwracała jego id oraz listę id produktów

### Zadanie 7

W kontrolerze `OrderController` dodaj kolejną metodę REST tak aby można było pobierać zamówienie o podanym id.
Szczegółowe wymagania:

- pobranie zamówienia powinno być realizowane poprzez uderzenie w endpoint `/api/orders?id={id}` metodą `GET`
- `id` zamówienia przekazujemy jako query param
- w sytuacji gdy nie istnieje zamówienie o podanym `id` metoda powinna zwrócić HTTP status 404 (zmodyfikować klasę `GlobalExceptionHandler`)

### Zadanie 8

Zmodyfikuj klasę `GlobalExceptionHandler` tak aby message wypisywany w sytuacji gdy nie udało się znaleźć zamówienia był wczytywany z pliku z zasobami. 
Zapewnij także translację dla języka polskiego.

### Zadanie 9

Zmodyfikuj klasę `Payment` tak aby reprezentowała encję trzymaną w bazie danych. Pamiętaj o zmodyfikowaniu interfejsu `PaymentRepository`

### Zadanie 10

Dodaj metodę REST pozwalającą wyszukiwać zamówienia o podanym statusie płatności (przekazanym jako parametr).

### Zadanie 11

Napisz testy jednostkowe warstwy serwisowej zamówień (klasa `OrderService`). 
Postaraj się przetestować wszystkie metody.

Pamiętaj, aby zamockować wszystkie zależności - mają to być testy stricte jednostkowe.

### Zadanie 12

Napisz testy warstwy repozytorium zamówień (interfejs `OrderRepository`). 
Postaraj się przetestować te metody, które są używane w klasach `OrderService` oraz `OrderSearchController`.

Pamiętaj, aby przygotować sobie początkowe dane w bazie do testów - najlepiej w postaci pliku `*.sql`

### Zadanie 13

Napisz testy warstwy kontrolera zamówień (klasa `OrderController`).
Postaraj się przetestować wszystkie metody udostępnione przez ten kontroler.

Pamiętaj o tym, że niezbędne będzie zamockowanie warstwy serwisowej.

### Zadanie 14

Napisz test integracyjny, w którym stworzysz zamówienie i zweryfikujesz czy kwota płatności za to zamówienie jest równa sumie cen produktów, wchodzących w skład zamówienia.  

W teście korzystaj wyłącznie z metod oferowanych przez klasę `ShopService`

Test powinien spowodować podniesienie całego kontekstu aplikacji

### Zadanie 15

Napisz analogiczny test integracyjny jak w zadaniu 14, ale tym razem korzystając z REST API wystawionego w `OrderController`

### Zadanie 16

Za pomocą adnotacji OpenAPI opisz operacje REST API wystawione w `OrderController`. Zweryfikuj efekty poprzez Swagger UI.
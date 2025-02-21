Biblioteksystem
Detta är ett Java-baserat biblioteksystem som gör det möjligt för användare att låna och lämna tillbaka böcker, visa sina aktuella lån och lista tillgängliga böcker. Det inkluderar också administrativa funktioner för att lägga till och ta bort böcker från katalogen. Applikationen använder MySQL för att lagra och hämta data, och den kommunicerar med databasen via JDBC.

Funktioner
Användarfunktioner:
Låna en bok: Användare kan låna en bok (om den är tillgänglig).
Lämna tillbaka en bok: Användare kan lämna tillbaka en bok de har lånat.
Lista aktuella lån: Användare kan se sina aktiva lån.
Lista alla böcker: Användare kan se alla tillgängliga böcker och deras aktuella status.
Administratörsfunktioner:
Lägga till nya böcker: Admin kan lägga till nya böcker i bibliotekets katalog.
Ta bort böcker: Admin kan ta bort böcker från katalogen.
Lista alla böcker och lånestatus: Admin kan visa alla böcker och deras aktuella lånestatus (om de är tillgängliga eller utlånade).
Förutsättningar
För att köra applikationen, se till att följande är installerat:

Java (version 8 eller högre)
MySQL (för databashantering)
JDBC-drivrutin för MySQL (för att ansluta Java med MySQL)
Installation
1. Databasinstallation
Först måste du skapa den nödvändiga MySQL-databasen och tabellerna.

Kör följande SQL-kommandon för att skapa din databas:

sql
Copy
CREATE DATABASE LibrarySystem;

USE LibrarySystem;

-- Skapa Books-tabellen
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    available BOOLEAN
);

-- Skapa Loans-tabellen
CREATE TABLE loans (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    book_id INT,
    loan_date DATE,
    return_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);

-- Skapa Users-tabellen
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);
2. JDBC-konfiguration
Sätt upp anslutningen till databasen i Database.java-klassen. Se till att rätt användarnamn och lösenord är angivna:

java
Copy
private static final String URL = "jdbc:mysql://localhost:3306/LibrarySystem";
private static final String USER = "root";
private static final String PASSWORD = "1122334455";
3. JDBC-drivrutin
Lägg till MySQL JDBC-drivrutinen i ditt projekt för att kunna ansluta till databasen. Om du använder IntelliJ IDEA kan du lägga till den via Project Structure -> Libraries och ladda ner den från MySQL Connector/J.

4. Kör applikationen
Efter att ha installerat och konfigurerat databasen och JDBC, kan du köra applikationen. Huvudmetoden körs i Main.java, där du kan se menyn och interagera med systemet.

Användning
Starta applikationen genom att köra Main.java.
Följ menyalternativen för att:
Låna eller lämna tillbaka böcker.
Lista dina aktuella lån.
Visa tillgängliga böcker.
För administratörer: Lägg till eller ta bort böcker från katalogen.
Förbättringar och framtida funktioner
Möjlighet att söka efter böcker baserat på titel eller författare.
Mer avancerad användarhantering, inklusive autentisering och behörighetskontroll.
Möjlighet att förlänga lånetid för böcker.

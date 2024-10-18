# MolnIntroUppgift 1

## Beskrivning
Denna webbapplikation bearbetar förfttare och böcker på en cloudbaserat databas i AWS:a molntjänst!
Detta projekt är en Maven Spring Boot-applikation med CI/CD pipeline via GitHub Actions och AWS. Syftet är att automatisera bygg-, test- och deploy-processen i en applikation.

### Teknisk Specifikation
- Tekniker: Java, Maven, Spring Boot, GitHub Actions, AWS.
- CI/CD Tools: GitHub Actions och AWS.
- Testning : Github Actions och AWS samt Unittester på Controllerklasser.
  
## Installation
1. Klona repositoryn:
   ```bash
   git clone https://github.com/EliasAtHome/molnIntroUppgift1api.git

## CI/CD
Nedan beskrivs CI/CD processen med förklaringar av de Noder som sekvensen går igenom för att kunna Automatisera CI/CD processen, och på så sätt hosta hela applikationen i Molnet. Det är hela ledet från där källkoden ligger i Repot till att det driftas och hostas på AWS i deras molntjänst.

- Git Repository: Koden för webbapplikationen finns i ett Git-repo på GitHub. Ändringar i koden och pushas  till repot.

- GitHub Actions: När en kodändring pushas till repot triggas en Action i Github. En CI/CD-pipeline definieras i en YAML-fil, som utför en serie steg för att bygga och testa applikationen. YAML-filen definerar alltså ett Workflow - sekvens av steg som körs när event triggas vid förändringar. Nya förändringar går igenom UnitTester och enhetstester för att säkerställa att koden fungerar som förväntat vilket är Contineus Integration delevn av CI/CD.

- AWS Pipeline: Om testerna går igenom, integreras Github Actions: workflow med en AWS Pipeline, som hanterar deployment-processen. Denna pipeline tar över och börjar med att bygga applikationen.

- AWS CodeBuild: AWS Pipeline integrerar med AWS CodeBuild, som bygger applikation i en säker och skalbar miljö. CodeBuild laddar ner koden från GitHub, installerar nödvändiga beroenden och kör bygget basserat på den build-workflow man har gjort i CodeBuild inställningarna.

- AWS Elastic Beanstalk: När byggprocessen är klar distribuerar CodeBuild den färdiga applikationen till AWS Elastic Beanstalk, där applikationen hostas och hanteras. Elastic Beanstalk automatiserar infrastrukturhantering, inklusive serverkonfiguration och lastbalansering, vilket gör att vi som utvecklare kan fokusera på kodutveckling snarare än drift.

### Github Actions:
![image](https://github.com/user-attachments/assets/aad0f5e4-14bf-4097-add0-9ee97939b457)

![image](https://github.com/user-attachments/assets/2cae62e6-93c2-41fe-ba49-ad2f79486fc3)

### AWS Deployment Process:
- AWS Pipeline:

![image](https://github.com/user-attachments/assets/353a5ca0-44a7-4f3f-aa71-c11e5d349d3c)

![image](https://github.com/user-attachments/assets/5ab29e02-d157-4b8e-b8d7-c08c48bd2d54)

![image](https://github.com/user-attachments/assets/acb117d4-f798-4650-83b1-6c0bfc0dc301)

- AWS CodeBuild:
  
![image](https://github.com/user-attachments/assets/db33f8ff-171b-41b5-8cb2-45d1f8e9b3cd)

- AWS BeansStalk:
 
![image](https://github.com/user-attachments/assets/c2ec9ae5-565e-472b-97fd-f57718a1d849)


## Endpoints:
Eftersom jag använder Swagger för Endpoint-Dokumentation länkas därför Swagger-länken samt bilder på följande endpoints:

- länk via envrionment: http://test1-env.eba-ghgfdppk.eu-north-1.elasticbeanstalk.com/
  (För att komma till endpoint som beskrivs i bilderna nedan: använd länkan ovanför och skriv exempelvis books -> /books)
  
- Swaggerlänk till listan av alla endpoints i projektet: http://test1-env.eba-ghgfdppk.eu-north-1.elasticbeanstalk.com/swagger-ui/index.html#/
 
![image](https://github.com/user-attachments/assets/016cae86-9e1a-4b7d-b4ce-ef6fdfd1565c)

![image](https://github.com/user-attachments/assets/c0e86481-8c81-4ec7-952f-cdc6cc741f07)


## Databas:
- Aws RDS. Databasen är en MySql databas som är skapad och driftas via AWS RDS.
- 
![image](https://github.com/user-attachments/assets/c3db41ba-a34c-4f92-a034-d37570236ee7)


![image](https://github.com/user-attachments/assets/49731451-36ab-488d-aae0-f142a40b4405)


## Testning:
I CI/CD ingår det att varje kodändring som triggas av en PUSH-event går igenom UnitTester för att säkerställa att koden är säker och följer kvaliteten som är uppsatt i Definition of Done. I detta projekt finns därför några få tester till vardera Service klass.

## VG-Del av uppgift:
I ett annat repo ligger en grundläggande klient för att hantera CRUD-operationer till denna webbservice.

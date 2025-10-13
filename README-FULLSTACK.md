# Sistema Full Stack de Cursos Gratuitos

## Descrição
Sistema completo para gerenciamento de cursos gratuitos da internet, desenvolvido com Java Spring Boot (backend) e React (frontend).

## Tecnologias Utilizadas

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database (em memória)
- Maven

### Frontend
- React 19
- Vite
- Axios
- React Router DOM

## Estrutura do Projeto

```
vite-project/
├── backend/                 # API Java Spring Boot
│   ├── src/main/java/com/cursos/
│   │   ├── CursosApplication.java
│   │   ├── model/Curso.java
│   │   ├── repository/CursoRepository.java
│   │   ├── service/CursoService.java
│   │   └── controller/CursoController.java
│   ├── src/main/resources/
│   │   ├── application.properties
│   │   └── data.sql
│   └── pom.xml
└── src/                     # Frontend React
    ├── services/api.js
    ├── pages/
    │   ├── Cursos/Cursos.jsx
    │   └── Admin/Admin.jsx
    └── styles/
        ├── cursos.css
        └── admin.css
```

## Funcionalidades

### API Endpoints
- `GET /api/cursos` - Lista todos os cursos
- `GET /api/cursos/{id}` - Busca curso por ID
- `GET /api/cursos/categoria/{categoria}` - Filtra por categoria
- `GET /api/cursos/buscar?titulo={titulo}` - Busca por título
- `POST /api/cursos` - Cria novo curso
- `PUT /api/cursos/{id}` - Atualiza curso
- `DELETE /api/cursos/{id}` - Remove curso

### Frontend
- Listagem de cursos com filtros
- Busca por título
- Filtro por categoria
- Página de administração (CRUD completo)
- Interface responsiva

## Como Executar

### Backend
1. Navegue até a pasta backend:
   ```bash
   cd backend
   ```

2. Execute com Maven:
   ```bash
   mvn spring-boot:run
   ```

3. A API estará disponível em: http://localhost:8080
4. Console H2: http://localhost:8080/h2-console

### Frontend
1. Na pasta raiz do projeto:
   ```bash
   npm install
   npm run dev
   ```

2. O frontend estará disponível em: http://localhost:5173

## Dados Iniciais
O sistema já vem com 8 cursos pré-cadastrados incluindo:
- React Completo
- Node.js para Iniciantes
- Python Fundamentos
- JavaScript ES6+
- Spring Boot API
- MySQL Básico
- Git e GitHub
- CSS Grid e Flexbox

## Páginas Disponíveis
- `/` - Página inicial
- `/cursos` - Lista de cursos com filtros
- `/usuario` - Perfil do usuário
- `/admin` - Administração de cursos (CRUD)
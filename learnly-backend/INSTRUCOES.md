# Como Executar o Learnly Backend

## Opção 1: Desenvolvimento (H2 em memória)
```bash
mvn spring-boot:run
```
- Funciona imediatamente sem configuração
- Dados são perdidos ao reiniciar
- Acesso H2 Console: http://localhost:8080/h2-console

## Opção 2: Produção (SQL Server)
1. **Instale e configure o SQL Server**
2. **Execute o script SQL:**
   ```sql
   -- Execute o arquivo learnly_sqlserver.sql
   ```
3. **Execute com perfil SQL Server:**
   ```bash
   mvn spring-boot:run -Dspring.profiles.active=sqlserver
   ```

## Alternando entre os modos:

### Desenvolvimento (H2):
```properties
# application.properties (padrão)
spring.datasource.url=jdbc:h2:mem:testdb
```

### Produção (SQL Server):
```bash
# Usar perfil sqlserver
java -jar target/learnly-api-0.0.1-SNAPSHOT.jar --spring.profiles.active=sqlserver
```

## Endpoints disponíveis:
- `GET /api/cursos` - Listar cursos
- `GET /api/usuarios` - Listar usuários  
- `POST /api/usuarios/login` - Login
- `POST /api/usuarios/registrar` - Registro

## Compatibilidade:
✅ Funciona com o frontend React existente
✅ Mesmos endpoints do LearnlyServer original
✅ Mesma estrutura de dados JSON
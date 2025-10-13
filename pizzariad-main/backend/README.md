# 🚀 Learnly Backend API

## 📁 Estrutura Organizada

```
backend/
├── src/main/java/com/learnly/
│   ├── api/
│   │   └── LearnlyServer.java     # Servidor principal
│   ├── models/                    # Modelos de dados (futuro)
│   └── handlers/                  # Handlers específicos (futuro)
└── README.md
```

## 🏃‍♂️ Como Executar

```bash
cd backend/src/main/java
javac com/learnly/api/LearnlyServer.java
java com.learnly.api.LearnlyServer
```

## 🌐 Endpoints Disponíveis

### 👤 Usuários
- `GET /api/usuarios` - Listar usuários (debug)
- `POST /api/usuarios/registrar` - Cadastrar usuário
- `POST /api/usuarios/login` - Fazer login

### 📚 Cursos
- `GET /api/cursos` - Listar todos os cursos

## 🔐 Sistema de Roles
- **Admin**: Email contém "admin"
- **User**: Outros emails

## 🎯 Funcionalidades
- ✅ CORS habilitado
- ✅ Validação de dados
- ✅ Sistema de autenticação
- ✅ Controle de acesso por roles
- ✅ API RESTful

---
**Desenvolvido para Learnly Platform** 📚
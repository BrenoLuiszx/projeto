const express = require('express');
const cors = require('cors');
const sqlite3 = require('sqlite3').verbose();
const path = require('path');

const app = express();
const PORT = 8080;

// Middleware
app.use(cors());
app.use(express.json());

// Banco de dados SQLite
const db = new sqlite3.Database(':memory:');

// Criar tabelas
db.serialize(() => {
  // Tabela de cursos
  db.run(`CREATE TABLE cursos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    descricao TEXT NOT NULL,
    url TEXT NOT NULL,
    categoria TEXT,
    instrutor TEXT,
    duracao INTEGER
  )`);

  // Tabela de usuários
  db.run(`CREATE TABLE usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    senha TEXT NOT NULL,
    foto TEXT
  )`);

  // Dados iniciais de cursos
  const stmtCursos = db.prepare(`INSERT INTO cursos (titulo, descricao, url, categoria, instrutor, duracao) VALUES (?, ?, ?, ?, ?, ?)`);
  
  stmtCursos.run('React Completo', 'Curso completo de React do básico ao avançado', 'https://www.youtube.com/watch?v=FXqX7oof0I0', 'Frontend', 'Matheus Battisti', 480);
  stmtCursos.run('Node.js para Iniciantes', 'Aprenda Node.js criando uma API REST', 'https://www.youtube.com/watch?v=LLqq6FemMNQ', 'Backend', 'Rocketseat', 360);
  stmtCursos.run('Python Fundamentos', 'Curso de Python para Data Science', 'https://www.youtube.com/watch?v=S9uPNppGsGo', 'Data Science', 'Curso em Video', 720);
  stmtCursos.run('JavaScript ES6+', 'Recursos modernos do JavaScript', 'https://www.youtube.com/watch?v=HN1UjzRSdBk', 'Frontend', 'Cod3r', 300);
  stmtCursos.run('Spring Boot API', 'Criando APIs REST com Spring Boot', 'https://www.youtube.com/watch?v=OHn1jLHGptw', 'Backend', 'DevDojo', 540);
  stmtCursos.run('MySQL Basico', 'Fundamentos de banco de dados MySQL', 'https://www.youtube.com/watch?v=Ofktsne-utM', 'Database', 'Curso em Video', 240);
  stmtCursos.run('Git e GitHub', 'Controle de versao com Git', 'https://www.youtube.com/watch?v=xEKo29OWILE', 'DevOps', 'Curso em Video', 180);
  stmtCursos.run('CSS Grid e Flexbox', 'Layout moderno com CSS', 'https://www.youtube.com/watch?v=x-4z_u8LcGc', 'Frontend', 'Origamid', 420);
  stmtCursos.run('Vue.js 3', 'Framework progressivo para interfaces', 'https://www.youtube.com/watch?v=wsAQQioPIJs', 'Frontend', 'Cod3r', 380);
  stmtCursos.run('Docker Essentials', 'Containerizacao de aplicacoes', 'https://www.youtube.com/watch?v=0xxHiOSJVe8', 'DevOps', 'Fabricio Veronez', 320);
  
  stmtCursos.finalize();
});

// Rotas da API

// Buscar por título (deve vir ANTES da rota com parâmetro)
app.get('/api/cursos/buscar', (req, res) => {
  const { titulo } = req.query;
  console.log('Buscando por título:', titulo);
  
  if (!titulo) {
    res.status(400).json({ error: 'Parâmetro titulo é obrigatório' });
    return;
  }
  
  db.all('SELECT * FROM cursos WHERE titulo LIKE ?', [`%${titulo}%`], (err, rows) => {
    if (err) {
      console.error('Erro na busca:', err);
      res.status(500).json({ error: err.message });
      return;
    }
    console.log('Resultados encontrados:', rows.length);
    res.json(rows);
  });
});

// Buscar por categoria
app.get('/api/cursos/categoria/:categoria', (req, res) => {
  const { categoria } = req.params;
  db.all('SELECT * FROM cursos WHERE categoria = ?', [categoria], (err, rows) => {
    if (err) {
      res.status(500).json({ error: err.message });
      return;
    }
    res.json(rows);
  });
});

// Listar todos os cursos
app.get('/api/cursos', (req, res) => {
  db.all('SELECT * FROM cursos', (err, rows) => {
    if (err) {
      res.status(500).json({ error: err.message });
      return;
    }
    res.json(rows);
  });
});

// Buscar curso por ID (deve vir POR ÚLTIMO)
app.get('/api/cursos/:id', (req, res) => {
  const { id } = req.params;
  db.get('SELECT * FROM cursos WHERE id = ?', [id], (err, row) => {
    if (err) {
      res.status(500).json({ error: err.message });
      return;
    }
    if (row) {
      res.json(row);
    } else {
      res.status(404).json({ error: 'Curso não encontrado' });
    }
  });
});

// Criar novo curso
app.post('/api/cursos', (req, res) => {
  const { titulo, descricao, url, categoria, instrutor, duracao } = req.body;
  
  db.run(
    'INSERT INTO cursos (titulo, descricao, url, categoria, instrutor, duracao) VALUES (?, ?, ?, ?, ?, ?)',
    [titulo, descricao, url, categoria, instrutor, duracao],
    function(err) {
      if (err) {
        res.status(500).json({ error: err.message });
        return;
      }
      res.json({
        id: this.lastID,
        titulo,
        descricao,
        url,
        categoria,
        instrutor,
        duracao
      });
    }
  );
});

// Atualizar curso
app.put('/api/cursos/:id', (req, res) => {
  const { id } = req.params;
  const { titulo, descricao, url, categoria, instrutor, duracao } = req.body;
  
  db.run(
    'UPDATE cursos SET titulo = ?, descricao = ?, url = ?, categoria = ?, instrutor = ?, duracao = ? WHERE id = ?',
    [titulo, descricao, url, categoria, instrutor, duracao, id],
    function(err) {
      if (err) {
        res.status(500).json({ error: err.message });
        return;
      }
      if (this.changes === 0) {
        res.status(404).json({ error: 'Curso não encontrado' });
      } else {
        res.json({ id: parseInt(id), titulo, descricao, url, categoria, instrutor, duracao });
      }
    }
  );
});

// Deletar curso
app.delete('/api/cursos/:id', (req, res) => {
  const { id } = req.params;
  
  db.run('DELETE FROM cursos WHERE id = ?', [id], function(err) {
    if (err) {
      res.status(500).json({ error: err.message });
      return;
    }
    if (this.changes === 0) {
      res.status(404).json({ error: 'Curso não encontrado' });
    } else {
      res.json({ message: 'Curso deletado com sucesso' });
    }
  });
});

// Rotas de Usuários

// Registrar usuário
app.post('/api/usuarios/registrar', (req, res) => {
  const { nome, email, senha, foto } = req.body;
  
  // Verificar se email já existe
  db.get('SELECT * FROM usuarios WHERE email = ?', [email], (err, row) => {
    if (err) {
      res.status(500).json({ error: err.message });
      return;
    }
    
    if (row) {
      res.status(400).json({ error: 'Email já cadastrado' });
      return;
    }
    
    // Inserir novo usuário
    db.run(
      'INSERT INTO usuarios (nome, email, senha, foto) VALUES (?, ?, ?, ?)',
      [nome, email, senha, foto],
      function(err) {
        if (err) {
          res.status(500).json({ error: err.message });
          return;
        }
        res.json({
          message: 'Usuário cadastrado com sucesso',
          usuario: {
            id: this.lastID,
            nome,
            email,
            foto
          }
        });
      }
    );
  });
});

// Login de usuário
app.post('/api/usuarios/login', (req, res) => {
  const { email, senha } = req.body;
  
  db.get('SELECT * FROM usuarios WHERE email = ? AND senha = ?', [email, senha], (err, row) => {
    if (err) {
      res.status(500).json({ error: err.message });
      return;
    }
    
    if (row) {
      res.json({
        message: 'Login realizado com sucesso',
        usuario: {
          id: row.id,
          nome: row.nome,
          email: row.email,
          foto: row.foto
        }
      });
    } else {
      res.status(400).json({ error: 'Email ou senha inválidos' });
    }
  });
});

// Iniciar servidor
app.listen(PORT, () => {
  console.log(`🚀 Servidor rodando na porta ${PORT}`);
  console.log(`📊 API disponível em: http://localhost:${PORT}/api/cursos`);
  console.log(`👥 Usuários: http://localhost:${PORT}/api/usuarios/registrar`);
});

// Fechar banco ao encerrar
process.on('SIGINT', () => {
  db.close((err) => {
    if (err) {
      console.error(err.message);
    }
    console.log('Banco de dados fechado.');
    process.exit(0);
  });
});
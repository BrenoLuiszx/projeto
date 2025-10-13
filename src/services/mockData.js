// Dados mock para funcionar sem backend
let cursosMock = [
  {
    id: 1,
    titulo: "React Completo",
    descricao: "Curso completo de React do básico ao avançado",
    url: "https://www.youtube.com/watch?v=FXqX7oof0I0",
    categoria: "Frontend",
    instrutor: "Matheus Battisti",
    duracao: 480
  },
  {
    id: 2,
    titulo: "Node.js para Iniciantes",
    descricao: "Aprenda Node.js criando uma API REST",
    url: "https://www.youtube.com/watch?v=LLqq6FemMNQ",
    categoria: "Backend",
    instrutor: "Rocketseat",
    duracao: 360
  },
  {
    id: 3,
    titulo: "Python Fundamentos",
    descricao: "Curso de Python para Data Science",
    url: "https://www.youtube.com/watch?v=S9uPNppGsGo",
    categoria: "Data Science",
    instrutor: "Curso em Video",
    duracao: 720
  },
  {
    id: 4,
    titulo: "JavaScript ES6+",
    descricao: "Recursos modernos do JavaScript",
    url: "https://www.youtube.com/watch?v=HN1UjzRSdBk",
    categoria: "Frontend",
    instrutor: "Cod3r",
    duracao: 300
  }
];

let usuariosMock = [];
let proximoIdCurso = 5;
let proximoIdUsuario = 1;

// Simula delay de rede
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const cursosAPIMock = {
  listarTodos: async () => {
    await delay(500);
    return { data: cursosMock };
  },
  
  buscarPorId: async (id) => {
    await delay(300);
    const curso = cursosMock.find(c => c.id === parseInt(id));
    return curso ? { data: curso } : Promise.reject({ response: { status: 404 } });
  },
  
  buscarPorCategoria: async (categoria) => {
    await delay(400);
    const cursos = cursosMock.filter(c => c.categoria === categoria);
    return { data: cursos };
  },
  
  buscarPorTitulo: async (titulo) => {
    await delay(400);
    const cursos = cursosMock.filter(c => 
      c.titulo.toLowerCase().includes(titulo.toLowerCase())
    );
    return { data: cursos };
  },
  
  criar: async (curso) => {
    await delay(600);
    const novoCurso = { ...curso, id: proximoIdCurso++ };
    cursosMock.push(novoCurso);
    return { data: novoCurso };
  },
  
  atualizar: async (id, curso) => {
    await delay(600);
    const index = cursosMock.findIndex(c => c.id === parseInt(id));
    if (index !== -1) {
      cursosMock[index] = { ...curso, id: parseInt(id) };
      return { data: cursosMock[index] };
    }
    return Promise.reject({ response: { status: 404 } });
  },
  
  deletar: async (id) => {
    await delay(500);
    const index = cursosMock.findIndex(c => c.id === parseInt(id));
    if (index !== -1) {
      cursosMock.splice(index, 1);
      return { data: { message: 'Curso deletado' } };
    }
    return Promise.reject({ response: { status: 404 } });
  }
};

export const usuarioAPIMock = {
  registrar: async (usuario) => {
    await delay(600);
    
    // Verifica se email já existe
    if (usuariosMock.find(u => u.email === usuario.email)) {
      return Promise.reject({ 
        response: { 
          data: { error: 'Email já cadastrado' } 
        } 
      });
    }
    
    const novoUsuario = { ...usuario, id: proximoIdUsuario++ };
    usuariosMock.push(novoUsuario);
    return { 
      data: { 
        message: 'Usuário cadastrado com sucesso', 
        usuario: novoUsuario 
      } 
    };
  },
  
  login: async (credentials) => {
    await delay(500);
    const usuario = usuariosMock.find(u => 
      u.email === credentials.email && u.senha === credentials.senha
    );
    
    if (usuario) {
      return { 
        data: { 
          message: 'Login realizado com sucesso', 
          usuario 
        } 
      };
    } else {
      return Promise.reject({ 
        response: { 
          data: { error: 'Email ou senha inválidos' } 
        } 
      });
    }
  }
};
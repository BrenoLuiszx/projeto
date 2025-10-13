import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import '../styles/userDropdown-bigtech.css';

const UserDropdown = () => {
  const { usuario, logout, syncUserData, isAuthenticated } = useAuth();
  const [isOpen, setIsOpen] = useState(false);
  
  console.log('👤 Dados do usuário:', usuario);
  

  const handleLogout = () => {
    logout();
    setIsOpen(false);
  };

  return (
    <div className="user-dropdown">
      <div 
        className="user-avatar" 
        onClick={() => setIsOpen(!isOpen)}
      >
        {isAuthenticated ? (
          <div className="logged-user">
            {usuario.foto ? (
              <img 
                src={usuario.foto} 
                alt={usuario.nome} 
                className="avatar-img"
                onLoad={() => console.log('✅ Foto carregada:', usuario.foto)}
                onError={() => console.log('❌ Erro ao carregar foto:', usuario.foto)}
              />
            ) : (
              <div className="avatar-placeholder">
                {usuario.nome.charAt(0).toUpperCase()}
              </div>
            )}
          </div>
        ) : (
          <div className="menu-icon">
            <div className="hamburger-line"></div>
            <div className="hamburger-line"></div>
            <div className="hamburger-line"></div>
          </div>
        )}
      </div>

      {isOpen && (
        <div className="dropdown-menu">
          {isAuthenticated ? (
            // Menu do usuário logado
            <div className="user-menu">
              <div className="user-info">
                <div className="user-name">{usuario.nome}</div>
                <div className="user-email">{usuario.email}</div>
              </div>
              <div className="menu-divider"></div>
              {usuario.role === 'admin' && (
                <Link to="/admin" className="menu-item" onClick={() => setIsOpen(false)}>
                  Administração
                </Link>
              )}
              <button className="menu-item" onClick={() => { syncUserData(); setIsOpen(false); }}>
                Sincronizar Dados
              </button>
              <button className="menu-item logout-btn" onClick={handleLogout}>
                Sair
              </button>
            </div>
          ) : (
            // Menu simples com links
            <div className="simple-menu">
              <Link to="/login" className="menu-item" onClick={() => setIsOpen(false)}>
                Login
              </Link>
              <Link to="/registro" className="menu-item" onClick={() => setIsOpen(false)}>
                Criar Conta
              </Link>
            </div>
          )}
        </div>
      )}

      {/* Overlay para fechar o dropdown */}
      {isOpen && (
        <div 
          className="dropdown-overlay" 
          onClick={() => setIsOpen(false)}
        ></div>
      )}
    </div>
  );
};

export default UserDropdown;
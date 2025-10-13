import React from 'react';

const CourseFilters = ({ 
  filtro, 
  setFiltro, 
  categoria, 
  viewMode, 
  setViewMode, 
  onBuscar, 
  onFiltrarCategoria, 
  onLimpar,
  totalCursos 
}) => {
  const categorias = [
    { id: 'Frontend', nome: 'Frontend', icon: '🎨' },
    { id: 'Backend', nome: 'Backend', icon: '⚙️' },
    { id: 'Data Science', nome: 'Data Science', icon: '📊' },
    { id: 'Database', nome: 'Database', icon: '🗄️' },
    { id: 'DevOps', nome: 'DevOps', icon: '🚀' }
  ];

  return (
    <div className="courses-filters">
      <div className="search-section">
        <div className="search-box">
          <svg className="search-icon" viewBox="0 0 24 24">
            <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"/>
          </svg>
          <input
            type="text"
            placeholder="Busque por tecnologia, instrutor ou tópico..."
            value={filtro}
            onChange={(e) => setFiltro(e.target.value)}
            onKeyPress={(e) => e.key === 'Enter' && onBuscar()}
          />
          <button className="search-btn" onClick={onBuscar}>
            Buscar
          </button>
        </div>
      </div>

      <div className="filter-controls">
        <div className="category-filters">
          <button 
            className={categoria === '' ? 'active' : ''}
            onClick={() => onFiltrarCategoria('')}
          >
            Todas
          </button>
          {categorias.map(cat => (
            <button 
              key={cat.id}
              className={categoria === cat.id ? 'active' : ''}
              onClick={() => onFiltrarCategoria(cat.id)}
            >
              {cat.icon} {cat.nome}
            </button>
          ))}
        </div>

        <div className="view-controls">
          <button 
            className={viewMode === 'grid' ? 'active' : ''}
            onClick={() => setViewMode('grid')}
            title="Visualização em grade"
          >
            <svg viewBox="0 0 24 24">
              <path d="M3 3h8v8H3V3zm10 0h8v8h-8V3zM3 13h8v8H3v-8zm10 0h8v8h-8v-8z"/>
            </svg>
          </button>
          <button 
            className={viewMode === 'list' ? 'active' : ''}
            onClick={() => setViewMode('list')}
            title="Visualização em lista"
          >
            <svg viewBox="0 0 24 24">
              <path d="M3 13h2v-2H3v2zm0 4h2v-2H3v2zm0-8h2V7H3v2zm4 4h14v-2H7v2zm0 4h14v-2H7v2zM7 7v2h14V7H7z"/>
            </svg>
          </button>
        </div>
      </div>

      <div className="courses-stats">
        <span>{totalCursos} cursos encontrados</span>
        {categoria && <span>• Categoria: {categoria}</span>}
        {filtro && <span>• Busca: "{filtro}"</span>}
        {(categoria || filtro) && (
          <button className="clear-filters" onClick={onLimpar}>
            Limpar filtros
          </button>
        )}
      </div>
    </div>
  );
};

export default CourseFilters;
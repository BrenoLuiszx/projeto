import React, { useState } from 'react';

const CourseCard = ({ curso, viewMode }) => {
  const [isFavorite, setIsFavorite] = useState(false);

  const getCategoryIcon = (categoria) => {
    const icons = {
      'Frontend': '🎨',
      'Backend': '⚙️',
      'Data Science': '📊',
      'Database': '🗄️',
      'DevOps': '🚀'
    };
    return icons[categoria] || '📚';
  };

  const formatDuration = (minutes) => {
    const hours = Math.floor(minutes / 60);
    const mins = minutes % 60;
    return hours > 0 ? `${hours}h ${mins}min` : `${mins}min`;
  };

  const getDifficultyLevel = (duracao) => {
    if (duracao < 240) return { level: 'Iniciante', color: '#4CAF50' };
    if (duracao < 480) return { level: 'Intermediário', color: '#FF9800' };
    return { level: 'Avançado', color: '#F44336' };
  };

  const difficulty = getDifficultyLevel(curso.duracao);

  const handleFavoriteToggle = (e) => {
    e.preventDefault();
    e.stopPropagation();
    setIsFavorite(!isFavorite);
  };

  return (
    <div className={`course-card ${viewMode}`}>
      <div className="course-image">
        <div className="category-badge">
          {getCategoryIcon(curso.categoria)} {curso.categoria}
        </div>
        <div className="duration-badge">
          {formatDuration(curso.duracao)}
        </div>
        <div className="difficulty-badge" style={{ backgroundColor: difficulty.color }}>
          {difficulty.level}
        </div>
      </div>
      
      <div className="course-content">
        <h3 className="course-title">{curso.titulo}</h3>
        <p className="course-description">{curso.descricao}</p>
        
        <div className="course-meta">
          <div className="instructor">
            <svg className="instructor-icon" viewBox="0 0 24 24">
              <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
            </svg>
            <span>{curso.instrutor}</span>
          </div>
          
          <div className="course-rating">
            <div className="stars">
              {[...Array(5)].map((_, i) => (
                <svg key={i} className="star" viewBox="0 0 24 24">
                  <path d="M12 2l3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2z"/>
                </svg>
              ))}
            </div>
            <span className="rating-text">4.8 (1.2k avaliações)</span>
          </div>
        </div>
        
        <div className="course-actions">
          <a 
            href={curso.url} 
            target="_blank" 
            rel="noopener noreferrer" 
            className="btn-primary"
          >
            <svg className="play-icon" viewBox="0 0 24 24">
              <path d="M8 5v14l11-7z"/>
            </svg>
            Assistir Agora
          </a>
          
          <button 
            className={`btn-secondary ${isFavorite ? 'favorited' : ''}`}
            onClick={handleFavoriteToggle}
            title={isFavorite ? 'Remover dos favoritos' : 'Adicionar aos favoritos'}
          >
            <svg viewBox="0 0 24 24">
              <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"/>
            </svg>
          </button>
          
          <button className="btn-secondary" title="Compartilhar curso">
            <svg viewBox="0 0 24 24">
              <path d="M18 16.08c-.76 0-1.44.3-1.96.77L8.91 12.7c.05-.23.09-.46.09-.7s-.04-.47-.09-.7l7.05-4.11c.54.5 1.25.81 2.04.81 1.66 0 3-1.34 3-3s-1.34-3-3-3-3 1.34-3 3c0 .24.04.47.09.7L8.04 9.81C7.5 9.31 6.79 9 6 9c-1.66 0-3 1.34-3 3s1.34 3 3 3c.79 0 1.50-.31 2.04-.81l7.12 4.16c-.05.21-.08.43-.08.65 0 1.61 1.31 2.92 2.92 2.92s2.92-1.31 2.92-2.92-1.31-2.92-2.92-2.92z"/>
            </svg>
          </button>
        </div>
      </div>
    </div>
  );
};

export default CourseCard;
import React, { useState, useEffect } from "react";
import { cursosAPI } from "../../services/api";
import Header from '../Header/Header';
import "../../styles/home.css";

const Home = () => {
  const [currentIndex, setCurrentIndex] = useState(0);
  const [cursos, setCursos] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    carregarCursos();
  }, []);

  const carregarCursos = async () => {
    try {
      const response = await cursosAPI.listarTodos();
      setCursos(Array.isArray(response.data) ? response.data : []);
    } catch (error) {
      console.error("Erro ao carregar cursos:", error);
      setCursos([]);
    } finally {
      setLoading(false);
    }
  };

  const formatDuration = (minutes) => {
    const hours = Math.floor(minutes / 60);
    return hours > 0 ? `${hours}h` : `${minutes}min`;
  };

  const cardsPerSlide = 10;
  const totalSlides = Math.ceil(cursos.length / cardsPerSlide);

  const nextSlide = () => {
    setCurrentIndex((prev) => (prev + 1) % Math.max(1, totalSlides));
  };

  const prevSlide = () => {
    setCurrentIndex((prev) => (prev - 1 + Math.max(1, totalSlides)) % Math.max(1, totalSlides));
  };

  return (
    <div className="home-container">
      <Header />

      {/* Main Content */}
      <main>
        {loading && (
          <div className="loading-overlay">
            <div className="loading-spinner"></div>
          </div>
        )}
        <section className="topo-do-site">
          <div className="começo">
            <div className="flex">
              <div className="txt">
                <h1>
                  Um site que mostra cursos gratuitos e de fácil acesso para
                  você<span>.</span>
                </h1>
                <p>
                  No Trabalho+, oferecemos cursos online para aprimorar suas
                  habilidades e prepará-lo para o mercado de trabalho
                  <span>.</span>
                </p>

                <div id="txt" className="btn">
                  <a href="">
                    <button>Saiba mais</button>
                  </a>
                </div>
              </div>

              <div className="img-topo-site">
                <img
                  className="outra-img"
                  src="/img/industrias_wd2219_2-1024x680.jpg"
                  alt="Pessoas estudando"
                />
              </div>
            </div>
          </div>
        </section>

        {/* Seção de Cursos em Destaque */}
        <section className="cursos-destaque">
          <div className="section-transition"></div>
          <div className="começo">
            <div className="section-header">
              <h2>Cursos em Destaque</h2>
              <p>Descubra os cursos mais procurados e transforme sua carreira</p>
            </div>
            
            <div className="carousel-container">
              <button className="carousel-nav prev" onClick={prevSlide}>
                <svg width="24" height="24" viewBox="0 0 24 24" fill="none">
                  <path d="M15 18l-6-6 6-6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                </svg>
              </button>
              
              <div className="cards-carousel">
                <div 
                  className="cards-track" 
                  style={{ transform: `translateX(-${currentIndex * 100}%)` }}
                >
                  {Array.from({ length: totalSlides }).map((_, slideIndex) => (
                    <div key={slideIndex} className="cards-slide">
                      <div className="cards-grid">
                        {cursos.slice(slideIndex * cardsPerSlide, slideIndex * cardsPerSlide + cardsPerSlide).map((curso, index) => (
                          <div key={slideIndex * cardsPerSlide + index} className="curso-card" onClick={() => window.location.href = '/cursos'}>
                            <div className="card-image">
                              <div className="category-tag">{curso.categoria}</div>
                            </div>
                            <div className="card-content">
                              <h3>{curso.titulo}</h3>
                              <p className="instructor">{curso.instrutor}</p>
                              <p className="description">{curso.descricao}</p>
                              <div className="card-footer">
                                <span className="duration">{formatDuration(curso.duracao)}</span>
                                <span className="level">Premium</span>
                              </div>
                            </div>
                          </div>
                        ))}
                      </div>
                    </div>
                  ))}
                </div>
              </div>
              
              <button className="carousel-nav next" onClick={nextSlide}>
                <svg width="24" height="24" viewBox="0 0 24 24" fill="none">
                  <path d="M9 18l6-6-6-6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                </svg>
              </button>
            </div>

            <div className="carousel-indicators">
              {Array.from({ length: Math.max(1, totalSlides) }).map((_, index) => (
                <button
                  key={index}
                  className={`indicator ${index === currentIndex ? 'active' : ''}`}
                  onClick={() => setCurrentIndex(index)}
                />
              ))}
            </div>
            
            <div className="view-all">
              <button className="btn-view-all" onClick={() => window.location.href = '/cursos'}>
                Explorar catálogo completo
              </button>
            </div>
          </div>
        </section>
      </main>
    </div>
  );
};

export default Home;

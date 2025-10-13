import React from "react";
import Header from '../Header/Header';
import "../../styles/home.css";

const Home = () => {
  return (
    <div className="home-container">
      <Header />

      {/* Main Content */}
      <main>
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
      </main>
    </div>
  );
};

export default Home;

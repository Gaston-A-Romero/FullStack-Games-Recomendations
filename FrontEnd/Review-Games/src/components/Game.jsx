import React from 'react';

const Game = ( {games} ) => {
    return (
        games.map((game) => (
            <section key={game.id_game}>
                <h3>Title: {game.title}</h3>
                <p>Company: {game.company}</p>
                <p>Developer team: {game.developer}</p>
                <p>Platform: {game.platform}</p>
                <img src={game.picture} alt={game.title} /> 
                <p>Release date: {game.release_date}</p>
                <p>Year launch: {game.year}</p>
                <p>Score: {game.public_score}</p>
            </section>
        ))
    );
}

export default Game;

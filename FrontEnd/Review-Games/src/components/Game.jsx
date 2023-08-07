import React from 'react';

const Game = (games) => {
    return (
        games.map((game) =>{
            <div>
                <h3>{game.title}</h3>
                <p>{game.company}</p>
                <p>{game.developer}</p>
                <p>{game.platform}</p>
                <img src={game.picture}></img>
                <p>{game.release_date}</p>
                <p>{game.year}</p>
                <p>{game.public_score}</p>
            </div>
        })
    )
}

export default Game;
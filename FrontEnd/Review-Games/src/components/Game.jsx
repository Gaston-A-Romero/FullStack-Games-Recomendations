import React from 'react';

const Game = (games) => {
    return (
        games.map((game) =>{
            <div>
                <h3>{game.title}</h3>
            </div>
        })
    )
}

export default Game;
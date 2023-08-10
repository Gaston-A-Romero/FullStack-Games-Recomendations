const Game = ( {games} ) => {
    return (
        <ul className='game-container'>
            {games.map((game) => (
                <li key={game.id_game} className='game'>
                    <h3>{game.title}</h3>
                    <p>Company: {game.company}</p>
                    <p>Developer team: {game.developer}</p>
                    <p>Platform: {game.platform}</p>
                    <img src={game.picture} alt={game.title} /> 
                    <p>Release date: {game.release_date}</p>
                    <p>Year launch: {game.year}</p>
                    <p>Score: {game.public_score}</p>
                    
                </li>
            ))}
        </ul>
    );
}

export default Game;

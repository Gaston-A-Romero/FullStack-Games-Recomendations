const Game = ( {games} ) => {
    return (
        <section className='game-container'>
            {games.map((game) => (
                <article key={game.id_game} className='game'>
                    <h3 className="game_title">{game.title}</h3>
                    <p className="game_company">Company: {game.company}</p>
                    <p className="game_developer">Developer team: {game.developer}</p>
                    <p className="game_platform">Platform: {game.platform}</p>
                    <img src={game.picture} alt={game.title} className="game_image" /> 
                    <p className="game_release_date">Release date: {game.release_date}</p>
                    <p className="game_release_year">Year launch: {game.year}</p>
                    <p className="game_score">Score: {game.public_score}</p>    

                </article>
            ))}
        </section>
    );
}

export default Game;

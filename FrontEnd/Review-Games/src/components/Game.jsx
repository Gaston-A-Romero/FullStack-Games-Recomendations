const Game = ( {games} ) => {
    return (
        <section className='grid p-2 m-2 xs:grid-cols-1 sm:grid-cols-1 md:grid-cols-3 lg:grid-cols-4
         '>
            {games.map((game) => (
                <article key={game.id_game} className='
                border border-gray-400 p-4 shadow-md m-2 rounded-xl
                flex flex-row gap-2'>
                    <img src={game.picture} alt={game.title} className="object-contain max-h-[200px] max-w-[100px]" /> 
                    <div className="p-2 m-2">
                        <h3 className="font-bold">{game.title}</h3>
                        <p className="game_company"> {game.company}</p>
                        <p className="game_developer">{game.developer}</p>
                        <p className="game_platform">{game.platform}</p>                    
                        <p className="game_release_date"> {game.release_date}</p>
                        <p className="game_release_year">Year launch: {game.year}</p>
                        <p className="game_score">{game.public_score}</p>  
                    </div>  

                </article>
            ))}
        </section>
    );
}

export default Game;

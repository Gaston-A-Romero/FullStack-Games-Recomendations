export default function GamesShowAnimation(){
    return (
        <section className=' grid grid-cols-4 w-full gap-3 p-20 m-28'>

                <div className="relative group">
                    <img src="./src/assets/Playstation_logo.png" className="transition-transform transform rotate-y-8 hover:opacity-75 hover:shadow-md" alt="Playstation Logo"/>
                    <img src="./src/assets/psn_kratos.png" className=" w-150 h-150 absolute inset-0 transition-transform transform -translate-y-40 opacity-0 group-hover:opacity-100" alt="Kratos"/>
                </div>
                <div className="relative group">
                    <img src="./src/assets/steam.svg" className="transition-transform transform rotate-y-8 hover:opacity-75 hover:shadow-md" alt="Steam"/>
                    <img src="./src/assets/steam_half_life.png" className="w-150 h-150 absolute inset-0 transition-transform transform -translate-y-40 opacity-0 group-hover:opacity-100" alt="Half Life"/>
                </div>
                <div className="relative group">
                    <img src="./src/assets/nintendo.svg" className="transition-transform transform rotate-y-8 hover:opacity-75 hover:shadow-md" alt="Nintendo"/>
                    <img src="./src/assets/nintendo_zelda.png" className="w-150 h-150 absolute inset-0 transition-transform transform -translate-y-40 opacity-0 group-hover:opacity-100" alt="Link Zelda"/>
                </div>
                <div className="relative group">
                    <img src="./src/assets/xbox_logo.png" className="transition-transform transform rotate-y-8 hover:opacity-75 hover:shadow-md" alt="Xbox"/>
                    <img src="./src/assets/xbox_halo.png" className="w-150 h-150 absolute inset-0 transition-transform transform -translate-y-40 opacity-0 group-hover:opacity-100" alt="Chief Master"/>
                </div>

                
                
               
                    
        </section> 
    )
}
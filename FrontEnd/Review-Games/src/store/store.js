import {create} from "zustand";
import {persist,devtools} from "zustand/middleware"


const useGlobalState = create(devtools(((set, get) => ({
    isLogged:false,
    access_token: null,
    expiration_token:null,
    profile: {},     
    /*searched_games: [],   */
    logInUser: (token,expiration) => set( {access_token:token,expiration_token: expiration, isLogged:true} ),
    logOut:()=> set({isLogged:false,profile:{},access_token:null}),
    setProfile: (profile) => set ({profile:profile}),
    /* setSearchedGames: (games) => set({searched_games : games})*/


  }))))


export default useGlobalState;

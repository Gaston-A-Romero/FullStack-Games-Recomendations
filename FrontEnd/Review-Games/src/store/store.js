import { create } from 'zustand';
import { devtools, persist } from 'zustand/middleware';

const useGlobalState = create(
  persist(
    devtools((set, get) => ({
      isLogged: false,
      access_token: null,
      expiration_token: null,
      isActivated: false,
      activateAcount: () => set({ isActivated: true }),
      profile: {},
      logInUser: (token, expiration) =>
        set({ access_token: token, expiration_token: expiration, isLogged: true }),
      logOut: () =>
        set({ isLogged: false, profile: {}, access_token: null, expiration_token: null }),
      setProfile: (profile) => set({ profile: profile }),
    })),
    {
      name: 'MyTaste', // Un nombre único para identificar el almacenamiento local
    }
  )
);

export default useGlobalState;

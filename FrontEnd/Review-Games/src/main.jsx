import React from 'react'; 
import ReactDOM from 'react-dom/client';
import './index.css';
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import {ProtectedRoute} from './components/ProtectedRoute.jsx'
import GamesListPage from './pages/GamesListPage.jsx';
import HomePage from './pages/HomePage.jsx';
import AutenticationPage from './pages/AutenticationPage.jsx';
import ErrorPage from './pages/ErrorPage.jsx';
import LoggedPage from './pages/LoggedPage.jsx';
import Feed from './components/Feed.jsx';
import Header from './components/Header.jsx';

const router = createBrowserRouter([
    {
        path:"/",
        element:<Header/>,
        errorElement:<ErrorPage/>,
        children:[
            {
                path:"/",
                element:<HomePage/>,

            },
            {
                path:"/games",
                element:<GamesListPage/>,
            },
            {
                path: "/auth",
                element: <AutenticationPage />,
                children: [
                  
                ],
            },
            {
              path: "/profile", 
              element: (
                <ProtectedRoute>
                  <LoggedPage />
                </ProtectedRoute>
              ),
            },
            {
              path: "/feed",
              element: (
                <ProtectedRoute>
                  <Feed />
                </ProtectedRoute>
              ),
            },
              
              
              
              
              
              
        ]
    },
    

])
ReactDOM.createRoot(document.getElementById('root')).render( 
    <React.StrictMode>
        <RouterProvider router={router}/>
    </React.StrictMode>
)

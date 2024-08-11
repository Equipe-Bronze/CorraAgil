import { createBrowserRouter } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import UserRegistration from "./pages/UserRegistration";

const router = createBrowserRouter([
    {
        path: '/',
        element: <Home/>
    },
    {
        path:'/login',
        element: <Login/>
    },
    {
        path:'/cadastro-usuarios',
        element: <UserRegistration/>
    }
])

export default router
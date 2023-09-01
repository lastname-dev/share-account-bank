import App from "App";
import AccountPage from "pages/AccountPage/AccountPage";
import IntroPage from "pages/IntroPage/IntroPage";
import LoginPage from "pages/LoginPage/LoginPage";
import MainPage from "pages/MainPage/MainPage";
import NotFoundPage from "pages/NotFoundPage/NotFoundPage";
import SignUpPage from "pages/SignUpPage/SignUpPage";
import { RouterProvider, createBrowserRouter } from "react-router-dom";

const AppRouter = () => {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <App />,
      errorElement: <NotFoundPage />,
      children: [
        {
          path: "/intro",
          element: <IntroPage />,
        },
        {
          path: "/signup",
          element: <SignUpPage />,
        },
        {
          path: "/login",
          element: <LoginPage />,
        },
        {
          path: "/main",
          element: <MainPage />,
        },
        {
          path: "/account/:groupId",
          element: <AccountPage />,
        },
      ],
    },
  ]);
  return <RouterProvider router={router} />;
};
export default AppRouter;

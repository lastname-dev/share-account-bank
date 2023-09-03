import App from "App";
import Body from "components/Layout/Body/Body";
import HeaderLessLayout from "components/Layout/HeaderLessLayout/HeaderLessLayout";
import AccountPage from "pages/AccountPage/AccountPage";
import IntroPage from "pages/IntroPage/IntroPage";
import LoginPage from "pages/LoginPage/LoginPage";
import MainPage from "pages/MainPage/MainPage";
import NotFoundPage from "pages/NotFoundPage/NotFoundPage";
import RegistAccountPage from "pages/RegistAccountPage/RegistAccountPage";
import SignUpPage from "pages/SignUpPage/SignUpPage";
import { RouterProvider, createBrowserRouter } from "react-router-dom";

const AppRouter = () => {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <HeaderLessLayout />,
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
      ],
    },
    {
      path: "/",
      element: <App />,
      errorElement: <NotFoundPage />,
      children: [
        {
          path: "/main",
          element: <MainPage />,
        },
        {
          path: "/account/:groupId",
          element: <AccountPage />,
        },
        {
          path: "/regist-account",
          element: <RegistAccountPage />,
        },
      ],
    },
  ]);
  return <RouterProvider router={router} />;
};
export default AppRouter;

import Body from "components/Layout/Body/Body";
import React from "react";
import { Outlet } from "react-router-dom";

function App() {
  return (
    <Body>
      <h1>HEADER</h1>
      <main>
        <Outlet />
      </main>
    </Body>
  );
}

export default App;

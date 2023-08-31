import Body from "components/Layout/Body";
import React from "react";
import { Outlet } from "react-router-dom";

function App() {
  return (
    <Body>
      Hi
      <main>
        <Outlet />
      </main>
    </Body>
  );
}

export default App;

import React from "react";
import { Outlet } from "react-router-dom";

function App() {
  return (
    <React.Fragment>
      Hi
      <main>
        <Outlet />
      </main>
    </React.Fragment>
  );
}

export default App;

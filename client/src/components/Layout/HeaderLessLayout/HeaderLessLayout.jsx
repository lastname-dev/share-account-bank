import Body from "components/Layout/Body/Body";
import React from "react";
import { Outlet } from "react-router-dom";

function HeaderLessLayout() {
  return (
    <Body>
      <main>
        <Outlet />
      </main>
    </Body>
  );
}

export default HeaderLessLayout;

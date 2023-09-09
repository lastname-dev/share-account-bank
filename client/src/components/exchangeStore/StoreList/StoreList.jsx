import { ListItem, imageStyle, textStyle, listItemStyle } from "./StoreList.style";
import * as React from "react";
import Box from "@mui/material/Box";
import List from "@mui/material/List";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import Divider from "@mui/material/Divider";

const StoreList = ({ stores, onListItemClick }) => {
  const [selectedIndex, setSelectedIndex] = React.useState(1);

  const handleListItemClick = (event, index) => {
    setSelectedIndex(index);
  };
  return (
    <ul>
      {stores.map((store, index) => (
        <Box key={index} sx={{ width: "100%", maxWidth: 360, bgcolor: "background.paper" }}>
          <List key={index} onClick={() => onListItemClick(store)} component="nav" aria-label="main mailbox folders">
            <ListItemButton
              selected={selectedIndex === { index }}
              onClick={(event) => handleListItemClick(event, { index })}
            >
              <img
                src={process.env.PUBLIC_URL + "/image/shinhan.png"}
                width="100px"
                height="50px"
                alt="Shinhan Logo"
                style={imageStyle}
              />
              <ListItemText primary={store.place_name} />
            </ListItemButton>
          </List>
          <Divider />
        </Box>
      ))}
    </ul>
  );
};

export default StoreList;

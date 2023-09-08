import * as React from "react";
import Paper from "@mui/material/Paper";
import InputBase from "@mui/material/InputBase";
import IconButton from "@mui/material/IconButton";
import SearchIcon from "@mui/icons-material/Search";
export default function SearchBar({ where, onChange, onSearch }) {
  const handleSearchClick = () => {
    onSearch();
  };
  return (
    <Paper component="form" sx={{ p: "5px 10px", display: "flex", alignItems: "center", width: 360 }}>
      <InputBase
        sx={{ ml: 1, flex: 1 }}
        placeholder="원하는 지역을 입력하세요"
        inputProps={{ "aria-label": "원하는 지역을 입력하세요" }}
        value={where}
        onChange={onChange}
      />
      <IconButton type="button" sx={{ p: "10px" }} aria-label="search" onClick={handleSearchClick}>
        <SearchIcon />
      </IconButton>
    </Paper>
  );
}

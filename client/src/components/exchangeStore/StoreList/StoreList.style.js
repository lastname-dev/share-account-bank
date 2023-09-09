import React from "react";
import styled from "styled-components";
export const ListItem = styled.li`
  /* background-color: #f0f0f0; */
  border: 1px solid #ccc;
  padding: 10px;
  margin: 7px 0;
  list-style-type: none;
`;
export const listItemStyle = {
  display: "flex", // 이미지와 텍스트를 가로로 나란히 정렬
  alignItems: "center", // 세로 중앙 정렬
};

export const imageStyle = {
  width: "30px", // 이미지의 너비 설정
  height: "30px", // 이미지의 높이 설정 (동일한 크기로 조절)
  marginRight: "10px", // 이미지와 텍스트 사이의 간격 설정
};

export const textStyle = {
  fontSize: "13px", // 텍스트의 글꼴 크기 설정
};

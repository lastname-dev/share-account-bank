import { styled } from "styled-components";

export const BodyWrapper = styled.section`
  min-height: 100vh;
  background-color: ${({ theme }) => theme.color.white};
  min-width: 370px;
  max-width: 480px;
  margin: 0 auto;
`;

import Button from "components/@common/Button/Button";
import Flex from "components/@common/Flex/Flex";
import { styled, keyframes } from "styled-components";

const rotate = keyframes`
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
`;

export const IntroPageWrapper = styled(Flex)`
  flex-direction: column;
  justify-content: space-around;
  height: 100vh;
  padding: 9rem 0;
  /* background-color: #bfe4ff; */
  background: linear-gradient(#b2ccff, aliceblue);
  background-image: img;
`;

export const LogoContainer = styled(Flex)`
  margin: 0 auto;
  width: 40%;
`;

export const LogoImage = styled.img`
  width: 80%;
  animation: ${rotate} 3s linear infinite;
  border-radius: 50%;
`;

export const ButtonWrapper = styled(Flex)`
  width: 100%;
  flex-direction: column;
  gap: 2rem;
`;

export const LoginButton = styled(Button)`
  width: 60%;
  height: 40%;
  background-color: ${({ theme }) => theme.color.primary};
  /* background-color: #f1f1f1; */
  color: ${({ theme }) => theme.color.white};
  font-size: ${({ theme }) => theme.fontSize.m};
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
`;

export const SigninButton = styled(LoginButton)``;

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
  background-color: ${({ theme }) => theme.color.white};
  background-image: img;
`;

export const LogoContainer = styled(Flex)`
  margin: 0 auto;
  width: 40%;
`;

export const LogoImage = styled.img`
  width: 80%;
  /* animation: ${rotate} 3s linear infinite; */
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
  color: ${({ theme }) => theme.color.white};
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
`;

export const SigninButton = styled(LoginButton)``;

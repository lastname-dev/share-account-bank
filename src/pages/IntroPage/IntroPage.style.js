import Button from "components/common/Button/Button";
import Flex from "components/common/Flex/Flex";
import { styled } from "styled-components";

export const IntroPageWrapper = styled(Flex)`
  flex-direction: column;
  justify-content: space-around;
  height: 100vh;
  padding: 9rem 0;
`;

export const LogoContainer = styled(Flex)`
  margin: 0 auto;
  width: 60%;
`;

export const LogoImage = styled.img`
  width: 100%;
`;

export const ButtonWrapper = styled(Flex)`
  width: 100%;
  flex-direction: column;
  gap: 2rem;
`;

export const LoginButton = styled(Button)`
  width: 60%;
  background-color: ${({ theme }) => theme.color.blue};
  color: ${({ theme }) => theme.color.white};
  font-size: ${({ theme }) => theme.fontSize.l};
`;

export const SigninButton = styled(LoginButton)``;

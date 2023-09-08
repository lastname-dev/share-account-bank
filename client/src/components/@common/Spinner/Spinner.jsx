import Flex from "components/@common/Flex/Flex";
import styled, { keyframes } from "styled-components";

const loadingAnimation = keyframes`
  from {
    transform:scale(1,1)
  }
  50% {
    transform:scale(1,1.8)
  }
  to {
    transform:scale(1,1)
  }
`;

const SpinnerBlock = styled(Flex)`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.1);
  backdrop-filter: blur(5px);

  div {
    width: 0.625rem;
    height: 2.5rem;
    background-color: ${({ theme }) => `${theme.color.lightgray};`};
  }

  div:not(:last-child) {
    margin-right: 0.625rem;
  }
`;

const Left = styled.div`
  animation: ${loadingAnimation} 1s infinite ease-in-out;
  animation-delay: -0.2s;
`;

const Center = styled.div`
  animation: ${loadingAnimation} 1s infinite ease-in-out;
`;

const Right = styled.div`
  animation: ${loadingAnimation} 1s infinite ease-in-out;
  animation-delay: 0.2s;
`;

const Spinner = () => (
  <SpinnerBlock role="alert" aria-label="로딩중입니다.">
    <Left />
    <Center />
    <Right />
  </SpinnerBlock>
);

export default Spinner;

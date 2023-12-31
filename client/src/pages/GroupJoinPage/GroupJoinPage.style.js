import Button from "components/@common/Button/Button";
import Flex from "components/@common/Flex/Flex";
import { styled } from "styled-components";

export const GroupJoinPageWrapper = styled(Flex)`
  flex-direction: column;
  justify-content: space-around;
  height: 100vh;
  padding: 9rem 0;
  align-items: center;
  background-image: url(/image/airplane_window.jpg);
  background-repeat: no-repeat;
  background-size: 170%;
  background-position: center;
`;

export const GroupJoinMessage = styled.span`
  color: ${({ theme }) => theme.color.white};
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
`;

export const GroupJoinImageContainer = styled(Flex)`
  margin: 0 auto;
  width: 50%;
`;

export const GroupJoinImage = styled.img`
  width: 100%;
`;

export const GroupJoinInfoText = styled.span`
  color: ${({ theme }) => theme.color.lightgray};
  font-size: ${({ theme }) => theme.fontSize.s};
  position: relative;
  bottom: 4rem;
`;

export const GroupJoinButton = styled(Button)`
  width: 20%;
  background-color: ${({ theme }) => theme.color.primary};
  color: ${({ theme }) => theme.color.white};
  font-size: ${({ theme }) => theme.fontSize.m};
`;

//종명
export const ModalOverlay = styled.div`
  position: fixed; /* 고정 위치로 설정하여 전체 화면을 덮습니다. */
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5); /* 반투명한 배경을 만듭니다. */
  z-index: 1000; /* 모달보다 위에 위치하도록 설정합니다. */
  display: ${(props) => (props.$show ? "block" : "none")}; /* 모달을 표시/숨깁니다. */
`;

export const ModalContainer = styled.div`
  position: absolute; /* 절대 위치로 설정하여 부모 컨테이너에 상대적으로 배치됩니다. */
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%); /* 화면 중앙으로 이동합니다. */
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
  width: 300px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  z-index: 1001; /* 모달보다 위에 위치하도록 설정합니다. */
  display: ${(props) => (props.$show ? "block" : "none")}; /* 모달을 표시/숨깁니다. */
`;

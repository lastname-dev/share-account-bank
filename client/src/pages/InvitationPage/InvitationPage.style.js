import Button from "components/@common/Button/Button";
import Flex from "components/@common/Flex/Flex";
import { styled } from "styled-components";

export const InvitationPageWrapper = styled(Flex)`
  flex-direction: column;
  justify-content: space-around;
  height: 100vh;
  padding: 9rem 0;
`;

export const InvitationMessage = styled.span`
  color: ${({ theme }) => theme.color.darkgray};
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
`;

export const InvitationImageContainer = styled(Flex)`
  margin: 0 auto;
  width: 60%;
`;

export const InvitationImage = styled.img`
  width: 100%;
`;

export const InvitationInfoText = styled.span`
  color: ${({ theme }) => theme.color.lightgray};
  font-size: ${({ theme }) => theme.fontSize.s};
  position: relative;
  bottom: 4rem;
`;

export const InvitationButton = styled(Button)`
  width: 60%;
  background-color: ${({ theme }) => theme.color.primary};
  color: ${({ theme }) => theme.color.white};
  font-size: ${({ theme }) => theme.fontSize.m};
`;

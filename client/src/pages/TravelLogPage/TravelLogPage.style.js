import Flex from "components/@common/Flex/Flex";
import { slideUp } from "constants/animation";
import { styled } from "styled-components";

export const TravelLogPageWrapper = styled(Flex)`
  width: 100%;
  padding: 3rem 0;
  flex-direction: column;
  align-items: start;
`;

export const TravelLogTitle = styled.h3`
  padding-bottom: 2rem;
  color: ${({ theme }) => theme.color.darkgray};
`;

export const TravelLogList = styled(Flex)`
  width: 100%;
  flex-direction: column;
  gap: 2rem;
`;

export const TravelCard = styled.div`
  width: 100%;
  height: 15rem;
  border-radius: 10px;
  overflow: hidden;
  background-color: ${({ theme }) => theme.color.offwhite};
  cursor: pointer;
  box-shadow: 0px 0px 1px 1px ${({ theme }) => theme.color.lightgray};
  font-weight: bold;
  font-size: ${({ theme }) => theme.fontSize.s};
  animation: ${slideUp} 1s ease-in-out;

  &:hover {
    transform: scale(1.01);
  }
`;
export const ImageContainer = styled(Flex)`
  position: relative;
  width: 100%;
  height: 15rem;
  background-color: ${({ theme }) => theme.color.offwhite};
`;
export const TravelImage = styled.img`
  width: 100%;
  height: 15rem;
  object-fit: cover;
`;
export const TravelCardInfoContiner = styled(Flex)`
  flex-direction: column;
  gap: 0.8rem;
  padding: 2rem;
  position: absolute;
  justify-content: start;
  align-items: start;
  height: 15rem;
  width: 100%;
  background-color: rgba(45, 45, 45, 0.5);
`;

export const TravelTitle = styled.h4`
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
  color: ${({ theme }) => theme.color.offwhite};
`;

export const TravelContent = styled.span`
  font-size: ${({ theme }) => theme.fontSize.s};
  color: ${({ theme }) => theme.color.offwhite};
`;

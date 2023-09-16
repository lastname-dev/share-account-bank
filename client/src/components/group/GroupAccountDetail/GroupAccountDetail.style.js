import Button from "components/@common/Button/Button";
import Flex from "components/@common/Flex/Flex";
import { styled } from "styled-components";

export const GroupAccountDetailWrapper = styled(Flex)`
  flex-direction: column;
  width: 100%;
  min-height: 20rem;
  padding: 2rem;
  border-radius: 10px;
  background-color: ${({ theme }) => theme.color.offwhite};
  justify-content: space-around;
  gap: 3rem;
`;

export const InfoContainer = styled(Flex)`
  justify-content: space-between;
`;

export const ProgressBarContainer = styled(Flex)`
  width: 60%;
  color: ${({ theme }) => theme.color.green};
  font-weight: bold;
`;

export const BalanceText = styled.span`
  margin-top: 1rem;
  color: ${({ theme }) => theme.color.gray};
`;

export const Name = styled.span`
  font-size: ${({ theme }) => theme.fontSize.s};
  color: ${({ theme }) => theme.color.darkgray};
  font-weight: bold;
  display: flex;
  align-items: center;
`;
export const Number = styled.span`
  font-size: ${({ theme }) => theme.fontSize.s};
  color: ${({ theme }) => theme.color.gray};
`;

export const MoneyContainer = styled(Flex)`
  width: 100%;
  justify-content: space-between;
`;

export const Money = styled.span`
  font-size: 1.6rem;
  font-weight: bold;
  color: ${({ theme }) => theme.color.darkgray};
`;
export const GroupAccountButtonContainer = styled(Flex)`
  justify-content: space-around;
  gap: 1rem;
`;

export const GroupAccountButton = styled(Button)`
  width: 50%;
  color: ${({ theme }) => theme.color.white};
  background-color: ${({ theme }) => theme.color.primary};
  font-size: ${({ theme }) => theme.fontSize.s};
  padding: 1rem 2rem;
  font-weight: bold;
`;
export const LogoImg = styled.img`
  width: 1.7rem;
  height: 1.7rem;
  margin-right: 0.5rem;
`;

export const ExchangeContainer = styled(Flex)`
  width: 50%;
  justify-content: start;
`;

export const CountryContainer = styled(Flex)`
  gap: 0.5rem;
  width: 50%;
  justify-content: end;
`;

export const CountryFlagImage = styled.img`
  width: 2rem;
`;

import Button from "components/@common/Button/Button";
import Flex from "components/@common/Flex/Flex";
import { styled } from "styled-components";

export const AccountItemWrapper = styled(Flex)`
  flex-direction: column;
  width: 100%;
  height: 17rem;
  padding: 2rem;
  border-radius: 10px;
  background-color: ${({ theme }) => theme.color.offwhite};
  justify-content: space-around;
  gap: 1rem;
`;

export const DepositContainer = styled(Flex)`
  flex-direction: column;
  justify-content: start;
  align-items: start;
  gap: 1rem;
  font-weight: 600;
  padding-bottom: 1rem;
  border-bottom: 1px solid ${({ theme }) => theme.color.lightgray};
`;

export const AccountItemNumber = styled.span`
  font-size: ${({ theme }) => theme.fontSize.s};
  color: ${({ theme }) => theme.color.gray};
`;

export const Deposit = styled.span`
  font-size: ${({ theme }) => theme.fontSize.s};
  color: ${({ theme }) => theme.color.darkgray};
`;

export const AccountButtonContainer = styled(Flex)`
  justify-content: end;
  gap: 1rem;
`;

export const DetailButton = styled(Button)`
  padding: 1rem 2rem;
  border-radius: 10px;
  font-weight: bold;
  font-size: ${({ theme }) => theme.fontSize.s};
  background-color: ${({ theme }) => theme.color.lightgray};
  color: ${({ theme }) => theme.color.primary};
`;

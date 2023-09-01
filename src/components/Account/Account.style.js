import Flex from "components/common/Flex/Flex";
import { styled } from "styled-components";

export const AccountWrapper = styled(Flex)`
  flex-direction: column;
  width: 100%;
  min-height: 17rem;
  padding: 2rem;
  border-radius: 10px;
  background-color: ${({ theme }) => theme.color.offwhite};
  justify-content: space-around;
  /* box-shadow: 0px 0px 8px 1px ${({ theme }) => theme.color.lightgray}; */
`;

export const AccountNameContainer = styled(Flex)`
  width: 100%;
  justify-content: start;
`;

export const AccountName = styled.span`
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
  color: ${({ theme }) => theme.color.black};
`;

export const DepositContainer = styled(Flex)`
  flex-direction: column;
  justify-content: start;
  gap: 1rem;
`;

export const AccountNumber = styled.span`
  font-size: ${({ theme }) => theme.fontSize.s};
  color: ${({ theme }) => theme.color.lightgray};
`;
export const Deposit = styled.span`
  font-size: ${({ theme }) => theme.fontSize.s};
  color: ${({ theme }) => theme.color.darkgray};
`;

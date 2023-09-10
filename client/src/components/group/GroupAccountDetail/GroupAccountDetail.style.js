import Button from "components/@common/Button/Button";
import Flex from "components/@common/Flex/Flex";
import { styled } from "styled-components";

export const GroupAccountDetailWrapper = styled(Flex)`
  flex-direction: column;
  width: 100%;
  min-height: 20rem;
  padding: 2rem;
  border-radius: 10px;
  background-color: #d7e8ff;
  justify-content: space-around;
  gap: 2rem;
`;

export const InfoContainer = styled(Flex)`
  flex-direction: column;
  align-items: flex-start;
  gap: 0.3rem;
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
  justify-content: space-between;
`;

export const Money = styled.span`
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
  color: ${({ theme }) => theme.color.darkgray};
`;
export const GroupAccountButtonContainer = styled(Flex)`
  justify-content: space-around;
`;

export const GroupAccountButton = styled(Button)`
  // color: ${({ theme }) => theme.color.black};
  background-color: #c0dcff;
  font-size: ${({ theme }) => theme.fontSize.s};
  padding: 1rem 2rem;
  /* font-weight: bold; */
`;
export const LogoImg = styled.img`
  width: 1.7rem;
  height: 1.7rem;
  margin-right: 0.5rem;
`;

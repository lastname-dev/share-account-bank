import { NativeSelect } from "@mui/material";
import Button from "components/@common/Button/Button";
import Flex from "components/@common/Flex/Flex";
import { styled } from "styled-components";

export const DepositPageWrapper = styled(Flex)`
  flex-direction: column;
  justify-content: space-around;
  height: calc(100vh - 6rem);
  padding: 6rem 0;
`;

export const DepositMessage = styled.span`
  color: ${({ theme }) => theme.color.darkgray};
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
`;

export const DepositImageContainer = styled(Flex)`
  margin: 0 auto;
  width: 60%;
  margin-bottom: 10rem;
`;

export const DepositImage = styled.img`
  width: 100%;
`;

export const SelectAccountBox = styled(Flex)`
  width: 100%;
  justify-content: space-between;
  align-items: center;
  gap: 1rem;
`;

export const AccountSelect = styled.select`
  width: 100%;
  color: ${({ theme }) => theme.color.darkgray};
  font-size: ${({ theme }) => theme.fontSize.m};
  padding: 0.5rem;
`;

export const DepositButton = styled(Button)`
  width: 60%;
  background-color: ${({ theme }) => theme.color.primary};
  color: ${({ theme }) => theme.color.white};
  font-size: ${({ theme }) => theme.fontSize.m};
`;

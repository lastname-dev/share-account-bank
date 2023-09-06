import { styled } from "styled-components";
import Flex from "../Flex/Flex";

export const LabelContainer = styled(Flex)`
  justify-content: space-between;
  gap: 1rem;
`;

export const InputLabel = styled.label`
  font-size: ${({ theme }) => theme.fontSize.s};
  color: ${({ theme }) => theme.color.darkgray};
  white-space: nowrap;
`;
export const InputBox = styled.input`
  padding: 0.5rem 1rem;
  border-bottom: solid 1px ${({ theme }) => theme.color.darkgray};
  width: 60%;
  text-align: end;
`;

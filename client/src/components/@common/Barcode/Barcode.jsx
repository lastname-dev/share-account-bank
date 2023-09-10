import Flex from "components/@common/Flex/Flex";
import { styled } from "styled-components";

export const BarcodeWrapper = styled(Flex)`
  flex-direction: column;
  margin: 1rem 0;
`;

export const BarcodeContainer = styled(Flex)`
  justify-content: space-evenly;
  height: 6rem;
`;
export const BarS = styled.div`
  width: 0.2rem;
  height: 100%;
  background-color: black;
`;
export const BarM = styled(BarS)`
  width: 0.4rem;
`;
export const BarL = styled(BarS)`
  width: 0.6rem;
`;

const Barcode = ({ startDate, endDate }) => {
  return (
    <BarcodeWrapper>
      <BarcodeContainer>
        <BarS />
        <BarS />
        <BarL />
        <BarM />
        <BarS />
        <BarS />
        <BarM />
        <BarL />
        <BarS />
        <BarM />
        <BarL />
        <BarS />
        <BarM />
        <BarM />
        <BarM />
        <BarL />
        <BarS />
        <BarS />
        <BarM />
        <BarS />
        <BarS />
        <BarM />
        <BarS />
        <BarS />
        <BarL />
        <BarS />
        <BarL />
        <BarS />
        <BarS />
        <BarS />
        <BarS />
        <BarM />
        <BarS />
        <BarS />
        <BarL />
        <BarS />
        <BarL />
        <BarS />
      </BarcodeContainer>
    </BarcodeWrapper>
  );
};

export default Barcode;
